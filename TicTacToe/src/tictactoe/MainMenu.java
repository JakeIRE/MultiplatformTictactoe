/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import de.vogella.mysql.first.MySQLAccess;
import java.awt.Dimension;
import java.awt.GridLayout;
import static java.awt.SystemColor.menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author jakec
 */
public class MainMenu extends JFrame implements ActionListener {
    
                     
    private javax.swing.JButton quit;
    private javax.swing.JButton invite;
    private javax.swing.JPanel menu;
    private String uname;
    private MySQLAccess db;
    private String type = "O";
    // End of variables declaration    
    
    public MainMenu(String uname, MySQLAccess db) throws Exception{
        this.uname = uname;
        this.db = db;
        db.setOnline(uname);
        this.setTitle("Main Menu");
        this.setBounds(100,100,100,300);
        this.setPreferredSize(new Dimension(800,500));
        this.setLayout(new GridLayout(1,1));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            try {
                db.setOffline(uname);
            } catch (Exception ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      });


        menu = new JPanel();
        menu.setLayout(new GridLayout(5,2));
        menu = new JPanel();
        menu.setLayout(new GridLayout(3,1));

        invite = new JButton("Invite a Player");
        invite.addActionListener(this);
        menu.add(invite);
        quit = new JButton("Quit game");
        quit.addActionListener(this);
        menu.add(quit);
        this.add(menu);
        this.pack();
        this.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton source = (JButton) ae.getSource();    
       
        if(source.equals(quit)){
            dispose();
        }
        if(source.equals(invite)){
            try {
                Object options[] = db.getOptions(uname);
                if(options.length > 1){
                String input = (String) JOptionPane.showInputDialog(null, "Players online",
        "Choose a player to play against", JOptionPane.QUESTION_MESSAGE, null, // Use
                                                                        // default
                                                                        // icon
        options, // Array of choices
        options[0]);
                    
                if(input != null && !input.equals("None"))
                    db.invite(uname, input);
                }
                else
                    JOptionPane.showMessageDialog(null, "Sorry nobody else is online");
            } catch (Exception ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean displayJoinerOption(String u) throws Exception{
        int selection = JOptionPane.showConfirmDialog(null, "Play against "+u+"?", ""+u+"?", JOptionPane.YES_NO_OPTION);
        if(selection == 0){
            db.beginGame(uname, u);
            type = "X";
            nextScreen(u);
            return false;
        }
        else {
            db.denyGame(uname);
            return true;
        }
    }

    public void nextScreen(String joiner) throws Exception {
        dispose();
        GameThread t = new GameThread(uname, joiner, type, db);
    }
    
}
