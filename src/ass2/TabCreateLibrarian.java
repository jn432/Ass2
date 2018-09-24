package ass2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

class TabCreateLibrarian extends JPanel {
    
    private JTextArea output;
    private boolean createLibrarian = true;
    
    public void setDetails(Librarian l) {
        
    }
    
    private boolean createUser(String username, char[] password, String degree) {
        if (username.equals("")) {
            output.setText("Username cannot be empty");
            return false;
        }
        else if (Ass2.LIB.findUser(username) != null) {
            output.setText("Username has already been taken");
            return false;
        }
        else if (Arrays.toString(password).equals("")) {
            output.setText("Password cannot be empty");
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
    
    public TabCreateLibrarian() {
        super();
        //create text font on the tab
        Font tabFont = new Font("Tahoma", 0, 20);
        
        //label for new username
        JLabel labelNewUser = new JLabel("New Username: ");
        labelNewUser.setFont(tabFont);
        this.add(labelNewUser);
        
        //text field for new username
        JTextField fieldNewUser = new JTextField();
        fieldNewUser.setFont(tabFont);
        this.add(fieldNewUser);
        
        //label for password
        JLabel labelNewPass = new JLabel("New Password: ");
        labelNewPass.setFont(tabFont);
        this.add(labelNewPass);
        
        //text field for password
        JPasswordField fieldNewPass = new JPasswordField();
        fieldNewPass.setFont(tabFont);
        this.add(fieldNewPass);
        
        //label for degree
        JLabel labelDegree = new JLabel("New Degree: ");
        labelDegree.setFont(tabFont);
        this.add(labelDegree);
        
        //drop down for degree
        String[] degree = {"Chemistry", "Computer Science", "Mathematics"};
        JComboBox boxDegree = new JComboBox(degree);
        boxDegree.setFont(tabFont);
        this.add(boxDegree);
        
        //create radio button group
        ButtonGroup radioGroup = new ButtonGroup();
        
        //create some output fields
        output = new JTextArea();
        output.setEditable(false);
        this.add(output);
        
        //create search button
        JButton buttonCreate = new JButton("Create user");
        buttonCreate.setFont(tabFont);
        buttonCreate.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                createUser(fieldNewUser.getText(), fieldNewPass.getPassword(), boxDegree.getSelectedItem().toString());
            }
        });
        this.add(buttonCreate);
        
        
        
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelNewUser)
                        .addComponent(labelNewPass)
                        .addComponent(labelDegree)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(fieldNewUser)
                        .addComponent(fieldNewPass)
                        .addComponent(boxDegree)
                        .addComponent(buttonCreate)
                        .addComponent(output)
                )
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNewUser)
                        .addComponent(fieldNewUser)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNewPass)
                        .addComponent(fieldNewPass)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelDegree)
                        .addComponent(boxDegree)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonCreate)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(output)
                )
        );
        
    }
    
}
