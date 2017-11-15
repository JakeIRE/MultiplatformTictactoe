/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import de.vogella.mysql.first.MySQLAccess;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import static java.awt.SystemColor.menuText;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author jakec
 */
public class Register extends JFrame implements ActionListener {
    private JPanel menu;
    private JLabel menuText;
    private JButton submit;
    private JTextField field[] = {new JTextField(1), new JTextField(1),new JTextField(1)};
    private MySQLAccess db = new MySQLAccess();
        public Register()  throws Exception{
            db.connectDataBase();
            this.setTitle("Register tictactoe account");
            this.setBounds(100,100,100,300);
            this.setPreferredSize(new Dimension(800,500));
            this.setLayout(new GridLayout(1,1));
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            
            
            
            menu = new JPanel();
            menu.setLayout(new GridLayout(5,2));

            menuText = new JLabel("Click on a square to start.", SwingConstants.CENTER);
            menuText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            
            menu = new JPanel();
            menu.setLayout(new GridLayout(3,1));

            menuText = new JLabel("Enter Account details", SwingConstants.CENTER);
            menuText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            menu.add(menuText);
            menu.add(field[0]);
            menu.add(field[1]);
            menu.add(field[2]);
            
            submit = new JButton("Submit");
            submit.addActionListener(this);
            menu.add(submit);
            this.add(menu);
            this.pack();
            this.setVisible(true);
        }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton source = (JButton) ae.getSource();    
        
        if(source.equals(submit)){
            try {
                verifyDetails();
            } catch (Exception ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void verifyDetails() throws Exception{
        String uname, p1, p2;
        uname = field[0].getText();
        p1 = field[1].getText();
        p2 = field[2].getText();
        if(p1.equals(p2))
            if(p1.length() > 0)
                if(uname.length() > 0)
                    if (uname.length() <= 10){
                        String ver = "";
                        ver = db.register(uname, p1);
                        menuText.setText(ver);
                        if (ver.length() == 0){
                            mainMenu(uname);
                        }
                    }
                    else
                        menuText.setText("username too long");
                else
                    menuText.setText("no username entered");
            else
                menuText.setText("No password entered");
        else
            menuText.setText("Passwords don't match");
        
    }
    
    public void mainMenu(String uname) throws Exception {
        dispose();
        new MainMenu(uname, db);
    }
}
