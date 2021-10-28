package com.cookandroid.withmt;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.CookieManager;

import okhttp3.CookieJar;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient{
    private static Retrofit retrofit = null;

    private static final String BASE_URL = "http://ec2-3-38-84-254.ap-northeast-2.compute.amazonaws.com:8080/";
    public static ApiInterface getApiService(){return getInstance().create(ApiInterface.class);}
    public static CookieManager cookieManager;
    private static Retrofit getInstance(){

        if(retrofit == null) {
            Gson gson = new GsonBuilder().setLenient().create();

            cookieManager = new CookieManager();
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .cookieJar(new JavaNetCookieJar(cookieManager))
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

    public static void test() {
        Log.d("Tag-test", cookieManager.getCookieStore().getCookies().toString());
    }
}
