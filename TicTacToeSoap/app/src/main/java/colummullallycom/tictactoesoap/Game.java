package colummullallycom.tictactoesoap;

import android.content.Intent;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ConnectSoap.Soap;

import static java.lang.Thread.sleep;

public class Game extends AppCompatActivity {
    String uName,jName;
    Thread t;
    Soap db = new Soap();
    boolean threadRun = true;
    boolean turn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        uName = (String) b.get("Code");
        jName = (String) b.get("Code1");
       /* t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(threadRun){
                    String lastTurn = null;
                    String temp = null;
                    try {
                        temp = db.getTurn(jName);
                        if(!temp.equals(lastTurn)){
                            lastTurn = temp;
                            String[] move = lastTurn.split(",");
                            if(move[0].equals("MOVE")){
                                if(move[1].matches("[0-9]")){
                                    disableReset();
                                    play(Integer.parseInt(move[1]));
                                    db.resetGame(jName);
                                }

                            }
                            else if(move[0].equals("QUIT")){
                                quit();
                            }
                        }
                        sleep(1000);
                    } catch (Exception ex) {
                        System.out.println("These aren't the bugs youre looking for");
                    }
                }
            }
        });
        t.start();

    }

    private void quit() {
    }

    private void disableReset() {
    }

    private void play(int n){
        if(turn)
            turn = false;
        else
            turn = true;


        if(gameState == -2) {
            if(!taken(n) && numSquares > 0) {
                squares[n].setBackground(playerBadge[player]);
                int y = n%numSides;
                int x = (int) n/numSides;
                takeSquare(x, y, player);
                gameState = getGameState(player);
                switch(gameState) {
                    case -2:
                        player = getPlayer();
                        numSquares = numSquares - 1;
                        if(turn)
                            curPlayerID.setText("It's your move");
                        else
                            curPlayerID.setText("It's player " +joiner+ "'s move");
                        curPlayerID.setBackground(playerBadge[player]);
                        break;

                    case -1:
                        JOptionPane.showMessageDialog(null, "Game over.\n It's a draw", "Drawn Game!", INFORMATION_MESSAGE);
                        db.setDraw(uname);
                        break;

                    case 0:
                        JOptionPane.showMessageDialog(null, "Game over.\n Player 1 Wins!", "PLAYER 1 WINS!", INFORMATION_MESSAGE);
                        if(type.equals("X"))
                            db.setWin(uname);
                        else
                            db.setLoss(uname);

                        break;

                    case 1:
                        JOptionPane.showMessageDialog(null, "Game over.\n Player 2 Wins!", "PLAYER 2 WINS!", INFORMATION_MESSAGE);
                        if(!type.equals("X"))
                            db.setWin(uname);
                        else
                            db.setLoss(uname);
                        break;

                    default:
                }
            } else {
                JOptionPane.showMessageDialog(null, "That square is already taken.\n Please try again.", "Square taken!", ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Game Over.\n Please press reset to restart.", "Game Over", ERROR_MESSAGE);
        }*/
    }

}
