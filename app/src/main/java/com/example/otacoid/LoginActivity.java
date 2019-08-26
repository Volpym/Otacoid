package com.example.otacoid;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.otacoid.database.User;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class LoginActivity extends AppCompatActivity {
    User user = new User();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button bSignUpButton = findViewById(R.id.buttonSignUp);
        final EditText eUsername = findViewById(R.id.editUsernameText);
        final EditText ePassword = findViewById(R.id.editPasswordText);


        bSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = eUsername.getText().toString();
                final String password = ePassword.getText().toString();
                new Login_Async().execute();






            }});
    }
    private class Login_Async extends AsyncTask<Void,Void,Void>{
       final EditText eUsername = findViewById(R.id.editUsernameText);
       final EditText ePassword = findViewById(R.id.editPasswordText);
       final String username = eUsername.getText().toString();
       final String password = ePassword.getText().toString();
       int response = 0;

       @Override
       protected Void doInBackground(Void... voids) {

            response = user.login(username,password);
            return null;
       }
        @Override
        protected void onPostExecute(Void result) {
            if(response == 200){
                Toast toast = Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_SHORT);
                toast.show();
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT);
                toast.show();

            }

        }



    }
    }










