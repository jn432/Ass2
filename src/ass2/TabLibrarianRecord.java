package ass2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.io.IOException;

class TabLibrarianRecord extends JPanel {
    
    private final Librarian LIBRARIAN;
    private final Library LIBRARY;
    
    //set it this way to call their data easier
    private JTextArea output;
    
    private void generateReport() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String formatDateTime = LocalDateTime.now().format(formatter);
            Formatter file = new Formatter("Record" + formatDateTime + ".txt");
            ArrayList<Record> list = new ArrayList<>();
            for (Book b : LIBRARY.getBooks().values()) {
                b.printDetails(output);
                b.generateRecord(file);
            }
            output.append("A file of this report has been generated");
            file.close();
        }
        catch (IOException e) {
            output.setText("Error in saving file: " + e);
        }
        
    }
    
    public TabLibrarianRecord(Librarian librarian, Library lib) {
        super();
        this.LIBRARIAN = librarian;
        this.LIBRARY = lib;
        //create text font on the tab
        Font tabFont = new Font("Tahoma", 0, 20);
        
        //label
        JLabel labelRecord = new JLabel("Click button to generate report");
        labelRecord.setFont(tabFont);
        this.add(labelRecord);
        
        //create button for creating a book
        JButton buttonRun = new JButton("Generate Report");
        buttonRun.setFont(tabFont);
        buttonRun.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                generateReport();
            }
        });
        this.add(buttonRun);
        
        //create some output fields
        output = new JTextArea();
        output.setEditable(false);
        this.add(output);
        
        //horrible layout stuff
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelRecord)
                        .addComponent(buttonRun)
                        .addComponent(output)
                )
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelRecord)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonRun)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(output)
                )
        );
        
    }
    
}
