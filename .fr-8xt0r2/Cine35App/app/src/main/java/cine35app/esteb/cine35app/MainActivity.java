package cine35app.esteb.cine35app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login;
    private Button signup;
    private TextView lblUsername;
    private TextView lblPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set the Variables with the interface information
        username = (EditText) findViewById(R.id.txtUsername);
        password = (EditText) findViewById(R.id.txtPassword);
        login = (Button) findViewById(R.id.btnLogIn);
        signup = (Button) findViewById(R.id.btnSignUp);
        lblUsername = (TextView) findViewById(R.id.lblUsername);
        lblPassword = (TextView) findViewById(R.id.lblPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username.getText().toString(), password.getText().toString());
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pIntent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(pIntent);
            }
        });
    }
    //Procedure to logIn as Admi or client
    private void validate(String pUsername, String pPassword)
    {
        if(pUsername.equals("Admi") && pPassword.equals("1234"))
        {
            Intent pIntent = new Intent(MainActivity.this, AdmiActivity.class);
            startActivity(pIntent);
        }
        else if (pUsername.equals("jPerez") && pPassword.equals("4321"))
        {
            Intent pClientIntent = new Intent(MainActivity.this, ClientActivity.class);
            startActivity(pClientIntent);
        }
    }
}
