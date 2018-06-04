package cine35app.esteb.cine35app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

public class UserManagmentActivity extends AppCompatActivity {
/*    private Button signOut;
    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Pelicula> users;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayout;
    private MoviesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_managment);
        recyclerView = (RecyclerView) findViewById(R.id.coments);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pIntent = new Intent(UserManagmentActivity.this, MainActivity.class);
                startActivity(pIntent);
            }
        });
    }

}*/
    private EditText searchBar;
    private Button searchButton;
    private Button signOut;
    private Button recomendations;
    private Button banned;
    private List<Cliente> users;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayout;
    private MoviesAdapter adapter;
    private String user;
    private String searchTerm="";
    boolean baneados=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        Bundle extras = getIntent().getExtras();
        user="";
        if (extras != null) {
            user = extras.getString("usuario");
        }
        searchButton = (Button) findViewById(R.id.search);
        searchBar=(EditText) findViewById(R.id.searchBar);
        banned=(Button) findViewById(R.id.favoritos);

        final String finalUser = user;
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchTerm=searchBar.getText().toString();
                users = new ArrayList<>();
                baneados=false;
                getUsersFromDB(0);
                adapter = new UserAdapter(UserManagmentActivity.this, users, finalUser);
                recyclerView.setAdapter(adapter);
            }
        });
        banned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchTerm="";
                users = new ArrayList<>();
                baneados=true;
                getUsersFromDB(0);
                adapter = new UsersAdapter(UserManagmentActivity.this, users, finalUser);
                recyclerView.setAdapter(adapter);

            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.coments);
        users = new ArrayList<>();
        getUsersFromDB(0);

        gridLayout = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayout);

        adapter = new UsersAdapter(this, users,user);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (gridLayout.findLastCompletelyVisibleItemPosition() == users.size() - 1) {
                    getUsersFromDB(users.get(users.size() - 1).getNombreUsuario());
                }

            }
        });
    }
    private void getUsersFromDB(int id) {

        AsyncTask<Integer, Void, Void> asyncTask = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... movieIds) {
                Queryphp query;
                if (baneados){
                    query = new Queryphp("recuperarUsers.php?id=" + usernames[0]+"&user="+user);
                }
                else{
                    query = new Queryphp("recuperarUsers.php?id=" + usernames[0]+"&search="+searchTerm);
                }
                try {
                    Response response = query.returnRequest();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);

                        Cliente usuario = new Cliente(object.getString("nombre"),object.getString("nacionalidad"),object.getString("correo"),object.getString("nombreUsuario"),object.getString("password"),object.getInt("tipo");

                        UserManagmentActivity.this.users.add(usuario);
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

