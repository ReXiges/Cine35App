package cine35app.esteb.cine35app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Response;

public class BaneoActivity extends AppCompatActivity {
    private ListView list;
    private ArrayList<Cliente> clientes;
    private ArrayList<String> data;
    private ArrayAdapter<String> adapter;
    private String userSelected="";
    private Button banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baneo);
        banner= (Button) findViewById(R.id.banB);
        list=(ListView) findViewById(R.id.bans);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                    userSelected=clientes.get(position).getNombreUsuario();
            }
        });
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchBanFromDB(userSelected);
                getUsersFromDB();
            }
        });
        data=new ArrayList<>();
        clientes= new ArrayList<>();
        getUsersFromDB();
        adapter= new ArrayAdapter<String>(BaneoActivity.this,android.R.layout.simple_list_item_1,data);
        list.setAdapter(adapter);




    }
    private void getUsersFromDB() {

        AsyncTask<Integer, Void, Void> asyncTask = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... movieIds) {
                Queryphp query= new Queryphp("getUsers.php?");
                try {
                    Response response = query.returnRequest();
                    String t=response.body().string();
                    JSONArray array = new JSONArray(t);
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);

                        Cliente cliente =  new Cliente(object.getString("nombre"),object.getString("nacionalidad"),object.getString("password"),object.getString("correo"),object.getString("nombreUsuario"));
                        BaneoActivity.this.clientes.add(cliente);
                        String baneado;
                        if(object.getInt("ban")==0){
                            baneado="No";
                        }
                        else{
                            baneado="Si";
                        }
                        BaneoActivity.this.data.add("Usuario: "+cliente.getNombreUsuario()+" Baneado: "+baneado);
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

    private void switchBanFromDB(final String user) {

        AsyncTask<Integer, Void, Void> asyncTask = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... movieIds) {
                Queryphp query= new Queryphp("banUsers.php?&user="+user);
                try {
                    Response response = query.returnRequest();
                    BaneoActivity.this.data=new ArrayList<>();
                    BaneoActivity.this.clientes=new ArrayList<>();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                getUsersFromDB();
            }
        };

        asyncTask.execute();
    }
}
