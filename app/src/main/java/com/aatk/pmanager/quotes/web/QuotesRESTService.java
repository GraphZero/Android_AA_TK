package com.aatk.pmanager.quotes.web;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface QuotesRESTService {

    @Headers("content-type: application/json")
    @GET("wp-json/posts?filter[orderby]=rand&filter[posts_per_page]=1")
    Call<List<Quote>> listQuotes();

}
