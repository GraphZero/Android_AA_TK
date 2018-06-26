package com.aatk.pmanager;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
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

public class MainActivity extends AppCompatActivity {
    private static final String PREFERENCES_NAME = "myPreferences";
    private static final String PREFERENCES_USERNAME = "username";
    private static final String PREFERENCES_PASSWORD = "password";
    private static final String TAG = "Main Activity";

    private boolean areDatabasesSet = false;
    public static UserDatabase userDatabase;

    private Button loginButton;
    private Button registerButton;


    private EditText usernameEditText;
    private EditText passwordEditText;
    private UserValidator userValidator;
    private SharedPreferences preferences;
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        setUpDatabases();
        setUpComponents();
        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        userValidator = new DatabaseLoginService(this, preferences);
        restoreData();
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
        setUpNotificationManager();
    }

    public void setUpNotificationManager(){
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        final Intent emptyIntent = new Intent();
        PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 1, emptyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

// build notification
// the addAction re-use the same intent to keep the example short
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!")
                        .setContentIntent(pIntent); //Required on Gingerbread and below
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

    private void restoreData() {
        String username = preferences.getString(PREFERENCES_USERNAME, "");
        String password = preferences.getString(PREFERENCES_PASSWORD, "");
        if ( username.length() > 0  && password.length() > 0 ){
            usernameEditText.setText(username);
            passwordEditText.setText(password);
        }
    }
}
