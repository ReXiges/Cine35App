package cine35app.esteb.cine35app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Response;

public class MovieEditor extends AppCompatActivity {
    private EditText nombre;
    private EditText anio;
    private EditText keywords;
    private EditText actores;
    private EditText directores;
    private EditText imagen;
    private EditText genero;
    private EditText sinopsis;
    private Button guardar;
    private Pelicula peli=null;
    private boolean edicion=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_editor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nombre= (EditText) findViewById(R.id.nombreP);
        anio=(EditText) findViewById(R.id.anioP);
        keywords=(EditText) findViewById(R.id.keywordsP);
        actores=(EditText) findViewById(R.id.actoresP);
        directores=(EditText) findViewById(R.id.directoresP);
        imagen=(EditText) findViewById(R.id.imagenP);
        genero=(EditText) findViewById(R.id.generoP);
        sinopsis=(EditText) findViewById(R.id.sinopsisP);
        guardar=(Button) findViewById(R.id.guardar);
        peli =(Pelicula) getIntent().getSerializableExtra("pelicula");
        Bundle extras= getIntent().getExtras();
        if (extras != null) {
            edicion=extras.getBoolean("edicion");
        }
        if(peli!=null){
            edicion=true;
            nombre.setText(peli.getNombre());
            anio.setText(String.valueOf(peli.getAnio()));
            keywords.setText(peli.getKeywords());
            actores.setText(peli.getActores());
            directores.setText(peli.getDirectores());
            imagen.setText(peli.getImagen());
            genero.setText(peli.getGenero());
            sinopsis.setText(peli.getSinopsis());
        }
        guardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(nombre.getText().toString().equals("") || directores.getText().toString().equals("")||genero.getText().toString().equals("")||anio.getText().toString().equals("")){
                    Toast.makeText(MovieEditor.this, "llene los datos", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(edicion){
                        setMovie(peli.getId(),false);
                    }
                    else{
                        setMovie(0,false);
                    }
                }
            }
        });



    }
    private void setMovie(int id, final boolean eliminar) {

        AsyncTask<Integer, Void, Void> asyncTask = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... movieIds) {
                Queryphp query;
                if (edicion){
                    if(eliminar){
                        query = new Queryphp("deleteMovie.php?id="+peli.getId());
                    }
                    else{
                        query = new Queryphp("updateMovie.php?nombre='"+nombre.getText().toString() +"'&anio="+anio.getText().toString()+"&keywords='"+keywords.getText().toString()+"'&actores='"+actores.getText().toString()+"'&directores='"+directores.getText().toString()+"'&imagen='"+imagen.getText().toString()+"'&genero='"+genero.getText().toString()+"'&sinopsis='"+sinopsis.getText().toString()+"'&id="+peli.getId());
                    }

                }
                else{
                    query = new Queryphp("insertMovie.php?nombre='"+nombre.getText().toString() +"'&anio="+anio.getText().toString()+"&keywords='"+keywords.getText().toString()+"'&actores='"+actores.getText().toString()+"'&directores='"+directores.getText().toString()+"'&imagen='"+imagen.getText().toString()+"'&genero='"+genero.getText().toString()+"'&sinopsis='"+sinopsis.getText().toString()+"'");
                }
                try {
                    Response response = query.returnRequest();



                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

        };

        asyncTask.execute(id);
    }

}
