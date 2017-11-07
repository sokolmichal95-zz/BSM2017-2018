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
    private String hashedPassword;
    private String base64Password;

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
                hashedPassword = Crypto.sha256(passwordTemp);
                base64Password = Crypto.base64Encode(hashedPassword);

                SharedPreferences preferences = getApplicationContext().getSharedPreferences("myapp_pref",
                        getApplicationContext().MODE_PRIVATE);
                String value = preferences.getString("password", "");
                if (value.equals(base64Password))
                {
                    Intent intent = new Intent(MainActivity.this, Options.class);
                    intent.putExtra("aqwerty", passwordTemp);
                    startActivity(intent);
                }
                else if (value.equals(""))
                {
                    SharedPreferences preferences2 = context.getSharedPreferences("myapp_pref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences2.edit();
                    editor.putString("password", base64Password);
                    editor.commit();

                    Intent intent = new Intent(MainActivity.this, NewMessage.class);
                    intent.putExtra("aqwerty", passwordTemp);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
