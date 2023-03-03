// Assignment #: Arizona State University Spring 2023 CSE205 #6
//         Name: Justin Koehle
//    StudentID: 1227108859
//      Lecture: T/TH 4:30PM
//  Description: Assignment 6 creates a JavaFX GUI application to mimic a college course enrollment system


//Note: when you submit on gradescope, you need to comment out the package line
//package application;

public class Course
{
    private String subject;
    private int courseNum;
    private String instructor;

    public Course()
    {
        subject = "?";
        courseNum = 0;
        instructor = "?";
    }

    public Course(String subject, int courseNum, String instructor)
    {
        this.subject = subject;
        this.courseNum = courseNum;
        this.instructor = instructor;
    }

    public String getSubject()
    {
        return subject;
    }

    public int getCourseNum()
    {
        return courseNum;
    }

    public String getInstructor()
    {
        return instructor;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public void setCourseNum(int courseNum)
    {
        this.courseNum = courseNum;
    }

    public void setInstructor(String instructor)
    {
        this.instructor = instructor;
    }

    public String toString()
    {
        return	"\nCourse #:\t\t" + subject + " " + courseNum +
        		"\nInstructor:\t"+ instructor + "\n";
    }
}