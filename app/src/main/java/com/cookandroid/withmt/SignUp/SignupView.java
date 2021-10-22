package com.cookandroid.withmt.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cookandroid.withmt.ApiClient;
import com.cookandroid.withmt.Login.LoginResponse;
import com.cookandroid.withmt.PreferenceCheck.PreferenceResearchView;
import com.cookandroid.withmt.R;
import com.cookandroid.withmt.Writing.WritingView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupView extends AppCompatActivity {
    InputMethodManager imm;
    EditText editName, editId, editPW, confirmPW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        editName = (EditText) findViewById(R.id.newName);
        editId = (EditText) findViewById(R.id.newId);
        editPW = (EditText) findViewById(R.id.newPW);
        confirmPW = (EditText) findViewById(R.id.confirmPW);

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
                if(editName.getText().toString().equals("") ||
                        editId.getText().toString().equals("") ||
                        editPW.getText().toString().equals("") ||
                        confirmPW.getText().toString().equals("") ||
                        radioGroup.getCheckedRadioButtonId() == -1){
                    Toast tmsg = Toast.makeText(SignupView.this, "빠진 부분 없이 전부 입력해주세요.", Toast.LENGTH_SHORT);
                    tmsg.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
                    tmsg.show();
                }
                else{ Intent intent = new Intent(getApplicationContext(), PreferenceResearchView.class);
                    startActivity(intent);}

            }
        });

        //아이디 중복 확인
        Button btnCheckname = (Button)findViewById(R.id.btnCheckname);
        TextView alertname = (TextView)findViewById(R.id.alertnickname);
        btnCheckname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiClient.getApiService().getNickname(editName.getText().toString())
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.isSuccessful()) {
                                    alertname.setVisibility(View.VISIBLE);
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                alertname.setText("이미 사용중인 닉네임입니다.");
                                alertname.setVisibility(View.VISIBLE);
                            }
                        });
            }
        });

    }
    //배경 클릭 시 키보드 숨김
    public void linearOnclick(View v){
        imm.hideSoftInputFromWindow(editId.getWindowToken(),0);
        imm.hideSoftInputFromWindow(editPW.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(editName.getWindowToken(),0);
        imm.hideSoftInputFromWindow(confirmPW.getWindowToken(), 0);
    }

}