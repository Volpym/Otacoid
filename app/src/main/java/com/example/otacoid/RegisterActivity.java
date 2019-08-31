package com.example.otacoid;


import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.otacoid.database.User;

public class RegisterActivity extends AppCompatActivity{


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Button bSignUpButton = findViewById(R.id.buttonSignIn);
        final EditText eUsername = findViewById(R.id.editUsernameText);
        final EditText ePassword = findViewById(R.id.editPasswordText);
        final EditText eEmail  = findViewById(R.id.editEmailText);

        bSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Register_Async().execute();
            }


    });}


        private class Register_Async extends AsyncTask<Void, Void, Void>{
        User user = new User();
        final EditText eUsername = findViewById(R.id.editUsernameText);
        final EditText ePassword = findViewById(R.id.editPasswordText);
        final EditText eEmail  = findViewById(R.id.editEmailText);
        final String username = eUsername.getText().toString();
        final String password = ePassword.getText().toString();
        final String email = eEmail.getText().toString();

        @Override
        protected Void doInBackground(Void... voids) {
            user.register(username,password,email);
            return null;
        }
    }

}







