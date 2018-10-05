package ass2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.io.IOException;
import java.util.Collections;

class TabLibrarianUserRecord extends JPanel {
    
    private final Librarian LIBRARIAN;
    private final Library LIBRARY;
    
    //set it this way to call their data easier
    private JTextArea output;
    
    public void generateUserReport(String username) {
        Student s = (Student) LIBRARY.findUser(username);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String formatDateTime = LocalDateTime.now().format(formatter);
            Formatter file = new Formatter("Record" + username + formatDateTime + ".txt");
            output.setText("Record is as follows:\n");
            file.format("Record is as follows:%n");
            for (Record r : s.getRecords()) {
                output.append(r.printRecord());
                r.generateRecord(file);
            }
            output.append("A file of this report has been generated");
            file.close();
        }
        catch (IOException e) {
            output.setText("Error in saving file: " + e);
        }
        
    }
    
    public TabLibrarianUserRecord(Librarian librarian, Library lib) {
        super();
        this.LIBRARIAN = librarian;
        this.LIBRARY = lib;
        //create text font on the tab
        Font tabFont = new Font("Tahoma", 0, 20);
        
        //label
        JLabel labelRecord = new JLabel("Select a user");
        labelRecord.setFont(tabFont);
        this.add(labelRecord);
        
        //create list of students
        ArrayList<String> keys = new ArrayList<>();
        for (String s : LIBRARY.getUsers().keySet()) {
            User u = LIBRARY.findUser(s);
            if (u.getUserType() == 1) {
                keys.add(u.getUsername());
            }
        }
        Collections.sort(keys);
        
        //combo box for all users
        DefaultComboBoxModel model = new DefaultComboBoxModel(keys.toArray());
        JComboBox boxStudent = new JComboBox(model);
        boxStudent.setFont(tabFont);
        boxStudent.setEditable(false);
        this.add(boxStudent);
        
        //create button for creating a book
        JButton buttonRun = new JButton("Generate Report");
        buttonRun.setFont(tabFont);
        buttonRun.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                generateUserReport(boxStudent.getSelectedItem().toString());
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
                        .addComponent(boxStudent)
                        .addComponent(buttonRun)
                        .addComponent(output)
                )
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelRecord)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(boxStudent)
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
