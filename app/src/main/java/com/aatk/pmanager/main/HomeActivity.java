package com.aatk.pmanager.main;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aatk.pmanager.MainActivity;
import com.aatk.pmanager.R;
import com.aatk.pmanager.accounts.repository.UserDao;
import com.aatk.pmanager.db.Users;
import com.aatk.pmanager.service.XMLWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;


/**
 * This is home activity.
 */

public class HomeActivity extends AppCompatActivity{
    private static final String TAG = "HomeActivity";
    private UserDao userDao;
    private TextView userName;
    private TextView carName;
    private String user;
    private XMLWriter xmlWriter;
    private Users userList;
    private String actualCar;
    private Button carAdder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initializeObjects();
        checkAndInitializeFiles();
        welcomeTheUser();
    }

    private void initializeObjects(){
        carName = (TextView) findViewById(R.id.carName);
        userDao = MainActivity.userDatabase.userDao();
        user = getIntent().getStringExtra("userName");
        carAdder = (Button)findViewById(R.id.carAdder);
        carAdder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HomeActivity.this, CarInputActivity.class);
                intent.putExtra("username", user);
                startActivity(intent);
            }
        });
        xmlWriter = new XMLWriter();
        userList = new Users();
        //CarAdder - button do dodania samochodu w razie, gdy go nie ma
        actualCar = findCar();
        fillCarAdder();
        insertUsers();
    }

    private void fillCarAdder(){
        if (actualCar != null && !actualCar.equals("undefined")){
            carAdder.setVisibility(View.GONE);
        }
        else{
            carAdder.setVisibility(View.VISIBLE);
        }
    }

    private String findCar(){
        File sdCardDirectory = Environment.getExternalStorageDirectory();
        File file = new File(sdCardDirectory + "/cars.txt");
        try{
            if (file.exists()) {
                String carLine = findCarForUser(file);
                if (carLine != null){
                    return carLine;
                }
            }
            else {
                file.createNewFile();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return "undefined";
    }

    private String findCarForUser(File file){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String readLine = "";
            while((readLine = bufferedReader.readLine()) != null){
                String userName = user;
                if (readLine.contains(userName)){
                    return readLine;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private void welcomeTheUser(){
        userName = (TextView)findViewById(R.id.userName);
        String welcome = "Witamy, " + user;
        userName.setText(welcome);
        if (actualCar.equals("undefined")){
            carName.setText("Don't have your car here?");
            carAdder.setVisibility(View.VISIBLE);
        }
    }

    public void insertUsers() {
        try {
            new AsyncTask<Void, Void, Users>() {
                @Override
                protected Users doInBackground(Void... voids) {
                    userList.setUserList(userDao.findAll());
                    return userList;
                }
            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void checkAndInitializeFiles() {
        String internalStorageCarFile = getFilesDir().getPath() + "/users_cars.xml";
        try{
            if (checkIfFileExists(internalStorageCarFile)){
                return;
            }
            else{
                File fileToCreate = new File(internalStorageCarFile);
                fileToCreate.createNewFile();
                xmlWriter.writeToFile(userList, fileToCreate);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private boolean checkIfFileExists(String filePath){
        File fileToCheck = new File(filePath);
        if (fileToCheck.exists()){
            Log.w(TAG, "The file users_cars exists");
            return true;
        }
        else{
            Log.w(TAG, "The file doesn't exist. Creating new file...");
            return false;
        }
    }
}
