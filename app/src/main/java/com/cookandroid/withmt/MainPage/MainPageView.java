package com.cookandroid.withmt.MainPage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cookandroid.withmt.ApiClient;
import com.cookandroid.withmt.MyPage.MyPageView;
import com.cookandroid.withmt.R;
import com.cookandroid.withmt.Writing.WritingView;
import com.cookandroid.withmt.WrittenPage.WrittenpageView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPageView extends AppCompatActivity {

    MyAdapter adapter;
    Button btn_mypage, btn_menu, btn_write;
    EditText edit_search;
    Spinner menu_spinner;
    TextView filter_date;
    DatePickerDialog datePickerDialog;
    ArrayList<WritingList> li, search_li;
    ListView lv_board;
    String userid, userpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        li = new ArrayList<WritingList>();


        adapter = new MyAdapter(getApplicationContext(), R.layout.main_list, li);
        ListView lv_board = (ListView) findViewById(R.id.lv_board);

        EditText edit_search = (EditText) findViewById(R.id.edit_search);
        TextView filter_date = (TextView) findViewById(R.id.filter_date);
        Button btn_mypage = (Button) findViewById(R.id.btn_mypage);
        Button btn_search = (Button) findViewById(R.id.btn_search);
        Button btn_calendar = (Button) findViewById(R.id.btn_calendar);
        Button btn_write = (Button) findViewById(R.id.btn_write);

        //스피너 setting
        Spinner menu_spinner = (Spinner) findViewById(R.id.menu_spinner);
        String[] list = getResources().getStringArray(R.array.main_menu);
        menu_spinner.setSelection(0);

        menu_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //추천순 선택시
                if (list[i].equals("추천순")) {
//                    Toast.makeText(getApplicationContext(), list[i] + " 선택되었습니다", Toast.LENGTH_SHORT).show();

                    Call <List<BoardResponse>> call = ApiClient.getApiService().getRecommend();
                    call.enqueue(new Callback<List<BoardResponse>>() {
                        @Override
                        public void onResponse(Call<List<BoardResponse>> call, Response<List<BoardResponse>> response) {
                            if(!response.isSuccessful()) {
                                Log.d("TagMain", String.valueOf(response.code()));
                                return;
                            }
//                            Log.d("TagMain", String.valueOf(response.code()));
//                            Log.d("Tag","추천순: "+response.body());

                            List<BoardResponse> list = response.body();
                            li.clear();
                            adapter.notifyDataSetChanged();

                            for(BoardResponse board : list) {
                                //서버 데이터 받아오기
                                Integer boardId = board.getBoardId();
                                String title_server = board.getTitle();
                                String date_server = board.getDate();
                                Integer gender_server = board.getGender();
                                String imoji_server = board.getImoji();
                                String nickname_server = board.getNickname();

                                //성별 값 텍스트로 변환
                                String gender = "";
                                if(gender_server == 0) {
                                    gender = "남자만";
                                } else if(gender_server == 1) {
                                    gender = "여자만";
                                } else if(gender_server == 2) {
                                    gender = "상관없음";
                                }

                                //이모지 변환
                                String imoji = "";
                                if(imoji_server.equals("BEAR")) {
                                    imoji = "🐻";
                                } else if(imoji_server.equals("TIGER")) {
                                    imoji = "🐯";
                                } else if(imoji_server.equals("RABBIT")) {
                                    imoji = "🐰";
                                } else if(imoji_server.equals("FOX")) {
                                    imoji = "🦊";
                                } else {
                                    imoji = "😊";
                                }
                                li.add(new WritingList(boardId,title_server,date_server, gender, imoji, nickname_server));
                                lv_board.setAdapter(adapter);
                            }
                        }
                        @Override
                        public void onFailure(Call<List<BoardResponse>> call, Throwable t) {
                            Log.d("TagMain", t.getMessage());

                        }
                    });

                    //최신순 선택시
                } else if (list[i].equals("최신순")) {
//                    Toast.makeText(getApplicationContext(), list[i] + " 선택되었습니다", Toast.LENGTH_SHORT).show();
                    Call<List<BoardResponse>> call = ApiClient.getApiService().getAll();
                    call.enqueue(new Callback<List<BoardResponse>>() {
                        @Override
                        public void onResponse(Call<List<BoardResponse>> call, Response<List<BoardResponse>> response) {
                            if(!response.isSuccessful()) {
                                Log.d("TagMain", String.valueOf(response.code()));
                                return;
                            }
//                            Log.d("TagMain", String.valueOf(response.code()));
//                            Log.d("Tag","최신순: "+response.body());

                            List<BoardResponse> list = response.body();
                            li.clear();
                            adapter.notifyDataSetChanged();

                            for(BoardResponse board : list) {
                                //서버 데이터 받아오기
                                Integer boardId = board.getBoardId();
                                String title_server = board.getTitle();
                                String date_server = board.getDate();
                                Integer gender_server = board.getGender();
                                String imoji_server = board.getImoji();
                                String nickname_server = board.getNickname();

                                //성별 값 텍스트로 변환
                                String gender = "";
                                if(gender_server == 0) {
                                    gender = "남자만";
                                } else if(gender_server == 1) {
                                    gender = "여자만";
                                } else if(gender_server == 2) {
                                    gender = "상관없음";
                                }

                                //이모지 변환
                                String imoji = "";
                                if(imoji_server.equals("BEAR")) {
                                    imoji = "🐻";
                                } else if(imoji_server.equals("TIGER")) {
                                    imoji = "🐯";
                                } else if(imoji_server.equals("RABBIT")) {
                                    imoji = "🐰";
                                } else if(imoji_server.equals("FOX")) {
                                    imoji = "🦊";
                                } else {
                                    imoji = "😊";
                                }

                                li.add(new WritingList(boardId,title_server,date_server, gender, imoji, nickname_server));
                                lv_board.setAdapter(adapter);
                            }
                        }
                        @Override
                        public void onFailure(Call<List<BoardResponse>> call, Throwable t) {
                            Log.d("TagMain", t.getMessage());
                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "아무것도 선택되지 않았습니다.", Toast.LENGTH_LONG).show();
            }
        });

        lv_board.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 다음넘어갈 화면
                Intent intent = new Intent(getApplicationContext(), WrittenpageView.class);

                //boardId값 넘기기
                intent.putExtra("boardId", li.get(position).boardId);

                startActivity(intent);
            }
        });

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                filter_date.setVisibility(View.VISIBLE);
                filter_date.setText(year+"-"+(month+1)+"-"+dayOfMonth);
            }
        }, mYear, mMonth, mDay);

        //datepicker 초기화 버튼
        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "초기화", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                filter_date.setVisibility(View.INVISIBLE);
                filter_date.setText("2021-00-00");
            }
        });

        search_li = new ArrayList<WritingList>();
        search_li.addAll(li);

        //검색 기능
        edit_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = edit_search.getText().toString();
                search(text);
            }
        });

        btn_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyPageView.class);
                startActivity(intent);
            }
        });

        btn_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WritingView.class);
                startActivity(intent);
            }
        });
    }

    class WritingList {
        Integer boardId;
        String title = "";
        String date = "";
        String gender;
        String imoji = "";
        String nickname = "";

        public WritingList(Integer boardId, String title, String date, String gender, String imoji, String nickname) {
            super();
            this.boardId = boardId;
            this.title = title;
            this.date = date;
            this.gender = gender;
            this.imoji = imoji;
            this.nickname = nickname;
        }

        public WritingList() {
        }
    }

    // 검색을 수행하는 메소드
    public void search(String charText) {

//        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
//        search_li.clear();
//
//        // 문자 입력이 없을때는 모든 데이터를 보여준다.
//        if (charText.length() == 0) {
//            search_li.addAll(li);
//        }
//        // 문자 입력을 할때..
//        else
//        {
//            // 리스트의 모든 데이터를 검색한다.
//            for(int i = 0;i < li.size(); i++)
//            {
//                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
//                if (li.get(i).toLowerCase().contains(charText))
//                {
//                    // 검색된 데이터를 리스트에 추가한다.
//                    search_li.add(li.get(i));
//                }
//            }
//        }
//        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
//        adapter.notifyDataSetChanged();
    }
}