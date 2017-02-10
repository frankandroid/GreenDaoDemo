package com.hhly.greendaodemo;

import android.content.Context;

import com.hhly.greendaodemo.gen.DaoMaster;
import com.hhly.greendaodemo.gen.DaoSession;
import com.hhly.greendaodemo.gen.UserDao;

import java.util.List;

/**
 * @创建者 frank
 * @时间 2017/2/10 11:01
 * @描述：${TODO}
 */

public class DBUtil {

    private DaoSession mDaoSession;
    private UserDao mUserDao;
    public DBUtil(Context context) {

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "user.db",
                null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        mDaoSession = daoMaster.newSession();
        mUserDao = mDaoSession.getUserDao();
    }

    /**
     * 增
     */
    public void insertUser(User user) {
        mUserDao.insert(user);
    }

    /**
     * 删一个
     */
    public void deleteOne(int id) {
        User user = mUserDao.queryBuilder().where(UserDao.Properties.Id.eq(id)).build().unique();
        mUserDao.delete(user);
    }

    /**
     * 删全部
     */
    public void deleteAll() {
        mUserDao.deleteAll();
    }

    /**
     * @param id 需要更新的age值。
     * 改
     */
    public void modify(int id) {

        User user = mUserDao.queryBuilder().where(UserDao.Properties.Id.eq(id)).build().unique();
        user.setUsername("张三被修改成了李四");
        mUserDao.update(user);
    }


    /**
     * 查一个
     */
    public User queryOne(int id) {

        User user = mUserDao.queryBuilder().where(UserDao.Properties.Id.eq(id)).build().unique();
        return user;
    }

    /**
     * 查询一批
     * @param startId 起始id
     * @param endId 结束id
     *
     */
    public List<User> querySome(int startId,int endId) {

        List<User> users = mUserDao.queryBuilder().where(UserDao.Properties.Id.between(startId,
                endId)).build().list();
        return users;
    }

    /**
     * 查全部
     */
    public List<User> queryAll() {
        return mUserDao.loadAll();
    }
}
