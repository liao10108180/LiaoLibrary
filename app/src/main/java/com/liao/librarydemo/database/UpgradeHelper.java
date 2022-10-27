package com.liao.librarydemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.liao.librarydemo.dao.DaoMaster;
import com.liao.librarydemo.dao.FingerDTODao;
import com.liao.librarydemo.dao.LoginDTODao;

/**
 * @Author: LiaoZhenHui
 * @CreateDate: 2022-08-16
 * @Description: 数据库版本升级
 */
public class UpgradeHelper extends DaoMaster.OpenHelper {

    public UpgradeHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        MigrationHelper.migrate(db, LoginDTODao.class, FingerDTODao.class);
    }
}
