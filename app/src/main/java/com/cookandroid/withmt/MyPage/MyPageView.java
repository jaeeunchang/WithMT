package com.cookandroid.withmt.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cookandroid.withmt.MyWriting.MywritingView;
import com.cookandroid.withmt.MainPage.MainPageView;
import com.cookandroid.withmt.PreferenceChangeView;
import com.cookandroid.withmt.R;
import com.cookandroid.withmt.View.LoginView;

public class MyPageView extends AppCompatActivity {

    Dialog logout_dialog, logout_dialog2;
    Button btn_back;
    LinearLayout writing_history, change_preference, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        Button btn_back = (Button) findViewById(R.id.btn_back);
        LinearLayout writing_history = (LinearLayout) findViewById(R.id.writing_history);
        LinearLayout change_preference = (LinearLayout) findViewById(R.id.change_preference);
        LinearLayout logout = (LinearLayout) findViewById(R.id.logout);

        logout_dialog = new Dialog(MyPageView.this);
        logout_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        logout_dialog.setContentView(R.layout.logout_dialog);

        logout_dialog2 = new Dialog(MyPageView.this);
        logout_dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        logout_dialog2.setContentView(R.layout.logout_dialog2);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainPageView.class);
                startActivity(intent);
            }
        });

        writing_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MywritingView.class);
                startActivity(intent);
            }
        });

        change_preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PreferenceChangeView.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_dialog1();
            }
        });
    }

    public void show_dialog1() {
        logout_dialog.show();
        logout_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btn_no = logout_dialog.findViewById(R.id.btn_no);
        Button btn_yes = logout_dialog.findViewById(R.id.btn_yes);

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout_dialog.dismiss(); // 다이얼로그 닫기
            }
        });

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout_dialog.dismiss(); // 다이얼로그 닫기
                show_dialog2();
            }
        });
    }

    public void show_dialog2() {
        logout_dialog2.show();
        logout_dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btn_yes = logout_dialog2.findViewById(R.id.btn_yes);

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout_dialog2.dismiss(); // 다이얼로그 닫기
                Intent intent = new Intent(getApplicationContext(), LoginView.class);
                startActivity(intent);
            }
        });
    }
}
