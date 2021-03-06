package webd4201.patelr;

import java.util.Date;

/**
 * @author Rut Patel
 * @version 1 (Jan 17 2021)
 */
public class Faculty extends User{

    /**
     * Creating the class variable for the default school code.
     */
    public static final String DEFAULT_SCHOOL_CODE = "SET" ;

    /**
     * Creating the class variable for the default school description.
     */
    public static final String DEFAULT_SCHOOL_DESCRIPTION = "School of Engineering & Technology";

    /**
     * Creating the class variable to store the default office location.
     */
    public static final String DEFAULT_OFFICE = "H-140";

    /**
     * Creating the class variable to store the default phone extension.
     */
    public static final int DEFAULT_PHONE_EXTENSION = 1234 ;

    /**
     * Creating the variable for school code.
     */
    private String schoolCode = "BITM" ;

    /**
     * Creating the variable for the school Description.
     */
    private String schoolDescription = "School of Buisness, IT & Management" ;

    /**
     *Creating the variable for the office location.
     */
    private String office = "BITM";

    /**
     * Creating the variable for the extension.
     */
    private int extension;

    //Getters for the class.

    /**
     *Getting the value for the school code.
     * @return schoolCode;
     */
    public String getSchoolCode() {
        return schoolCode;
    }

    /**
     * Getting the value for the school description.
     * @return schoolDescription;
     */
    public String getSchoolDescription() {
        return schoolDescription;
    }

    /**
     * Getting the value for the school office.
     * @return office;
     */
    public String getOffice() {
        return office;
    }

    /**
     * Getting the value for the extension.
     * @return extension;
     */
    public int getExtension() {
        return extension;
    }

    //Setters the class.

    /**
     * Setting the value for the school code.
     * @param schoolCode;
     */
    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    /**
     * Setting the value for the school description.
     * @param schoolDescription;
     */
    public void setSchoolDescription(String schoolDescription) {
        this.schoolDescription = schoolDescription;
    }

    /**
     * Setting the value for the office.
     * @param office;
     */
    public void setOffice(String office) {
        this.office = office;
    }

    /**
     * Setting the value for the extension.
     * @param extension;
     */
    public void setExtension(int extension) {
        this.extension = extension;
    }

    /**
     * Creating the parametrized constructor for faculty class.
     * @param id;
     * @param password;
     * @param firstName;
     * @param lastName;
     * @param emailAddress;
     * @param lastAccess;
     * @param enrolDate;
     * @param enabled;
     * @param type;
     * @param schoolCode;
     * @param schoolDescription;
     * @param office;
     * @param extension;
     * @throws InvalidUserDataException;
     */
    public Faculty(long id, String password, String firstName, String lastName, String emailAddress,  Date enrolDate, Date lastAccess,boolean enabled, char type, String schoolCode, String schoolDescription, String office, int extension) throws InvalidUserDataException {
        super(id, password, firstName, lastName, emailAddress,  enrolDate,lastAccess, enabled, type);
        setSchoolCode(schoolCode);
        setExtension(extension);
        setOffice(office);
        setSchoolDescription(schoolDescription);
    }

    /**
     * Creating the default constructor.
     * @throws InvalidUserDataException;
     */
    public Faculty() throws InvalidUserDataException {
        super();
        setSchoolCode(schoolCode);
        setExtension(extension);
        setOffice(office);
        setSchoolDescription(schoolDescription);
    }

    /**
     * Setting the value as faculty for getTypeForDisplay().
     * @return String;
     */
    public String getTypeForDisplay() { return "Faculty"; }

    /**
     * Overriding the toString() method to display the message.
     * @return String;
     */
    public String toString() {
        return    getTypeForDisplay() + " Info for: " + getId() +
                "\nName: " + getFirstName() + " " +getLastName() +" (" + getEmailAddress() + ")" +
                "\nCreated On: " + formatter.format(getEnrolDate()) +
                "\nLast Access: " + formatter.format(getLastAccess()) +
                 "\n" + schoolDescription + "(" + schoolCode + ")" +
                "\nOffice: " + DEFAULT_OFFICE +
                "\n(905)721-2000 " + getExtension();
    }
}
