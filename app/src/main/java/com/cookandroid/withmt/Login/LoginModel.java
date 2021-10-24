package com.cookandroid.withmt.Login;

import android.os.AsyncTask;
import android.util.Log;

import com.cookandroid.withmt.ApiClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel {
    boolean result;

    public boolean checkLogin(String id, String pw) {
        LoginRequest loginRequest = new LoginRequest(id, pw);

        //retrofit 생성
        Call<String> call = ApiClient.getApiService().postLogin(loginRequest);

        //비동기식
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()) {
                    Log.d("Tag", "로그인 성공");
                    result = true;
                }
                else {
                    Log.d("Tag", "로그인 실패");
                    result = false;
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("Tag", String.valueOf(t));
            }
        });
        return result;
    }
}
