package cmis242_project_4;

// Jason Howarth
// 18DEC2016
// CMIS 242 7983
// DESCRIPTION: PROJECT 4

//IMPORT STATEMENTS
import java.text.DecimalFormat;

public class Student {

    //INSTANCE VARIABLES
    int id;
    String name;
    String major;
    int totalCredits;
    int totalQP;
    int GPA;//GPA=totalQP/totalCredits
    int inputCredits;
    int inputQualityPoints;

    //NEW STUDENT CONSTRUCTOR
    public Student(String name, String major) {
        this.name = name;
        this.major = major;
        totalCredits = 0;
        totalQP = 0;
    }

    //GETTERS

    //name
    public String getStudentName(int key) {
        return name;
    }

    //major
    public String getStudentMajor(int key) {
        return major;
    }

    //totalCredits
    public int getStudentTotalCredits(int key) {
        return totalCredits;
    }

    //totalQP
    public int getStudentTotalQP(int key) {
        return totalQP;
    }

    //GPA
    public int getStudentGPA(int key) {
        //GPA=totalQP/totalCredits
        if (totalCredits == 0){
            GPA = 4;
        } else {
            GPA = getStudentTotalQP(key)/getStudentTotalCredits(key);
        }
        return GPA;
    }

    //SETTERS
    
    //totalCredits
    public void setStudentTotalCredits(int inputCredits) {
        this.totalCredits = inputCredits;
    }

    //totalQP
    public void setStudentTotalQP(int inputQualityPoints) {
        this.totalQP = inputQualityPoints;
    }

    //---toString---
    //returns a labeled string: id, name, major, totalCredits, totalQP and GPA.
    @Override
    public String toString() {
        DecimalFormat gpaFormat = new DecimalFormat("#.0");
        String studentDescription =
                 "\nName: " + getStudentName(id)
                + "\nMajor: " + getStudentMajor(id)
                + "\nTotal Credits: " + getStudentTotalCredits(id)
                + "\nTotal Quality Points: " + getStudentTotalQP(id)
                + "\nGPA: " + gpaFormat.format(getStudentGPA(id));
        return studentDescription;
    }

}
