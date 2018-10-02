package ass2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

class TabEditUser extends JPanel {
    
    private final Admin ADMIN;
    private final Library LIBRARY;
    
    private JTextArea output;
    
    private User editedUser;
    private JComboBox boxOldUsername;
    private JTextField fieldUsername;
    private JPasswordField fieldPassword;
    private JComboBox boxDegree;
    
    
    //used to set details in tab after a user is searched for
    public void setDetails(User u) {
        editedUser = u;
        boxOldUsername.setSelectedItem(u.getUsername());
        fieldUsername.setText(u.getUsername());
        fieldPassword.setText(Arrays.toString(u.getPassword()));
        //if user is a student
        if (u.getUserType() == 1) {
            //set degree and enable combo box
            Student s = (Student) u;
            boxDegree.setSelectedItem(s.getDegree());
            boxDegree.setEnabled(true);
        }
        else {
            //disable combo box
            boxDegree.setEnabled(false);
        }
    }
    
    
    private boolean editUser() {
        String username = fieldUsername.getText();
        char[] password = fieldPassword.getPassword();
        String degree = boxDegree.getSelectedItem().toString();
        //check if username is empty
        if (username.equals("")) {
            output.setText("Username cannot be empty");
            return false;
        }
        //check if password is empty
        else if (Arrays.toString(password).equals("")) {
            output.setText("Password cannot be empty");
            return false;
        }
        //check if username is already taken
        else if (LIBRARY.findUser(username) != null) {
            output.setText("Username has already been taken");
            return false;
        }
        ADMIN.editUser(editedUser, username, password, degree);
        output.setText("User has been edited:");
        output.append(editedUser.getDetails());
        return true;
    }
    
    public TabEditUser(Admin admin, Library lib) {
        super();
        this.ADMIN = admin;
        this.LIBRARY = lib;
        //create text font on the tab
        Font tabFont = new Font("Tahoma", 0, 20);
        
        //label for old username
        JLabel labelOldUsername = new JLabel("Old username: ");
        labelOldUsername.setFont(tabFont);
        this.add(labelOldUsername);
        
        //create and sort an array of all users
        Object[] userArray = LIBRARY.getUsers().keySet().toArray();
        Arrays.sort(userArray);
        
        //combo box for all users
        boxOldUsername = new JComboBox(userArray);
        boxOldUsername.setFont(tabFont);
        boxOldUsername.setEditable(false);
        boxOldUsername.setSelectedIndex(-1);
        boxOldUsername.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User u = LIBRARY.findUser(boxOldUsername.getSelectedItem().toString());
                setDetails(u);
            }
        });
        this.add(boxOldUsername);
        
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
        
        //create button for create/delete
        JButton buttonEdit = new JButton("Edit User");
        buttonEdit.setFont(tabFont);
        buttonEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                editUser();
            }
        });
        this.add(buttonEdit);
        
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
                        .addComponent(buttonEdit)
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
                        .addComponent(buttonEdit)
                )
        );
        
    }
    
}
