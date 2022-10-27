package com.liao.librarydemo.database;

import com.liao.common.LiaoUtils;
import com.liao.librarydemo.dao.DaoMaster;
import com.liao.librarydemo.dao.DaoSession;

/**
 * @Author: LiaoZhenHui
 * @CreateDate: 2022-08-16
 * @Description: description class
 */
public class DaoHelper {

    private static DaoHelper mInstance;


    private static DaoMaster mDaoMaster;


    private static DaoSession mDaoSession;

    /**
     * 数据库名
     */
    private static final String DB_NAME = "ybzlkj.db";

    /**
     * 单例模式获得操作数据库对象
     *
     * @return
     */
    public static DaoHelper getInstance() {
        if (mInstance == null) {
            synchronized (DaoHelper.class) {
                if (mInstance == null) {
                    mInstance = new DaoHelper();
                }
            }
        }
        return mInstance;
    }

    /**
     * 判断是否有存在数据库，如果没有则创建
     *
     * @return
     */
    public DaoMaster getDaoMaster() {
        if (mDaoMaster == null) {
//            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(LiaoUtils.getApp().getApplicationContext(), DB_NAME, null);
            UpgradeHelper openHelper = new UpgradeHelper(LiaoUtils.getApp().getApplicationContext(), DB_NAME,null);
            mDaoMaster = new DaoMaster(openHelper.getWritableDb());
        }
        return mDaoMaster;
    }

    /**
     * 完成对数据库的添加、删除、修改、查询操作，仅仅是一个接口
     *
     * @return
     */
    public DaoSession getDaoSession() {
        if (mDaoSession == null) {
            if (mDaoMaster == null) {
                mDaoMaster = getDaoMaster();
            }
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }
}
