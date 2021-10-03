package com.cookandroid.withmt.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.cookandroid.withmt.R;

public class WrittenpageView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writtenpage);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

        Button btnjoin = (Button) findViewById(R.id.btnjoin);
        btnjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //잠시 글쓰기 페이지로 등록
                Intent intent = new Intent(getApplicationContext(), WritingView.class);
                startActivity(intent);
//                intent.setData(Uri.parse("URL"));
//                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.tool_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            //뒤로가기
            case android.R.id.home:
                finish();
                return true;
            //메뉴 삭제버튼
            case R.id.tool_del:
                AlertDialog.Builder dlg = new AlertDialog.Builder(WrittenpageView.this);
                dlg.setNegativeButton("취소", null);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}