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
        backPane.addTab("Create", new TabCreateBook(librarian, lib));
        backPane.addTab("Create", new TabEditBook(librarian, lib));
        //backPane.addTab("Search", new TabSearchBook(backPane, tabReserveBook, lib));
        //backPane.addTab("Reserve", tabReserveBook);
    }
    
}
