package cine35app.esteb.cine35app;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login;
    private Button signup;
    private TextView lblUsername;
    private TextView lblPassword;
    private ArrayList<Cliente> listaUsuarios = new ArrayList<Cliente>();
    private ArrayList<Administrador> listaAdmins = new ArrayList<Administrador>();
    private List<Cliente> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
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
        Queryphp query = new Queryphp("login.php?nombreUsuario='" + pUsername+"'&password='"+pPassword+"'");
        try{
            Response response= query.returnRequest();
            JSONArray array = new JSONArray(response.body().string());
            if(array.length()==0){
                Context context = getApplicationContext();
                CharSequence text = "Usuario invalido";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else{
                JSONObject object = array.getJSONObject(0);
                Cliente cliente =  new Cliente(object.getString("nombre"),object.getString("nacionalidad"),object.getString("password"),object.getString("correo"),object.getString("nombreUsuario"));
                if( object.getString("tipo").equals("1") && pPassword.equals(cliente.getPassword()))
                {
                    Intent pIntent = new Intent(MainActivity.this, AdmiActivity.class);
                    startActivity(pIntent);
                }
                else if (pPassword.equals(cliente.getPassword()))
                {
                    Intent pClientIntent = new Intent(MainActivity.this, ClientActivity.class);
                    startActivity(pClientIntent);
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
