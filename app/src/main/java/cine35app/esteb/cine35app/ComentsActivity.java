package cine35app.esteb.cine35app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

public class ComentsActivity extends AppCompatActivity {
    private Button submit;

    private EditText comentSubmit;
    private int idpelicula;
    private String user;
    private List<String> comentarios = new ArrayList<>();
    private ListView list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coments);
        submit= (Button) findViewById(R.id.submit);
        comentSubmit= (EditText) findViewById(R.id.coment);
        list= (ListView) findViewById(R.id.listaComents);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Queryphp q= new Queryphp("insertComment.php?id="+idpelicula+"&user="+user+"&content="+comentSubmit.getText().toString());
                try {
                    q.returnRequest();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                comentarios = new ArrayList<>();
                getComentsFromDB();
                adapter= new ArrayAdapter<String>(ComentsActivity.this,android.R.layout.simple_list_item_1,comentarios);
                list.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("user");
            idpelicula=extras.getInt("idpelicula");
        }
        getComentsFromDB();
        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,comentarios);
        list.setAdapter(adapter);



    }
    private void getComentsFromDB() {

        AsyncTask<Integer, Void, Void> asyncTask = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... Ids) {
                Queryphp query= new Queryphp("recuperarComentarios.php?id="+String.valueOf(idpelicula));
                try {
                    Response response = query.returnRequest();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);


                        ComentsActivity.this.comentarios.add("Usuario: "+object.getString("usuario")+"\n"+object.getString("content"));
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

        };
        asyncTask.execute();
    }
    }


