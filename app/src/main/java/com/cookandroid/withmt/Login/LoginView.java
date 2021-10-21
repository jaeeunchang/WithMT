package com.cookandroid.withmt.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.cookandroid.withmt.MainPage.MainPageView;
import com.cookandroid.withmt.R;
import com.cookandroid.withmt.SignUp.SignupView;

public class LoginView extends AppCompatActivity {
    EditText edId, edPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        edId = (EditText) findViewById(R.id.editId);
        edPW = (EditText) findViewById(R.id.editPW);

//        View view = getWindow().getDecorView();
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            if(view != null) {
//                view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//                getWindow().setStatusBarColor(Color.parseColor("#dbe4f3"));
//            }
//        }
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
                intent = new Intent(getApplicationContext(), MainPageView.class);
                startActivity(intent);
                break;
        }
    };
}