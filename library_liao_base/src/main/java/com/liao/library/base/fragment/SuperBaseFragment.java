package com.liao.library.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.library.base.fragment
 * @ClassName: SuperBaseFragment
 * @Description: 超级BaseFragment, 已做了懒加载处理
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/26 17:58
 */
public abstract class SuperBaseFragment extends Fragment {
    protected String TAG = this.getClass().getSimpleName();

    protected AppCompatActivity mActivity;

    protected View mContentView;

    /**
     * 是否已加载
     */
    protected boolean isLoaded = false;

    @Override
    public void onAttach(@NonNull Context context) {
        mActivity = (AppCompatActivity) context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getCreateView(inflater, container, savedInstanceState, getContentViewId());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContentView = view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //处理懒加载
        if (!isLoaded && !isHidden()) {
            isLoaded = true;
            lazyInit();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.mActivity = null;
        mContentView = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mActivity = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //注销EventBus
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * 是否注册EventBus
     * 保证Fragment中带有@Subscribe注解的方法，否则注册后会报错
     *
     * @param isRegister
     */
    public void registerFragmentEventBus(boolean isRegister) {
        if (isRegister) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 获取CreateView
     *
     * @return
     */
    protected abstract View getCreateView(@NonNull LayoutInflater inflater,
                                          @Nullable ViewGroup container,
                                          @Nullable Bundle savedInstanceState,
                                          @LayoutRes int layoutResID);

    /**
     * 获取布局id
     *
     * @return
     */
    protected abstract int getContentViewId();

    /**
     * 初始化
     */
    protected abstract void init(Bundle bundle);

    /**
     * 懒加载方法
     */
    protected void lazyInit() {
        init(getArguments());
    }
}
