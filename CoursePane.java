// Assignment #: Arizona State University Spring 2023 CSE205 #6
//         Name: Justin Koehle
//    StudentID: 1227108859
//      Lecture: T/TH 4:30PM
//  Description: Assignment 6 creates a JavaFX GUI application to mimic a college course enrollment system

//Note: when you submit on gradescope, you need to comment out the package line
//package application;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

public class CoursePane extends HBox
{
    //GUI components
	
    private ArrayList<Course> courseList;
    private VBox checkboxContainer = new VBox();

    //Step 1.1: declare any necessary instance variables here
    Object sourceButton = new Object();
    private int courseCount=0;
    ComboBox<String> subjectBox = new ComboBox<>();
    TextField numField = new TextField();
    TextField instructorField = new TextField();
    Label bottomLeft = new Label();
    Button addButton = new Button("Add =>");
    Button dropButton = new Button("Drop <=");
	Course coursey;
    private int courseN;
    private String courseSubject;
    Label subjectLabel = new Label("Subject");
    Label courseNumLabel = new Label("Course Num");
    Label instructorLabel = new Label("Instructor");
    Label bottomRight = new Label();    
    
    //constructor
    public CoursePane()
    {
        //step 1.2: initialize instance variables
        courseList = new ArrayList<Course>();
        Label labelLeft = new Label("Add Course(s)");
        labelLeft.setTextFill(Color.BLUE);
        labelLeft.setFont(Font.font(null, 14));
        subjectBox.getItems().addAll("CSE","AME","BME","CHM","AME","DAT","EEE");
        subjectBox.setValue("CSE");
        bottomRight.setText("Total course enrolled: " + courseCount);
        bottomLeft.setText("No course entered");
        Label labelRight = new Label("Course(s) Enrolled");
        labelRight.setTextFill(Color.BLUE);
        labelRight.setFont(Font.font(null, 14));
        checkboxContainer.setSpacing(15);
        checkboxContainer.setPadding(new Insets(5,5,5,5));
        
        //set up the layout. Note: CoursePane is a HBox and contains
        //leftPane, centerPane and rightPane. Pick proper layout class
        //and use nested sub-pane if necessary, then add each GUI components inside.
        
        //step 1.3: create and set up the layout of the leftPane, leftPane contains a top label, the center sub-pane
        //and a label show at the bottom
        
        BorderPane leftPane = new BorderPane();
        GridPane subPane = new GridPane();
        subPane.add(subjectLabel, 0, 0);
        subPane.add(subjectBox, 1, 0);
        subPane.add(courseNumLabel, 0, 1);
        subPane.add(numField, 1, 1);
        subPane.add(instructorLabel, 0, 2);
        subPane.add(instructorField, 1, 2);
        
        subPane.setAlignment(Pos.CENTER);
        subPane.setHgap(10);
        subPane.setVgap(10);
        
        leftPane.setTop(labelLeft);
        leftPane.setLeft(subPane);
        leftPane.setBottom(bottomLeft);
        leftPane.setPadding(new Insets(10, 10, 10, 10));
        leftPane.setStyle("-fx-border-color: black");
        
        //step 1.4: create and set up the layout of the centerPane which holds the two buttons
		
        VBox centerPane = new VBox();
        centerPane.setSpacing(20);
        centerPane.setAlignment(Pos.CENTER);
        centerPane.getChildren().addAll(addButton, dropButton);

        //step 1.5: create and set up the layout of the rightPane, rightPane contains a top label,
        //checkboxContainer and a label show at the bottom
        
        BorderPane rightPane = new BorderPane();
        rightPane.setTop(labelRight);
        rightPane.setBottom(bottomRight);
        rightPane.setLeft(checkboxContainer);
        rightPane.setPadding(new Insets(10, 10, 10, 10));
        rightPane.setStyle("-fx-border-color: black");
        
        //CoursePane is a HBox. Add leftPane, centerPane and rightPane inside
        
        this.getChildren().addAll(leftPane, centerPane, rightPane);
        this.setSpacing(15);
        this.setPadding(new Insets(10, 10, 10, 10));	

        //Step 3: Register the GUI component with its handler class
        
        addButton.setOnAction(new ButtonHandler());
        dropButton.setOnAction(new ButtonHandler());
        subjectBox.setOnAction(new ComboBoxHandler());
        
    } //end of constructor

    //step 2.1: Whenever a new Course is added or one or several courses are dropped/removed, this method will
    //1) clear the original checkboxContainer;
    //2) create a CheckBox for each Course object inside the courseList, and also add it inside the checkboxContainer;
    //3) register the CheckBox with the CheckBoxHandler.
    public void updateCheckBoxContainer()
    {
        checkboxContainer.getChildren().clear();
        for(int i = 0; i < courseList.size(); ++i)
        {	
        	//instantiates a new CheckBox using information from the element accessed in courseList
        	CheckBox courseBox = new CheckBox("Course #:\t\t" + courseList.get(i).getSubject() + " " + courseList.get(i).getCourseNum()
        			+"\nInstructor:\t\t" + courseList.get(i).getInstructor());
        	checkboxContainer.getChildren().add(courseBox);
        	courseBox.setOnAction(new CheckBoxHandler(courseList.get(i)));
        	//gets the amounts of elements in courseList to display on bottom right label
        	courseCount = courseList.size();
        	bottomRight.setText("Total course enrolled: " + courseCount);
        }
        //----
    }

    //Step 2.2: Create a ButtonHandler class
    private class ButtonHandler implements EventHandler<ActionEvent>
    {

        public void handle(ActionEvent e)
        {		//resets the text every time a button is pressed
        		bottomLeft.setText("No course entered");
        		bottomLeft.setTextFill(Color.BLACK);
        		//determine whether the add or drop button is selected
                sourceButton = e.getSource();
                try {
                    //if addButton is the source
                    if (sourceButton == addButton)
                    {
                    	//error handling in case a textField is left empty
                    	if (numField.getText().equals("") || instructorField.getText().equals(""))
                        {
                          bottomLeft.setTextFill(Color.RED);
                          bottomLeft.setText("At least one field is empty. Fill all fields");
                        }
                    	else //grabs the course info from the nodes, then instantiates a new Course
                    		{courseN = Integer.parseInt(numField.getText());
                            coursey = new Course(courseSubject, courseN, instructorField.getText());
                            boolean validCourse = true;
                            
                            //Uses a for loop to iterate over the elements in courseList,
                            //then displays an error message if a duplicate is found
                            for (int i = 0; i < courseList.size(); i++)
                            {
                            	if (courseList.get(i).toString().equals(coursey.toString()))
                            	{
                            		validCourse = false;
                            		bottomLeft.setTextFill(Color.RED);
                            		bottomLeft.setText("Duplicated course - Not added");
                            	}
                            }
                            
                            //Adds the course to the courseList
                            if (validCourse == true)
                            {
                            	courseList.add(coursey);
                            	updateCheckBoxContainer();
                            	bottomLeft.setText("Course added successfully");
                                numField.clear();
                                instructorField.clear();
                            }
                    		}
                           //Error handling in case a field is left empty
                       
                    }
                    //if dropButton is the source
                    else if (sourceButton == dropButton)
                    {
                    	updateCheckBoxContainer();
                    	courseCount = courseList.size();
                    	bottomRight.setText("Total course enrolled: " + courseCount);
                    }
                } //end of try
                //error handling if course number is not an integer
                catch(NumberFormatException ex)
                {
                    bottomLeft.setTextFill(Color.RED);
                    bottomLeft.setText("Error! Course number must be an integer");
                }
                catch(Exception exception) //error handling for all other exceptions
                {
					System.out.println("This is an error - invalid action detected");
                }
        }
         //end of handle() method
    } //end of ButtonHandler class

    ////Step 2.3: A ComboBoxHandler
    private class ComboBoxHandler implements EventHandler<ActionEvent>
    {

		@Override
		public void handle(ActionEvent arg0) {	
			//grabs the value from the subjectBox and assigns it to the instance variable
			courseSubject = subjectBox.getValue();
			//System.out.println(courseSubject);
			
		}
       //----

    }//end of ComboBoxHandler

    //Step 2.4: A CheckBoxHandler
    private class CheckBoxHandler implements EventHandler<ActionEvent>
    {
        // Pass in a Course object so that we know which course is associated with which CheckBox
        private Course oneCourse;

        //constructor - sets the course passed as a parameter to the instance variable
        public CheckBoxHandler(Course oneCourse)
        {
           this.oneCourse = oneCourse;
        }
        public void handle(ActionEvent e)
        {
        	//removes the selected course from the courseList
        	CheckBox temp = ((CheckBox) e.getSource());
        	if (temp.isSelected())
        	{
            courseList.remove(oneCourse);
        	}
        }
    }//end of CheckBoxHandler

} //end of CoursePane class