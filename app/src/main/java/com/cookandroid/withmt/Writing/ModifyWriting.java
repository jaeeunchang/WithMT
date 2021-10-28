package com.cookandroid.withmt.Writing;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.cookandroid.withmt.ApiClient;
import com.cookandroid.withmt.BoardDetail.BoardDetailResponse;
import com.cookandroid.withmt.BoardDetail.BoardDetailView;
import com.cookandroid.withmt.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyWriting extends AppCompatActivity {
    int gender, member, boardid;
    String climbDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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

        //이전 액티비티에서 boardid 가져옴
        Intent intent = getIntent();
        boardid = intent.getIntExtra("boardId", 0);

        // boarddetailview에서 글 가져오기
        ApiClient.getApiService().getBoard(boardid)
                .enqueue(new Callback<BoardDetailResponse>() {
                    @Override
                    public void onResponse(Call<BoardDetailResponse> call, Response<BoardDetailResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<BoardDetailResponse> call, Throwable t) {

                    }
                });

        //등록(수정) > boarddetailview
        Button btnEnter = (Button) findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiClient.getApiService().putBoard(boardid)
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if(response.isSuccessful()){
                                    Intent modifyIntent = new Intent(getApplicationContext(), BoardDetailView.class);
                                    modifyIntent.putExtra("boardId", boardid);
                                    setResult(RESULT_OK, modifyIntent);
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
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
