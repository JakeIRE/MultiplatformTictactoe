package colummullallycom.tictactoesoap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class Invite extends AppCompatActivity {
    String uName;
    ListView invitels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        uName = (String) b.get("Code");
        invitels=(ListView)findViewById(R.id.iList);
    }
}
