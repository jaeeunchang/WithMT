package com.cookandroid.withmt.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.cookandroid.withmt.MainPage.MainPageView;
import com.cookandroid.withmt.R;
import com.cookandroid.withmt.SignUp.SignupView;

public class LoginView extends AppCompatActivity {
    LoginPresenter loginPresenter;
    EditText edId, edPW;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginPresenter = new LoginPresenter(this);

        edId = (EditText) findViewById(R.id.editId);
        edPW = (EditText) findViewById(R.id.editPW);

        imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
    }

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
                loginPresenter.isLogin(edId.getText().toString(), edPW.getText().toString());
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
}