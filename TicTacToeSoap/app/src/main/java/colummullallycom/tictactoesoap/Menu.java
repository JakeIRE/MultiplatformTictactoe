package colummullallycom.tictactoesoap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ConnectSoap.Soap;

import static java.lang.Thread.sleep;

public class Menu extends AppCompatActivity {
    String uName;
    Thread t;
    Button quitBtn,inviteBtn,leaderboardBtn;
    Soap db = new Soap();
    boolean threadRun = true;
    boolean displayJoiner = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        uName = (String) b.get("Code");
        t =  new Thread(new Runnable() {
            @Override
            public void run() {
                while(threadRun){
                    try {
                        String gameStart = db.checkGameStart(uName);
                        if(!gameStart.equals("")){
                            nextScreen(gameStart);
                            threadRun = false;
                        }
                        String joiner = db.getJoiner(uName);
                        if(!joiner.equals("") || displayJoiner){
                            displayJoiner = true;
                            displayJoinerOption(joiner);
                        }
                        sleep(100);
                    } catch (Exception ex) {
                        System.out.println("You're dumb");
                    }
                }
            }
        });
        t.start();
        inviteBtn = (Button) findViewById(R.id.button9);
        leaderboardBtn=(Button)findViewById(R.id.button4);
    }

    private void nextScreen(String gameStart) {
        threadRun = false;
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void displayJoinerOption(String joiner) {
    }

    protected void onStart() {
        super.onStart();
        leaderboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(Menu.this,LeaderBoard.class);
                Bundle bundle= new Bundle();
                bundle.putString("Code",uName);
                myIntent.putExtras(bundle);
                startActivity(myIntent);
            }
        });
        inviteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO
                //checks and stuff
                /*
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
                }*/
                Intent myIntent=new Intent(Menu.this,Invite.class);
                Bundle bundle= new Bundle();
                bundle.putString("Code",uName);
                bundle.putString("Code1",uName);
                myIntent.putExtras(bundle);
                startActivity(myIntent);

            }
        });

    }

    public void test(){

    }
}
