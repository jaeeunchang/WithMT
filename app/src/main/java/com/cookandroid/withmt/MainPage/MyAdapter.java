package com.cookandroid.withmt.MainPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cookandroid.withmt.R;

import java.util.ArrayList;

class MyAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<MainPageView.WritingList> li;
    LayoutInflater inf;

    public MyAdapter(Context context, int layout, ArrayList<MainPageView.WritingList> li) {
        this.context = context;
        this.layout = layout;
        this.li = li;
        inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return li.size();
    }

    @Override
    public Object getItem(int position) {
        return li.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inf.inflate(R.layout.main_list, null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        ImageView calender_icon = (ImageView) convertView.findViewById(R.id.calender_icon);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView gender_info = (TextView) convertView.findViewById(R.id.gender_info);
        TextView gender = (TextView) convertView.findViewById(R.id.gender);
        TextView imoji = (TextView) convertView.findViewById(R.id.user_icon);
        TextView nickname = (TextView) convertView.findViewById(R.id.user);

        MainPageView.WritingList w = li.get(position);
        title.setText(w.title);
        date.setText(w.date);
        gender.setText(w.gender);
        imoji.setText(w.imoji);
        nickname.setText(w.nickname);

        return convertView;
    }
}