/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import de.vogella.mysql.first.MySQLAccess;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jakec
 */
public class GameThread extends Thread{
    private boolean threadRun = false;
    private String type;
    private String joiner;
    private MySQLAccess db;
    private String uname;
    private TicTacToeGame m;

    public GameThread(String uname, String joiner, String type, MySQLAccess db) throws Exception{
        this.db = db;
        this.uname = uname;
        this.joiner = joiner;
        this.type = type;
        threadRun = true;
        m = new TicTacToeGame(uname, joiner, type, db);
    }

    public void run(){
        while(threadRun){
            String lastTurn = "";
            String temp = null;
            try {
                temp = db.getTurn(joiner);
            } catch (Exception ex) {
                Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(!temp.equals(lastTurn)){
                lastTurn = temp;
                String[] move = lastTurn.split(" ");
                if(move.length > 1){
                    m.play(Integer.parseInt(move[1]));
                }
                else if(move[0].equals("RESET")){
                    
                }
            }
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
