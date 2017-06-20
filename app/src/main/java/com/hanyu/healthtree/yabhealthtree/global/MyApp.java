package com.hanyu.healthtree.yabhealthtree.global;

import android.app.Application;
import android.content.Context;


/**
 * 自己应用的Application
 * Created by Administrator on 2017/4/15.
 */

public class MyApp extends Application{

    public static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

    }
}
