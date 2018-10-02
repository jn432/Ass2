package ass2;

import java.awt.Font;
import javax.swing.*;

class WindowAdmin extends JFrame {
    
    public WindowAdmin(Admin admin, Library lib) {
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
        TabCreateUser tabCreate = new TabCreateUser(admin, lib);
        TabEditUser tabEdit = new TabEditUser(admin, lib);
        TabDeleteUser tabDelete = new TabDeleteUser(admin, lib);
        
        //add tabs
        backPane.addTab("Find", new TabSearchUser(backPane, tabEdit, tabDelete, lib));
        backPane.addTab("Create", tabCreate);
        backPane.addTab("Edit", tabEdit);
        backPane.addTab("Delete", tabDelete);
    }
    
}
