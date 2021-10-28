package com.cookandroid.withmt.Writing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cookandroid.withmt.ApiClient;
import com.cookandroid.withmt.R;
import com.cookandroid.withmt.BoardDetail.BoardDetailView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WritingView extends AppCompatActivity {
    int gender, member;
    String climbDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writing);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        EditText title = (EditText) findViewById(R.id.title);
        EditText content = (EditText) findViewById(R.id.content);
        EditText num = (EditText) findViewById(R.id.num);
        EditText openkakao = (EditText) findViewById(R.id.openkakao);

        //DatePickerDialog
        Button picDate = (Button) findViewById(R.id.picDate);
        TextView date = (TextView) findViewById(R.id.date);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                date.setText(i+"년 "+(i1+1)+"월 "+i2+"일");
                Calendar calendar = Calendar.getInstance();
                calendar.set(i, i1, i2);
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                climbDate = format.format(calendar.getTime());
            }
        }, Calendar.YEAR, Calendar.MONTH, Calendar.DATE);

        //선택 버튼 누르면 datepicker 호출
        picDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinSex);
        final String[] genderList = {"성별 무관", "여성만", "남성만"};
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genderList);
        spinner.setAdapter(adapter);
        // 성별 선택
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = 2-position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        //새글 등록 버튼>작성한 글
        Button btnEnter = (Button) findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //내용 미입력 시 토스트
                if(title.getText().toString().equals("")||
                        picDate.getText().toString().equals("")||
                        num.getText().toString().equals("")||
                        openkakao.getText().toString().equals("")||
                        content.getText().toString().equals("")){
                    Toast tmsg = Toast.makeText(WritingView.this, "빠진 부분 없이 전부 입력해주세요.", Toast.LENGTH_SHORT);
                    tmsg.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
                    tmsg.show();
                }
                else{
                    member = Integer.parseInt(num.getText().toString());

                    WritingRequest writingRequest = new WritingRequest("1", title.getText().toString(), member, openkakao.getText().toString(), content.getText().toString(), gender, climbDate);
                    Call<String> call = ApiClient.getApiService().postWriting(writingRequest);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if(response.isSuccessful()){
                                int boardId = Integer.parseInt(response.body());
                                Intent intent = new Intent(getApplicationContext(), BoardDetailView.class);
                                intent.putExtra("boardId", boardId);
                                startActivity(intent);
                                finish();
                                Log.d("Tag", String.valueOf(boardId));
                            }
                            else{
                                Log.d("Tag", String.valueOf(response.code()));
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("Tag", String.valueOf(t));
                        }
                    });
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            //뒤로가기 버튼
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}