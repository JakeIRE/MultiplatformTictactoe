package colummullallycom.tictactoesoap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import ConnectSoap.Soap;

public class LeaderBoard extends AppCompatActivity {
    String uName;
    ListView leaderboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        uName = (String) b.get("Code");
        leaderboard=(ListView)findViewById(R.id.Leaderboard);
<<<<<<< HEAD
        Soap db= new Soap();
        String[] values=db.getLeaderboard();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        leaderboard.setAdapter(adapter);
=======
>>>>>>> parent of 83f421c... it cant hurt too much -jake craven 17:57 15/11/2017
    }
}
