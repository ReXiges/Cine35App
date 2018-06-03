package cine35app.esteb.cine35app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserManagmentActivity extends AppCompatActivity {
    private Button signOut;
    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Pelicula> movies;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayout;
    private MoviesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_managment);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pIntent = new Intent(UserManagmentActivity.this, MainActivity.class);
                startActivity(pIntent);
            }
        });
    }

}
