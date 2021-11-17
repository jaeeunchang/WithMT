package com.cookandroid.withmt;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.cookandroid.withmt.Login.LoginRequest;
import com.cookandroid.withmt.Login.LoginView;
import com.cookandroid.withmt.MainPage.MainPageView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
    String userid, userpw;

    @Override
    protected void onCreate(Bundle savedInstanceStare) {
        super.onCreate(savedInstanceStare);
        setContentView(R.layout.splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //자동 로그인
                SharedPreferences userinfo = getSharedPreferences("userinfo", Activity.MODE_PRIVATE);
                userid = userinfo.getString("inputId", null);
                userpw = userinfo.getString("inputPW", null);

                if(userid != null && userpw != null){
                    LoginRequest loginRequest = new LoginRequest(userid, userpw);

                    //retrofit 생성
                    Call<String> call = ApiClient.getApiService().postLogin(loginRequest);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if(response.isSuccessful()) {
                                Log.d("Tag", String.valueOf(response.headers()));
                                Intent intent = new Intent(getApplicationContext(), MainPageView.class);
                                startActivity(intent);
                                finish();
                            }
                            else {

                            }
                        }
                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.e("Tag", String.valueOf(t));
                        }
                    });
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), LoginView.class);
                    startActivity(intent);
                    finish();
                }
            }
        },2000);


    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}