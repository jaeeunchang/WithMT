package com.cookandroid.withmt.Writing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cookandroid.withmt.ApiClient;
import com.cookandroid.withmt.BoardDetail.BoardDetailResponse;
import com.cookandroid.withmt.R;
import com.cookandroid.withmt.BoardDetail.BoardDetailView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WritingView extends AppCompatActivity {
    InputMethodManager imm;
    EditText title, content, num, openkakao;
    int gender, member, boardid;
    String climbDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writing);

        imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        title = (EditText) findViewById(R.id.title);
        content = (EditText) findViewById(R.id.content);
        num = (EditText) findViewById(R.id.num);
        openkakao = (EditText) findViewById(R.id.openkakao);
        Button btnEnter = (Button) findViewById(R.id.btnEnter);

        //제목에서 엔터 누를 경우 포커스 이동
        title.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && keyCode == KeyEvent.KEYCODE_ENTER){
                    num.requestFocus();
                    return true;
                }
                return false;
            }
        });

        //DatePickerDialog
        Button picDate = (Button) findViewById(R.id.picDate);
        TextView date = (TextView) findViewById(R.id.date);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(i, i1, i2);
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                date.setText(dateFormat.format(calendar.getTime()));
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

        Spinner spinner = (Spinner) findViewById(R.id.spinGender);
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

        Intent intent = getIntent();
        boardid = intent.getIntExtra("boardId", 0);
        Log.d("Tag", String.valueOf(boardid));
        //수정하기로 넘어왔을 때
        if(boardid != 0){
            // boarddetailview에서 글 가져오기
            ApiClient.getApiService().getBoard(boardid)
                    .enqueue(new Callback<List<BoardDetailResponse>>() {
                        @Override
                        public void onResponse(Call<List<BoardDetailResponse>> call, Response<List<BoardDetailResponse>> response) {
                            if(response.isSuccessful()){
                                List<BoardDetailResponse> res = response.body();
                                title.setText(res.get(0).getBoard().getTitle());
                                date.setText(res.get(0).getBoard().getDate());
                                num.setText(String.valueOf(res.get(0).getBoard().getMember()));
                                switch (res.get(0).getBoard().getGender()){
                                    case 0: spinner.setSelection(2); break;
                                    case 1: spinner.setSelection(1); break;
                                    case 2: spinner.setSelection(0); break;
                                }
                                content.setText(res.get(0).getBoard().getContent());
                                openkakao.setText(res.get(0).getBoard().getLink());
                            }
                        }

                        @Override
                        public void onFailure(Call<List<BoardDetailResponse>> call, Throwable t) {

                        }
                    });
            //등록(수정) > boarddetailview
            btnEnter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //내용 미입력 시 토스트
                    if(title.getText().toString().equals("")||
                            date.getText().toString().equals("")||
                            num.getText().toString().equals("")||
                            openkakao.getText().toString().equals("")||
                            content.getText().toString().equals("")){
                        Toast tmsg = Toast.makeText(WritingView.this, "빠진 부분 없이 전부 입력해주세요.", Toast.LENGTH_SHORT);
                        tmsg.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
                        tmsg.show();
                    }
                    else{
                        member = Integer.parseInt(num.getText().toString());
                        ApiClient.getApiService().putBoard(boardid, new WritingRequest(title.getText().toString(), member, openkakao.getText().toString(), content.getText().toString(), gender, date.getText().toString()))
                                .enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        if(response.isSuccessful()){
                                            Intent modifyIntent = new Intent(getApplicationContext(), BoardDetailView.class);
                                            modifyIntent.putExtra("boardId", boardid);
                                            modifyIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(modifyIntent);
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {

                                    }
                                });
                    }
                }
            });
        }else{
            //새글 등록 버튼>작성한 글
            btnEnter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //내용 미입력 시 토스트
                    if(title.getText().toString().equals("")||
                            date.getText().toString().equals("")||
                            num.getText().toString().equals("")||
                            openkakao.getText().toString().equals("")||
                            content.getText().toString().equals("")){
                        Toast tmsg = Toast.makeText(WritingView.this, "빠진 부분 없이 전부 입력해주세요.", Toast.LENGTH_SHORT);
                        tmsg.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
                        tmsg.show();
                    }
                    else{
                        member = Integer.parseInt(num.getText().toString());

                        ApiClient.getApiService().postWriting(new WritingRequest(title.getText().toString(), member, openkakao.getText().toString(), content.getText().toString(), gender, climbDate))
                            .enqueue(new Callback<Integer>() {
                                @Override
                                public void onResponse(Call<Integer> call, Response<Integer> response) {
                                    if(response.isSuccessful()){
                                        int boardId = response.body();
                                        Intent intent = new Intent(getApplicationContext(), BoardDetailView.class);
                                        intent.putExtra("boardId", boardId);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Log.d("Tag", String.valueOf(response.code()));
                                    }
                                }

                                @Override
                                public void onFailure(Call<Integer> call, Throwable t) {
                                    Log.d("Tag", String.valueOf(t));
                                }
                            });
                    }
                }
            });
        }



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

    //배경 클릭 시 키보드 숨김
    public void linearOnclick(View v){
        imm.hideSoftInputFromWindow(title.getWindowToken(),0);
        imm.hideSoftInputFromWindow(content.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(num.getWindowToken(),0);
        imm.hideSoftInputFromWindow(openkakao.getWindowToken(), 0);
    }

}