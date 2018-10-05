package ass2;

import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;

class WindowLogin extends JFrame {
    
    private final Library LIBRARY;
    
    private void attemptLogin(String username, char[] password, JLabel output) {
        //look for a User with this username
        User u = LIBRARY.findUser(username);
        
        //if a user is found and the password is correct
        if (u != null) {
            
            //and the password is correct
            if (u.verifyUser(password)) {
                switch (u.getUserType()) {
                    //user is a student
                    case 1:
                        new WindowStudent((Student) u, LIBRARY).setVisible(true);
                        this.dispose();
                        break;

                    //user is a librarian
                    case 2:
                        new WindowLibrarian((Librarian) u, LIBRARY).setVisible(true);
                        this.dispose();
                        break;

                    //user is an admin
                    case 3:
                        new WindowAdmin((Admin) u, LIBRARY).setVisible(true);
                        this.dispose();
                        break;
                }
            }
            else {
                output.setText("Invalid username/password combination");
            }
            
        }
        //user not found or password is incorrect
        else {
            output.setText("Username or password is incorrect");
        }
    }

    public WindowLogin(Library lib) {
        //create the frame
        super("Library system");
        this.setBounds(0,0,640, 480);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //set the library
        LIBRARY = lib;
        
        //create the login panel
        JPanel loginPanel = new JPanel();
        this.add(loginPanel);
        
        //create text font on the panel
        Font textFont = new Font("Tahoma", 0, 24);
        
        //create output text field
        JLabel labelOutput = new JLabel("");
        labelOutput.setFont(textFont);
        loginPanel.add(labelOutput);
        
        //create text label for username
        JLabel labelUser = new JLabel("Username");
        labelUser.setFont(textFont);
        loginPanel.add(labelUser);
        
        //create text label for password
        JLabel labelPass = new JLabel("Password");
        labelPass.setFont(textFont);
        loginPanel.add(labelPass);
        
        //create text field
        JTextField fieldUser = new JTextField();
        fieldUser.setFont(textFont);
        loginPanel.add(fieldUser);
        
        //create password field
        JPasswordField fieldPass = new JPasswordField();
        fieldPass.setFont(textFont);
        fieldPass.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                //if pressed key is Enter
                if (evt.getKeyChar()==KeyEvent.VK_ENTER) {
                    attemptLogin(fieldUser.getText(),fieldPass.getPassword(), labelOutput);
                }
            }
        });
        loginPanel.add(fieldPass);
        
        
        //create login button
        JButton buttonLogin = new JButton("Login");
        buttonLogin.setFont(textFont);
        buttonLogin.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                attemptLogin(fieldUser.getText(),fieldPass.getPassword(), labelOutput);
            }
        });
        loginPanel.add(buttonLogin);
        
        
        //set up the layout
        
        GroupLayout layout = new GroupLayout(loginPanel);
        loginPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(labelUser)
                        .addComponent(labelPass)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(fieldUser)
                        .addComponent(fieldPass)
                        .addComponent(buttonLogin)
                        .addComponent(labelOutput)
                )
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelUser)
                        .addComponent(fieldUser)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelPass)
                        .addComponent(fieldPass)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(buttonLogin)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelOutput)
                )
        );
    }
    
}
