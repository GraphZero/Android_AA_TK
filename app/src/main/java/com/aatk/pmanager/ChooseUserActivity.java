package com.aatk.pmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.aatk.pmanager.service.XMLHelper;
import com.fasterxml.jackson.databind.util.BeanUtil;

import java.util.List;

public class ChooseUserActivity extends AppCompatActivity {
    private Button backButton;
    private static String[] USERS;
    private  String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseUserActivity.this, MessageActivity.class);
                intent.putExtra("username", userName);

                startActivity(intent);
            }
        });
        USERS = new String[XMLHelper.getAllUsers(getFilesDir()).size()];
        String[] tmp = new String[XMLHelper.getAllUsers(getFilesDir()).size()];
        System.out.println(tmp.length);
        tmp = XMLHelper.getAllUsers(getFilesDir()).toArray(tmp);
        for (int i=  0; i < tmp.length; i++){
            System.out.println(tmp[i] + " ");
        }
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.actv);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.simple_list_item_1, tmp);
        autoCompleteTextView.setAdapter(adapter);
        userName = adapter.getItem(4);
    }
}
