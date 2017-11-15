package colummullallycom.tictactoesoap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ConnectSoap.Soap;

public class Login extends AppCompatActivity {
    EditText uNameIn;
    String uName,password;
    EditText passwordIn;
    Button sBtn,rBtn;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        uNameIn = (EditText) findViewById(R.id.editText);
        uNameIn.setText("");
        passwordIn = (EditText) findViewById(R.id.editText2);
        sBtn = (Button) findViewById(R.id.button2);
        rBtn = (Button) findViewById(R.id.button);
        t=(TextView) findViewById(R.id.textView);

    }
    protected void onStart() {
        super.onStart();

        sBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                uName = uNameIn.getText().toString();
                password=passwordIn.getText().toString();
                //TODO
                //checks and stuff
                Soap db =new Soap();
                String output = null;
                output = db.loginVerify(uName, password);
                t.setText(output);
               if(output.equals("")){
                Intent myIntent=new Intent(Login.this,Menu.class);
                Bundle bundle= new Bundle();
                bundle.putString("Code",uName);
                myIntent.putExtras(bundle);
                startActivity(myIntent);}
                


            }
        });

    }

}
