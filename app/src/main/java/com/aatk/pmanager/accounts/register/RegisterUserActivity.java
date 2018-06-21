package com.aatk.pmanager.accounts.register;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.aatk.pmanager.R;
import com.aatk.pmanager.accounts.domain.Role;
import com.aatk.pmanager.main.HomeActivity;

public class RegisterUserActivity extends AppCompatActivity {
    private Button registerButton;
    private Button getHelpButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Spinner spinner;
    private RegisterService registerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        registerService = new DatabaseRegisterService();
        setUpButtons();
        setUpEditTexts();
        setUpSpinner();
    }

    private void setUpButtons(){
        registerButton = findViewById(R.id.button);
        getHelpButton = findViewById(R.id.button1);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
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
        usernameEditText = findViewById(R.id.editText);
        passwordEditText = findViewById(R.id.editText2);
    }

    private void setUpSpinner(){
        spinner = findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roles_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    private void registerUser() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String role = spinner.getSelectedItem().toString();
                registerService.registerUser(username, password, Role.valueOf(role.toUpperCase()));
                goToHomeActivity();
            }
        });
    }

    private void goToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

}
