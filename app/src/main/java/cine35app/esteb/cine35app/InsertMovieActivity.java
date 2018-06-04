package cine35app.esteb.cine35app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Response;

public class InsertMovieActivity extends AppCompatActivity {
    private EditText movieName;
    private EditText director;
    private EditText gender;
    private EditText year;
    private EditText actores;
    private EditText image;
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
        image = (EditText) findViewById(R.id.imagenURL);
        saveMovie = (Button) findViewById(R.id.btnSave);
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
         else {
        Queryphp query = new Queryphp("searchMovie.php?nombre='" + movieName + "'");
        try {
            Response response = query.returnRequest();
            if (!response.body().string().equals("[]")) {
                info.setText("Movie already in System");
            } else {
                query.setQuery("insertMovie.php?&nombre='" + movieName + "'&anio='" + year + "'$keywords='" + "'a,a,a''" + "'&actores='" + actores + "'&directores='" + director + "'&imagen='" + "aass" + "'&genero='" + gender + "'");
                response = query.returnRequest();
                Context context = getApplicationContext();
                CharSequence text = "Movie registered";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Intent pIntent = new Intent(InsertMovieActivity.this, AdmiActivity.class);
                startActivity(pIntent);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

    private void cancelFunction()
    {
        Intent pIntent = new Intent(InsertMovieActivity.this, AdmiActivity.class);
    }
}
