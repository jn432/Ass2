package ass2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class TabLibrarianBook extends JPanel {
    
    private final Librarian LIBRARIAN;
    private final Library LIBRARY;
    
    //set it this way to call their data easier
    private JTextArea output;
    
    private JTextField fieldISBN;
    private JTextField fieldTitle;
    private JTextField fieldAuthor;
    private JTextField fieldLocation;
    
    private DefaultComboBoxModel model;
    private JComboBox boxOldISBN;
    
    public void setDetails(Book b) {
        //if book does not exist
        if (b == null) {
            fieldISBN.setText("");
            fieldTitle.setText("");
            fieldAuthor.setText("");
            fieldLocation.setText("");
        }
        else {
            boxOldISBN.setSelectedItem(b.getISBN());
            fieldISBN.setText(Integer.toString(b.getISBN()));
            fieldTitle.setText(b.getTitle());
            fieldAuthor.setText(b.getAuthor());
            fieldLocation.setText(b.getLocation());
        }
    }
    
    //grabs user input and tries to makes a book, returns true if it was created,
    //otherwise returns false
    private boolean createBook() {
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
            model.addElement(ISBN);
            output.setText("Book " + ISBN + " has been created.");
            return true;
        }
    }
    
    private boolean editBook() {
        int oldISBN = 0;
        int ISBN = 0;
        try {
            oldISBN = Integer.parseInt(boxOldISBN.getSelectedItem().toString());
            ISBN = Integer.parseInt(fieldISBN.getText());
        }
        catch (NumberFormatException e) {
            output.setText("Input an integer for ISBN");
            return false;
        }
        Book b = LIBRARY.findBook(oldISBN);
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
            //edit the book
            model.removeElement(oldISBN);
            LIBRARIAN.createBook(ISBN, title, author, location);
            model.addElement(ISBN);
            output.setText("Book has been edited.");
            return true;
        }
    }
    
    private void deleteBook() {
        Book b = LIBRARY.findBook(Integer.parseInt(boxOldISBN.getSelectedItem().toString()));
        if (b != null) {
            model.removeElement(b.getISBN());
            LIBRARIAN.deleteBook(b);
            output.setText("Book has been deleted");
            
        }
        else {
            output.setText("Please select a book");
        }
    }
    
    public TabLibrarianBook(Librarian librarian, Library lib) {
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
        JButton buttonCreate = new JButton("Create");
        buttonCreate.setFont(tabFont);
        buttonCreate.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                createBook();
            }
        });
        this.add(buttonCreate);
        
        //create button for Edit Student
        JButton buttonEdit = new JButton("Edit");
        buttonEdit.setFont(tabFont);
        buttonEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                editBook();
            }
        });
        buttonEdit.setEnabled(false);
        this.add(buttonEdit);
        
        //create button for Delete
        JButton buttonDelete = new JButton("Delete");
        buttonDelete.setFont(tabFont);
        buttonDelete.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                deleteBook();
            }
        });
        buttonDelete.setEnabled(false);
        this.add(buttonDelete);
        
        //create list of books
        ArrayList<String> keys = new ArrayList<>();
        for (Integer i : LIBRARY.getBooks().keySet()) {
            Book b = LIBRARY.findBook(i);
            keys.add(Integer.toString(b.getISBN()));
        }
        Collections.sort(keys);
        //and add the New Book option at the end
        keys.add("New Book");
        
        //label for old ISBN
        JLabel labelOldISBN = new JLabel("Old ISBN: ");
        labelOldISBN.setFont(tabFont);
        this.add(labelOldISBN);
        
        //combo box for all users
        model = new DefaultComboBoxModel(keys.toArray());
        boxOldISBN = new JComboBox(model);
        boxOldISBN.setFont(tabFont);
        boxOldISBN.setEditable(false);
        boxOldISBN.setSelectedIndex(keys.size() - 1);
        boxOldISBN.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Book b = LIBRARY.findBook(Integer.parseInt(boxOldISBN.getSelectedItem().toString()));
                    setDetails(b);
                    buttonCreate.setEnabled(false);
                    buttonEdit.setEnabled(true);
                    buttonDelete.setEnabled(true);
                }
                catch (NumberFormatException nfe) {
                    setDetails(null);
                    buttonCreate.setEnabled(true);
                    buttonEdit.setEnabled(false);
                    buttonDelete.setEnabled(false);
                }
            }
        });
        this.add(boxOldISBN);
        
        
        //horrible layout stuff
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelOldISBN)
                        .addComponent(labelISBN)
                        .addComponent(labelTitle)
                        .addComponent(labelAuthor)
                        .addComponent(labelLocation)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(boxOldISBN)
                        .addComponent(fieldISBN)
                        .addComponent(fieldTitle)
                        .addComponent(fieldAuthor)
                        .addComponent(fieldLocation)
                        .addComponent(output)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(buttonCreate)
                                )
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(buttonEdit)
                                )
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(buttonDelete)
                                )
                        )
                )
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelOldISBN)
                        .addComponent(boxOldISBN)
                )
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
                        .addComponent(buttonCreate)
                        .addComponent(buttonEdit)
                        .addComponent(buttonDelete)
                )
        );
        
    }
    
}
