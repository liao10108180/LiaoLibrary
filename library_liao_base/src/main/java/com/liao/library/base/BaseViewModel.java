package com.liao.library.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.liao.common.LiaoUtils;
import com.liao.library.http.HttpDisposableHelper;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.library.base
 * @ClassName: BaseViewModel
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/26 17:13
 */
public class BaseViewModel extends AndroidViewModel {
    protected String TAG = this.getClass().getSimpleName();

    /**
     * Toast LiveData
     */
    public MutableLiveData<String> toastLiveData = new MutableLiveData<>();
    public HttpDisposableHelper mDisposableHelper;

    public BaseViewModel() {
        super(LiaoUtils.getApp());
        mDisposableHelper = new HttpDisposableHelper();

    }

    public static <T extends BaseViewModel> ViewModelProvider.Factory createViewModelFactory(T viewModel) {
        return new ViewModelFactory(viewModel);
    }

    static class ViewModelFactory implements ViewModelProvider.Factory {
        BaseViewModel viewModel;

        public ViewModelFactory(BaseViewModel viewModel) {
            this.viewModel = viewModel;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) viewModel;
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposableHelper.unSubscribe();
        mDisposableHelper = null;
    }
}
