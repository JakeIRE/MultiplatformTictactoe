/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author jakec
 */
public class Welcome extends JFrame implements ActionListener {
    // Variables declaration - do not modify                     
    private javax.swing.JButton register;
    private javax.swing.JButton login;
    private javax.swing.JPanel menu;
    // End of variables declaration      
    
    public Welcome(){
        

        this.setTitle("Register tictactoe account");
        this.setBounds(100,100,100,300);
        this.setPreferredSize(new Dimension(800,500));
        this.setLayout(new GridLayout(1,1));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        menu = new JPanel();
        menu.setLayout(new GridLayout(5,2));
        menu = new JPanel();
        menu.setLayout(new GridLayout(3,1));

        login = new JButton("Login");
        login.addActionListener(this);
        menu.add(login);
        register = new JButton("Register");
        register.addActionListener(this);
        menu.add(register);
        this.add(menu);
        this.pack();
        this.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton source = (JButton) ae.getSource();    
       
        if(source.equals(register)){
            try {
                dispose();
                Register r = new Register();
            } catch (Exception ex) {
                Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(source.equals(login)){
            dispose();
            try {
                Login l = new Login();
            } catch (Exception ex) {
                Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
