package com.cookandroid.withmt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Dialog filter_dialog;
    Button btn_mypage, btn_menu, btn_filter, btn_write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_mypage = (Button) findViewById(R.id.btn_mypage);
        Button btn_search = (Button) findViewById(R.id.btn_search);
        Button btn_filter = (Button) findViewById(R.id.btn_filter);
        Button btn_write = (Button) findViewById(R.id.btn_write);

        filter_dialog = new Dialog(MainActivity.this);
        filter_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        filter_dialog.setContentView(R.layout.filter_dialog);


        btn_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyPage.class);
                startActivity(intent);
            }
        });

        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_filter();
            }
        });
    }


    public void show_filter() {
        filter_dialog.show();
        filter_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btn_close = filter_dialog.findViewById(R.id.btn_close);
        Button btn_fsearch = filter_dialog.findViewById(R.id.btn_fsearch);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter_dialog.dismiss(); // 다이얼로그 닫기
            }
        });

        btn_fsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter_dialog.dismiss(); // 다이얼로그 닫기
            }
        });
    }
}