package com.liao.librarydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hjq.toast.ToastUtils;
import com.liao.library.base.activity.BaseBindingActivity;
import com.liao.library.utils.BarUtils;
import com.liao.librarydemo.R;
import com.liao.librarydemo.adapter.MainAdapter;
import com.liao.librarydemo.databinding.ActivityMainBinding;
import com.liao.librarydemo.entity.MainEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseBindingActivity<ActivityMainBinding> {
    private List<MainEntity> mEntityList;

    private MainAdapter mAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        setItemEntity();

        mAdapter = new MainAdapter(mEntityList);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                MainEntity entity = (MainEntity) adapter.getData().get(position);
                if (entity.getClazz() != null) {
                    //跳转页面
                    startActivity(new Intent(MainActivity.this, entity.getClazz()));
                }
            }
        });
        mBinding.recyclerView.setAdapter(mAdapter);

    }

    private void setItemEntity() {
        mEntityList = new ArrayList<>();
        mEntityList.add(new MainEntity(true, "Base", null));
        mEntityList.add(new MainEntity("BaseActivity", null));
        mEntityList.add(new MainEntity("BaseBindingActivity", WebActivity.class));
        mEntityList.add(new MainEntity("BaseVMActivity", TestVMActivity.class));
        mEntityList.add(new MainEntity("BaseFragment", null));
        mEntityList.add(new MainEntity("BaseBindingFragment", null));
        mEntityList.add(new MainEntity("BaseVMFragment", null));
        mEntityList.add(new MainEntity("WebSocketActivity", WebSocketActivity.class));
        mEntityList.add(new MainEntity("DatabaseActivity", DatabaseActivity.class));

        mEntityList.add(new MainEntity(true, "Http", null));
        mEntityList.add(new MainEntity("HttpTest", null));

        mEntityList.add(new MainEntity(true, "Utils", null));
        mEntityList.add(new MainEntity("ActivityUtils", null));
        mEntityList.add(new MainEntity("BarUtils", null));
        mEntityList.add(new MainEntity("TimeUtils", null));
    }

}