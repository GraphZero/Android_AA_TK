package com.aatk.pmanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.aatk.pmanager.service.XMLHelper;

import java.io.File;
import java.util.List;

public class MessageActivity extends AppCompatActivity {
    private EditText userNameToWrite;
    private TextView telNumber;
    private EditText smsText;
    private Button sendButton;
    private Button callButton;
    private Button chooseUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeObjects();
        userNameToWrite.setText(getIntent().getStringExtra("username"));
    }

    private void initializeObjects(){
        userNameToWrite = (EditText) findViewById(R.id.receiverName);
        callButton = (Button) findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dialNumber(telNumber.getText().toString());
            }
        });
        telNumber = (TextView) findViewById(R.id.telNumberView);
        smsText = (EditText) findViewById(R.id.smsText);
        sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS(telNumber.getText().toString(), smsText.getText().toString());
            }
        });
        userNameToWrite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (userNameToWrite != null){
                    String userLine = findUserTel(userNameToWrite.getText().toString());
                    if (userLine != null){
                        String textNumber = userLine.substring(userLine.indexOf("telNumber")).substring(10);
                        telNumber.setText(textNumber);
                    }
                }
            }
        });
        chooseUserButton = (Button) findViewById(R.id.chooseUserButton);
        chooseUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MessageActivity.this, ChooseUserActivity.class);
                startActivity(intent);
            }
        });
    }

    private void sendSMS(String phoneNumber, String message) {
        System.out.println("SENDING MESSAGE: " + phoneNumber + " " + message);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    public void dialNumber(String number){
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number, number)));
    }

    public String findUserTel(String userName){
        File sdCardDirectory = Environment.getExternalStorageDirectory();
        File file = new File(sdCardDirectory + "/cars.txt");
        String userInfoLine = XMLHelper.findCarForUser(file, userName);
        return userInfoLine;
    }
}
