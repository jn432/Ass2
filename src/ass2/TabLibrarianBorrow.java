package ass2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.InputMismatchException;

class TabLibrarianBorrow extends JPanel {
    
    private final Librarian LIBRARIAN;
    private final Library LIBRARY;
    
    //set it this way to call their data easier
    private JTextArea output;
    
    private JTextField fieldStudent;
    private JTextField fieldISBN;
    
    private void borrowBook() {
        try {
            Student s = (Student) LIBRARY.findUser(fieldStudent.getText());
            int ISBN = Integer.parseInt(fieldISBN.getText());
            Book b = LIBRARY.findBook(ISBN);
            if (s != null && b != null) {
                boolean borrowed = LIBRARIAN.borrowBook(s, b);
                if (borrowed) {
                    output.setText("Book has been borrowed\n");
                }
                else {
                    output.setText("Book cannot be borrowed\n");
                }
            }
            else {
                output.setText("");
                if (s == null) {
                    output.append("Student not found");
                }
                if (b == null) {
                    output.append("Book not found");
                }
            }
        }
        catch (InputMismatchException e) {
            output.setText("ISBN must be a natural number");
        }
    }
    
    private void returnBook() {
        try {
            int ISBN = Integer.parseInt(fieldISBN.getText());
            Book b = LIBRARY.findBook(ISBN);
            boolean returned = LIBRARIAN.returnBook(b);
            if (returned) {
                output.setText("Book is now available, return to shelf");
            }
            else {
                output.setText("Book is still reserved by someone else, please hold the book");
            }
        }
        catch (InputMismatchException e) {
            output.setText("ISBN must be a natural number");
        }
    }
    
    public TabLibrarianBorrow(Librarian librarian, Library lib) {
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
        
        //label for student username
        JLabel labelStudent = new JLabel("Student: ");
        labelStudent.setFont(tabFont);
        this.add(labelStudent);
        
        //text field for student username
        fieldStudent = new JTextField();
        fieldStudent.setFont(tabFont);
        this.add(fieldStudent);
        
        //create an output area
        output = new JTextArea();
        output.setEditable(false);
        this.add(output);
        
        //create button for creating a book
        JButton buttonBorrow = new JButton("Borrow");
        buttonBorrow.setFont(tabFont);
        buttonBorrow.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                borrowBook();
            }
        });
        this.add(buttonBorrow);
        
        //create button for Edit Student
        JButton buttonReturn = new JButton("Return");
        buttonReturn.setFont(tabFont);
        buttonReturn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                returnBook();
            }
        });
        
        //horrible layout stuff
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelISBN)
                        .addComponent(labelStudent)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(fieldISBN)
                        .addComponent(fieldStudent)
                        .addComponent(output)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(buttonBorrow)
                                )
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(buttonReturn)
                                )
                        )
                )
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelISBN)
                        .addComponent(fieldISBN)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelStudent)
                        .addComponent(fieldStudent)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(output)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(buttonBorrow)
                        .addComponent(buttonReturn)
                )
        );
        
    }
    
}
