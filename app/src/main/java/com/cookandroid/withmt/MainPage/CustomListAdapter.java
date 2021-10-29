//package com.cookandroid.withmt.MainPage;
//
//import android.content.ClipData;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CustomListAdapter extends BaseAdapter {
//
//    private Context context;
//    private List<BoardResponse> boardList;
//
//    public CustomListAdapter(Context context) {
//        this.context = context;
//        this.boardList = new ArrayList<>();
//    }
//
//    public void updateItem(List<BoardResponse> boardList) {
//        this.boardList = boardList;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public int getCount() {
//        return boardList.size();
////        return 0;
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return boardList.get(i);
////        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
////        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        View cView = view;
//        if (cView == null) {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            cView = inflater.inflate(R.layout.lv_board, viewGroup, false);
//
//        }
//        Board board = boardList.get(i);
//    }
//
//}
