package com.cookandroid.withmt.WrittenPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.cookandroid.withmt.R;
import com.cookandroid.withmt.Writing.WritingView;

public class WrittenpageView extends AppCompatActivity {
    Dialog CheckDel;

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
                Uri uri = Uri.parse("");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
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
            case R.id.tool_end:


            case R.id.tool_modify:
                Intent intent = new Intent(getApplicationContext(), WritingView.class);
                startActivity(intent);
                return true;

            case R.id.tool_del:
                CheckDel = new Dialog(WrittenpageView.this);
                CheckDel.requestWindowFeature(Window.FEATURE_NO_TITLE);
                CheckDel.setContentView(R.layout.delete_dialog);
                showDialogDel();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showDialogDel(){
        CheckDel.show();
        CheckDel.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnNo:
                CheckDel.dismiss();
                break;
            case R.id.btnOk:
                finish();
        }
    }
}