package com.cookandroid.withmt.MyPage;

import android.util.Log;

import androidx.annotation.NonNull;

import com.cookandroid.withmt.ApiClient;
import com.cookandroid.withmt.PreferenceCheck.Preference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPagePresenter extends MyPageView {

//    public static void main(String[] args) {
//        ApiClient.getApiService().postLogout(new Logout()).enqueue(new Callback<Logout>() {
//            @Override
//            public void onResponse(@NonNull Call<Logout> call, @NonNull Response<Logout> response) {
//                if (response.isSuccessful()) {
//                    if(response.code() == 200) {
//
//                    }
//                    Logout logout = response.body();
//                    if (logout != null) {
//                        Log.d("data.getUserId()", logout.getUserId() + "");
//                        Log.d("data.getPasswd()", logout.getPasswd() + "");
//                        Log.e("putData end", "======================================");
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Preference> call, @NonNull Throwable t) {
//
//            }
//        });
//    }
}
