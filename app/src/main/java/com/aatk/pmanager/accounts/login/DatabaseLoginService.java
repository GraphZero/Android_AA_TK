package com.aatk.pmanager.accounts.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.aatk.pmanager.MainActivity;
import com.aatk.pmanager.accounts.domain.User;
import com.aatk.pmanager.accounts.repository.UserDao;
import com.aatk.pmanager.main.HomeActivity;

public class DatabaseLoginService implements UserValidator {
    private static final String PREFERENCES_USERNAME = "username";
    private static final String PREFERENCES_PASSWORD = "password";
    private UserDao userDao;
    private Context context;
    public static String actualUser;
    private SharedPreferences preferences;
    private String username;
    private String password;

    public DatabaseLoginService(Context context, SharedPreferences preferences) {
        this.context = context;
        this.preferences = preferences;
        userDao = MainActivity.userDatabase.userDao();
    }

    private class MyTask extends AsyncTask<String, Void, Boolean>
    {
        @Override
        protected Boolean doInBackground(String... params)
        {
            //do stuff and return the value you want
            actualUser = params[0];
            //do stuff and return the value you want\
            if ( params.length < 2 ){
                return false;
            }
            User user = userDao.findByUsername(params[0]);
            if ( user != null ){
                return user.getPassword().equals(params[1]);
            } else{
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result)
        {
            // Call activity method with results
            validateUser(result);
        }
    }

    @Override
    public void validateUser(String username, String password) {
        this.username = username;
        this.password = password;
        MyTask myTask = new MyTask();
        myTask.execute(username ,password);
    }

    private void validateUser(Boolean result){
        if (result){
            saveUserData();
            Intent intent = new Intent(context, HomeActivity.class);
            intent.putExtra("userName", actualUser);
            context.startActivity(intent);
        }
    }

    private void saveUserData() {
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        String usernameToSave = username;
        String passwordToSave = password;
        preferencesEditor.putString(PREFERENCES_USERNAME, usernameToSave);
        preferencesEditor.putString(PREFERENCES_PASSWORD, passwordToSave);
        preferencesEditor.apply();
    }

}
