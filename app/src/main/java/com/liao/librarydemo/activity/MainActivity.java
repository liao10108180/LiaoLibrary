package com.liao.librarydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.liao.library.base.activity.BaseBindingActivity;
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
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                MainEntity entity = (MainEntity) adapter.getData().get(position);
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
            }
        });
        mBinding.recyclerView.setAdapter(mAdapter);

    }

    private void setItemEntity() {
        mEntityList = new ArrayList<>();
        mEntityList.add(new MainEntity(true, "Base", 0x01));
        mEntityList.add(new MainEntity("BaseActivity", 0x11));
        mEntityList.add(new MainEntity("BaseBindingActivity", 0x12));
        mEntityList.add(new MainEntity("BaseVMActivity", 0x13));
        mEntityList.add(new MainEntity("BaseFragment", 0x14));
        mEntityList.add(new MainEntity("BaseBindingFragment", 0x15));
        mEntityList.add(new MainEntity("BaseVMFragment", 0x16));

        mEntityList.add(new MainEntity(true, "Http", 0x20));
        mEntityList.add(new MainEntity("HttpTest", 0x21));

        mEntityList.add(new MainEntity(true, "Utils", 0x30));
        mEntityList.add(new MainEntity("ActivityUtils", 0x31));
        mEntityList.add(new MainEntity("BarUtils", 0x32));
        mEntityList.add(new MainEntity("TimeUtils", 0x33));
    }

}