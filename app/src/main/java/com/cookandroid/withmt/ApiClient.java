package com.cookandroid.withmt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient{
    private static final String BASE_URL = "http://ec2-3-38-84-254.ap-northeast-2.compute.amazonaws.com:8080/";
    public static ApiInterface getApiService(){return getInstance().create(ApiInterface.class);}
    private static Retrofit getInstance(){
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }
}
