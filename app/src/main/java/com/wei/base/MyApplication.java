package com.wei.base;

import android.app.Application;
import android.content.Context;

/**
 * @author qyl
 * @data 2016-05-31
 * @info 自定义Application
 */
public class MyApplication extends Application {//Application

    /**
     * Filed Comment: 全局context.
     */
    private static Context sContext;


    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }


    /**
     * 获取全局Context.
     */
    public static Context getContext() {
        return sContext;
    }


}
