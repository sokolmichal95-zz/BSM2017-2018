package com.example.msokol.message;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {

    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        context = getApplicationContext();

        final EditText oldPassword = (EditText) findViewById(R.id.oldPassword);
        final EditText newPassword = (EditText) findViewById(R.id.newPassword);
        Button savePassword = (Button) findViewById(R.id.savePasswordButton);

        savePassword.setOnClickListener(new View.OnClickListener() {
            SharedPreferences preferences = context.getSharedPreferences("myapp_pref", context.MODE_PRIVATE);
            String value = preferences.getString("password", "");

            @Override
            public void onClick(View v) {
                if(value.equals(Crypto.base64Encode(Crypto.sha256(oldPassword.getText().toString())))){
                    SharedPreferences preferences1 = context.getSharedPreferences("myapp_pref",context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences1.edit();
                    editor.putString("password", Crypto.base64Encode(Crypto.sha256(newPassword.getText().toString())));
                    editor.commit();

                    Intent intent = new Intent(ChangePassword.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(context, "Wrong password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}