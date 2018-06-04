package cine35app.esteb.cine35app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

public class ClientActivity extends AppCompatActivity {
    private EditText searchBar;
    private Button searchButton;
    private Button signOut;
    private Button recomendations;
    private Button favorites;
    private List<Pelicula> movies;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayout;
    private MoviesAdapter adapter;
    private String user="";
    private String searchTerm="";
    boolean favoritos=false;
    private Button agregar;
    private boolean isAdmin;
    private Button recommend;
    private Button gestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        Bundle extras = getIntent().getExtras();

        searchButton = (Button) findViewById(R.id.search);
        searchBar=(EditText) findViewById(R.id.searchBar);
        favorites=(Button) findViewById(R.id.favoritos);
        agregar= (Button) findViewById(R.id.agregarP);
        recommend=(Button) findViewById(R.id.recommend);
        gestion= (Button) findViewById(R.id.gestion);

        if (extras != null) {
            user = extras.getString("usuario");
            isAdmin=extras.getBoolean("admin");
            if(isAdmin){
                favorites.setVisibility(View.GONE);
                recommend.setVisibility(View.GONE);
            }
            else{
                agregar.setVisibility(View.GONE);
                gestion.setVisibility(View.GONE);
            }

        }

        final String finalUser = user;
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchTerm=searchBar.getText().toString();
                movies = new ArrayList<>();
                favoritos=false;
                getMoviesFromDB(0);
                adapter = new MoviesAdapter(ClientActivity.this, movies, finalUser,isAdmin);
                recyclerView.setAdapter(adapter);
            }
        });
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pIntentD = new Intent(ClientActivity.this, MovieEditor.class);
                pIntentD.putExtra("edicion",false);
                ClientActivity.this.startActivity(pIntentD);
            }
        });
        favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchTerm="";
                movies = new ArrayList<>();
                favoritos=true;
                getMoviesFromDB(0);
                adapter = new MoviesAdapter(ClientActivity.this, movies, finalUser,isAdmin);
                recyclerView.setAdapter(adapter);

            }
        });
        gestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pIntentD = new Intent(ClientActivity.this, BaneoActivity.class);
                ClientActivity.this.startActivity(pIntentD);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.coments);
        movies = new ArrayList<>();
        getMoviesFromDB(0);

        gridLayout = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayout);

        adapter = new MoviesAdapter(this, movies,user,isAdmin);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (gridLayout.findLastCompletelyVisibleItemPosition() == movies.size() - 1) {
                    getMoviesFromDB(movies.get(movies.size() - 1).getId());
                }

            }
        });
    }
    private void getMoviesFromDB(int id) {

        AsyncTask<Integer, Void, Void> asyncTask = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... movieIds) {
                Queryphp query;
                if (favoritos){
                    query = new Queryphp("recuperarPelisF.php?id=" + movieIds[0]+"&user="+user);
                }
                else{
                    query = new Queryphp("recuperarPelis.php?id=" + movieIds[0]+"&search="+searchTerm);
                }
                try {
                    Response response = query.returnRequest();
                    String t=response.body().string();
                    JSONArray array = new JSONArray(t);
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);

                        Pelicula movie = new Pelicula(object.getInt("idMovie"),object.getString("nombre"),object.getInt("anio"),object.getString("keywords"),object.getString("actores"),object.getString("directores"),object.getString("imagen"),object.getString("genero"),object.getString("sinopsis"));
                        ClientActivity.this.movies.add(movie);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.notifyDataSetChanged();
            }
        };

        asyncTask.execute(id);
    }
}