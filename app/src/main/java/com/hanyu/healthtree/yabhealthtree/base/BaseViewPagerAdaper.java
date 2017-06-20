package com.hanyu.healthtree.yabhealthtree.base;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.TintableBackgroundView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

/**
 * Created by xumingming on 2017/6/20.
 *
 * 简单封装的ViewPager的适配器
 */

public abstract  class BaseViewPagerAdaper<T> extends PagerAdapter {

    protected Context mContext;
    protected List<T> mList;

    public BaseViewPagerAdaper(Context mContext, List<T> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null?0:mList.size();
    }



    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       return getItem(container,position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }


    public  T getPositionItem(int position) {
        return mList.get(position);
    }



    /**
     * 子类必须实现的方法 用来创建每隔Item
     * @param container
     * @param position
     * @return
     */
    public abstract Object getItem(ViewGroup container, int position);


}
