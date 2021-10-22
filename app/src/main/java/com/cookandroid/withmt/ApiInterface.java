package com.cookandroid.withmt;

import com.cookandroid.withmt.Login.LoginResponse;
import com.cookandroid.withmt.SignUp.SignUpRequest;
import com.cookandroid.withmt.SignUp.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("login")
    Call<String> LoginPost(@Body LoginResponse loginResponse);

    @GET("users/double?nickname={nickname}")
    Call<String> getNickname(@Path("nickname") String nickname);
}
