package com.hhly.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.add)
    Button mAdd;
    @BindView(R.id.delete_single)
    Button mDeleteSingle;
    @BindView(R.id.modify)
    Button mModify;
    @BindView(R.id.query_all)
    Button mQueryAll;
    @BindView(R.id.query_single)
    Button mQuerySingle;
    @BindView(R.id.delete_all)
    Button mDeleteAll;
    @BindView(R.id.recycleView)
    RecyclerView mRecycleView;
    @BindView(R.id.activity_main)
    LinearLayout mActivityMain;
    @BindView(R.id.query_some)
    Button mQuerySome;


    private int age = 1;
    private DBUtil mDbUtil;

    private List<User> mUsers = new ArrayList<>();
    private UserAdapter mUserAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mDbUtil = new DBUtil(this);
        for (int i = 0; i < 20; i++) {
            User user = new User(null, "张三", i + "");
            mDbUtil.insertUser(user);
        }

        mAdd.setOnClickListener(this);
        mDeleteSingle.setOnClickListener(this);
        mDeleteAll.setOnClickListener(this);
        mModify.setOnClickListener(this);
        mQuerySingle.setOnClickListener(this);
        mQueryAll.setOnClickListener(this);
        mQuerySome.setOnClickListener(this);

        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mUsers = mDbUtil.queryAll();
        mUserAdapter = new UserAdapter(mUsers);
        mRecycleView.setAdapter(mUserAdapter);

    }


    /**
     * 为防止空指针，需要有该数据。
     */
    @Override
    public void onClick(View v) {

        User user;

        switch (v.getId()) {
            case R.id.add:
                user = new User(null, "张三", (age++) + "");
                mDbUtil.insertUser(user);
                List<User> users1 = mDbUtil.queryAll();
                mUserAdapter.setUsers(users1);
                mRecycleView.scrollToPosition(users1.size()-1);
                break;
            case R.id.delete_single:
                mDbUtil.deleteOne(10);//删除id为10，先得确认里面有id为10的数据
                mUserAdapter.setUsers(mDbUtil.queryAll());
                break;
            case R.id.delete_all:
                mDbUtil.deleteAll();
                mUserAdapter.setUsers(mDbUtil.queryAll());
                age =1;
                break;
            case R.id.modify:
                mDbUtil.modify(10);//修改id为10的item
                mUserAdapter.setUsers(mDbUtil.queryAll());
                break;
            case R.id.query_single:
                User user1 = mDbUtil.queryOne(10);//查询id为10
                mUsers.clear();
                mUsers.add(user1);
                mUserAdapter.setUsers(mUsers);
                break;
            case R.id.query_all:
                mUserAdapter.setUsers(mDbUtil.queryAll());
                break;
            case R.id.query_some:
                List<User> users = mDbUtil.querySome(1, 10);//查询1-10的id
                if (!checkIsNull(users)) {
                    mUsers.clear();
                    mUsers.addAll(users);
                    mUserAdapter.setUsers(mUsers);
                }
                break;
        }
        mUserAdapter.notifyDataSetChanged();
    }


    /**
     * @return true 为Null
     * false 不为Null
     */
    public boolean checkIsNull(List<User> users) {
        if (users != null && users.size() > 0) {
            return false;
        }
        return true;
    }


}
