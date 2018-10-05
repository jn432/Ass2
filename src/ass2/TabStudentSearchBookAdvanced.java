package ass2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

class TabStudentSearchBookAdvanced extends JPanel {
    
    private final Library LIBRARY;
    
    private JTextArea output;
    
    private int choice = 1;
    
    private void searchBookTitle(String search) {
        try {
            ArrayList<Book> books = LIBRARY.getBooksByTitle(search);
            if (books.size() > 0) {
                for (Book b : books) {
                    b.printDetails(output);
                }
            }
            else {
                output.setText("No books found");
            }
            
        }
        catch (NullPointerException e) {
            output.setText("Book not found");
        }
    }
    
    private void searchBookAuthor(String search) {
        try {
            int ISBN = Integer.parseInt(search);
            Book b = LIBRARY.findBook(ISBN);
            b.printDetails(output);
        }
        catch (NumberFormatException e) {
            output.setText("You must enter an int to search by ISBN");
        }
        catch (NullPointerException e) {
            output.setText("Book not found");
        }
            
    }
    
    private void searchBookKeyword(String search) {
        try {
            int ISBN = Integer.parseInt(search);
            Book b = LIBRARY.findBook(ISBN);
            b.printDetails(output);
        }
        catch (NumberFormatException e) {
            output.setText("You must enter an int to search by ISBN");
        }
        catch (NullPointerException e) {
            output.setText("Book not found");
        }
            
    }
    
    public TabStudentSearchBookAdvanced(Library lib) {
        super();
        this.LIBRARY = lib;
        //create text font on the tab
        Font tabFont = new Font("Tahoma", 0, 20);
        
        //label for Search
        JLabel labelSearch = new JLabel("Search by: ");
        labelSearch.setFont(tabFont);
        this.add(labelSearch);
        
        //label for terms
        JLabel labelTerms = new JLabel("Search terms: ");
        labelTerms.setFont(tabFont);
        this.add(labelTerms);
        
        //create group for radio buttons
        ButtonGroup group = new ButtonGroup();
        
        //create button for searching by title
        JRadioButton buttonTitle = new JRadioButton("Title");
        buttonTitle.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                choice = 1;
            }
        });
        this.add(buttonTitle);
        group.add(buttonTitle);
        buttonTitle.setSelected(true);
        
        //create button for searching by author
        JRadioButton buttonAuthor = new JRadioButton("Author");
        buttonAuthor.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                choice = 2;
            }
        });
        this.add(buttonAuthor);
        group.add(buttonAuthor);
        
        //create button for searching by keyword
        JRadioButton buttonKeyword = new JRadioButton("Keyword");
        buttonKeyword.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                choice = 3;
            }
        });
        this.add(buttonKeyword);
        group.add(buttonKeyword);
        
        //text field for search term
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
                if (choice == 1) {
                    searchBookTitle(fieldSearch.getText());
                }
                else if (choice == 2) {
                    searchBookAuthor(fieldSearch.getText());
                }
                else {
                    searchBookKeyword(fieldSearch.getText());
                }
            }
        });
        this.add(buttonSearch);
        
        
        
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelSearch)
                        .addComponent(labelTerms)
                )
                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(buttonTitle)
                                )
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(buttonAuthor)
                                )
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(buttonKeyword)
                                )
                        )
                        .addComponent(fieldSearch)
                        .addComponent(buttonSearch)
                        .addComponent(output)
                )
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelSearch)
                        .addComponent(buttonTitle)
                        .addComponent(buttonAuthor)
                        .addComponent(buttonKeyword)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelTerms)
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
