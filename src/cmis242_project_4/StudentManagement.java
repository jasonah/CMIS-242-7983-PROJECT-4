package cmis242_project_4;

// Jason Howarth
// 18DEC2016
// CMIS 242 7983
// DESCRIPTION: PROJECT 4
//IMPORT STATEMENTS
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.*;

public class StudentManagement {

    //VARIABLES
    int inputID;
    String comboBoxSelection;

    //MAIN
    public static void main(String[] args) {
        StudentManagement studentManagementApp = new StudentManagement();
    }//END MAIN

    public StudentManagement() {

        //CREATE FRAME
        JFrame studentManagementFrame = new JFrame();

        //HASMAP STUDENT DATABASE- KEY(ID) VALUE(STUDENT)
        Map<Integer, Student> studentDatabase = new HashMap<>();

        //CONTAINER PANEL
        JPanel containerPanel = new JPanel();
        studentManagementFrame.add(containerPanel);
        containerPanel.setLayout(new GridLayout(5, 1));//rows, columns for five panels
        //Add empty border - Top,Left,Bottom,Right
        containerPanel.setBorder(BorderFactory.createEmptyBorder(2, 10, 10, 10));

        //CREATE 5 PANELS
        JPanel studentIdPanel = new JPanel();//PANEL 1
        JPanel studentNamePanel = new JPanel();//PANEL 2
        JPanel studentMajorPanel = new JPanel();//PANEL 3
        JPanel selectionComboBoxPanel = new JPanel();//PANEL 4
        JPanel processRequestPanel = new JPanel();//PANEL 5

        //ADD 5 PANELS TO containerPanel
        containerPanel.add(studentIdPanel);//STUDENT ID
        containerPanel.add(studentNamePanel);//STUDENT NAME
        containerPanel.add(studentMajorPanel);//STUDENT MAJOR
        containerPanel.add(selectionComboBoxPanel);//SELECTION
        containerPanel.add(processRequestPanel);//PROCESS REQUEST BUTTON

        //STUDENT ID FIELD AND LABEL
        JLabel studentIdFieldLabel = new JLabel("Id:");//LABEL
        JTextField studentIdTextField = new JTextField("");//TEXT FIELD
        studentIdTextField.setColumns(12);
        studentIdTextField.setBackground(Color.white);
        studentIdTextField.setEditable(true);
        studentIdPanel.add(studentIdFieldLabel);//ADD LABEL TO PANEL
        studentIdPanel.add(studentIdTextField);//ADD TEXT FIELD TO PANEL

        //STUDENT ID PANEL
        studentIdPanel.setVisible(true);
        studentIdPanel.setLayout(new GridLayout(1, 2));//rows,columns

        //STUDENT NAME FIELD AND LABEL
        JLabel studentNameFieldLabel = new JLabel("Name:");//LABEL
        JTextField studentNameTextField = new JTextField("");//TEXT FIELD
        studentNameTextField.setColumns(12);
        studentNameTextField.setBackground(Color.white);
        studentNameTextField.setEditable(true);
        studentNamePanel.add(studentNameFieldLabel);//ADD LABEL
        studentNamePanel.add(studentNameTextField);//ADD TEXT FIELD

        //STUDENT NAME PANEL
        studentNamePanel.setVisible(true);
        studentNamePanel.setLayout(new GridLayout(1, 2));//rows,columns

        //STUDENT MAJOR FIELD AND LABEL
        JLabel studentMajorLabel = new JLabel("Major:");//LABEL
        JTextField studentMajorTextField = new JTextField("");//TEXT FIELD
        studentMajorTextField.setColumns(12);
        studentMajorTextField.setBackground(Color.white);
        studentMajorTextField.setEditable(true);

        //STUDENT MAJOR PANEL
        studentMajorPanel.add(studentMajorLabel);//ADD LABEL
        studentMajorPanel.add(studentMajorTextField);//ADD TEXT FIELD
        studentMajorPanel.setVisible(true);
        studentMajorPanel.setLayout(new GridLayout(1, 2));//rows,columns

        //SELECTION COMBO BOX
        //ARRAY
        String[] selectionComboBoxArray = {"Insert", "Delete", "Find", "Update"};
        //LABEL
        JLabel selectionComboBoxLabel = new JLabel("Choose Selection:");//COMBO BOX LABEL
        JComboBox<String> selectionComboBox = new JComboBox<>(selectionComboBoxArray);
        selectionComboBox.setSelectedIndex(0);//SET COMBO BOX TO INDEX 0
        comboBoxSelection = (String) selectionComboBox.getSelectedItem();

        //SELECTION COMBO BOX PANEL
        selectionComboBoxPanel.add(selectionComboBoxLabel);//ADD LABEL TO PANEL
        selectionComboBoxPanel.add(selectionComboBox);//ADD COMBO BOX TO PANEL

        //SELECTION COMBO BOX LISTENER
        selectionComboBox.addActionListener((ActionEvent event) -> {
            comboBoxSelection = (String) selectionComboBox.getSelectedItem();
        });

        //SELECTION COMBO BOX PANEL
        selectionComboBoxPanel.setVisible(true);
        selectionComboBoxPanel.setLayout(new GridLayout(1, 2));//rows,columns

        //PROCESS BUTTON
        //LABEL
        JButton processRequestButton = new JButton("Process Request");
        //PANEL
        processRequestPanel.add(processRequestButton);
        processRequestButton.setVisible(true);
        //BORDER
        processRequestPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 150));

        //PROCESS BUTTON LISTENER
        processRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (null != comboBoxSelection) //START actionPerformed
                {
                    switch (comboBoxSelection) {
                        //INSERT
                        case "Insert":
                            try {
                                //get inputID and check if integer
                                inputID = Integer.parseInt(studentIdTextField.getText());
                                //check if input is greater than 1
                                if (inputID >= 1) {
                                    if (studentDatabase.containsKey(inputID) == false) { // check if id exists
                                        //create new key-value pair with inputID and Student
                                        Student newStudent = new Student(studentNameTextField.getText(),
                                                studentMajorTextField.getText());
                                        studentDatabase.put(inputID, newStudent);
                                        //confirm record added
                                        JOptionPane.showMessageDialog(null, "Student ID: "
                                                + inputID + studentDatabase.get(inputID), "Inserted Record",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        //clear fields
                                        studentIdTextField.setText("");
                                        studentNameTextField.setText("");
                                        studentMajorTextField.setText("");
                                    } else {
                                        JOptionPane.showMessageDialog(null, "ID " + inputID
                                                + " already exists. Enter a new ID", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                        //clear fields
                                        studentIdTextField.setText("");
                                    }
                                } else {
                                    integerErrorMessage();
                                    studentIdTextField.setText("");
                                }
                            } catch (NumberFormatException exception) {
                                integerErrorMessage();
                                //clear fields
                                studentIdTextField.setText("");
                                inputID = 0;
                            }
                            break;
                        //DELETE
                        case "Delete":
                            try {
                                //get inputID and check if integer
                                inputID = Integer.parseInt(studentIdTextField.getText());
                                //check if input is greater than 1
                                if (inputID >= 1) {
                                    if (studentDatabase.containsKey(inputID) == true) { // check if id exists
                                        //delete record
                                        studentDatabase.remove(inputID);
                                        //confirm deleted record id
                                        JOptionPane.showMessageDialog(null, "Student ID: "
                                                + inputID + " deleted.", "Deleted Record",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        //clear fields
                                        studentIdTextField.setText("");
                                    } else {
                                        doesNotExistErrorMessage();
                                        //clear fields
                                        studentIdTextField.setText("");
                                    }
                                } else {
                                    integerErrorMessage();
                                    studentIdTextField.setText("");
                                }

                            } catch (NumberFormatException exception) {
                                integerErrorMessage();
                                studentIdTextField.setText("");
                                inputID = 0;
                            }
                            break;
                        //FIND
                        case "Find":
                            try {
                                //get inputID and check if integer
                                inputID = Integer.parseInt(studentIdTextField.getText());
                                //check if input is greater than 1
                                if (inputID >= 1) {
                                    if (studentDatabase.containsKey(inputID) == true) { // check if id exists
                                        //if yes, return student info
                                        JOptionPane.showMessageDialog(null, "Student ID: "
                                                + inputID + studentDatabase.get(inputID), "Found Record",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        //clear fields
                                        studentIdTextField.setText("");
                                    } else {
                                        doesNotExistErrorMessage();
                                        //clear fields
                                        studentIdTextField.setText("");
                                    }
                                } else {//
                                    integerErrorMessage();
                                    studentIdTextField.setText("");
                                }
                            } catch (NumberFormatException exception) {
                                integerErrorMessage();
                                studentIdTextField.setText("");
                                inputID = 0;
                            }
                            break;
                        //UPDATE
                        default:
                            try {
                                //GET inputID AND CHECK IF INTEGER
                                inputID = Integer.parseInt(studentIdTextField.getText());
                                //CHECK IF inputID IS >=1
                                if (inputID >= 1) {
                                    int gradeMultiplier = 0;
                                    int qualityPoints = 0;
                                    int credits = 0;
                                    if (studentDatabase.containsKey(inputID) == true) { //check if id exists
                                        //CHOOSE GRADE
                                        String[] gradesArray = {"A", "B", "C", "D", "F"};
                                        JFrame chooseGradeFrame = new JFrame();
                                        String chooseGrade = (String) JOptionPane.showInputDialog(chooseGradeFrame,
                                                "Choose grade:",//MESSAGE
                                                "",//WINDOW TITLE
                                                JOptionPane.QUESTION_MESSAGE,
                                                null,
                                                gradesArray,
                                                gradesArray[0]);
                                        //SET gradeMultiplier VALUE BASED ON SELECTED GRADE
                                        if (null != chooseGrade) {
                                            switch (chooseGrade) {
                                                case "A":
                                                    gradeMultiplier = 4;
                                                    break;
                                                case "B":
                                                    gradeMultiplier = 3;
                                                    break;
                                                case "C":
                                                    gradeMultiplier = 2;
                                                    break;
                                                case "D":
                                                    gradeMultiplier = 1;
                                                    break;
                                                default:
                                                    gradeMultiplier = 0;
                                                    break;
                                            }
                                        }
                                        //IF A GRADE WAS CHOSEN, CONTINUE TO CREDITS
                                        if (null != chooseGrade) {
                                            //CHOOSE CREDITS
                                            String[] creditsArray = {"3", "6"};
                                            JFrame chooseCreditsFrame = new JFrame();
                                            String chooseCredits
                                                    = (String) JOptionPane.showInputDialog(chooseCreditsFrame,
                                                            "Choose credits:",//MESSAGE
                                                            "",//WINDOW TITLE
                                                            JOptionPane.QUESTION_MESSAGE,
                                                            null,
                                                            creditsArray,
                                                            creditsArray[0]);
                                            //SET credits VALUE BASED ON SELECTED CREDITS
                                            if (null != chooseCredits) {
                                                switch (chooseCredits) {
                                                    case "3":
                                                        credits = 3;
                                                        qualityPoints = gradeMultiplier * credits;
                                                        break;
                                                    default:
                                                        credits = 6;
                                                        qualityPoints = gradeMultiplier * credits;
                                                        break;
                                                }
                                            }
                                            //IF CREDITS CHOSEN, THEN UPDATE STUDENT'S totalQP and totalCredits
                                            if (null != chooseCredits) {
                                                //update totalCredits
                                                studentDatabase.get(inputID).setStudentTotalCredits(studentDatabase.get(inputID).getStudentTotalCredits(inputID) + credits);
                                                //update totalQP
                                                studentDatabase.get(inputID).setStudentTotalQP(studentDatabase.get(inputID).getStudentTotalQP(inputID) + qualityPoints);
                                                //confirmation
                                                JOptionPane.showMessageDialog(null, "Student ID: "
                                                        + inputID + studentDatabase.get(inputID), "Updated Record",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                            }
                                        } else {//IF A GRADE WAS NOT CHOSEN, THEN CLEAR AND RETURN TO APP
                                            //clear fields
                                            studentIdTextField.setText("");
                                        }
                                        //clear fields
                                        studentIdTextField.setText("");
                                    } else {
                                        doesNotExistErrorMessage();
                                        //clear fields
                                        studentIdTextField.setText("");
                                    }
                                } else {
                                    integerErrorMessage();
                                    studentIdTextField.setText("");
                                }
                            } catch (NumberFormatException exception) {
                                integerErrorMessage();
                                studentIdTextField.setText("");
                                inputID = 0;
                            }
                            break;
                    }
                }
            }//END actionPerformed
        });//END PROCESS BUTTON LISTENER      

        //studentManagementFrame
        studentManagementFrame.setTitle("Project 4");
        studentManagementFrame.setLayout(new GridLayout(0, 1));//rows,columns
        studentManagementFrame.setBackground(Color.LIGHT_GRAY);
        studentManagementFrame.setSize(300, 200);//WIDTH,HEIGHT
        studentManagementFrame.setResizable(false);//MAKES FRAME NOT RESIZEABLE
        studentManagementFrame.setLocationRelativeTo(null);//CENTERS ON SCREEN
        studentManagementFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//CLOSABLE
        studentManagementFrame.setVisible(true);
        studentManagementFrame.pack();

        //CLOSE WINDOW LISTENER ADAPTER
        studentManagementFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {

                try {
                    File outDataFile = new File("outData.txt");
                    FileOutputStream fileOutStream = new FileOutputStream(outDataFile);
                    PrintWriter printWriter = new PrintWriter(fileOutStream);

                    printWriter.println("*** Student Database Records ***\n");

                    //Write database records
                    int totalGPA = 0;
                    for (Map.Entry<Integer, Student> entry : studentDatabase.entrySet()) {
                        Integer key = entry.getKey();
                        Object value = entry.getValue();
                        printWriter.println("Student ID: " + key + value + "\n");
                        //add all student GPAs together
                        totalGPA = studentDatabase.get(key).getStudentGPA(key) + totalGPA;

                    }
                    //Write number of records
                    printWriter.println("\nTotal Student Records: " + studentDatabase.size());

                    //Write average GPA
                    DecimalFormat gpaFormat = new DecimalFormat("#.0");
                    printWriter.println("\nAverage Student GPA: "
                            + gpaFormat.format(totalGPA / studentDatabase.size()));

                    printWriter.flush();
                    printWriter.close();
                    fileOutStream.close();
                } catch (Exception e) {
                    System.out.println("Exception" + e.getMessage());
                }
                studentManagementFrame.dispose();
            }
        });//END CLOSE WINDOW LISTENER ADAPTER

    }//END StudentManagement Method

//    public void clearTextFields() {
//        studentIdTextField.setText("");
//        studentNameTextField.setText("");
//        studentMajorTextField.setText("");
//    }
    
    public void integerErrorMessage() {
        JOptionPane.showMessageDialog(null, "Enter an integer greater than 0 in ID text field.",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void doesNotExistErrorMessage() {
        JOptionPane.showMessageDialog(null, "ID " + inputID
                + " does not exist. Enter a new ID to find.", "Error",
                JOptionPane.ERROR_MESSAGE);
    }

}//END StudentManagement Class
