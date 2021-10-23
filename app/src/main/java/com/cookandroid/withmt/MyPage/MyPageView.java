package com.cookandroid.withmt.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cookandroid.withmt.ApiClient;
import com.cookandroid.withmt.MyWriting.MywritingView;
import com.cookandroid.withmt.MainPage.MainPageView;
import com.cookandroid.withmt.PreferenceChangeView;
import com.cookandroid.withmt.R;
import com.cookandroid.withmt.Login.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageView extends AppCompatActivity {

    Dialog logout_dialog, logout_dialog2;
    TextView user_icon, user_nickname, user_info;
    Button btn_back;
    LinearLayout writing_history, change_preference, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        Button btn_back = (Button) findViewById(R.id.btn_back);
        TextView user_icon = (TextView) findViewById(R.id.user_icon);
        TextView user_nickname = (TextView) findViewById(R.id.user_nickname);
        TextView user_info = (TextView) findViewById(R.id.user_info);
        LinearLayout writing_history = (LinearLayout) findViewById(R.id.writing_history);
        LinearLayout change_preference = (LinearLayout) findViewById(R.id.change_preference);
        LinearLayout logout = (LinearLayout) findViewById(R.id.logout);

        logout_dialog = new Dialog(MyPageView.this);
        logout_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        logout_dialog.setContentView(R.layout.logout_dialog);

        logout_dialog2 = new Dialog(MyPageView.this);
        logout_dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        logout_dialog2.setContentView(R.layout.logout_dialog2);



        Call<MyInfo> call = ApiClient.getApiService().getUserInfo("test16");
        call.enqueue(new Callback<MyInfo>() {
            @Override
            public void onResponse(Call<MyInfo> call, Response<MyInfo> response) {
                if(!response.isSuccessful()) {
                    user_info.setText("code:"+response.code());
                    return;
                }

                MyInfo info = response.body();
                String imoji = "";
                if(info.getImoji()=="BEAR") {
                    imoji = "🐻";
                } else if(info.getImoji() == "TIGER") {
                    imoji = "🐯";
                } else if(info.getImoji() == "RABBIT") {
                    imoji = "🐰";
                } else if(info.getImoji() == "TIGER") {
                    imoji = "🐯";
                }
                user_icon.append(info.getImoji());
                user_nickname.append(info.getNickname());

                String content="";
                content += "imoji: " + info.getImoji() +"\n";
                content += "imoji: " + info.getGender() +"\n";
                content += "imoji: " + info.getAge() +"\n";
                content += "imoji: " + info.getFriendship() +"\n";
                content += "imoji: " + info.getClimbingMate() +"\n";
                content += "imoji: " + info.getClimbingLevel() +"\n\n";

                user_info.append(content);
            }

            @Override
            public void onFailure(Call<MyInfo> call, Throwable t) {
                user_info.setText(t.getMessage());
            }
        });


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
                SharedPreferences userinfo = getSharedPreferences("userinfo", Activity.MODE_PRIVATE);
                SharedPreferences.Editor autoLogin = userinfo.edit();
                autoLogin.clear();
                autoLogin.commit();
            }
        });
    }
}
