package com.example.contactapp;


import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyCustomAdapter extends BaseAdapter {

    private Context mContext;

    private List<UserInfo> userList = new ArrayList<UserInfo>();
    private ArrayList<UserInfo> arraylist;


    public MyCustomAdapter(@NonNull Context context, List<UserInfo> list) {
        mContext = context;
        userList = list;
        arraylist = new ArrayList<>();
        arraylist.addAll(userList);
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.userinfolayout,null);
            holder = new ViewHolder();
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.myImage = view.findViewById(R.id.imageID);
        holder.nameInfo = view.findViewById(R.id.nameID);
        holder.descInfo = view.findViewById(R.id.descID);

        holder.nameInfo.setText(userList.get(position).name);
        holder.descInfo.setText(userList.get(position).desc);
        holder.myImage.setImageResource(userList.get(position).imageID);
        return view;
    }

        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            userList.clear();
            if (charText.length() == 0) {
                userList.addAll(arraylist);
            } else {
                for (UserInfo wp : arraylist) {
                    if (wp.name.toLowerCase(Locale.getDefault()).contains(charText)) {
                        userList.add(wp);
                    }
                }
            }
            notifyDataSetChanged();
    }

    class ViewHolder{
        ImageView myImage ;
        TextView nameInfo;
        TextView descInfo ;
    }
}