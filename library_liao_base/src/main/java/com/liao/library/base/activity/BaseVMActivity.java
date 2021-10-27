package com.liao.library.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.library.base
 * @ClassName: BaseVMActivity
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/26 17:12
 */
public abstract class BaseVMActivity<VM extends BaseViewModel, VDB extends ViewDataBinding> extends SuperBaseActivity {
    protected VM mViewModel;
    protected VDB mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VM vm = createViewModel();
        mViewModel = (VM) new ViewModelProvider(this, BaseViewModel.createViewModelFactory(vm)).get(vm.getClass());
        mBinding = DataBindingUtil.setContentView(this, getContentViewId());
        mBinding.setVariable(1, mViewModel);
        mBinding.setLifecycleOwner(this);
        mBinding.executePendingBindings();

        init(savedInstanceState);
        initObserver();
    }

    /**
     * 获取ViewModel实例
     *
     * @return
     */
    protected abstract VM createViewModel();

    /**
     * 初始化观察者
     */
    protected void initObserver() {
        mViewModel.toastLiveData.observe(this, s -> showToast(s));
    }

    /**
     * 显示Toast
     *
     * 如果需要使用其它Toast样式，请重写该方法后具体实现自己所需的Toast样式
     *
     * @param toastMsg
     */
    protected void showToast(String toastMsg) {
        Toast.makeText(this, toastMsg, Toast.LENGTH_SHORT).show();
    }
}
