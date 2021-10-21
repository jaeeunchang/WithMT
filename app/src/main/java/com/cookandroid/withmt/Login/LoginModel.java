package com.cookandroid.withmt.Login;

import android.util.Log;
import android.widget.Toast;

import com.cookandroid.withmt.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Tag;

public class LoginModel {
    LoginView loginView = new LoginView();

    public boolean checkLogin(String id, String pw) {
        final boolean[] result = new boolean[1];
        LoginResponse loginResponse = new LoginResponse(id, pw);

        //retrofit 생성
        ApiClient.getApiService().LoginPost(loginResponse)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            if(response.code()==200){
                                Log.e("Tag", "로그인 성공");
                                result[0] = true;
                            }
                            else{
                                Log.e("Tag", "로그인 실패");
                                result[0] = false;
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("Tag", "연결 실패");
                    }
                });

        return result[0];
    }
}
