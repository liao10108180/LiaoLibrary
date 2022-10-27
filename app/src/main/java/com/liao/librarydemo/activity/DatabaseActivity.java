package com.liao.librarydemo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.liao.library.base.activity.BaseVMActivity;
import com.liao.librarydemo.R;
import com.liao.librarydemo.bean.dto.FingerDTO;
import com.liao.librarydemo.bean.dto.LoginDTO;
import com.liao.librarydemo.dao.LoginDTODao;
import com.liao.librarydemo.database.DaoHelper;
import com.liao.librarydemo.databinding.ActivityDatabaseBinding;
import com.liao.librarydemo.viewmodel.DatabaseViewModel;

import java.util.List;

/**
 * @Author: LiaoZhenHui
 * @CreateDate: 2022-08-16
 * @Description: description class
 */
public class DatabaseActivity extends BaseVMActivity<DatabaseViewModel, ActivityDatabaseBinding> {


    @Override
    protected DatabaseViewModel createViewModel() {
        return new DatabaseViewModel();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_database;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        mBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        mBinding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<LoginDTO> list = DaoHelper.getInstance().getDaoSession().getLoginDTODao().queryBuilder().list();
                Log.i(TAG, list.toString());
                for (LoginDTO loginDTO : list) {
                    if (loginDTO.getAccount().equals("ttt")) {
                        String decodePw = new String(Base64.decode(loginDTO.getPassword(), Base64.DEFAULT));
                        Log.i(TAG, "decodePw: " + decodePw);
                    }
                }
            }
        });

        mBinding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看是否有相同账号的记录
                LoginDTO loginDTO = DaoHelper.getInstance().getDaoSession().getLoginDTODao().queryBuilder()
                        .where(LoginDTODao.Properties.Account.eq("www")).unique();

                Log.i(TAG, loginDTO == null ? "null" : loginDTO.toString());
                if (loginDTO == null) {
                    return;
                }

                loginDTO.setPassword("bbb");
                DaoHelper.getInstance().getDaoSession().getLoginDTODao().save(loginDTO);

            }
        });

        mBinding.btnFinger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FingerDTO fingerDTO = new FingerDTO();
                fingerDTO.setFingerSign(System.currentTimeMillis() + "");

                DaoHelper.getInstance().getDaoSession().getFingerDTODao().save(fingerDTO);

                Log.i(TAG, DaoHelper.getInstance().getDaoSession().getFingerDTODao().queryBuilder().list().toString());
            }
        });

        TextUtils.join(",", DaoHelper.getInstance().getDaoSession().getFingerDTODao().queryBuilder().list());

    }

    private void save() {
        if (TextUtils.isEmpty(mBinding.etAccount.getText().toString().trim())) {
            showToast("账号不可空");
            return;
        }
        if (TextUtils.isEmpty(mBinding.etPassword.getText().toString().trim())) {
            showToast("密码不可空");
            return;
        }

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setAccount(mBinding.etAccount.getText().toString().trim());

        String encodePw = Base64.encodeToString(mBinding.etPassword.getText().toString().trim().getBytes(), Base64.DEFAULT);
        loginDTO.setPassword(encodePw);
        loginDTO.setDepartment("美中");
        loginDTO.setDepartmentCode("4403050269");

        DaoHelper.getInstance().getDaoSession().getLoginDTODao().save(loginDTO);

    }
}
