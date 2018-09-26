package ass2;

import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;

public class WindowStudent extends JFrame {
    
    public WindowStudent(Student student) {
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
        TabReserveBook tabReserveBook = new TabReserveBook(student);
        backPane.addTab("Search", new TabSearchBook(backPane, tabReserveBook));
        backPane.addTab("Reserve", tabReserveBook);
    }
    
}
