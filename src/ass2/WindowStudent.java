package ass2;

import java.awt.Font;
import javax.swing.*;

class WindowStudent extends JFrame {
    
    public WindowStudent(Student student, Library lib) {
        super("Student window");
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
        TabStudentReserveBook tabReserveBook = new TabStudentReserveBook(student, lib);
        backPane.addTab("Search", new TabStudentSearchBook(tabReserveBook, lib));
        backPane.addTab("Reserve", tabReserveBook);
    }
    
}
