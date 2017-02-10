package com.hhly.greendaodemo;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 frank
 * @时间 2017/2/10 10:30
 * @描述：${TODO}
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<User> mUsers = new ArrayList<>();

    public UserAdapter(List<User> users) {

        mUsers.clear();
        mUsers.addAll(users);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_useradapter,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mAge.setText(mUsers.get(position).getAge());
        holder.mKey.setText(mUsers.get(position).getId()+"");
        holder.mName.setText(mUsers.get(position).getUsername());

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mKey;
        private TextView mAge;

        public ViewHolder(View itemView) {
            super(itemView);

            mKey = (TextView) itemView.findViewById(R.id.key);
            mName = (TextView) itemView.findViewById(R.id.name);
            mAge = (TextView) itemView.findViewById(R.id.age);

        }
    }

    public void setUsers(List<User> users){

        mUsers.clear();
        mUsers.addAll(users);
    }



}
