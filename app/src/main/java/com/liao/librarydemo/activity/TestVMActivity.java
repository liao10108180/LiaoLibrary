package com.liao.librarydemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.ListPopupWindow;

import com.liao.library.base.activity.BaseVMActivity;
import com.liao.librarydemo.R;
import com.liao.librarydemo.databinding.ActivityTestBinding;
import com.liao.librarydemo.viewmodel.TestViewModel;
import com.liao.librarydemo.widget.EditSelectPopup;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.librarydemo.activity
 * @ClassName: TestVMActivity
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/11/1 15:23
 */
public class TestVMActivity extends BaseVMActivity<TestViewModel, ActivityTestBinding> {

    private ListPopupWindow mListPopupWindow;

    @Override
    protected TestViewModel createViewModel() {
        return new TestViewModel();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_test;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        mBinding.et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("点击了EditText");
            }
        });

        mBinding.et.setOnFocusChangeListener((v, hasFocus) -> {
            Log.e(TAG, "hasFocus: " + hasFocus);
        });

        mBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.et.clearFocus();
            }
        });

        initListPopupWindow();

    }


    private void initListPopupWindow() {
        mListPopupWindow = new ListPopupWindow(this);

//        mListPopupWindow.setAdapter();
    }

}
