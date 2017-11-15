package colummullallycom.tictactoesoap;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ConnectSoap.Soap;

import static java.lang.Thread.sleep;

public class Game extends AppCompatActivity {
    String uName,jName;
    Thread t;
    Soap db = new Soap();
    boolean threadRun = true;
    boolean turn = false;
    private int gameState;
    private int[][] board;
    private int[] playerBadge;
    private int player;
    private String type;
    private int numSquares;
    private TextView curPlayerID = (TextView) findViewById(R.id.UpdateBox);
    private Button[] squares  = new Button[9];
    private int numSides;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        uName = (String) b.get("Code");
        jName = (String) b.get("Code1");

        squares[0] = (Button) findViewById(R.id.button13);
        squares[1] = (Button) findViewById(R.id.button11);
        squares[2] = (Button) findViewById(R.id.button12);
        squares[3] = (Button) findViewById(R.id.button3);
        squares[4] = (Button) findViewById(R.id.button24);
        squares[5] = (Button) findViewById(R.id.button22);
        squares[6] = (Button) findViewById(R.id.button10);
        squares[7] = (Button) findViewById(R.id.button5);
        squares[8] = (Button) findViewById(R.id.button7);

        t = new Thread(new Runnable() {
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
        try {
            threadRun = false;
        } catch (Exception ex) {
            System.out.println("nice");
        }
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
                squares[n].setBackgroundColor(playerBadge[player]);
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
                            curPlayerID.setText("It's player " +jName+ "'s move");
                        curPlayerID.setBackgroundColor(playerBadge[player]);
                        break;

                    case -1:
                        //JOptionPane.showMessageDialog(null, "Game over.\n It's a draw", "Drawn Game!", INFORMATION_MESSAGE);
                        db.setDraw(uName);
                        break;

                    case 0:
                        //JOptionPane.showMessageDialog(null, "Game over.\n Player 1 Wins!", "PLAYER 1 WINS!", INFORMATION_MESSAGE);
                        if(type.equals("X"))
                            db.setWin(uName);
                        else
                            db.setLoss(uName);

                        break;

                    case 1:
                        //JOptionPane.showMessageDialog(null, "Game over.\n Player 2 Wins!", "PLAYER 2 WINS!", INFORMATION_MESSAGE);
                        if(!type.equals("X"))
                            db.setWin(uName);
                        else
                            db.setLoss(uName);
                        break;

                    default:
                }
            } else {
                //JOptionPane.showMessageDialog(null, "That square is already taken.\n Please try again.", "Square taken!", ERROR_MESSAGE);
            }
        } else {
            //6JOptionPane.showMessageDialog(null, "Game Over.\n Please press reset to restart.", "Game Over", ERROR_MESSAGE);
        }
    }


    public int getPlayer() {
        player = player + 1;
        player = player % 2;
        return player;
    }

    public int getGameState(int p) {
        int state = -2;

        for(int i=0;i<numSides;i++) {
            if(board[i][0] == p && board[i][1] == p && board[i][2] == p) {
                state = p;
            }
            if(board[0][i] == p && board[1][i] == p && board[2][i] == p) {
                state = p;
            }
        }

        if(board[0][0] == p && board[1][1] == p && board[2][2] == p) {
            state = p;
        }
        if(board[0][2] == p && board[1][1] == p && board[2][0] == p) {
            state = p;
        }

        if(numSquares == 1) {
            state = -1;
        }
        return state;
    }

    public int[][] getBoard() {
        return board;
    }

    public void takeSquare(int x, int y, int p) {
        board[x][y] = player;
    }

    public boolean taken(int n) {
        int y = n%numSides;
        int x = (int) n/numSides;

        board = getBoard();
        if(board[x][y] == -1) {
            return false;
        } else {
            return true;
        }
    }


}
