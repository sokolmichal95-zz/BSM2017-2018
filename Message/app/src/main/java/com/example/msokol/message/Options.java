package com.example.msokol.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Options extends AppCompatActivity {

    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        context = getApplicationContext();

        /*final String passwordTemp;
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            passwordTemp = null;
        } else {
            passwordTemp = extras.getString("aqwerty");
        }*/

        Button showMessage = (Button) findViewById(R.id.showMessage);
        Button editMessage = (Button) findViewById(R.id.editMessage);
        Button changePassword = (Button) findViewById(R.id.changePassword);

        showMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options.this, ShowMessage.class);
                //intent.putExtra("aqwerty", passwordTemp);
                startActivity(intent);
            }
        });

        editMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options.this, NewMessage.class);
                //intent.putExtra("aqwerty", passwordTemp);
                startActivity(intent);
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options.this, ChangePassword.class);
                startActivity(intent);
            }
        });
    }
}