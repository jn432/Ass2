package ass2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class TabDeleteUser extends JPanel {
    
    private JTextArea output;
    
    private User deletedUser;
    
    
    //print out the details of account
    public void setDetails(User u) {
        deletedUser = u;
        output.setText("Are you sure you wish to delete this account?");
        output.append(u.getDetails());
    }
    
    private void deleteUser() {
        //Admin.deleteUser(deletedUser);
    }
    
    public TabDeleteUser() {
        super();
        //create text font on the tab
        Font tabFont = new Font("Tahoma", 0, 20);
        
        //create some output fields
        output = new JTextArea();
        output.setEditable(false);
        this.add(output);
        
        //create button for create/delete
        JButton buttonDelete = new JButton("Delete User");
        buttonDelete.setFont(tabFont);
        buttonDelete.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                deleteUser();
            }
        });
        this.add(buttonDelete);
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(output)
                        .addComponent(buttonDelete)
                )
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(output)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(buttonDelete)
                )
        );
        
    }
    
}
