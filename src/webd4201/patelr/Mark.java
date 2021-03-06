package webd4201.patelr;

import java.text.DecimalFormat;

/**
 * @author Rut Patel
 * @version 1 (Jan 18 2021)
 */
public class Mark {

    /**
     * Creating a class variable for the minimum gpa.
     */
    public static final float MINIMUM_GPA = 0.0F;

    /**
     * Creating a class variable for the maximum gpa.
     */
    public static final float MAXIMUM_GPA = 5.0F;

    /**
     * Creating a decimal format GPA foe storing GPA.
     */
    public static final DecimalFormat GPA = new DecimalFormat("#.##");

    /**
     * Creating instance variable fpr coursecode.
     */
    private String courseCode ;

    /**
     * Creating instance variable for coursname.
     */
    private String courseName ;

    /**
     * Creating instance variable for result.
     */
    private String result ;

    /**
     * Creating instance variable for gpaweighting.
     */
    private float gpaWeighting ;

    //Getter for the class.

    /**
     * Getting the value for the course code.
     * @return courseCode;
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Getting the value for the coursename.
     * @return courseName;
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Getting the value for the result.
     * @return result;
     */
    public String getResult() {
        return result;
    }

    /**
     * Getting the value for the GPA weighting.
     * @return gpaWeighting;
     */
    public float getGpaWeighting() {
        return gpaWeighting;
    }

    //Setters for the class.

    /**
     * Setting the value for the course code.
     * @param courseCode;
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Setting the value for the course Name.
     * @param courseName;
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Setting the value for the results.
     * @param result;
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Setting the value for the gpa weighting.
     * @param gpaWeighting;
     */
    public void setGpaWeighting(float gpaWeighting) {
        this.gpaWeighting = gpaWeighting;
    }

    /**
     * Creating the constructor for the mark.
     * @param courseCode;
     * @param courseName;
     * @param result;
     * @param gpaWeighting;
     */
    public Mark(String courseCode, String courseName, String result, float gpaWeighting) {
        setCourseCode(courseCode);
        setCourseName(courseName);
        setResult(result);
        setGpaWeighting(gpaWeighting);
    }

    /**
     * Overriding the string method for the marks.
     * @return String;
     */
    @Override
    public String toString() {
        return  getCourseCode() + " " + String.format("%-35s", getCourseName()) + "  " + getResult() + "  " + getGpaWeighting()  ;
    }
}
