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

                //서버 데이터 받아오기
                String imoji_server = info.getImoji();
                String nickname_server = info.getNickname();
                Integer gender_server = info.getGender();
                Integer age_server = info.getGender();
                Integer friendship_server = info.getFriendship();
                Integer climbingmate_server = info.getClimbingMate();
                Double level_server = info.getClimbingLevel();

                //이모지 변환
                String imoji = "";
                if(imoji_server == "BEAR") {
                    imoji = "🐻";
                } else if(imoji_server == "TIGER") {
                    imoji = "🐯";
                } else if(imoji_server == "RABBIT") {
                    imoji = "🐰";
                } else if(imoji_server == "FOX") {
                    imoji = "🦊";
                }
                user_icon.append(imoji);

                //닉네임
                user_nickname.append(nickname_server);

                //선호도 값 텍스트로 변환
                String prefer = "";
                if(friendship_server == 1 && climbingmate_server == 1) {
                    prefer = "친목+등산";
                } else if(friendship_server == 1 && climbingmate_server == 0) {
                    prefer = "친목 위주";
                } else if(friendship_server == 0 && climbingmate_server == 1) {
                    prefer = "등산 위주";
                }

                //등산능력 값 텍스트로 변환
                String level = "";
                if(level_server == 0) {
                    level = "입문자";
                } else if(level_server == 0.33) {
                    level = "경험자";
                } else if(level_server == 0.66) {
                    level = "숙련가";
                } else if(level_server == 1) {
                    level = "전문가";
                }

                //성별 값 텍스트로 변환
                String gender = "";
                if(gender_server == 0) {
                    gender = "남";
                } else if(gender_server == 1) {
                    gender = "여";
                }

                //나이 값 텍스트로 변환
                String age = "";
                switch (age_server) {
                    case 1:
                        age = "10대";
                        break;
                    case 2:
                        age = "20대";
                        break;
                    case 3:
                        age = "30대";
                        break;
                    case 4:
                        age = "40대";
                        break;
                    case 5:
                        age = "50대";
                        break;
                    case 6:
                        age = "60대 이상";
                        break;
                    default:
                        age = "X";
                        break;
                }

                //사용자 정보 추가
                user_info.append(level+"/"+prefer+"/"+gender+"/"+age);
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
            }
        });
    }
}
