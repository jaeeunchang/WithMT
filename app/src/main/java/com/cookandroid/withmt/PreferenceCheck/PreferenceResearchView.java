package com.cookandroid.withmt.PreferenceCheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.cookandroid.withmt.ApiClient;
import com.cookandroid.withmt.MainPage.MainPageView;
import com.cookandroid.withmt.R;
import com.cookandroid.withmt.SignUp.SignupView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreferenceResearchView extends AppCompatActivity {

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
        RadioButton q1r1 = (RadioButton) findViewById(R.id.q1r1);
        RadioButton q1r2 = (RadioButton) findViewById(R.id.q1r2);
        RadioButton q1r3 = (RadioButton) findViewById(R.id.q1r3);
        RadioButton q1r4 = (RadioButton) findViewById(R.id.q1r4);

        RadioGroup rGroup2 = (RadioGroup) findViewById(R.id.rGroup2);
        RadioButton q2r1 = (RadioButton) findViewById(R.id.q2r1);
        RadioButton q2r2 = (RadioButton) findViewById(R.id.q2r2);
        RadioButton q2r3 = (RadioButton) findViewById(R.id.q2r3);
        RadioButton q2r4 = (RadioButton) findViewById(R.id.q2r4);
        RadioButton q2r5 = (RadioButton) findViewById(R.id.q2r5);

        RadioGroup rGroup3 = (RadioGroup) findViewById(R.id.rGroup3);
        RadioButton q3r1 = (RadioButton) findViewById(R.id.q3r1);
        RadioButton q3r2 = (RadioButton) findViewById(R.id.q3r2);
        RadioButton q3r3 = (RadioButton) findViewById(R.id.q3r3);
        RadioButton q3r4 = (RadioButton) findViewById(R.id.q3r4);
        RadioButton q3r5 = (RadioButton) findViewById(R.id.q3r5);

        RadioGroup rGroup4 = (RadioGroup) findViewById(R.id.rGroup4);
        RadioButton q4r1 = (RadioButton) findViewById(R.id.q4r1);
        RadioButton q4r2 = (RadioButton) findViewById(R.id.q4r2);
        RadioButton q4r3 = (RadioButton) findViewById(R.id.q4r3);

        CheckBox cb_friend = (CheckBox) findViewById(R.id.cb_friend);
        CheckBox cb_hiking = (CheckBox) findViewById(R.id.cb_hiking);

        Button btn_submit = (Button) findViewById(R.id.btn_submit);

        Preference p = new Preference();

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rGroup1.getCheckedRadioButtonId() == -1 ||
                        rGroup2.getCheckedRadioButtonId() == -1 ||
                        rGroup3.getCheckedRadioButtonId() == -1 ||
                        rGroup4.getCheckedRadioButtonId() == -1 ||
                        (!cb_friend.isChecked() && !cb_hiking.isChecked()))
                {
                    // no radio buttons are checked
//                    Toast.makeText(getApplicationContext(), "답을 다 입력하지 않았습니다.", Toast.LENGTH_LONG).show();
                    Toast tmsg = Toast.makeText(getApplicationContext(), "빠진 부분 없이 전부 입력해주세요.", Toast.LENGTH_SHORT);
                    tmsg.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
                    tmsg.show();
                }
                else
                {
                    // one of the radio buttons is checked
                    // 선택된것 데이터 전송
                    switch (rGroup1.getCheckedRadioButtonId()) {//climbingLevel
                        case R.id.q1r1:
                            p.setClimbingLevel("0"); //0
                            break;
                        case R.id.q1r2:
                            p.setClimbingLevel("0.33"); //0.33
                            break;
                        case R.id.q1r3:
                            p.setClimbingLevel("0.66"); //0.66
                            break;
                        case R.id.q1r4:
                            p.setClimbingLevel("1"); //1
                            break;
                    }

                    switch (rGroup2.getCheckedRadioButtonId()) {//difficulty
                        case R.id.q2r1:
                            p.setDifficulty("0"); //0
                            break;
                        case R.id.q2r2:
                            p.setDifficulty("0.25"); //0.25
                            break;
                        case R.id.q2r3:
                            p.setDifficulty("0.5"); //0.5
                            break;
                        case R.id.q2r4:
                            p.setDifficulty("0.75"); //0.75
                            break;
                        case R.id.q2r5:
                            p.setDifficulty("0.1"); //1
                            break;
                    }

                    switch (rGroup3.getCheckedRadioButtonId()) {//exercise
                        case R.id.q3r1:
                            p.setExercise("0"); //0
                            break;
                        case R.id.q3r2:
                            p.setExercise("0.25"); //0.25
                            break;
                        case R.id.q3r3:
                            p.setExercise("0.5"); //0.5
                            break;
                        case R.id.q3r4:
                            p.setExercise("0.75"); //0.75
                            break;
                        case R.id.q3r5:
                            p.setExercise("1"); //1
                            break;
                    }

                    switch (rGroup4.getCheckedRadioButtonId()) {//frequency
                        case R.id.q4r1: //0
                            p.setFrequency("0");
                            break;
                        case R.id.q4r2: //0.5
                            p.setFrequency("0.5");
                            break;
                        case R.id.q4r3: //1
                            p.setFrequency("1");
                            break;
                    }

                    if(cb_friend.isChecked()) {//friendship
                        p.setFriendship("1"); //1
                    } else {
                        p.setFriendship("0"); //0
                    }

                    if(cb_hiking.isChecked()) {//climbingMate
                        p.setClimbingMate("1"); //1
                    } else {
                        p.setClimbingMate("0"); //0
                    }

                    Intent intent = new Intent(getApplicationContext(), MainPageView.class);
                    startActivity(intent);
                }
            }
        });

        ApiClient.getApiService().putPreference(new Preference()).enqueue(new Callback<Preference>() {
            @Override
            public void onResponse(@NonNull Call<Preference> call, @NonNull Response<Preference> response) {
                if (response.isSuccessful()) {
                    Preference p = response.body();
                    if (p != null) {
                        Log.d("data.getClimbingLevel()", p.getClimbingLevel() + "");
                        Log.d("data.getDifficulty()", p.getDifficulty() + "");
                        Log.d("data.getExercise()", p.getExercise() + "");
                        Log.d("data.getFrequency()", p.getFrequency() + "");
                        Log.d("data.getFriendship()", p.getFriendship() + "");
                        Log.d("data.getClimbingMate()", p.getClimbingMate() + "");
                        Log.e("putData end", "======================================");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Preference> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
