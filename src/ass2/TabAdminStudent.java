package ass2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.ArrayList;

class TabAdminStudent extends JPanel {
    
    private final Admin ADMIN;
    private final Library LIBRARY;
    
    private JTextArea output;

    private JComboBox boxOldUsername;
    private JTextField fieldUsername;
    private JPasswordField fieldPassword;
    private DefaultComboBoxModel model;
    private JComboBox boxDegree;
    
    
    //used to set details in tab after a user is searched for
    public void setDetails(Student s) {
        //if user does not exist
        if (s == null) {
            fieldUsername.setText("");
            fieldPassword.setText("");
        }
        else {
            boxOldUsername.setSelectedItem(s.username);
            fieldUsername.setText(s.username);
            fieldPassword.setText(new String(s.password));
            boxDegree.setSelectedItem(s.getDegree());
        }
    }
    
    //create a new student
    private boolean createStudent() {
        String username = fieldUsername.getText();
        char[] password = fieldPassword.getPassword();
        String degree = boxDegree.getSelectedItem().toString();
        
        //check if username string is empty
        if (username.equals("")) {
            output.setText("Username cannot be empty");
            return false;
        }
        //check if password string is empty
        else if (password.equals("")) {
            output.setText("Password cannot be empty");
            return false;
        }
        //check if username has been taken
        else if (LIBRARY.findUser(username) != null) {
            output.setText("Username has already been taken");
            return false;
        }
        else {
            //create user account and add it to user map
            ADMIN.createStudent(username, password, degree);
            model.addElement(username);
            output.setText("Student " + username + " has been created.");
            return true;
        }
    }
    
    //edit an existing student
    private boolean editStudent() {
        Student s = (Student) LIBRARY.findUser(boxOldUsername.getSelectedItem().toString());
        String username = fieldUsername.getText();
        char[] password = fieldPassword.getPassword();
        String degree = boxDegree.getSelectedItem().toString();
        //check if username is empty
        if (username.equals("")) {
            output.setText("Username cannot be empty");
            return false;
        }
        //check if password is empty
        else if (password.toString().equals("")) {
            output.setText("Password cannot be empty");
            return false;
        }
        //check if username is already taken
        else if (LIBRARY.findUser(username) != null && !boxOldUsername.getSelectedItem().toString().equals(username)) {
            output.setText("Username has already been taken");
            return false;
        }
        model.removeElement(s.getUsername());
        ADMIN.editUser(s, username, password, degree);
        model.addElement(username);
        output.setText("Student has been edited:");
        output.append(s.getDetails());
        return true;
    }

    //delete the student
    private void deleteStudent() {
        Student s = (Student) LIBRARY.findUser(boxOldUsername.getSelectedItem().toString());
        if (s != null) {
            model.removeElement(s.getUsername());
            ADMIN.deleteUser(s);
            output.setText("Student has been deleted");
            
        }
        else {
            output.setText("Please select a student");
        }
    }
    
    public TabAdminStudent(Admin admin, Library lib) {
        super();
        this.ADMIN = admin;
        this.LIBRARY = lib;
        //create text font on the tab
        Font tabFont = new Font("Tahoma", 0, 20);
        
        //label for old username
        JLabel labelOldUsername = new JLabel("Old username: ");
        labelOldUsername.setFont(tabFont);
        this.add(labelOldUsername);
        
        //label for new username
        JLabel labelUser = new JLabel("New Username: ");
        labelUser.setFont(tabFont);
        this.add(labelUser);
        
        //text field for new username
        fieldUsername = new JTextField();
        fieldUsername.setFont(tabFont);
        this.add(fieldUsername);
        
        //label for password
        JLabel labelPass = new JLabel("New Password: ");
        labelPass.setFont(tabFont);
        this.add(labelPass);
        
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
        
        //create button for Create Student
        JButton buttonCreate = new JButton("Create");
        buttonCreate.setFont(tabFont);
        buttonCreate.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                createStudent();
            }
        });
        this.add(buttonCreate);
        
        //create button for Edit Student
        JButton buttonEdit = new JButton("Edit");
        buttonEdit.setFont(tabFont);
        buttonEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                editStudent();
            }
        });
        buttonEdit.setEnabled(false);
        this.add(buttonEdit);
        
        //create button for Delete
        JButton buttonDelete = new JButton("Delete");
        buttonDelete.setFont(tabFont);
        buttonDelete.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                deleteStudent();
            }
        });
        buttonDelete.setEnabled(false);
        this.add(buttonDelete);
        
        //create list of users
        ArrayList<String> keys = new ArrayList<>();
        for (String s : LIBRARY.getUsers().keySet()) {
            User u = LIBRARY.findUser(s);
            if (u.getUserType() == 1) {
                keys.add(u.getUsername());
            }
        }
        Collections.sort(keys);
        //and add the New Student option at the end
        keys.add("New Student");
        
        //combo box for all users
        model = new DefaultComboBoxModel(keys.toArray());
        boxOldUsername = new JComboBox(model);
        boxOldUsername.setFont(tabFont);
        boxOldUsername.setEditable(false);
        boxOldUsername.setSelectedIndex(keys.size() - 1);
        boxOldUsername.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Student s = (Student) LIBRARY.findUser(boxOldUsername.getSelectedItem().toString());
                setDetails(s);
                //if there is no user selected(New Student selected)
                if (s == null) {
                    buttonCreate.setEnabled(true);
                    buttonEdit.setEnabled(false);
                    buttonDelete.setEnabled(false);
                }
                //if a user has been found
                else {
                    buttonCreate.setEnabled(false);
                    buttonEdit.setEnabled(true);
                    buttonDelete.setEnabled(true);
                }
            }
        });
        this.add(boxOldUsername);
        
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelOldUsername)
                        .addComponent(labelUser)
                        .addComponent(labelPass)
                        .addComponent(labelDegree)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(boxOldUsername)
                        .addComponent(fieldUsername)
                        .addComponent(fieldPassword)
                        .addComponent(boxDegree)
                        .addComponent(output)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(buttonCreate)
                                )
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(buttonEdit)
                                )
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(buttonDelete)
                                )
                        )
                        
                )
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelOldUsername)
                        .addComponent(boxOldUsername)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelUser)
                        .addComponent(fieldUsername)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelPass)
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
                        .addComponent(buttonCreate)
                        .addComponent(buttonEdit)
                        .addComponent(buttonDelete)
                )
        );
        
    }
    
}
