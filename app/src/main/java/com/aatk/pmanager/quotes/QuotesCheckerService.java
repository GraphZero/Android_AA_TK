package com.aatk.pmanager.quotes;

import android.os.AsyncTask;
import android.util.Log;

import com.aatk.pmanager.quotes.web.Quote;
import com.aatk.pmanager.quotes.web.QuotesRESTService;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuotesCheckerService {
    private static final String TAG = "Car Checker";
    private QuotesRESTService quotesRESTService;
    private QuotesCheckerActivity context;
    private List<Quote> quotes;
    private int quoteToAdd = 0;

    public QuotesCheckerService(QuotesCheckerActivity context) {
        this.context = context;
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://quotesondesign.com/")
                .build();
        quotesRESTService = retrofit.create(QuotesRESTService.class);
    }

    private class GetCarsTask extends AsyncTask<String, Void, List<Quote>>
    {
        @Override
        protected List<Quote> doInBackground(String... params)
        {
            try {
                Log.i(TAG, quotesRESTService.listQuotes().request().toString());
                if ( quotes == null || quotes.size() == 0 ){
                    quotes = quotesRESTService
                            .listQuotes()
                            .execute()
                            .body();
                } else{
                    quotes.add(quotesRESTService
                            .listQuotes()
                            .execute()
                            .body()
                            .get(0));
                }

            } catch (JsonSyntaxException | IOException e ) {
                Log.i(TAG, "getAsyncCarModelsByManufacturer: couldnt get response " + e.getMessage());
                e.printStackTrace();
                quotes = new ArrayList<>();
            }
            return quotes;
        }

        @Override
        protected void onPostExecute(List<Quote> result) {
            quotes = result;
            context.addQuote(quotes.get(quoteToAdd));
            quoteToAdd++;
            Log.i(TAG, "onClick: " + quotes.get(0) );
        }
    }

    public void getAsyncCarModelsByManufacturer(){
        GetCarsTask myTask = new QuotesCheckerService.GetCarsTask();
        myTask.execute();
    }

    public List<Quote> getQuotes() {
        return quotes;
    }
}
