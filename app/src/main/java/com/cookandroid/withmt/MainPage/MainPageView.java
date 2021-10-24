package com.cookandroid.withmt.MainPage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cookandroid.withmt.ApiClient;
import com.cookandroid.withmt.MyPage.MyInfo;
import com.cookandroid.withmt.MyPage.MyPageView;
import com.cookandroid.withmt.R;
import com.cookandroid.withmt.Writing.WritingView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPageView extends AppCompatActivity {


    Button btn_mypage, btn_menu, btn_write;
    Spinner menu_spinner;
    TextView filter_date;
    DatePickerDialog datePickerDialog;
    ArrayList<WritingList> li;
    ListView lv;
    String userid, userpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        li = new ArrayList<WritingList>();
//        li.add(new WritingList("여러분 등산 같이갑시다요 산행은 즐거워 참여하세요","2021-10-14",0,"BEAR","18장재은"));

        MyAdapter adapter = new MyAdapter(getApplicationContext(), R.layout.main_list, li);
        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(adapter);


        Button btn_mypage = (Button) findViewById(R.id.btn_mypage);
        Button btn_search = (Button) findViewById(R.id.btn_search);
        Button btn_calendar = (Button) findViewById(R.id.btn_calendar);
        Button btn_write = (Button) findViewById(R.id.btn_write);

        Spinner menu_spinner = (Spinner) findViewById(R.id.menu_spinner);
        TextView filter_date = (TextView) findViewById(R.id.filter_date);

        //userid값 넘겨받기
//        SharedPreferences userinfo = getSharedPreferences("userinfo", MODE_PRIVATE);
//        userid = userinfo.getString("inputId", "none");
//        userpw = userinfo.getString("inputPW", "엥");
//        Toast.makeText(getApplicationContext(), "userid:"+userid+", userpw:"+userpw, Toast.LENGTH_LONG).show();


        String select_menu = menu_spinner.getSelectedItem().toString();
        if(select_menu == "추천순") {
            //추천순 GET

            Call<MainList> call =  ApiClient.getApiService().getRecommend();
            call.enqueue(new Callback<MainList>() {
                @Override
                public void onResponse(Call<MainList> call, Response<MainList> response) {
                    if(!response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "code"+response.code(), Toast.LENGTH_LONG).show();
                        return;
                    }

                    MainList list = response.body();

                    //서버 데이터 받아오기
                    String title_server = list.getBoard().getTitle();
                    String date_server = list.getBoard().getTitle();
                    Integer gender_server = list.getBoard().getGender();
                    String imoji_server = list.getUser().getImoji();
                    String nickname_server = list.getUser().getNickname();

                    //성별 값 텍스트로 변환
                    String gender = "";
                    if(gender_server == 0) {
                        gender = "남";
                    } else if(gender_server == 1) {
                        gender = "여";
                    } else if(gender_server == 2) {
                        gender = "상관없음";
                    }

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

                    li.add(new WritingList(title_server,date_server, gender, imoji, nickname_server));
                }
                @Override
                public void onFailure(Call<MainList> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } else if(select_menu == "최신순") {
            //최신순 GET
        }

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

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WritingView.class);
                startActivity(intent);
            }
        });
    }

    class WritingList {
        String title = "";
        String date = "";
        String gender;
        String imoji = "";
        String nickname = "";

        public WritingList(String title, String date, String gender, String imoji ,String nickname ) {
            super();
            this.title = title;
            this.date = date;
            this.gender = gender;
            this.imoji = imoji;
            this.nickname = nickname;
        }

        public WritingList() {
        }
    }


    class MyAdapter extends BaseAdapter {
        Context context;
        int layout;
        ArrayList<WritingList> li;
        LayoutInflater inf;

        public MyAdapter(Context context, int layout, ArrayList<WritingList> li) {
            this.context = context;
            this.layout = layout;
            this.li = li;
            inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return li.size();
        }

        @Override
        public Object getItem(int position) {
            return li.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inf.inflate(layout, null);
            }

            TextView title = (TextView) convertView.findViewById(R.id.title);
            ImageView calender_icon = (ImageView) convertView.findViewById(R.id.calender_icon);
            TextView date = (TextView) convertView.findViewById(R.id.date);
            TextView gender_info = (TextView) convertView.findViewById(R.id.gender_info);
            TextView gender = (TextView) convertView.findViewById(R.id.gender);
            TextView imoji = (TextView) convertView.findViewById(R.id.user_icon);
            TextView nickname = (TextView) convertView.findViewById(R.id.user);

            WritingList w = li.get(position);
            title.setText(w.title);
            title.setText(w.date);
            title.setText(w.gender);
            title.setText(w.imoji);
            title.setText(w.nickname);

            return convertView;
        }
    }

}