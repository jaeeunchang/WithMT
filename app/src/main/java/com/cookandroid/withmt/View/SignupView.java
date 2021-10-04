package com.cookandroid.withmt.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.cookandroid.withmt.MainActivity;
import com.cookandroid.withmt.PreferenceResearch;
import com.cookandroid.withmt.R;

public class SignupView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        Button btnNew = (Button) findViewById(R.id.btnNew);
        ImageButton btnX = (ImageButton) findViewById(R.id.btnClose);

        Spinner spinner = (Spinner) findViewById(R.id.spinAge);
        final String[] age = {"10대", "20대", "30대", "40대", "50대", "60대 이상"};

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, age);
        spinner.setAdapter(adapter);

        //닫기>로그인화면
        btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //회원가입버튼
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PreferenceResearch.class);
                startActivity(intent);
            }
        });


    }
}