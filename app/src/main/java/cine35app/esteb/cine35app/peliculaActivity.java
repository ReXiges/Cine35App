package cine35app.esteb.cine35app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import okhttp3.Response;

public class peliculaActivity extends AppCompatActivity {
    private ListView list;
    private Button rate;
    private EditText rateR;
    private ArrayList<String> info= new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private String user;
    private Queryphp q= new Queryphp("");
    private Pelicula peli;
    private float totaRate=0;
    private boolean isAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_pelicula);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        list= (ListView) findViewById(R.id.peliInfo);
        rate= (Button) findViewById(R.id.rateB);
        rateR= (EditText) findViewById(R.id.rateR);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("user");
        }
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(rateR.getText().toString().equals(""))||Integer.valueOf(rateR.getText().toString())<=5 &&Integer.valueOf(rateR.getText().toString())>=0){
                    updateRateFromDB(user,peli.getId());
                    getAllRatesFromDB(peli.getId());
                    info.set(5,"Calificacion: "+totaRate);
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(peliculaActivity.this, "valor debe ser entre 0 y 5", Toast.LENGTH_SHORT).show();
                }


            }
        });
        peli =(Pelicula) getIntent().getSerializableExtra("pelicula");
        info.add("Nombre: "+peli.getNombre());
        info.add("Año: "+peli.getAnio());
        info.add("Director(es): "+peli.getDirectores());
        info.add("Reparto: "+peli.getActores());
        info.add("Genero: "+peli.getGenero());
        getAllRatesFromDB(peli.getId());
        info.add("Calificacion: Calculando");
        info.add("Sinopsis \n"+peli.getSinopsis());
        adapter= new ArrayAdapter<String>(peliculaActivity.this,android.R.layout.simple_list_item_1,info);
        list.setAdapter(adapter);
    }
    private void getAllRatesFromDB(final int id) {

        AsyncTask<Integer, Void, Void> asyncTask = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... Ids) {
                peliculaActivity.this.totaRate=0;
                Queryphp query= new Queryphp("getAllRate.php?id="+id);
                try {
                    Response response = query.returnRequest();

                    JSONArray array = new JSONArray(response.body().string());


                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);
                        peliculaActivity.this.totaRate+= object.getInt("calificacion");
                    }
                    if(peliculaActivity.this.totaRate!=0){
                        peliculaActivity.this.totaRate=peliculaActivity.this.totaRate/array.length();
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
        asyncTask.execute();
    }

    private void updateRateFromDB(final String user, final int id) {

        AsyncTask<Integer, Void, Void> asyncTask = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... Ids) {
                int sumArray=0;
                Queryphp query= new Queryphp("getRate.php?id="+id+"&user="+user);
                try {
                    Response response = query.returnRequest();

                    JSONArray array = new JSONArray(response.body().string());

                    if(array.length()==0){
                        Queryphp q= new Queryphp("insertRate.php?id="+id+"&user="+user+"&content="+rateR.getText().toString());
                        q.returnRequest();
                    }
                    else{
                        Queryphp q= new Queryphp("updateRate.php?id="+id+"&user="+user+"&content="+rateR.getText().toString());
                        q.returnRequest();
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
        asyncTask.execute();
    }

}
