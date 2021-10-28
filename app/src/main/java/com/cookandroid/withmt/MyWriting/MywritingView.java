package com.cookandroid.withmt.MyWriting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.cookandroid.withmt.ApiClient;
import com.cookandroid.withmt.R;
import com.cookandroid.withmt.BoardDetail.BoardDetailView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MywritingView extends AppCompatActivity {
    String userid;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mywriting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        ListView myList = (ListView) findViewById(R.id.myList);
        LinearLayout nothingList = (LinearLayout) findViewById(R.id.nothingList);

        SharedPreferences userinfo = getSharedPreferences("userinfo", Activity.MODE_PRIVATE);
        userid = userinfo.getString("inputId", "x");

        Call<List<MyWritingResponse>> call = ApiClient.getApiService().getMyWriting(userid);
        call.enqueue(new Callback<List<MyWritingResponse>>() {
            @Override
            public void onResponse(Call<List<MyWritingResponse>> call, Response<List<MyWritingResponse>> response) {
                if(response.isSuccessful()){
                    Log.d("Tag", String.valueOf(response.code()));

                    List<MyWritingResponse> array = response.body();
                    ArrayList<String> titleList = new ArrayList<>();

                    if(array.size()==0){
                        nothingList.setVisibility(View.VISIBLE);
                        myList.setVisibility(View.GONE);
                    }
                    else{
                        nothingList.setVisibility(View.GONE);
                        myList.setVisibility(View.VISIBLE);
                    }

                    for(MyWritingResponse re : array){
                        titleList.add(re.getTitle());
                    }
                    adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, titleList);
                    myList.setAdapter(adapter);
                    myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Log.d("Tag", String.valueOf(array.get(position).getBoardId()));
                            Intent intent = new Intent(getApplicationContext(), BoardDetailView.class);
                            intent.putExtra("boardId", array.get(position).getBoardId());
                            startActivity(intent);
                        }
                    });
                }
                else{
                    Log.d("Tag", String.valueOf(response.code()));
                    nothingList.setVisibility(View.VISIBLE);
                    myList.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<MyWritingResponse>> call, Throwable t) {
                nothingList.setVisibility(View.VISIBLE);
                myList.setVisibility(View.GONE);
                Log.e("Tag", String.valueOf(t));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}