package cine35app.esteb.cine35app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ClientActivity extends AppCompatActivity {
    private EditText search;
    private Button searchButton;
    private Button signOut;
    private Button recomendations;
    private Button favorites;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        search = (EditText) findViewById(R.id.txtSearchClient);
        searchButton = (Button) findViewById(R.id.btnSearchClient);
        signOut = (Button) findViewById(R.id.btnSignOut);
        recomendations = (Button) findViewById(R.id.btnRecomendations);
        favorites = (Button) findViewById(R.id.btnFavorite);
    }
}
