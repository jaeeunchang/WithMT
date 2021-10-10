package com.cookandroid.withmt;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient{
    private static final String BASE_URL = "http://ec2-3-38-84-254.ap-northeast-2.compute.amazonaws.com:8080/";
    public static ApiInterface getApiService(){return getInstance().create(ApiInterface.class);}
    private static Retrofit getInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
