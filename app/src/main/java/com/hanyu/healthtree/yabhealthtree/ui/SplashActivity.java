package com.hanyu.healthtree.yabhealthtree.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.hanyu.healthtree.yabhealthtree.MainActivity;
import com.hanyu.healthtree.yabhealthtree.R;
import com.hanyu.healthtree.yabhealthtree.base.BaseActivity;
import com.hanyu.healthtree.yabhealthtree.global.GlobalParam;

/**
 * Created by xumingming on 2017/6/20.
 */

public class SplashActivity extends BaseActivity {

    private static final int SPLASH_TIME = 2000;



    @Override
    public void initView(Bundle savedInstanceState) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               start();
            }
        },SPLASH_TIME);
    }

    private void start(){
        intent = new Intent();
        log(GlobalParam.getOpen()+"");
        if(!GlobalParam.getOpen()){
            //如果是第一次下载 打开
            intent.setClass(SplashActivity.this, WelcomeActivity.class);
        }else{
            intent.setClass(SplashActivity.this, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected int getToolbarLayout() {
        return NO_ACTION_BAR;
    }
}
