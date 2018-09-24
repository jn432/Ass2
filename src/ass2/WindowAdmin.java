package ass2;

import java.awt.Font;
import javax.swing.*;

public class WindowAdmin extends JFrame {
    
    public WindowAdmin(Admin admin) {
        super("Admin window");
        //setting the frame
        this.setBounds(0,0,640, 480);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //create text font on the panel
        Font tabFont = new Font("Tahoma", 0, 24);
        
        //create backpanel
        JTabbedPane backPane = new JTabbedPane();
        backPane.setFont(tabFont);
        this.add(backPane);
        
        //create the tabs
        TabCreateUser tabCreate = new TabCreateUser();
        TabEditUser tabEdit = new TabEditUser();
        TabDeleteUser tabDelete = new TabDeleteUser();
        
        //add tabs
        backPane.addTab("Find", new TabSearchUser(backPane, tabEdit, tabDelete));
        backPane.addTab("Create", tabCreate);
        backPane.addTab("Edit", tabEdit);
        backPane.addTab("Delete", tabDelete);
        
        //disable the edit and delete tabs initially
        backPane.setEnabledAt(2, false);
        backPane.setEnabledAt(3, false);
    }
    
}
