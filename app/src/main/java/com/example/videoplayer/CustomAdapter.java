package com.example.videoplayer;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private ArrayList<playerObjects> playerObjectsList = new ArrayList<>();
    private Context context;
    public CustomAdapter(Context context, ArrayList<playerObjects> list){
        this.playerObjectsList = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return playerObjectsList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       if(convertView == null){
           LayoutInflater layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = layoutInflater.inflate(R.layout.list_adapter,null);
       }
        TextView textView = (TextView)convertView.findViewById(R.id.textView);
       textView.setText("URL:-"+playerObjectsList.indexOf(playerObjectsList.get(position)));
       if(playerObjectsList.get(position).isPlaying){
           textView.setBackgroundColor(Color.parseColor("#4d90fe"));
       }
       else {
           textView.setBackgroundColor(Color.parseColor("#00000000"));
       }
        return convertView;
    }
}
