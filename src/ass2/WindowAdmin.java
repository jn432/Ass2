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
        TabAdminStudent tabStu = new TabAdminStudent(admin, lib);
        TabAdminLibrarian tabLib = new TabAdminLibrarian(admin, lib);
        
        //add tabs
        backPane.addTab("Find", new TabAdminSearch(backPane, tabStu, tabLib, lib));
        backPane.addTab("Student", tabStu);
        backPane.addTab("Librarian", tabLib);
        backPane.addTab("Logout", new TabLogout(this, lib));
    }
    
}
