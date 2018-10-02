package ass2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

class TabDeleteUser extends JPanel {
    
    private final Admin ADMIN;
    private final Library LIBRARY;
    
    private JTextArea output;
    private JComboBox boxUsername;
    
    
    //print out the details of account
    public void setDetails(User u) {
        output.setText("Are you sure you wish to delete this account?");
        boxUsername.setSelectedItem(u);
        output.append(u.getDetails());
    }
    
    
    //deleted the searched user
    private void deleteUser(User u) {
        if (u != null) {
            ADMIN.deleteUser(u);
            output.setText("User has been deleted");
            u = null;
        }
        else {
            output.setText("Please select");
        }
    }
    
    //constructor, sets up the tab
    public TabDeleteUser(Admin admin, Library lib) {
        super();
        this.ADMIN = admin;
        this.LIBRARY = lib;
        //create text font on the tab
        Font tabFont = new Font("Tahoma", 0, 20);
        
        //create some output fields
        output = new JTextArea();
        output.setEditable(false);
        this.add(output);
        
        //create and sort an array of all users
        Object[] userArray = LIBRARY.getUsers().keySet().toArray();
        Arrays.sort(userArray);
        
        //combo box for all users
        boxUsername = new JComboBox(userArray);
        boxUsername.setFont(tabFont);
        boxUsername.setEditable(false);
        boxUsername.setSelectedIndex(-1);
        boxUsername.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User u = LIBRARY.findUser(boxUsername.getSelectedItem().toString());
                setDetails(u);
            }
        });
        this.add(boxUsername);
        
        //create button for create/delete
        JButton buttonDelete = new JButton("Delete User");
        buttonDelete.setFont(tabFont);
        buttonDelete.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                User u = LIBRARY.findUser(boxUsername.getSelectedItem().toString());
                deleteUser(u);
            }
        });
        this.add(buttonDelete);
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(boxUsername)
                        .addComponent(output)
                        .addComponent(buttonDelete)
                )
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(boxUsername)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(output)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(buttonDelete)
                )
        );
        
    }
    
}
