package com.cookandroid.withmt.PreferenceCheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.cookandroid.withmt.ApiClient;
import com.cookandroid.withmt.Login.LoginView;
import com.cookandroid.withmt.MainPage.MainPageView;
import com.cookandroid.withmt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreferenceResearchView extends AppCompatActivity {

    Preference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference_research);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);

        RadioGroup rGroup1 = (RadioGroup) findViewById(R.id.rGroup1);
        RadioGroup rGroup2 = (RadioGroup) findViewById(R.id.rGroup2);
        RadioGroup rGroup3 = (RadioGroup) findViewById(R.id.rGroup3);
        RadioGroup rGroup4 = (RadioGroup) findViewById(R.id.rGroup4);
        CheckBox cb_friend = (CheckBox) findViewById(R.id.cb_friend);
        CheckBox cb_hiking = (CheckBox) findViewById(R.id.cb_hiking);

        Button btn_submit = (Button) findViewById(R.id.btn_submit);

        Preference p = new Preference("0.33","0.25","0.25","0","1","1");

        //기본 setting(응답안하고 앱 종료시 기본값 입력)
        Call<Preference> call = ApiClient.getApiService().putPreference(p);
        call.enqueue(new Callback<Preference>() {
            @Override
            public void onResponse(Call<Preference> call, Response<Preference> response) {
                if(response.isSuccessful()) {
                    Log.d("Tag", "설문조사 응답코드: "+response.code());
                }
            }
            @Override
            public void onFailure(Call<Preference> call, Throwable t) {
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rGroup1.getCheckedRadioButtonId() == -1 ||
                        rGroup2.getCheckedRadioButtonId() == -1 ||
                        rGroup3.getCheckedRadioButtonId() == -1 ||
                        rGroup4.getCheckedRadioButtonId() == -1 ||
                        (!cb_friend.isChecked() && !cb_hiking.isChecked()))
                {
                    // 하나라도 빠진 부분이 있을경우
                    Toast tmsg = Toast.makeText(getApplicationContext(), "빠진 부분 없이 전부 입력해주세요.", Toast.LENGTH_SHORT);
                    tmsg.show();
                } else {
                    // 선택된것 데이터 전송
                    switch (rGroup1.getCheckedRadioButtonId()) {//climbingLevel
                        case R.id.q1r1: p.setClimbingLevel("0"); break;
                        case R.id.q1r2: p.setClimbingLevel("0.33"); break;
                        case R.id.q1r3: p.setClimbingLevel("0.66"); break;
                        case R.id.q1r4: p.setClimbingLevel("1"); break;
                    }

                    switch (rGroup2.getCheckedRadioButtonId()) {//difficulty
                        case R.id.q2r1: p.setDifficulty("0"); break;
                        case R.id.q2r2: p.setDifficulty("0.25"); break;
                        case R.id.q2r3: p.setDifficulty("0.5"); break;
                        case R.id.q2r4: p.setDifficulty("0.75"); break;
                        case R.id.q2r5: p.setDifficulty("1"); break;
                    }

                    switch (rGroup3.getCheckedRadioButtonId()) {//exercise
                        case R.id.q3r1: p.setExercise("0"); break;
                        case R.id.q3r2: p.setExercise("0.25"); break;
                        case R.id.q3r3: p.setExercise("0.5"); break;
                        case R.id.q3r4: p.setExercise("0.75"); break;
                        case R.id.q3r5: p.setExercise("1"); break;
                    }

                    switch (rGroup4.getCheckedRadioButtonId()) {//frequency
                        case R.id.q4r1: p.setFrequency("0"); break;
                        case R.id.q4r2: p.setFrequency("0.5"); break;
                        case R.id.q4r3: p.setFrequency("1"); break;
                    }

                    if(cb_friend.isChecked()) {//friendship
                        p.setFriendship("1");
                    } else {
                        p.setFriendship("0");
                    }

                    if(cb_hiking.isChecked()) {//climbingMate
                        p.setClimbingMate("1");
                    } else {
                        p.setClimbingMate("0");
                    }

                    preference = new Preference(p.getClimbingLevel(),p.getDifficulty(),p.getExercise(),p.getFrequency(),p.getFriendship(),p.getClimbingMate());

                    Call<Preference> call = ApiClient.getApiService().putPreference(preference);
                    call.enqueue(new Callback<Preference>() {
                        @Override
                        public void onResponse(Call<Preference> call, Response<Preference> response) {
                            if(response.isSuccessful()) {
                                Intent intent = new Intent(getApplicationContext(), MainPageView.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Preference> call, Throwable t) {
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(getApplicationContext(), LoginView.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), LoginView.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
