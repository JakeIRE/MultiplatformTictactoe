package colummullallycom.tictactoesoap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    EditText uNameIn;
    String uName,password;
    EditText passwordIn;
    Button sBtn,rBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        uNameIn = (EditText) findViewById(R.id.editText);
        passwordIn = (EditText) findViewById(R.id.editText2);
        sBtn = (Button) findViewById(R.id.button2);
        rBtn = (Button) findViewById(R.id.button);
    }
    protected void onStart() {
        super.onStart();

        sBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                uName = uNameIn.getText().toString();
                password=passwordIn.getText().toString();
                //TODO
                Intent myIntent=new Intent(Register.this,Menu.class);
                Bundle bundle= new Bundle();
                bundle.putString("Code",uName);
                myIntent.putExtras(bundle);
                startActivity(myIntent);

            }
        });

    }

}
