package cine35app.esteb.cine35app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdmiActivity extends AppCompatActivity {

    private EditText search;
    private Button searchBtn;
    private Button signout;
    private Button insertMovie;
    private Button userManagment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admi);
        search = (EditText) findViewById(R.id.txtSearch);
        searchBtn = (Button) findViewById(R.id.btnSearch);
        signout = (Button) findViewById(R.id.btnSignOut);
        insertMovie = (Button) findViewById(R.id.btnInsertMovie);
        userManagment = (Button) findViewById(R.id.btnUserManage);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pIntent = new Intent(AdmiActivity.this, MainActivity.class);
                startActivity(pIntent);
            }
        });

        insertMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pIntent = new Intent(AdmiActivity.this, InsertMovieActivity.class);
                startActivity(pIntent);
            }
        });

        userManagment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pIntent = new Intent(AdmiActivity.this, UserManagmentActivity.class);
                startActivity(pIntent);
            }
        });
    }
}
