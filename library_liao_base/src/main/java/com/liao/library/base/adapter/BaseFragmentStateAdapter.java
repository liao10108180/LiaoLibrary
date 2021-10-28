package com.liao.library.base.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.library.base.adapter
 * @ClassName: BaseFragmentStateAdapter
 * @Description: 此适配器适用于ViewPager2实现Fragment
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/27 14:28
 */
public class BaseFragmentStateAdapter extends FragmentStateAdapter {
    private List<Fragment> mFragments;

    public BaseFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragments) {
        super(fragmentActivity);
        this.mFragments = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return mFragments.get(position);
    }

    @Override
    public int getItemCount() {
        if (mFragments == null) {
            return 0;
        } else {
            return mFragments.size();
        }
    }
}
