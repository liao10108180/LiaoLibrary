package com.liao.librarydemo;

import com.liao.library.base.BaseViewModel;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.librarydemo
 * @ClassName: MainViewModel
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/26 17:31
 */
public class MainViewModel extends BaseViewModel {

    public void testToast(){
        toastLiveData.postValue("111111");
    }
}
