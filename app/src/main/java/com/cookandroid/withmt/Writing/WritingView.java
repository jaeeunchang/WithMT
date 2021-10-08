package com.cookandroid.withmt.Writing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cookandroid.withmt.R;
import com.cookandroid.withmt.WrittenPage.WrittenpageView;

import java.util.Calendar;

public class WritingView extends AppCompatActivity {

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
        EditText substacne = (EditText) findViewById(R.id.substance);
        EditText num = (EditText) findViewById(R.id.num);
        EditText openkakao = (EditText) findViewById(R.id.openkakao);

        //DatePickerDialog
        Button picDate = (Button) findViewById(R.id.picDate);
        TextView date = (TextView) findViewById(R.id.date);

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDate = c.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                date.setText(i+"년 "+(i1+1)+"월 "+i2+"일");
            }
        }, mYear, mMonth, mDate);

        picDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinSex);
        final String[] sex = {"성별 무관", "여성만", "남성만"};
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sex);
        spinner.setAdapter(adapter);

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
                        substacne.getText().toString().equals("")){
                    Toast tmsg = Toast.makeText(WritingView.this, "빠진 부분 없이 전부 입력해주세요.", Toast.LENGTH_SHORT);
                    tmsg.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
                    tmsg.show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), WrittenpageView.class);
                    finish();
                    startActivity(intent);
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