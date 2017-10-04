package com.example.msokol.message;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ShowMessage extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_message);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myapp_pref",
                getApplicationContext().MODE_PRIVATE);
        String value = preferences.getString("message", "");

        TextView showMessage = (TextView) findViewById(R.id.messageView);
        showMessage.setText(value);
    }
}