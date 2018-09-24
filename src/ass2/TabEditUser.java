package ass2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

class TabEditUser extends JPanel {
    
    private JTextArea output;
    
    private User editedUser;
    private JTextField fieldID;
    private JTextField fieldUsername;
    private JPasswordField fieldPassword;
    private JComboBox boxDegree;
    
    
    //used to set details in tab after a user is searched for
    public void setDetails(User u) {
        editedUser = u;
        fieldID.setText(Integer.toString(u.getID()));
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
    
    
    private void editUser() {
        boolean check = Admin.editUser(editedUser, fieldUsername.getText(), fieldPassword.getPassword(), boxDegree.getSelectedItem().toString());
        if (check) {
            output.setText("User has been edited:");
            output.append(editedUser.getDetails());
        }
        else {
            output.setText("Error in editing file");
        }
    }
    
    public TabEditUser() {
        super();
        //create text font on the tab
        Font tabFont = new Font("Tahoma", 0, 20);
        
        //label for id
        JLabel labelID = new JLabel("ID: ");
        labelID.setFont(tabFont);
        this.add(labelID);
        
        //text field for id(cannot be edited)
        fieldID = new JTextField();
        fieldID.setFont(tabFont);
        fieldID.setEditable(false);
        this.add(fieldID);
        
        //label for username
        JLabel labelUser = new JLabel("Username: ");
        labelUser.setFont(tabFont);
        this.add(labelUser);
        
        //text field for new username
        fieldUsername = new JTextField();
        fieldUsername.setFont(tabFont);
        this.add(fieldUsername);
        
        //label for password
        JLabel labelPass = new JLabel("Password: ");
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
                        .addComponent(labelID)
                        .addComponent(labelUser)
                        .addComponent(labelPass)
                        .addComponent(labelDegree)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(fieldID)
                        .addComponent(fieldUsername)
                        .addComponent(fieldPassword)
                        .addComponent(boxDegree)
                        .addComponent(output)
                        .addComponent(buttonEdit)
                )
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelID)
                        .addComponent(fieldID)
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
