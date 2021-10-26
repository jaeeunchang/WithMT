package com.cookandroid.withmt.PreferenceCheck;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.cookandroid.withmt.ApiClient;

import java.io.IOException;

public class PRpresenter {

    public static void main(String[] args) {
//        ApiClient.getApiService().putPreference(new Preference()).enqueue(new Callback<Preference>() {
//            @Override
//            public void onResponse(@NonNull Call<Preference> call, @NonNull Response<Preference> response) {
//                if (response.isSuccessful()) {
//                    Preference p = response.body();
//                    if (p != null) {
//                        Log.d("data.getClimbingLevel()", p.getClimbingLevel() + "");
//                        Log.d("data.getDifficulty()", p.getDifficulty() + "");
//                        Log.d("data.getExercise()", p.getExercise() + "");
//                        Log.d("data.getFrequency()", p.getFrequency() + "");
//                        Log.d("data.getFriendship()", p.getFriendship() + "");
//                        Log.d("data.getClimbingMate()", p.getClimbingMate() + "");
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


//        Call<Object> putPreference = ApiClient.getApiService().putPreference();
//        try{
//            System.out.println(putPreference.execute().body());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}

//public class PRpresenter implements PRConstants.Presenter {
//    PRConstants.View PRView;
//
//    public static void main(String[] args) {
//        Call<Object> putPreference = ApiClient.getApiService().putPreference("100");
//        try{
//            System.out.println(putPreference.execute().body());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public PRpresenter(PRConstants.View view){
//    }
//}
