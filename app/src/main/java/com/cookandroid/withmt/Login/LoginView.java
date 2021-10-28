package com.cookandroid.withmt.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.cookandroid.withmt.ApiClient;
import com.cookandroid.withmt.MainPage.MainPageView;
import com.cookandroid.withmt.R;
import com.cookandroid.withmt.SignUp.SignupView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginView extends AppCompatActivity {
    EditText edId, edPW;
    InputMethodManager imm;
    String userid, userpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //상태바 설정
        View view = getWindow().getDecorView();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(view != null) {
                view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getWindow().setStatusBarColor(Color.parseColor("#dbe4f3"));
            }
        }

        edId = (EditText) findViewById(R.id.editId);
        edPW = (EditText) findViewById(R.id.editPW);

        imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
    }

    // 배경 클릭 시 키보드 숨김
    public void linearOnclick(View v){
        imm.hideSoftInputFromWindow(edId.getWindowToken(),0);
        imm.hideSoftInputFromWindow(edPW.getWindowToken(), 0);
    }

    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            //회원가입 클릭 시
            case R.id.btnSignup:
                intent = new Intent(getApplicationContext(), SignupView.class);
                startActivity(intent);
                break;

            //로그인 클릭 시
            case R.id.btnLogin:
                userid = edId.getText().toString();
                userpw = edPW.getText().toString();
                LoginRequest loginRequest = new LoginRequest(userid, userpw);

                //retrofit 생성
                Call<String> call = ApiClient.getApiService().postLogin(loginRequest);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()) {
                            Log.d("Tag", String.valueOf(response.headers()));
                            Log.d("Tag", "로그인 성공");
                            goToMain();
                            SharedPreferences userinfo = getSharedPreferences("userinfo", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor autoLogin = userinfo.edit();
                            autoLogin.putString("inputId", userid);
                            autoLogin.putString("inputPW", userpw);
                            autoLogin.commit();
                        }
                        else {
                            Log.d("Tag", "로그인 실패");
                            setEditBg();
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("Tag", String.valueOf(t));
                    }
                });
                break;
        }
    };

    //메인으로 이동
    public void goToMain(){
        Intent intent = new Intent(getApplicationContext(), MainPageView.class);
        startActivity(intent);
        finish();
    }
    //로그인 실패 시 editText 배경 빨간색으로 변경
    public void setEditBg(){
        edId.setBackgroundResource(R.drawable.et_error);
        edPW.setBackgroundResource(R.drawable.et_error);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Tag", "Destroy");
        ApiClient.test();
    }
}