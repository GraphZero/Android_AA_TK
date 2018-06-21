package com.aatk.pmanager;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aatk.pmanager.accounts.login.DatabaseLoginService;
import com.aatk.pmanager.accounts.login.UserValidator;
import com.aatk.pmanager.accounts.register.RegisterUserActivity;
import com.aatk.pmanager.accounts.repository.UserDatabase;
import com.aatk.pmanager.main.HomeActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private boolean areDatabasesSet = false;
    public static UserDatabase userDatabase;

    private Button loginButton;
    private Button registerButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private UserValidator userValidator;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpDatabases();
        setUpComponents();
        userValidator = new DatabaseLoginService(this);
    }

    private void setUpDatabases(){
        if ( !areDatabasesSet ){
            userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "UserDatabase").build();
            areDatabasesSet = true;
        } else{
            Log.w(TAG, "setUpDatabases: " + " databases are already set!");
        }
    }

    private void setUpComponents(){
        setUpButtons();
        setUpEditTexts();
    }

    private void setUpButtons(){
        loginButton = findViewById(R.id.button4);
        registerButton = findViewById(R.id.button5);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUser();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void setUpEditTexts(){
        usernameEditText = findViewById(R.id.editText10);
        passwordEditText = findViewById(R.id.editText11);
    }

    private void registerUser() {
        Intent intent = new Intent(this, RegisterUserActivity.class);
        startActivity(intent);
    }

    private void validateUser(){
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        userValidator.validateUser(username, password);
    }

}
