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
        TabCreateStudent tabStudent = new TabCreateStudent();
        TabCreateLibrarian tabLibrarian = new TabCreateLibrarian();
        backPane.addTab("Find user", new TabSearchUser(backPane, tabStudent, tabLibrarian));
        backPane.addTab("Create/Edit Student", tabStudent);
        backPane.addTab("Create/Edit Librarian", tabLibrarian);
    }
    
}
