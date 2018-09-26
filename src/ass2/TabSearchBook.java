package ass2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class TabSearchBook extends JPanel {
    
    private JTextArea output;
    private int choice = 1;
    JTabbedPane backPane;
    TabReserveBook tabReserveBook;
    
    private void searchBook(String search) {
        switch (choice) {
            //search by ISBN
            case 1:
                try {
                    int ISBN = Integer.parseInt(search);
                    Book b = Ass2.LIB.findBook(ISBN);
                    b.printDetails(output);
                    backPane.setSelectedIndex(1);
                    tabReserveBook.setDetails(b);
                }
                catch (NumberFormatException e) {
                    output.setText("You must enter an int to search by ISBN");
                }
                catch (NullPointerException e) {
                    output.setText("Book not found");
                }
                break;
            //search by author
            case 2:
            case 3:
        }

            
    }
    
    public TabSearchBook(JTabbedPane backPane, TabReserveBook tabReserveBook) {
        super();
        this.backPane = backPane;
        this.tabReserveBook = tabReserveBook;
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
        
        //create radio button group
        ButtonGroup radioGroup = new ButtonGroup();
        
        //create radio buttons
        JRadioButton radioISBN = new JRadioButton("ISBN", true);
        radioISBN.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                choice = 1;
            }
        });
        this.add(radioISBN);
        radioGroup.add(radioISBN);
                
        JRadioButton radioAuthor = new JRadioButton("Author", false);
        radioAuthor.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                choice = 2;
            }
        });
        this.add(radioAuthor);
        radioGroup.add(radioAuthor);
        
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
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(radioISBN)
                                )
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(radioAuthor)
                                )
                        )
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
                        .addComponent(radioISBN)
                        .addComponent(radioAuthor)
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
