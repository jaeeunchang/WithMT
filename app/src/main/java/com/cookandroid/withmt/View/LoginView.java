package com.cookandroid.withmt.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cookandroid.withmt.R;

public class LoginView extends AppCompatActivity {
    Button btnLogin, btnSignup;
    EditText edId, edPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        edId = (EditText) findViewById(R.id.editId);
        edPW = (EditText) findViewById(R.id.editPW);
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
                break;
        }
    };
}