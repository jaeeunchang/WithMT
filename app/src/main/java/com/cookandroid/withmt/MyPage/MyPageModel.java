package com.cookandroid.withmt.MyPage;

import android.util.Log;

import androidx.annotation.NonNull;

import com.cookandroid.withmt.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageModel {
//    public static void main(String[] args) {
//
//        Call<MyInfo> call = ApiClient.getApiService().getUserInfo("userid");
//        call.enqueue(new Callback<MyInfo>() {
//            @Override
//            public void onResponse(Call<MyInfo> call, Response<MyInfo> response) {
//                if(!response.isSuccessful()) {
//                    textViewResult.setText("code:"+response.code());
//                    return;
//                }
//
//                MyInfo info = response.body();
//                    String content="";
//                    content += "nickname: " + info.getNickname() +"\n";
//                    content += "imoji: " + info.getImoji() +"\n";
//                    content += "imoji: " + info.getGender() +"\n";
//                    content += "imoji: " + info.getAge() +"\n";
//                    content += "imoji: " + info.getFriendship() +"\n";
//                    content += "imoji: " + info.getClimbingMate() +"\n";
//                    content += "imoji: " + info.getClimbingLevel() +"\n\n";
//
//                    textViewResult.append(content);
//            }
//
//            @Override
//            public void onFailure(Call<MyInfo> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
//            }
//        });
    }

//    public static void main(String[] args) {
//        ApiClient.getApiService().getUserInfo("userid").enqueue(new Callback<MyInfo>() {
//            @Override
//            public void onResponse(@NonNull Call<MyInfo> call, @NonNull Response<MyInfo> response) {
//                if (response.isSuccessful()) {
//                    if(response.code() == 200) {
//                        MyInfo info = response.body();
//                        if (info != null) {
//                            Log.d("data.getNickname()", info.getNickname() + "");
//                            Log.d("data.getGender()", info.getGender() + "");
//                            Log.d("data.getAge()", info.getAge() + "");
//                            Log.d("data.getFriendship()", info.getFriendship() + "");
//                            Log.d("data.getClimbingMate()", info.getClimbingMate() + "");
//                            Log.d("data.getClimbingLevel()", info.getClimbingLevel() + "");
//                            Log.e("putData end", "======================================");
//                        }
//                    } else if(response.code() == 401) {
//                        Log.e("MyPageTag", "401에러");
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<MyInfo> call, @NonNull Throwable t) {
////                Log.e("MyPageTag", "onFailure");
//                Log.e("연결실패", t.getMessage());
//            }
//        });
//    }
