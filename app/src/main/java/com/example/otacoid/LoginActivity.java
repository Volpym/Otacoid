package com.example.otacoid;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.otacoid.database.User;
import com.example.otacoid.scraper.Scrapper;
import com.example.otacoid.urlManager.UrlManager;

public class LoginActivity extends AppCompatActivity {
    User user = new User();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button bSignInButton = findViewById(R.id.buttonSignIn);
        Button bSignUpButton = findViewById(R.id.bOpenRegister);
        ImageView bImageView = findViewById(R.id.logo_image_view);


        bImageView.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        bSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Login_Async().execute();

        }});

        bSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                Toast toast = Toast.makeText(getApplicationContext(), "Time to register", Toast.LENGTH_LONG);
                toast.show();
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
                Toast toast = Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_LONG);
                toast.show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);


            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT);
                toast.show();

            }

        }



    }
    }










