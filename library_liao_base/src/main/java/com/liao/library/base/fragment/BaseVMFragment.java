package com.liao.library.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.liao.library.base.BaseViewModel;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.library.base.fragment
 * @ClassName: BaseVMFragment
 * @Description: 适用于MVVM的BaseFragment
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/27 10:35
 */
public abstract class BaseVMFragment<VM extends BaseViewModel, VDB extends ViewDataBinding> extends SuperBaseFragment {

    protected VM mViewModel;
    protected VDB mBinding;

    @Override
    protected View getCreateView(@NonNull LayoutInflater inflater,
                                 @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState,
                                 int layoutResID) {

        VM vm = createViewModel();
        mViewModel = (VM) new ViewModelProvider(this, BaseViewModel.createViewModelFactory(vm)).get(vm.getClass());
        mBinding = DataBindingUtil.inflate(inflater, layoutResID, container, false);
        mBinding.setVariable(1, mViewModel);
        mBinding.setLifecycleOwner(this);
        mBinding.executePendingBindings();
        return mBinding.getRoot();
    }

    @Override
    protected void lazyInit() {
        super.lazyInit();
        //初始化观察者
        initObserver();

    }

    /**
     * 初始化观察者
     */
    protected void initObserver() {
        mViewModel.toastLiveData.observe(this, s -> showToast(s));
    }

    /**
     * 获取ViewModel实例
     *
     * @return
     */
    protected abstract VM createViewModel();

    /**
     * 显示Toast
     * <p>
     * 如果需要使用其它Toast样式，请重写该方法后具体实现自己所需的Toast样式
     *
     * @param toastMsg
     */
    protected void showToast(String toastMsg) {
        Toast.makeText(getContext(), toastMsg, Toast.LENGTH_SHORT).show();
    }

}
