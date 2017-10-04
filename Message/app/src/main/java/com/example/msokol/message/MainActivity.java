package com.example.msokol.message;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String passwordTemp;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        Button passConfirm = (Button) findViewById(R.id.passwordConfirmation);

        final EditText password = (EditText) findViewById(R.id.password);

        passConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordTemp = password.getText().toString();

                //hash passwordTemp and convert it to base64
                //later compare password from xml with base64 of passwordTemp

                SharedPreferences preferences = getApplicationContext().getSharedPreferences("myapp_pref",
                        getApplicationContext().MODE_PRIVATE);
                String value = preferences.getString("password", "");
                if (value.equals(passwordTemp))
                {
                    Intent intent = new Intent(MainActivity.this, Options.class);
                    startActivity(intent);
                }
                else if (value.equals(""))
                {
                    SharedPreferences preferences2 = context.getSharedPreferences("myapp_pref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences2.edit();
                    editor.putString("password", passwordTemp);
                    editor.commit();

                    Intent intent = new Intent(MainActivity.this, NewMessage.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
