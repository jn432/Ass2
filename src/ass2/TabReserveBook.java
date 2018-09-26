package ass2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class TabReserveBook extends JPanel {
    
    private Student student;
    private JTextField fieldISBN;
    private JTextArea output;
    private Book b;
    
    
    public void setDetails(Book b) {
        this.b = b;
        b.printDetails(output);
        fieldISBN.setText(Integer.toString(b.getISBN()));
    }
    
    private void reserveBook() {
        try {
            int ISBN = Integer.parseInt(fieldISBN.getText());
            Book b = Ass2.LIB.findBook(ISBN);
            if (b.getReservedStatus().equals("Reserved")) {
                output.setText("Book has been previously reserved. You will be added to a queue.\n ");
            }
            else {
                output.setText("Book has been reserved\n");
            }
            b.reserveBook(student);
            b.printDetails(output);
        }
        catch (NumberFormatException e) {
            output.setText("You must enter an int to reserve a book\n");
        }
        catch (NullPointerException e) {
            output.setText("Book not found\n");
        }
            
    }
    
    
    
    public TabReserveBook(Student student) {
        super();
        this.student = student;
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

