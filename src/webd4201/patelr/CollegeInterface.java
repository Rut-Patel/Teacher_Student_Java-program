package webd4201.patelr;

/**
 * Interface for College with constant and method headers.
 */
public interface CollegeInterface {

    /**
     * Declaring values for the static fields for the name of the college.
     */
    public static final String COLLEGE_NAME = "Durham College";

    /**
     * declaring valued forthe static field for the phone numeber of the college
     */
    public static final String PHONE_NUMBER  = "(905)721-2000" ;

    /**
     * Getting the type
     * @return :  String
     */
    public String getTypeForDisplay();
}
