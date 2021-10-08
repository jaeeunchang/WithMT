package com.cookandroid.withmt.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.cookandroid.withmt.PreferenceCheck.PreferenceResearchView;
import com.cookandroid.withmt.R;
import com.cookandroid.withmt.Writing.WritingView;

public class SignupView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        EditText editName = (EditText) findViewById(R.id.editName);
        EditText editId = (EditText) findViewById(R.id.editId);
        EditText editPW = (EditText) findViewById(R.id.editPW);
        EditText confirmPW = (EditText) findViewById(R.id.confirmPW);

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

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        RadioButton rdFemale = (RadioButton) findViewById(R.id.rdFemale);
        RadioButton rdMale = (RadioButton) findViewById(R.id.rdMale);

        //회원가입버튼
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(editName.getText().toString().equals("")||editId.getText().toString().equals("")||
//                        editPW.getText().toString().equals("")||confirmPW.getText().toString().equals("")||
//                        !rdFemale.isChecked()||!rdMale.isChecked()){
//            Toast tmsg = Toast.makeText(SignupView.this, "빠진 부분 없이 전부 입력해주세요.", Toast.LENGTH_SHORT);
//                    tmsg.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
//                    tmsg.show();
//                }
//                else{ }
                Intent intent = new Intent(getApplicationContext(), PreferenceResearchView.class);
                startActivity(intent);
            }
        });


    }
}