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

import me.aflak.libraries.FingerprintCallback;
import me.aflak.libraries.FingerprintDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, FingerprintCallback {

    private String passwordTemp;
    private String hashedPassword;
    private String base64Password;

    private String value;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        Button passConfirm = (Button) findViewById(R.id.passwordConfirmation);

        final EditText password = (EditText) findViewById(R.id.password);
        findViewById(R.id.fingerprintLogin).setOnClickListener(this);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myapp_pref",
                getApplicationContext().MODE_PRIVATE);
        value = preferences.getString("password", "");

        passConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordTemp = password.getText().toString();
                hashedPassword = Crypto.sha256(passwordTemp);
                base64Password = Crypto.base64Encode(hashedPassword);


                if (value.equals(base64Password)) {
                    Intent intent = new Intent(MainActivity.this, Options.class);
                    intent.putExtra("aqwerty", passwordTemp);
                    startActivity(intent);
                } else if (value.equals("")) {
                    if (passwordTemp.equals("")) {
                        Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    } else {
                        SharedPreferences preferences2 = context.getSharedPreferences("myapp_pref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences2.edit();
                        editor.putString("password", base64Password);
                        editor.commit();

                        Intent intent = new Intent(MainActivity.this, NewMessage.class);
                        intent.putExtra("aqwerty", passwordTemp);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (!value.equals("")) {
            if (FingerprintDialog.isAvailable(this)) {
                FingerprintDialog.initialize(this)
                        .title("LogIn")
                        .message("Use Fingerprint Scanner")
                        .callback(this)
                        .show();
            }
        } else {
            Toast.makeText(MainActivity.this, "You must set password first", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAuthenticationSuccess() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myapp_pref",
                getApplicationContext().MODE_PRIVATE);
        String value = preferences.getString("password", "");
        String message = preferences.getString("message", "");

        if (value.equals("") && message.equals("")) {
            Intent intent = new Intent(MainActivity.this, NewMessage.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(MainActivity.this, Options.class);
            startActivity(intent);
        }
    }

    @Override
    public void onAuthenticationCancel() {
        Toast.makeText(MainActivity.this, "Something Went Wrong",
                Toast.LENGTH_SHORT).show();
    }
}
