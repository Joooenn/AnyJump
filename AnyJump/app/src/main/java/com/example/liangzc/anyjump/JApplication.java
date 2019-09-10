package com.example.liangzc.anyjump;

import android.app.Application;
import android.content.Context;

import com.example.liangzc.anyjump.anyJump.JActivityManager;

/**
 * Created by liangzc on 2019/1/11.
 */

public class JApplication extends Application {

    private Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        // 初始化跳转管理模块
        JActivityManager.getInstance().init(this);
    }

    public Context getmContext() {
        return mContext;
    }
}
