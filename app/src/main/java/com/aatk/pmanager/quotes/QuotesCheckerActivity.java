package com.aatk.pmanager.quotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.aatk.pmanager.R;
import com.aatk.pmanager.quotes.web.Quote;

import java.util.ArrayList;
import java.util.List;

public class QuotesCheckerActivity extends AppCompatActivity {
    public static final String TAG = "Quotes Checker Activity";
    private QuotesCheckerService quotesCheckerService;
    private Button checkCarButton;
    private List<Quote> quotes;
    private List<String> quotesContent;
    private ArrayAdapter<String> quotesAdapter;
    private ListView quotesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_checker);
        quotes = new ArrayList<>();
        quotesContent = new ArrayList<>();
        quotesCheckerService = new QuotesCheckerService(this);
        initializeButtons();
        setUpListAdapter();
    }

    private void setUpListAdapter(){
        quotesListView = findViewById(R.id.quotesListView);
        //quotesContent = mapToString(quotes);
        quotesAdapter = new ArrayAdapter<>(this, R.layout.row, quotesContent );
        quotesListView.setAdapter(quotesAdapter);
    }

    private List<String> mapToString(List<Quote> quotes){
        List<String> sQuotes = new ArrayList<>();
        for (Quote q : quotes ){
            sQuotes.add(q.getContent());
        }
        return sQuotes;
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

    void addQuote(Quote quote){
        quotes.add(quote);
        quotesContent.add(quote.getContent().replaceAll("<p>|</p>", "") + "- " + quote.getTitle());
        quotesAdapter.notifyDataSetChanged();
    }

}
