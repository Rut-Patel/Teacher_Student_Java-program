/*
 *CustomerDA - this file is contains all of the data access methods, that actually get/set data to the database.
 * @author Rut Patel
 * @version 3.0 (02 January 2021)
 * @since 1.0
 */
package webd4201.patelr;


import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static webd4201.patelr.User.Digest;


public class StudentDA {

    /**
     * declaring aStudent of type Student.
     */
    static Student aStudent;
    /**
     * Declaring the connection of type Connection.
     */
    static Connection aConnection;
    /**
     * Declaring the statement of type Statement.
     */
    static Statement aStatement;
    /**
     * Declaring programCode of type string.
     */
    static String programCode ;
    /**
     * Declaring programDescription of type string.
     */
    static String programDescription ;
    /**
     *Declaring year of type int.
     */
    static int year ;
    /**
     * Declaring id of type long.
     */
    static long id;
    /**
     * Declaring password of type string
     */
    static String password;
    /**
     * Declaring firstname of type string
     */
    static String firstName;
    /**
     * Declaring lastname of type string
     */
    static String lastName;
    /**
     * Declaring emailaddress of type string
     */
    static String emailAddress;
    /**
     * Declaring lastAccess of type Date
     */
    static Date lastAccess;
    /**
     * Declaring enroldate of type Date
     */
    static Date enrolDate;
    /**
     * Declaring enabled of type boolean.
     */
    static boolean enabled;
    /**
     * Declaring type of char.
     */
    static char type;

    //private static final SimpleDateFormat SQL_DF = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Establishing the connection.
     * @param c;
     */
    public static void initialize(Connection c)
    {
        try {
            aConnection=c;
            aStatement=aConnection.createStatement();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    /**
     * close the database connection
     */
    public static void terminate()
    {
        try
        { 	// close the statement
            aStatement.close();
        }
        catch (SQLException e)
        { System.out.println(e);	}
    }

    /**
     * Retrieves the data by querying.
     * @param id;
     * @return Student
     * @throws NotFoundException if the data record is not found in the database.
     */
    public static Student retrieve(long id) throws NotFoundException
    {
        aStudent = null;

        String sqlQuery = "SELECT users.Id, Password, FirstName, LastName, EmailAddress, LastAccess,\n" +
                "EnrolDate, Enable, Type, ProgramCode, ProgramDescription, Year\n" +
                "FROM users, students WHERE users.id = students.StudentId AND users.Id ='" + id + "'";

        try {
            ResultSet rs = aStatement.executeQuery(sqlQuery);

            boolean gotData = rs.next();
            if (gotData) {
                password = rs.getString("Password");
                firstName = rs.getString("FirstName");
                lastName = rs.getString("LastName");
                emailAddress = rs.getString("EmailAddress");
                lastAccess = rs.getTimestamp("LastAccess");
                enrolDate = rs.getTimestamp("EnrolDate");
                programCode = rs.getString("ProgramCode");
                programDescription = rs.getString("ProgramDescription");
                year = rs.getInt("Year");
                enabled = rs.getBoolean("Enable");
                type = rs.getString("Type").charAt(0);

                try {
                    aStudent = new Student(id, password, firstName, lastName, emailAddress, enrolDate, lastAccess, enabled, type, programCode, programDescription, year);
                } catch (InvalidUserDataException e) {
                    System.out.println("Your record Contains some invalid Data.");
                }
            } else {
                throw (new NotFoundException("Problem retrieving Student record, with id: " + id + " does not exist in the system."));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return aStudent;
    }

    /**
     * Creates a record in the database.
     * @param aStudent;
     * @return boolean
     * @throws DuplicateException is thrown if the data is getting duplicated.
     */
    public static boolean create(Student aStudent) throws DuplicateException
    {
        boolean inserted = false;
        id = aStudent.getId();
        password = aStudent.getPassword();
        firstName = aStudent.getFirstName();
        lastName = aStudent.getLastName();
        emailAddress = aStudent.getEmailAddress();
        lastAccess = aStudent.getLastAccess();
        enrolDate = aStudent.getEnrolDate();
        programCode = aStudent.getProgramCode();
        programDescription = aStudent.getProgramDescription();
        year = aStudent.getYear();
        enabled = true;
        type = aStudent.getType();

//        String sqlInsertUsers = "INSERT INTO users " +
//                                "(Id, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate, Enable,Type) " +
//                "VALUES ('" + id + "', '" + password + "', '" + firstName + "','" + lastName + "','" + emailAddress + "','" + lastAccess + "','" + enrolDate + "','" + enabled + "','" + type +"')";

//        String sqlInsertStudent = "INSERT INTO students " +
//                                "(StudentId, ProgramCode, ProgramDescription, Year)" +
//                            "VALUES ('" + id + "','" + programCode + "','" + programDescription+ "','" + year + "')";

        try
        {
            retrieve(id);
            throw(new DuplicateException("Problem with creating Student record, id: " + id +" already exists in the system."));
        }
        catch(NotFoundException e)
        {
            try
            {  // execute the SQL update statement
                PreparedStatement psInsertUsers = aConnection.prepareStatement("INSERT INTO users (Id, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate, Enable,Type) VALUES (?,?,?,?,?,?,?,?,?)");
                psInsertUsers.setLong(1,id);
                psInsertUsers.setString(2,Digest(password,"sha1"));
                psInsertUsers.setString(3,firstName);
                psInsertUsers.setString(4,lastName);
                psInsertUsers.setString(5,emailAddress);
                psInsertUsers.setDate(6, new java.sql.Date(lastAccess.getTime()));
                psInsertUsers.setDate(7, new java.sql.Date(enrolDate.getTime()));
                psInsertUsers.setBoolean(8, enabled);
                psInsertUsers.setString(9, String.valueOf(type));
                psInsertUsers.execute();

                PreparedStatement psInsertStudents = aConnection.prepareStatement("INSERT INTO students (StudentId, ProgramCode, ProgramDescription, Year) VALUES (?,?,?,?)");
                psInsertStudents.setLong(1,id);
                psInsertStudents.setString(2,programCode);
                psInsertStudents.setString(3,programDescription);
                psInsertStudents.setInt(4,year);
                psInsertStudents.execute();
            }
            catch (SQLException | NoSuchAlgorithmException ee)
            {
                System.out.println(ee);
            }
        }
        return inserted;
    }

    /**
     *Updates the selected record.
     * @param aStudent;
     * @return int
     * @throws NotFoundException;
     */
    public static int update(Student aStudent) throws NotFoundException
    {
        int records = 0;

        id = aStudent.getId();
        password = aStudent.getPassword();
        firstName = aStudent.getFirstName();
        lastName = aStudent.getLastName();
        emailAddress = aStudent.getEmailAddress();
        lastAccess = aStudent.getLastAccess();
        enrolDate = aStudent.getEnrolDate();
        programCode = aStudent.getProgramCode();
        programDescription = aStudent.getProgramDescription();
        year = aStudent.getYear();
        enabled = true;
        type = aStudent.getType();

        /*String sqlUpdateUsers = "UPDATE users " +
                                " SET Password      = '" + password +"', " +
                                " FirstName   = '" + firstName +"', " +
                                " LastName   = '" + lastName +"'," +
                                " EmailAddress   = '" + emailAddress +"', " +
                                " LastAccess   = '" + lastAccess +"' ," +
                                " EnrolDate   = '" + enrolDate +"', " +
                                " Enable   = '" + enabled +"', " +
                                " Type   = '" + type +"' " +
                                " WHERE Id = '" + id + "'";

        String sqlUpdateStudent = "UPDATE students " +
                                " SET ProgramCode  = '" + programCode +"', " +
                                " ProgramDescription   = '" + programDescription +"', " +
                                " Year   = '" + year +"' " +
                                " WHERE StudentId = '" + id + "'";
        */
        // NotFoundException is thrown by find method
        try
        {
            Student.retrieve(id);  //determine if there is a Customer record to be updated
            // if found, execute the SQL update statement
            PreparedStatement psInsertStudents = aConnection.prepareStatement("UPDATE students SET ProgramCode = ?,ProgramDescription = ?, Year = ? WHERE StudentId = ?");
            psInsertStudents.setLong(4,id);
            psInsertStudents.setString(1,programCode);
            psInsertStudents.setString(2,programDescription);
            psInsertStudents.setInt(3,year);


            PreparedStatement psInsertUsers = aConnection.prepareStatement("UPDATE users SET Password = ? ,FirstName = ?,LastName = ?,EmailAddress = ? ,LastAccess = ? ,EnrolDate = ?, Enable = ? ,Type = ? WHERE Id = ?");
            psInsertUsers.setLong(9,id);
            psInsertUsers.setString(1,Digest(password,"sha1"));
            psInsertUsers.setString(2,firstName);
            psInsertUsers.setString(3,lastName);
            psInsertUsers.setString(4,emailAddress);
            psInsertUsers.setDate(5, new java.sql.Date(lastAccess.getTime()));
            psInsertUsers.setDate(6, new java.sql.Date(enrolDate.getTime()));
            psInsertUsers.setBoolean(7, enabled);
            psInsertUsers.setString(8, String.valueOf(type));

        }catch(NotFoundException e)
        {
            throw new NotFoundException("Student with id " + id  + " cannot be updated, does not exist in the system.");
        }catch (SQLException e)
        { System.out.println(e);} catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return records;

    }

    /**
     * Deletes the selected record.
     * @param aStudent;
     * @return int
     * @throws NotFoundException if the data record is not found in the database
     */
    public static int delete(Student aStudent) throws NotFoundException
    {
        int records = 0;
        // retrieve the id (key)
        id = aStudent.getId();
        // create the SQL delete statement
        String sqlDeleteUsers = "DELETE FROM users " +
                "WHERE Id = '" + id +"'";

        String sqlDeleteStudents = "DELETE FROM students " +
                "WHERE StudentId = '" + id +"'";
        // see if this customer already exists in the database
        try
        {
            Student.retrieve(id);  //used to determine if record exists for the passed Customer
            // if found, execute the SQL update statement
            records = aStatement.executeUpdate(sqlDeleteStudents);
            records = aStatement.executeUpdate(sqlDeleteUsers);

        }catch(NotFoundException e)
        {
            throw new NotFoundException("Student with id " + id
                    + " cannot be deleted, does not exist.");
        }catch (SQLException e)
        { System.out.println(e);	}
        return records;
    }

}
