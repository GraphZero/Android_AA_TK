package com.aatk.pmanager.main;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aatk.pmanager.R;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class CarInputActivity extends AppCompatActivity {
    private String user;
    private EditText model;
    private EditText carNumber;
    private EditText telNumber;
    private Button addToExternalFileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_input);
        initializeFields();
    }

    private void initializeFields(){
        user = getIntent().getStringExtra("username");
        model = (EditText) findViewById(R.id.model);
        carNumber = (EditText) findViewById(R.id.carNumber);
        telNumber = (EditText) findViewById(R.id.telNumber);
        addToExternalFileButton = (Button) findViewById(R.id.addCar);
        addToExternalFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDataToExternal();
                Intent intent = new Intent(CarInputActivity.this, HomeActivity.class);
                intent.putExtra("userName", user);
                startActivity(intent);
            }
        });
    }

    private void saveDataToExternal(){
        String lineToWrite = "User:" + user + "," + "model:" + model.getText().toString() + "," + "carNumber:" + carNumber.getText().toString() + "," + "telNumber:" + telNumber.getText().toString();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        File fileToWrite = new File(path, "cars.txt");
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(fileToWrite, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(lineToWrite.toString() + "\n");
            bufferedWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
