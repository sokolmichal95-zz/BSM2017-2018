package com.example.msokol.message;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewMessage extends AppCompatActivity {

    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_new_message);
        Button saveButton = (Button) findViewById(R.id.saveMessageButton);
        final EditText addMessage = (EditText) findViewById(R.id.addMessageET);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = context.getSharedPreferences("myapp_pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("message", addMessage.getText().toString());
                editor.commit();
                Intent intent = new Intent(NewMessage.this, Options.class);
                startActivity(intent);
            }
        });
    }
}