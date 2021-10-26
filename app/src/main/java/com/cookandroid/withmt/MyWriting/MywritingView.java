package com.cookandroid.withmt.MyWriting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.cookandroid.withmt.ApiClient;
import com.cookandroid.withmt.R;

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
        userid = userinfo.getString("inputId", "");

        Call<List<MyWritingResponse>> call = ApiClient.getApiService().getMyWriting(userid);
        call.enqueue(new Callback<List<MyWritingResponse>>() {
            @Override
            public void onResponse(Call<List<MyWritingResponse>> call, Response<List<MyWritingResponse>> response) {
                if(response.isSuccessful()){
                    Log.d("Tag", String.valueOf(response.headers()));
                    nothingList.setVisibility(View.GONE);
                    myList.setVisibility(View.VISIBLE);

                    List<MyWritingResponse> array = response.body();
                    ArrayList<String> listItem = new ArrayList<>();

                    for(MyWritingResponse re : array){
                        listItem.add(re.getTitle());
                    }
                    adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listItem);
                    myList.setAdapter(adapter);
                }
                else{
                    Log.d("Tag", String.valueOf(response.headers()));
                }
            }

            @Override
            public void onFailure(Call<List<MyWritingResponse>> call, Throwable t) {
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