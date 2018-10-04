package ass2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class TabStudentSearchBook extends JPanel {
    
    private final Library LIBRARY;
    
    private JTextArea output;
    TabStudentReserveBook tabReserveBook;
    
    private void searchBook(String search) {
        try {
            int ISBN = Integer.parseInt(search);
            Book b = LIBRARY.findBook(ISBN);
            b.printDetails(output);
            tabReserveBook.setDetails(b);
        }
        catch (NumberFormatException e) {
            output.setText("You must enter an int to search by ISBN");
        }
        catch (NullPointerException e) {
            output.setText("Book not found");
        }
            
    }
    
    public TabStudentSearchBook(TabStudentReserveBook tabReserveBook, Library lib) {
        super();
        this.tabReserveBook = tabReserveBook;
        this.LIBRARY = lib;
        //create text font on the tab
        Font tabFont = new Font("Tahoma", 0, 20);
        
        //label for ISBN
        JLabel labelSearch = new JLabel("Search: ");
        labelSearch.setFont(tabFont);
        this.add(labelSearch);
        
        //text field for ISBN
        JTextField fieldSearch = new JTextField();
        fieldSearch.setFont(tabFont);
        this.add(fieldSearch);
        
        //create some output fields
        output = new JTextArea();
        output.setEditable(false);
        this.add(output);
        
        //create search button
        JButton buttonSearch = new JButton("Search");
        buttonSearch.setFont(tabFont);
        buttonSearch.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                searchBook(fieldSearch.getText());
            }
        });
        this.add(buttonSearch);
        
        
        
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelSearch)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(fieldSearch)
                        .addComponent(buttonSearch)
                        .addComponent(output)
                )
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelSearch)
                        .addComponent(fieldSearch)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonSearch)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(output)
                )
        );
        
    }
    
}
