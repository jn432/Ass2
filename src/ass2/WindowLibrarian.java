package ass2;

import java.awt.Font;
import javax.swing.*;

class WindowLibrarian extends JFrame {
    
    public WindowLibrarian(Librarian librarian, Library lib) {
        super("Librarian window");
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
        backPane.addTab("Book", new TabLibrarianBook(librarian, lib));
        backPane.addTab("All Records", new TabLibrarianRecord(librarian, lib));
        backPane.addTab("User Records", new TabLibrarianUserRecord(librarian, lib));
        backPane.addTab("Logout", new TabLogout(this, lib));
    }
    
}
