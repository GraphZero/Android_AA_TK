package com.aatk.pmanager.quotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aatk.pmanager.R;
import com.aatk.pmanager.quotes.web.Quote;

import java.util.ArrayList;
import java.util.List;

public class QuotesCheckerActivity extends AppCompatActivity {
    public static final String TAG = "Car Checker Activity";
    private QuotesCheckerService quotesCheckerService;
    private Button checkCarButton;
    private List<Quote> quotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_checker);
        quotes = new ArrayList<>();
        quotesCheckerService = new QuotesCheckerService(this);
        initializeButtons();
    }

    private void initializeButtons(){
        checkCarButton = findViewById(R.id.getAllModels);

        checkCarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                quotesCheckerService.getAsyncCarModelsByManufacturer();
            }
        });

    }

    void initializeQuotes(){

    }

}
