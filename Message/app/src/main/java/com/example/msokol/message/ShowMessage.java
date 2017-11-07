package com.example.msokol.message;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.security.GeneralSecurityException;

public class ShowMessage extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        final String passwordTemp;
//        Bundle extras = getIntent().getExtras();
//        if(extras == null) {
//            passwordTemp = null;
//        } else {
//            passwordTemp = extras.getString("aqwerty");
//        }

        setContentView(R.layout.activity_show_message);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myapp_pref",
                getApplicationContext().MODE_PRIVATE);
        String value = null;
        try {
            value = AESCrypt.decrypt(preferences.getString("password", ""), preferences.getString("message", ""));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        TextView showMessage = (TextView) findViewById(R.id.messageView);
        showMessage.setText(value);
    }
}