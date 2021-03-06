/*
 *DatabaseConnect - Used to manage database Connectivity(Opening and closing connections)
 * @author Rut Patel
 * @version 3.0 (02 January 2021)
 * @since 1.0
 */

package webd4201.patelr;

import java.sql.*;

public class DatabaseConnect
{
    /**
     * Database location
     */
    static String url = "jdbc:postgresql://127.0.0.1:5432/webd4201_db";

    /**
     * Connection object to open port to db
     */
    static Connection aConnection;

    /**
     * Database User
     */
    static String user = "webd4201_admin";

    /**
     * Database user password
     */
    static String password = "webd4201_password";

    /**
     * establishes the database connection
     * @return Connection to the webd4201example_db database
     */
    public static Connection initialize()
    {
        try
        {
            Class.forName("org.postgresql.Driver"); // loads the JDBC Driver for PostGreSQL
            aConnection = DriverManager.getConnection(url, user, password); // creates the database connection instance

        }
        catch (ClassNotFoundException e)  //will occur if you did not import the PostGreSQL *.jar file with drivers
        {
            System.out.println(e);
        }
        catch (SQLException e)	//any other database exception (misnamed db, misnamed user, wrong password, etc)
        {
            System.out.println(e);
        }
        return aConnection;
    }

    /**
     * closes the database connection
     */
    public static void terminate()
    {
        try
        {
            aConnection.close();
        }
        catch (SQLException e)
        { System.out.println(e);	}
    }
}
