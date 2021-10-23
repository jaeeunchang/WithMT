package com.cookandroid.withmt.MainPage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.cookandroid.withmt.MyPage.MyPageView;
import com.cookandroid.withmt.R;
import com.cookandroid.withmt.Writing.WritingView;

import java.util.Calendar;

public class MainPageView extends AppCompatActivity {

    String userid, userpw;
    Button btn_mypage, btn_menu, btn_write;
    TextView filter_date;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_mypage = (Button) findViewById(R.id.btn_mypage);
        Button btn_search = (Button) findViewById(R.id.btn_search);
        Button btn_calendar = (Button) findViewById(R.id.btn_calendar);
        Button btn_write = (Button) findViewById(R.id.btn_write);

        TextView filter_date = (TextView) findViewById(R.id.filter_date);


        SharedPreferences userinfo = getSharedPreferences("userinfo", Activity.MODE_PRIVATE);
        userid = userinfo.getString("inputId", "none");
        userpw = userinfo.getString("inputPW", "엥");
        Toast.makeText(getApplicationContext(), "userid:"+userid+", userpw:"+userpw, Toast.LENGTH_LONG).show();

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                filter_date.setText(year+"-"+(month+1)+"-"+dayOfMonth);
            }
        }, mYear, mMonth, mDay);

        //datepicker에 초기화 버튼 추가
        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "초기화", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                filter_date.setText("2021-00-00");
                //Your code
            }
        });

        btn_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyPageView.class);
                startActivity(intent);
            }
        });

        btn_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

//        filter_date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                filter_date.setText("2021-00-00");
//            }
//        });

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WritingView.class);
                startActivity(intent);
            }
        });
    }
}