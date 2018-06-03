package cine35app.esteb.cine35app;

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
    private EditText search;
    private Button searchButton;
    private Button signOut;
    private Button recomendations;
    private Button favorites;
    private List<Pelicula> movies;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayout;
    private MoviesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        signOut = (Button) findViewById(R.id.btnSignOut);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        movies = new ArrayList<>();
        getMoviesFromDB(0);

        gridLayout = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayout);

        adapter = new MoviesAdapter(this, movies);
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

                Queryphp query = new Queryphp("recuperarPelis.php?id=" + movieIds[0]);
                try {
                    Response response = query.returnRequest();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);

                        Pelicula movie = new Pelicula(object.getInt("idMovie"),object.getString("nombre"),object.getInt("anio"),object.getString("keywords"),object.getString("actores"),object.getString("directores"),object.getString("imagen"),object.getString("genero"));

                        movies.add(movie);
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
