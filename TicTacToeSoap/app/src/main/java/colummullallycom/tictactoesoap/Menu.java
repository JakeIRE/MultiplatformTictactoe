package colummullallycom.tictactoesoap;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ConnectSoap.Soap;

import static java.lang.Thread.sleep;

public class Menu extends AppCompatActivity {
    String uName;
    Thread t;
    String joiner, joiner2;
    Button quitBtn,inviteBtn,leaderboardBtn, accept, deny, statBtn;
    TextView texty;
    String type = "O";
    Soap db = new Soap();
    boolean threadRun = true;
    boolean displayJoiner = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        texty = (TextView) findViewById(R.id.textView3);
        statBtn = (Button) findViewById(R.id.button8);
        accept = (Button) findViewById(R.id.button14);
        deny = (Button) findViewById(R.id.denybut);
        uName = (String) b.get("Code");
        texty.setVisibility(View.INVISIBLE);
        accept.setVisibility(View.INVISIBLE);
        deny.setVisibility(View.INVISIBLE);
        db.denyGame(uName);
        inviteBtn = (Button) findViewById(R.id.button9);
        t =  new Thread(new Runnable() {
            @Override
            public void run() {
                while(threadRun){
                    try {
                        sleep(1000);
                        String gameStart = db.checkGameStart(uName);
                        if(!gameStart.equals("")){
                            threadRun = false;
                        }
                        joiner = db.getJoiner(uName);
                        if(!joiner.equals("") && !displayJoiner){
                            threadRun = false;

                        }
                    } catch (Exception ex) {
                        System.out.println(ex.toString());
                    }
                }

                startActivityFromMainThread();

            }
        });
        t.start();
        leaderboardBtn=(Button)findViewById(R.id.button4);
    }

    private void nextScreen(String gameStart) {
        threadRun = false;
        db.denyGame(uName);
    }
    @Override
    protected void onResume(){
        super.onResume();
        t.start();
    }

    public void startActivityFromMainThread(){

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Intent myIntent=new Intent(Menu.this,inviteQuestion.class);
                Bundle bundle= new Bundle();
                bundle.putString("Code",uName);
                bundle.putString("Code1",joiner);
                myIntent.putExtras(bundle);
                startActivity(myIntent);
            }
        });
    }
    private void displayJoinerOption(String joiner, Context baseContext)  {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getBaseContext());

        dialog.setTitle( "Invite" )
                .setMessage("Play with "+joiner+"?")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                    }
                }).show();
    }
    /*
    protected void onDestroy(){
        super.onDestroy();
        threadRun = false;
    }*/
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

        deny.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                texty.setVisibility(View.INVISIBLE);
                accept.setVisibility(View.INVISIBLE);
                deny.setVisibility(View.INVISIBLE);
                inviteBtn.setVisibility(View.VISIBLE);
                leaderboardBtn.setVisibility(View.VISIBLE);
                quitBtn.setVisibility(View.VISIBLE);
                statBtn.setVisibility(View.VISIBLE);
            }
        });


        deny.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                threadRun = false;
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                db.checkGameStart(joiner2);
            }
        });
    }
    public void test(String joiner){
        displayJoinerOption(joiner,getBaseContext());
    }



}
/*
public class LongOperation extends AsyncTask<String, Void, String> {


    @Override
    protected String doInBackground() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);

        dialog.setTitle( "Invite" )
                .setMessage("Play with "+joiner+"?")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                    }
                }).show();
        return "Executed";
    }

    @Override
    protected void onPostExecute(String result) {
        //txt.setText("Executed"); // txt.setText(result);
        // might want to change "executed" for the returned string passed
        // into onPostExecute() but that is upto you
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onProgressUpdate(Void... values) {}
}*/

