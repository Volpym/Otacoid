package com.example.otacoid;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.otacoid.database.User;

public class RegisterActivity extends AppCompatActivity{
    User user = new User();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Button bSignUpButton = findViewById(R.id.buttonSignUp);
        final EditText eUsername = findViewById(R.id.editUsernameText);
        final EditText ePassword = findViewById(R.id.editPasswordText);
        final EditText eEmail  = findViewById(R.id.editEmailText);



        bSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = eUsername.getText().toString();
                final String password = ePassword.getText().toString();
                final String email = eEmail.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        user.register(username,password,email);
                    }
                }).start();
                Context context = getApplicationContext();
                CharSequence text = "Registered!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }

        });
    }

}







