package ass2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class TabStudentReserveBook extends JPanel {
    
    private final Student STUDENT;
    private final Library LIBRARY;
    
    private JTextField fieldISBN;
    private JTextArea output;
    
    //display information on the output, and set the field to the found book
    public void setDetails(Book b) {
        b.printDetails(output);
        fieldISBN.setText(Integer.toString(b.getISBN()));
    }
    
    //reserves the book if available, add to queue if borrowed or reserved
    private boolean reserveBook() {
        try {
            int ISBN = Integer.parseInt(fieldISBN.getText());
            Book b = LIBRARY.findBook(ISBN);
            if (!STUDENT.reserveBook(b)) {
                output.setText("You have already reserved this book");
                return false;
            }
            if (b.getReservedStatus().equals("Reserved")) {
                output.setText("Book is currently reserved. You will be added to a queue.\n ");
            }
            else if (b.getReservedStatus().equals("Borrowed")) {
                output.setText("Book is currently borrowed. You will be added to a queue.\n ");
            }
            else {
                output.setText("Book has been reserved\n");
            }
            b.printDetails(output);
            return true;
        }
        catch (NumberFormatException e) {
            output.setText("You must enter an int to reserve a book\n");
        }
        catch (NullPointerException e) {
            output.setText("Book not found\n");
        }
        return false;
    }
    
    
    
    public TabStudentReserveBook(Student student, Library lib) {
        super();
        this.STUDENT = student;
        LIBRARY = lib;
        //create text font on the tab
        Font tabFont = new Font("Tahoma", 0, 20);
        
        //label for ISBN
        JLabel labelISBN = new JLabel("ISBN: ");
        labelISBN.setFont(tabFont);
        this.add(labelISBN);
        
        //text field for ISBN
        fieldISBN = new JTextField();
        fieldISBN.setFont(tabFont);
        this.add(fieldISBN);
        
        //create some output fields
        output = new JTextArea();
        output.setEditable(false);
        this.add(output);
        
        //create search button
        JButton buttonReserve = new JButton("Reserve book");
        buttonReserve.setFont(tabFont);
        buttonReserve.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                reserveBook();
            }
        });
        this.add(buttonReserve);
        
        
        
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelISBN)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(fieldISBN)
                        .addComponent(buttonReserve)
                        .addComponent(output)
                )
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelISBN)
                        .addComponent(fieldISBN)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonReserve)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(output)
                )
        );
        
    }
    
}

