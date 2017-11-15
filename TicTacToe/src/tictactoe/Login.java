 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import de.vogella.mysql.first.MySQLAccess;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author jakec
 */
public class Login extends JFrame implements ActionListener {
    private JPanel menu;
    private JLabel menuText;
    private JButton submit;
    private JTextField field[] = {new JTextField(10), new JTextField(10)};
    private MySQLAccess db = new MySQLAccess();
  
    public Login() throws Exception{
        setDefaultCloseOperation(Welcome.EXIT_ON_CLOSE);
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

        menuText = new JLabel("Enter Login details", SwingConstants.CENTER);
        menuText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        menu.add(menuText);
        menu.add(field[0]);
        menu.add(field[1]);

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
                verifyLogin();
            } catch (Exception ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void verifyLogin() throws Exception{
        String uname = field[0].getText();
        String pass = field[1].getText();
        String output = db.loginVerify(uname, pass);
        if(output.length() == 0)
            continueMain(uname);
        else
            menuText.setText(output);
                
    }
    
    public void continueMain(String u) throws Exception{
        dispose();
        MenuThread m = new MenuThread(u, db);
        m.start();
    }
    
    
}
