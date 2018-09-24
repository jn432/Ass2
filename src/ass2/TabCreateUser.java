package ass2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

class TabCreateUser extends JPanel {
    
    private int choice = 1;
    
    //set it this way to call their data easier
    private JTextArea output;
    private JTextField fieldUsername;
    private JPasswordField fieldPassword;
    private JComboBox boxDegree;
    
    //grabs user input, and tries to make a student or librarian, returns true if it was created,
    //otherwise returns false
    private boolean createUser() {
        String username = fieldUsername.getText();
        char[] password = fieldPassword.getPassword();
        String degree = boxDegree.getSelectedItem().toString();
        if (username.equals("")) {
            output.setText("Username cannot be empty");
            return false;
        }
        else if (Arrays.toString(password).equals("")) {
            output.setText("Password cannot be empty");
            return false;
        }
        else if (Ass2.LIB.findUser(username) != null) {
            output.setText("Username has already been taken");
            return false;
        }
        else {
            //create user account and add it to user file
            User u;
            switch(choice) {
                case 1:
                    if (Admin.createStudent(username, password, degree)) {
                        output.setText("Student " + username + " has been created.");
                        return true;
                    }
                    else {
                        output.setText("Username has already been taken");
                        return false;
                    }
                case 2:
                    if (Admin.createLibrarian(username, password)) {
                        output.setText("Librarian " + username + " has been created.");
                        return true;
                    }
                    else {
                        output.setText("Username has already been taken");
                        return false;
                    }
            }
            return false;
        }
    }
    
    public TabCreateUser() {
        super();
        //create text font on the tab
        Font tabFont = new Font("Tahoma", 0, 20);
        
        //label for new username
        JLabel labelNewUser = new JLabel("New Username: ");
        labelNewUser.setFont(tabFont);
        this.add(labelNewUser);
        
        //text field for new username
        fieldUsername = new JTextField();
        fieldUsername.setFont(tabFont);
        this.add(fieldUsername);
        
        //label for password
        JLabel labelNewPass = new JLabel("New Password: ");
        labelNewPass.setFont(tabFont);
        this.add(labelNewPass);
        
        //text field for password
        fieldPassword = new JPasswordField();
        fieldPassword.setFont(tabFont);
        this.add(fieldPassword);
        
        //label for degree
        JLabel labelDegree = new JLabel("New Degree: ");
        labelDegree.setFont(tabFont);
        this.add(labelDegree);
        
        //drop down for degree
        String[] degree = {"Chemistry", "Computer Science", "Mathematics"};
        boxDegree = new JComboBox(degree);
        boxDegree.setFont(tabFont);
        this.add(boxDegree);
        
        //create an output area
        output = new JTextArea();
        output.setEditable(false);
        this.add(output);
        
        //create button for creating a user
        JButton buttonRun = new JButton("Create Student");
        buttonRun.setFont(tabFont);
        buttonRun.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                createUser();
            }
        });
        this.add(buttonRun);
        
        //create radio button group
        ButtonGroup radioGroup = new ButtonGroup();
        
        //radiobutton for selecting student
        JRadioButton radioStudent = new JRadioButton("Student", true);
        radioStudent.addItemListener(new ItemListener() {
            //change the choice, button and enable the combo box
            public void itemStateChanged(ItemEvent evt) {
                choice = 1;
                buttonRun.setText("Create Student");
                boxDegree.setEnabled(true);
            }
        });
        this.add(radioStudent);
        radioGroup.add(radioStudent);
        
        //radiobutton for selecting librarian
        JRadioButton radioLibrarian = new JRadioButton("Librarian", false);
        radioLibrarian.addItemListener(new ItemListener() {
            //change the choice, button and disable the combo box
            public void itemStateChanged(ItemEvent evt) {
                choice = 2;
                buttonRun.setText("Create Librarian");
                boxDegree.setEnabled(false);
            }
        });
        this.add(radioLibrarian);
        radioGroup.add(radioLibrarian);
        
        //horrible layout stuff
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(radioStudent)
                                )
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(radioLibrarian)
                                )
                        )
                        .addComponent(labelNewUser)
                        .addComponent(labelNewPass)
                        .addComponent(labelDegree)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(fieldUsername)
                        .addComponent(fieldPassword)
                        .addComponent(boxDegree)
                        .addComponent(output)
                        .addComponent(buttonRun)
                )
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(radioStudent)
                        .addComponent(radioLibrarian)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNewUser)
                        .addComponent(fieldUsername)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNewPass)
                        .addComponent(fieldPassword)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelDegree)
                        .addComponent(boxDegree)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(output)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(buttonRun)
                )
        );
        
    }
    
}
