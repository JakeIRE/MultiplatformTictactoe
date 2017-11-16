package colummullallycom.tictactoesoap;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
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
    private TextView curPlayerID;
    int x;
    private Button[] squares  = new Button[9];
    private int numSides;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        curPlayerID=(TextView) findViewById(R.id.UpdateBox);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        gameState = -2;
        player = 1;
        numSides = 3;
        numSquares = 9;
        uName = (String) b.get("Code");
        jName = (String) b.get("Code1");
        type=(String) b.get("Code3");
        if(type.equals("X"))
            turn = true;
        else
            turn = false;

        playerBadge = new int[2];
        board = new int[numSides][numSides];
        playerBadge[0] =  Color.RED ;
        playerBadge[1] =  Color.GREEN ;
        for(int i=0;i<numSides;i++) {
            for(int j=0;j<numSides;j++) {
                board[i][j] = -1;
                int pos = ((i*numSides) + j);
            }
        }




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
                                    x =Integer.parseInt(move[1]);
                                    Handler handler = new Handler(Looper.getMainLooper());
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            play(x);
                                            squares[x].setBackgroundColor(playerBadge[player]);
                                        }
                                    });
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
    @Override
    protected void onStart(){
        super.onStart();
        squares[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(turn){
                    play(0);
                   db.setMove("MOVE,0",uName);}
               // squares[0].setBackgroundColor(playerBadge[player]);
            }
        });
        squares[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(turn){
                play(1);
                db.setMove("MOVE,1",uName);}
                //squares[1].setBackgroundColor(playerBadge[player]);
            }
        });
        squares[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(turn){
                play(2);
                db.setMove("MOVE,2",uName);}
               // squares[2].setBackgroundColor(playerBadge[player]);
            }
        });
        squares[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(turn){
                play(3);
                db.setMove("MOVE,3",uName);}
               // squares[3].setBackgroundColor(playerBadge[player]);
            }
        });squares[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(turn){
                play(4);
                db.setMove("MOVE,4",uName);}
               // squares[4].setBackgroundColor(playerBadge[player]);
            }
        });squares[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(turn){
                play(5);
                db.setMove("MOVE,5",uName);}
               // squares[5].setBackgroundColor(playerBadge[player]);
            }
        });squares[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                            if(turn){
                play(6);
                db.setMove("MOVE,6",uName);}
               // squares[6].setBackgroundColor(playerBadge[player]);
            }
        });squares[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(turn){
                play(7);
                db.setMove("MOVE,7",uName);}
              //  squares[7].setBackgroundColor(playerBadge[player]);
            }
        });squares[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(turn){
                play(8);
                db.setMove("MOVE,8",uName);}
                //squares[8].setBackgroundColor(playerBadge[player]);
            }
        });




    }
    private void quit() {
        try {
            threadRun = false;
        } catch (Exception ex) {
            System.out.println("nice");
        }
    }

    private void play(int n){
        if(turn)
            turn = false;
        else
            turn = true;

        if(gameState == -2) {
            if(!taken(n) && numSquares > 0) {

                System.out.println("works?");
                squares[n].setBackgroundColor(playerBadge[getPlayer()]);

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
        board[x][y] = -1;
    }

    public boolean taken(int n) {
        int y = n%numSides;
        int x = (int) n/numSides;

        board = getBoard();
        if(board[x][y] == -1) {
            curPlayerID.setText("Its there");
            return false;
        } else {
            curPlayerID.setText("Its not there");
            return true;
        }
    }


}
