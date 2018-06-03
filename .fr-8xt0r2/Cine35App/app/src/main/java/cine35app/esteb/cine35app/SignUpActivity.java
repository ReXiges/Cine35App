package cine35app.esteb.cine35app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {
    private EditText name;
    private EditText lastname;
    private EditText email;
    private EditText address;
    private EditText username;
    private EditText password;
    private EditText conPassword;
    private  Button signUp;
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = (EditText) findViewById(R.id.txtName);
        lastname = (EditText) findViewById(R.id.txtLastname);
        email = (EditText) findViewById(R.id.txtEmail);
        address = (EditText) findViewById(R.id.txtAddress);
        username = (EditText) findViewById(R.id.txtSignUpUsername);
        password = (EditText) findViewById(R.id.txtSignUpPassword);
        conPassword = (EditText) findViewById(R.id.txtConPassword);
        signUp = (Button) findViewById(R.id.btnCreateUser);
        info = (TextView) findViewById(R.id.lblInfo);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(name.getText().toString(), lastname.getText().toString(), email.getText().toString(), address.getText().toString(),
                        username.getText().toString(), password.getText().toString(), conPassword.getText().toString());
            }
        });
    }
    private void validate(String pName, String pLastname, String pEmail, String pAddress, String pUsername, String pPassword, String pConPassword)
    {
        if(pName.equals("") || pLastname.equals("") || pEmail.equals("") || pAddress.equals("") || pUsername.equals("") || pPassword.equals("") || pConPassword.equals(""))
        {
            info.setText("Information is not valid, please check!");
        }
        else if(!(pPassword.equals(pConPassword) ))
        {
            info.setText("Passwords must be equal!");
        }
        else
        {
            Intent pIntent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(pIntent);
        }
    }
}
