package com.liao.library.base.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.liao.library.utils.KeyboardUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.library.base
 * @ClassName: SuperBaseActivity
 * @Description: 超级BaseActivity
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/26 14:38
 */
public abstract class SuperBaseActivity extends AppCompatActivity {
    protected String TAG = this.getClass().getSimpleName();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销EventBus
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * 获取布局id
     *
     * @return
     */
    protected abstract int getContentViewId();

    /**
     * 初始化
     */
    protected abstract void init(Bundle savedInstanceState);

    /**
     * 是否注册EventBus
     * 保证Activity中带有@Subscribe注解的方法，否则注册后会报错
     *
     * @param isRegister
     */
    public void registerActivityEventBus(boolean isRegister) {
        if (isRegister) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                KeyboardUtils.hideSoftInput(this);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据View是否为EditText来决定是否隐藏软键盘
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if ((v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationOnScreen(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getRawX() > left && event.getRawX() < right
                    && event.getRawY() > top && event.getRawY() < bottom);
        }
        return false;
    }
}
