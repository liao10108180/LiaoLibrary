package com.liao.librarydemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.ListPopupWindow;

import com.liao.library.base.activity.BaseVMActivity;
import com.liao.librarydemo.PinYinUtils;
import com.liao.librarydemo.R;
import com.liao.librarydemo.adapter.EditSelectAdapter;
import com.liao.librarydemo.databinding.ActivityTestBinding;
import com.liao.librarydemo.viewmodel.TestViewModel;
import com.liao.librarydemo.widget.EditSelectPopup;

import java.util.ArrayList;
import java.util.List;

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

    private List<String> list = new ArrayList<>();

    private EditSelectPopup<String> editSelectPopup;

    private PinYinUtils<String> pinYinUtils;

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

//        mBinding.et.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editSelectPopup.showAsDropDown(mBinding.et);
//            }
//        });
//
        mBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                editSelectPopup.show();
                List<String> result = pinYinUtils.matchList(mBinding.et.getText().toString().trim());

                Log.e(TAG, "onClick: " + result.toString());
            }
        });
//
//
//        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
//            @Override
//            public boolean queueIdle() {
//                initEditPopup();
//
//                list.add("11111");
//                list.add("22222");
//                list.add("33333");
//                list.add("44444");
//
//                editSelectPopup.setList(list);
//                return false;
//            }
//        });


        list.add("HIB疫苗病毒");
        list.add("张三");
        list.add("李四");
        list.add("沉鱼");
        list.add("落雁");

        pinYinUtils = new PinYinUtils.Builder(list)
                .matchContent(new PinYinUtils.MatchContent<String>() {
                    @Override
                    public String matchContent(String data) {
                        return data;
                    }
                }).build();


    }

    private void initEditPopup() {
        editSelectPopup = new EditSelectPopup.Builder(this, mBinding.et)
                .setItemContent(new EditSelectAdapter.ItemContent<String>() {
                    @Override
                    public String showItemText(String data) {
                        return data;
                    }
                }).setOnClickListener(new EditSelectPopup.ClickListener<String>() {
                    @Override
                    public void onEditClick(View v) {

                    }

                    @Override
                    public void onItemClick(String data) {

                    }
                }).build();
    }


}
