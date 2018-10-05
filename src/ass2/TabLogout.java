package ass2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class TabLogout extends JPanel {
    
    public TabLogout(JFrame frame, Library lib) {
        super();
        
        //create text font on the tab
        Font tabFont = new Font("Tahoma", 0, 20);
        
        //label for some text
        JLabel label = new JLabel("Click the button to log out");
        label.setFont(tabFont);
        this.add(label);
        
        //create search button
        JButton buttonQuit = new JButton("Quit");
        buttonQuit.setFont(tabFont);
        buttonQuit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new WindowLogin(lib).setVisible(true);
                frame.dispose();
            }
        });
        this.add(buttonQuit);
        
        
        
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(label)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(buttonQuit)
                )
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label)
                        .addComponent(buttonQuit)
                )
        );
        
    }
    
}
