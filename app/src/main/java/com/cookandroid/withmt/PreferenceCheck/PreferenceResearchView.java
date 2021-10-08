package com.cookandroid.withmt.PreferenceCheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.cookandroid.withmt.MainPage.MainPageView;
import com.cookandroid.withmt.R;

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
                    Toast.makeText(getApplicationContext(), "답을 다 입력하지 않았습니다.", Toast.LENGTH_LONG).show();
                }
                else
                {   // one of the radio buttons is checked
                    // 선택된것 데이터 전송
                    switch (rGroup1.getCheckedRadioButtonId()) {
                        case R.id.q1r1:
                            //0
                            break;
                        case R.id.q1r2:
                            //0.33
                            break;
                        case R.id.q1r3:
                            //0.66
                            break;
                        case R.id.q1r4:
                            //1
                            break;
                    }

                    switch (rGroup2.getCheckedRadioButtonId()) {
                        case R.id.q2r1:
                            //0
                            break;
                        case R.id.q2r2:
                            //0.25
                            break;
                        case R.id.q2r3:
                            //0.5
                            break;
                        case R.id.q2r4:
                            //0.75
                            break;
                        case R.id.q2r5:
                            //1
                            break;
                    }

                    switch (rGroup3.getCheckedRadioButtonId()) {
                        case R.id.q3r1:
                            //0
                            break;
                        case R.id.q3r2:
                            //0.25
                            break;
                        case R.id.q3r3:
                            //0.5
                            break;
                        case R.id.q3r4:
                            //0.75
                            break;
                        case R.id.q3r5:
                            //1
                            break;
                    }

                    switch (rGroup4.getCheckedRadioButtonId()) {
                        case R.id.q4r1:
                            //0
                            break;
                        case R.id.q4r2:
                            //0.5
                            break;
                        case R.id.q4r3:
                            //1
                            break;
                    }

                    if(cb_friend.isChecked()) {
                        //1
                    } else {
                        //0
                    }

                    if(cb_hiking.isChecked()) {
                        //1
                    } else {
                        //0
                    }

                    Intent intent = new Intent(getApplicationContext(), MainPageView.class);
                    startActivity(intent);
                }
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
