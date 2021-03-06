/*
 *Student class which is extending the users class.
 * @author Rut Patel
 * @version 3.0 (02 January 2021)
 * @since 1.0
 */
package webd4201.patelr;

import java.sql.Connection;
import java.util.Date;
import java.util.Vector;

/**
 * @author : Rut Patel
 * @version : 1 (17 Jan 2021)
 */
public class Student extends User{

    /**
     * Initialize the connection.
     * @param c;
     */
    public static void initialize(Connection c)
    {
        StudentDA.initialize(c);
    }

    /**
     * Retrieves the data for the student class.
     * @param id;
     * @return student
     * @throws NotFoundException if the data record is not found in the database.
     */
    public static Student retrieve(long id) throws NotFoundException
    {
        return StudentDA.retrieve(id);
    }

    /**
     *Terminates the connection.
     */
    public static void terminate()
    {
        StudentDA.terminate();
    }

    /**
     *Creates the record.
     * @return boolean
     * @throws DuplicateException is thrown if the data is getting duplicated.
     */
    public boolean create() throws DuplicateException
    {
        return StudentDA.create(this);
    }

    /**
     * Deletes the record.
     * @return Integer
     * @throws NotFoundException if the data record is not found in the database.
     */
    public Integer delete() throws NotFoundException
    {
        return StudentDA.delete(this);
    }

    /**
     *
     * @return integer
     * @throws NotFoundException if the data record is not found in the database.
     */
    public Integer update() throws NotFoundException
    {
        return StudentDA.update(this);
    }



    /**
     * Declaring class variable for Default Program Code.
     */
    public static String DEFAULT_PROGRAM_CODE = "UNDC" ;

    /**
     * Declaring class variable for Default program Description.
     */
    public static String DEFAULT_PROGRAM_DESCRIPTION = "Undeclared" ;

    /**
     * Declaring class variable for Default year.
     */
    public static int DEFAULT_YEAR = 1 ;

    /**
     * Creating instance variable for program code.
     */
    private String programCode ;

    /**
     * Creating instance variable for program Description.
     */
    private String programDescription ;

    /**
     * Creating instance variable for year.
     */
    private int year ;

    /**
     * Creating vector to store marks.
     */
    private Vector<Mark> marks ;

    //Getters for the class student.

    /**
     * Getter for the Program code.
     * @return programCode
     */
    public String getProgramCode() {
        return programCode;
    }

    /**
     * Getter for the program Description
     * @return programDescription
     */
    public String getProgramDescription() {
        return programDescription;
    }

    /**
     * Getter for the year.
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * Getter for the vector of mark.
     * @return marks
     */
    public Vector<Mark> getMarks() {
        return marks;
    }

    //Setters for the student class.

    /**
     * Setting value of the program code.
     * @param programCode;
     */
    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    /**
     * Setting program Description for Student Class.
     * @param programDescription;
     */
    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    /**
     * Setting the year.
     * @param year;
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Setting marks for the class.
     * @param marks;
     */
    public void setMarks(Vector<Mark> marks) {
        this.marks = marks;
    }

    /**
     * Creating the parametrized constructor without the marks vector.
     * @param id;
     * @param password;
     * @param firstName;
     * @param lastName;
     * @param emailAddress;
     * @param lastAccess;
     * @param enrolDate;
     * @param enabled;
     * @param type;
     * @param programCode;
     * @param programDescription;
     * @param year;
     * @param marks;
     * @throws InvalidUserDataException;
     */
    public Student(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char type, String programCode, String programDescription, int year, Vector<Mark> marks) throws InvalidUserDataException {
        super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
        setProgramCode(programCode);
        setProgramDescription(programDescription);
        setYear(year);
        setMarks(marks);
    }

    /***
     * Overloading the created parametrized constructor with the marks.
     * @param id;
     * @param password;
     * @param firstName;
     * @param lastName;
     * @param emailAddress;
     * @param lastAccess;
     * @param enrolDate;
     * @param enabled;
     * @param type;
     * @param programCode;
     * @param programDescription;
     * @param year;
     * @throws InvalidUserDataException;
     */
    public Student(long id, String password, String firstName, String lastName, String emailAddress, Date enrolDate, Date lastAccess, boolean enabled, char type, String programCode, String programDescription, int year) throws InvalidUserDataException {
        this(id, password, firstName, lastName, emailAddress,  enrolDate,lastAccess, enabled, type,programCode,programDescription,year, new Vector<Mark>() ) ;
    }

    /**
     * Default constructor setting all the values to the default class value.
     * @throws InvalidUserDataException;
     */
    public Student() throws InvalidUserDataException {
        this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME,
                DEFAULT_LAST_NAME, DEFAULT_EMAIL_ADDRESS,
                new Date(), new Date(),DEFAULT_ENABLED_STATUS ,
                DEFAULT_TYPE, DEFAULT_PROGRAM_CODE, DEFAULT_PROGRAM_DESCRIPTION, DEFAULT_YEAR);

    }

    /**
     * Setting the type to the student for the display.
     * @return String;
     */
    public String getTypeForDisplay() { return "Student"; }

    /**
     * Creating to string method to display the message.
     * @return String;
     */
    @Override
    public String toString() {
        return  getTypeForDisplay() + " Info for:" +
                "\n" + getFirstName() + " " + getLastName() + " (" + getId() + ")" +
                "\nCurrently in the " + getYear() + " year of " + getProgramDescription() + " (" + getProgramCode() + ")" +
                "\nEnrolled :" + formatter.format(getEnrolDate());

    }

}
