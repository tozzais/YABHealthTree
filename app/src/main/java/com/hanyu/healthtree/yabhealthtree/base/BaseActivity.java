package com.hanyu.healthtree.yabhealthtree.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hanyu.healthtree.yabhealthtree.R;
import com.hanyu.healthtree.yabhealthtree.global.MyApp;
import com.hanyu.healthtree.yabhealthtree.util.toast.ToastCommom;
import com.hanyu.healthtree.yabhealthtree.widget.ProgressLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;


public abstract class BaseActivity<T> extends AppCompatActivity {

    //不需要标题栏的时候 getToolbarLayout方法返回 -1 即可；
    public static final int NO_ACTION_BAR = -1;


    public  String TAG = "";

    private Toolbar mToolbar;
    public LinearLayout parentView;
    public TextView mTitle;
    protected FrameLayout mFlContainer;
    protected Context mContext;
    protected Activity mActivity;
    protected ProgressLayout progress_layout;
    protected AppBarLayout mHeaderView;
    protected Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        setTheme(GlobalParams.getTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        //设置只能屏幕方向
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        mContext = this;
        mActivity = this;

        TAG = getClass().getName();


        progress_layout = (ProgressLayout) findViewById(R.id.progress_layout);
        mHeaderView = (AppBarLayout) findViewById(R.id.layout_header);
        mFlContainer = (FrameLayout) findViewById(R.id.content_container);
        parentView = (LinearLayout) findViewById(R.id.parent_view);


        int toolbarLayoutRes = getToolbarLayout();
        if (toolbarLayoutRes >= 0){
            //添加toolbar
            mHeaderView.setVisibility(View.VISIBLE);
            mHeaderView.addView(LayoutInflater.from(mContext).inflate(toolbarLayoutRes, mHeaderView, false));
        }else{
            mHeaderView.setVisibility(View.GONE);

        }
        //内容View
        mFlContainer.addView(LayoutInflater.from(mContext).inflate(getLayoutId(), mHeaderView, false));

        EventBus.getDefault().register(this);
        ButterKnife.bind(this);


        initView(savedInstanceState);
        loadData();
        initListener();
    }



    protected int getToolbarLayout() {
        return R.layout.base_toolbar;
    }

    /**
     * 初始化 标题栏 只能内部调用
     */
    private void initToolbar(String title,boolean isCanBack) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle("");
            setSupportActionBar(mToolbar);
            mTitle = (TextView) findViewById(R.id.tv_title);
            mTitle.setText(title);

            if(isCanBack){
                mToolbar.setNavigationIcon(R.mipmap.back);
                mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
            }else{
                mToolbar.setNavigationIcon(null);
            }
//

        }
    }

    public abstract int getLayoutId();

    //需要使用 savedInstanceState参数的时候 重写这个方法
    public  void initView(Bundle savedInstanceState){};
    //有加载数据的时候 重写这个方法
    public  void loadData(){};
    //有监听的时候 重写这个方法
    public  void initListener(){};

    public void  setBackTitle(String title) {
        initToolbar(title,true);
    }


    public void  setNotBackTitle(String title) {
        initToolbar(title,false);
    }


    public Toolbar getToolbar(){
        return mToolbar;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }



    @Subscribe
    public void onEvent(T t) {

    }

    //封装弹出框提示
    protected void tsg(String str){
       ToastCommom.createToastConfig().ToastShow(MyApp.mContext,str);
    }
    protected void log(String str){//打印提示封装
        Log.e(TAG,str);
    }

    protected void showProress() {
        progress_layout.showLoading();
    }

    protected void showContent() {
        progress_layout.showContent();
    }

    protected void showError() {
        progress_layout.showError(R.string.loading_error, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
    }
    protected void showError(String message) {
        progress_layout.showError(message, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
    }


}
