package ass2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.ArrayList;

class TabAdminLibrarian extends JPanel {
    
    private final Admin ADMIN;
    private final Library LIBRARY;
    
    private JTextArea output;

    private JComboBox boxOldUsername;
    private JTextField fieldUsername;
    private JPasswordField fieldPassword;
    DefaultComboBoxModel model;
    
    
    //used to set details in tab after a user is searched for
    public void setDetails(Librarian l) {
        //if user does not exist
        if (l == null) {
            fieldUsername.setText("");
            fieldPassword.setText("");
        }
        else {
            boxOldUsername.setSelectedItem(l.getUsername());
            fieldUsername.setText(l.getUsername());
            fieldPassword.setText(l.getPassword().toString());
        }
    }
    
    private boolean createLibrarian() {
        String username = fieldUsername.getText();
        char[] password = fieldPassword.getPassword();
        
        //check if username string is empty
        if (username.equals("")) {
            output.setText("Username cannot be empty");
            return false;
        }
        //check if password string is empty
        else if (password.toString().equals("")) {
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
            ADMIN.createLibrarian(username, password);
            model.addElement(username);
            output.setText("Librarian " + username + " has been created.");
            return true;
        }
    }
    
    private boolean editLibrarian() {
        Librarian l = (Librarian) LIBRARY.findUser(boxOldUsername.getSelectedItem().toString());
        String username = fieldUsername.getText();
        char[] password = fieldPassword.getPassword();
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
        model.removeElement(l.getUsername());
        ADMIN.editUser(l, username, password, null);
        model.addElement(username);
        output.setText("Librarian has been edited:");
        output.append(l.getDetails());
        return true;
    }

    //deleted the student
    private void deleteLibrarian() {
        Librarian l = (Librarian) LIBRARY.findUser(boxOldUsername.getSelectedItem().toString());
        if (l != null) {
            model.removeElement(l.getUsername());
            ADMIN.deleteUser(l);
            output.setText("Librarian has been deleted");
            
        }
        else {
            output.setText("Please select a student");
        }
    }
    
    public TabAdminLibrarian(Admin admin, Library lib) {
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
        
        //create some output fields
        output = new JTextArea();
        output.setEditable(false);
        this.add(output);
        
        //create button for Create Librarian
        JButton buttonCreate = new JButton("Create");
        buttonCreate.setFont(tabFont);
        buttonCreate.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                createLibrarian();
            }
        });
        this.add(buttonCreate);
        
        //create button for Edit Librarian
        JButton buttonEdit = new JButton("Edit");
        buttonEdit.setFont(tabFont);
        buttonEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                editLibrarian();
            }
        });
        buttonEdit.setEnabled(false);
        this.add(buttonEdit);
        
        //create button for Delete Librarian
        JButton buttonDelete = new JButton("Delete");
        buttonDelete.setFont(tabFont);
        buttonDelete.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                deleteLibrarian();
            }
        });
        buttonDelete.setEnabled(false);
        this.add(buttonDelete);
        
        //create list of users
        ArrayList<String> keys = new ArrayList<>();
        for (String s : LIBRARY.getUsers().keySet()) {
            User u = LIBRARY.findUser(s);
            if (u.getUserType() == 2) {
                keys.add(u.getUsername());
            }
        }
        Collections.sort(keys);
        //and add the New Student option at the end
        keys.add("New Librarian");
        
        //combo box for all users
        model = new DefaultComboBoxModel(keys.toArray());
        boxOldUsername = new JComboBox(model);
        boxOldUsername.setFont(tabFont);
        boxOldUsername.setEditable(false);
        boxOldUsername.setSelectedIndex(keys.size() - 1);
        boxOldUsername.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Librarian l = (Librarian) LIBRARY.findUser(boxOldUsername.getSelectedItem().toString());
                setDetails(l);
                //if there is no user selected(New User selected)
                if (l == null) {
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
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(boxOldUsername)
                        .addComponent(fieldUsername)
                        .addComponent(fieldPassword)
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
