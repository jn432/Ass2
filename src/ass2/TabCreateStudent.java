package ass2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

class TabCreateStudent extends JPanel {
    
    private JTextArea output;
    private int choice = 1;
    private boolean createLibrarian = true;
    
    private JTextField fieldUsername;
    private JPasswordField fieldPassword;
    private JComboBox boxDegree;
    
    public void setDetails(Student s) {
        fieldUsername.setText(s.getUsername());
        fieldPassword.setText(Arrays.toString(s.getPassword()));
        boxDegree.setSelectedItem(s.getDegree());
    }
    
    private boolean editStudent() {
        return false;
    }
    
    private boolean createStudent() {
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
            User u = new Student(username, password, degree);
            output.setText("Student " + username + " has been created.");
            Ass2.LIB.getUserList().add(u);
            Ass2.LIB.saveFile(Ass2.LIB.getUserList());
            return true;
        }
    }
    
    public TabCreateStudent() {
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
        
        //create some output fields
        output = new JTextArea();
        output.setEditable(false);
        this.add(output);
        
        //create button for create/delete
        JButton buttonRun = new JButton("Edit/Delete User");
        buttonRun.setFont(tabFont);
        buttonRun.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                switch(choice) {
                    case 1:
                        //edit the student
                        break;
                    case 2:
                        createStudent();
                        break;
                }
            }
        });
        this.add(buttonRun);
        
        //create radio button group
        ButtonGroup radioGroup = new ButtonGroup();
        
        //radiobutton for create
        JRadioButton radioCreate = new JRadioButton("Edit/Delete", true);
        radioCreate.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                choice = 1;
                buttonRun.setText("Edit User");
            }
        });
        this.add(radioCreate);
        radioGroup.add(radioCreate);
        
        //radiobutton for edit/delete
        JRadioButton radioEditDelete = new JRadioButton("Create", false);
        radioEditDelete.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                choice = 2;
                buttonRun.setText("Create User");
            }
        });
        this.add(radioEditDelete);
        radioGroup.add(radioEditDelete);
        
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(radioCreate)
                                )
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(radioEditDelete)
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
                        .addComponent(radioCreate)
                        .addComponent(radioEditDelete)
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
