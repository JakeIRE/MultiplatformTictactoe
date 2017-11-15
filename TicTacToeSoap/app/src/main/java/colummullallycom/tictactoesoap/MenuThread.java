package colummullallycom.tictactoesoap;

import android.os.AsyncTask;

import ConnectSoap.Soap;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class MenuThread extends AsyncTask{
    private boolean threadRun = false;
    private String joiner = "";
    private String gameStart = "";
    private Soap db;
    private String uname;

    public MenuThread(String uname){
        this.uname = uname;
    }
    public void setBool(boolean t) {
        threadRun = false;
    }

    @Override
    protected Object doInBackground() {
            try {
                gameStart = db.checkGameStart(uname);
                joiner = db.getJoiner(uname);
                if(!gameStart.equals("")){

                    threadRun = false;
                }

                if(!joiner.equals("")){

                }
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(MenuThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(MenuThread.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}