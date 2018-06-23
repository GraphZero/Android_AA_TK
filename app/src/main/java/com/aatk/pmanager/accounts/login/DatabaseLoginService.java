package com.aatk.pmanager.accounts.login;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.aatk.pmanager.MainActivity;
import com.aatk.pmanager.accounts.domain.User;
import com.aatk.pmanager.accounts.repository.UserDao;
import com.aatk.pmanager.main.HomeActivity;

public class DatabaseLoginService implements UserValidator {
    private UserDao userDao;
    private Context context;

    public DatabaseLoginService(Context context) {
        this.context = context;
        userDao = MainActivity.userDatabase.userDao();
    }

    private class MyTask extends AsyncTask<String, Void, Boolean>
    {
        @Override
        protected Boolean doInBackground(String... params)
        {
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
        MyTask myTask = new MyTask();
        myTask.execute(username ,password);
    }

    private void validateUser(Boolean result){
        if (result){
            Intent intent = new Intent(context, HomeActivity.class);
            context.startActivity(intent);
        }
    }

}
