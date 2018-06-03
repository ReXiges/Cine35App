package cine35app.esteb.cine35app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InsertMovieActivity extends AppCompatActivity {
    private EditText movieName;
    private EditText director;
    private EditText gender;
    private EditText year;
    private EditText actores;
    private Button image;
    private Button saveMovie;
    private Button cancel;
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_movie);
        movieName = (EditText) findViewById(R.id.txtMovieName);
        director = (EditText) findViewById(R.id.txtDirector);
        gender = (EditText) findViewById(R.id.txtGender);
        year = (EditText) findViewById(R.id.txtYear);
        actores = (EditText) findViewById(R.id.txtActors);
        image = (Button) findViewById(R.id.btnImage);
        saveMovie = (Button) findViewById(R.id.btnSave);
        cancel = (Button) findViewById(R.id.btnMovieCancel);
        info = (TextView) findViewById(R.id.lblMovieInfo);

        saveMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(movieName.getText().toString(), director.getText().toString(), gender.getText().toString(), year.getText().toString(), actores.getText().toString());
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelFunction();
            }
        });
    }

    private void validate(String pName, String pDirector, String pGender, String pYear, String pActores)
    {
        if(pName.equals("") || pDirector.equals("") || pGender.equals("") || pYear.equals("") || pActores.equals(""))
        {
            info.setText("Information is not valid, please check!");
        }
        else
        {
            Intent pIntent = new Intent(InsertMovieActivity.this, AdmiActivity.class);
            startActivity(pIntent);
        }
    }

    private void cancelFunction()
    {
        Intent pIntent = new Intent(InsertMovieActivity.this, AdmiActivity.class);
    }
}
