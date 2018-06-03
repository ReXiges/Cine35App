package cine35app.esteb.cine35app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserManagmentActivity extends AppCompatActivity {
    private Button signOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_managment);
        signOut = (Button) findViewById(R.id.btnSignOutUserManage);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pIntent = new Intent(UserManagmentActivity.this, MainActivity.class);
                startActivity(pIntent);
            }
        });
    }
}
