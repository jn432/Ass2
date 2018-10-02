package ass2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class TabEditBook extends JPanel {
    
    private final Librarian LIBRARIAN;
    private final Library LIBRARY;
    
    //set it this way to call their data easier
    private JTextArea output;
    private JTextField fieldISBN;
    private JTextField fieldTitle;
    private JTextField fieldAuthor;
    private JTextField fieldLocation;
    
    //grabs user input and tries to makes a book, returns true if it was created,
    //otherwise returns false
    private boolean editBook() {
        int ISBN;
        try {
            ISBN = Integer.parseInt(fieldISBN.getText());
        }
        catch (NumberFormatException e) {
            output.setText("Input an integer for ISBN");
            return false;
        }
        String title = fieldTitle.getText();
        String author = fieldAuthor.getText();
        String location = fieldLocation.getText();
        
        //check if username string is empty
        if (title.equals("")) {
            output.setText("Username cannot be empty");
            return false;
        }
        //check if password string is empty
        else if (author.equals("")) {
            output.setText("Password cannot be empty");
            return false;
        }
        //check if ISBN has been taken
        else if (LIBRARY.findBook(ISBN) != null) {
            output.setText("Book ISBN has already been taken");
            return false;
        }
        else {
            //create book and add it to the map
            LIBRARIAN.createBook(ISBN, title, author, location);
            
            output.setText("Book " + ISBN + " has been created.");
            return true;
        }
    }
    
    public TabEditBook(Librarian librarian, Library lib) {
        super();
        this.LIBRARIAN = librarian;
        this.LIBRARY = lib;
        //create text font on the tab
        Font tabFont = new Font("Tahoma", 0, 20);
        
        //label for new ISBN
        JLabel labelISBN = new JLabel("ISBN: ");
        labelISBN.setFont(tabFont);
        this.add(labelISBN);
        
        //text field for ISBN
        fieldISBN = new JTextField();
        fieldISBN.setFont(tabFont);
        this.add(fieldISBN);
        
        //label for title
        JLabel labelTitle = new JLabel("Title: ");
        labelTitle.setFont(tabFont);
        this.add(labelTitle);
        
        //text field for title
        fieldTitle = new JTextField();
        fieldTitle.setFont(tabFont);
        this.add(fieldTitle);
        
        //label for author
        JLabel labelAuthor = new JLabel("Author: ");
        labelAuthor.setFont(tabFont);
        this.add(labelAuthor);
        
        //text field for author
        fieldAuthor = new JTextField();
        fieldAuthor.setFont(tabFont);
        this.add(fieldAuthor);
        
        //label for location
        JLabel labelLocation = new JLabel("Location: ");
        labelLocation.setFont(tabFont);
        this.add(labelLocation);
        
        //text field for location
        fieldLocation = new JTextField();
        fieldLocation.setFont(tabFont);
        this.add(fieldLocation);
        
        //create an output area
        output = new JTextArea();
        output.setEditable(false);
        this.add(output);
        
        //create button for creating a book
        JButton buttonRun = new JButton("Create Book");
        buttonRun.setFont(tabFont);
        buttonRun.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                editBook();
            }
        });
        this.add(buttonRun);
        
        //horrible layout stuff
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelISBN)
                        .addComponent(labelTitle)
                        .addComponent(labelAuthor)
                        .addComponent(labelLocation)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(fieldISBN)
                        .addComponent(fieldTitle)
                        .addComponent(fieldAuthor)
                        .addComponent(fieldLocation)
                        .addComponent(output)
                        .addComponent(buttonRun)
                )
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelISBN)
                        .addComponent(fieldISBN)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelTitle)
                        .addComponent(fieldTitle)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelAuthor)
                        .addComponent(fieldAuthor)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelLocation)
                        .addComponent(fieldLocation)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(output)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(buttonRun)
                )
        );
        
    }
    
}
