package com.hanyu.healthtree.yabhealthtree.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.hanyu.healthtree.yabhealthtree.MainActivity;
import com.hanyu.healthtree.yabhealthtree.R;
import com.hanyu.healthtree.yabhealthtree.adapter.WelcomeAdapter;
import com.hanyu.healthtree.yabhealthtree.base.BaseActivity;
import com.hanyu.healthtree.yabhealthtree.global.GlobalParam;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.hanyu.healthtree.yabhealthtree.R.id.btn_login;

/**
 * Created by xumingming on 2017/6/20.
 */

public class WelcomeActivity extends BaseActivity {


    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(btn_login)
    Button btnLogin;
    private int[] imageResoures = {R.mipmap.splash, R.mipmap.ic_launcher, R.mipmap.ic_launcher};


    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }


    @Override
    public void initView(Bundle savedInstanceState) {
        viewpager.setAdapter(new WelcomeAdapter(this, initData()));

    }

    @Override
    public void initListener() {
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int count = viewpager.getAdapter().getCount();
                if (position == count - 1) {
                    btnLogin.setVisibility(View.VISIBLE);
                } else {
                    btnLogin.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //获取数据源
    private List<Integer> initData() {
        List<Integer> resoures = new ArrayList<Integer>();
        for (Integer i : imageResoures) {
            resoures.add(i);
        }
        return resoures;

    }


    @OnClick(btn_login)
    public void onClick() {

        //下次进入的时候就不用进入欢迎页了
        GlobalParam.saveOpen(true);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        finish();
    }

    @Override
    protected int getToolbarLayout() {
        return -1;
    }
}
