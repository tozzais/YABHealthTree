package com.hanyu.healthtree.yabhealthtree.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hanyu.healthtree.yabhealthtree.base.BaseViewPagerAdaper;

import java.util.List;

/**
 * Created by xumingming on 2017/6/20.
 */

public class WelcomeAdapter extends BaseViewPagerAdaper<Integer> {


    public WelcomeAdapter(Context mContext, List<Integer> mList) {
        super(mContext, mList);
    }

    @Override
    public Object getItem(ViewGroup container, int position) {
        Integer resouresId = getPositionItem(position);

        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(resouresId);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context,BigImageActivity.class);
//                context.startActivity(intent);
            }
        });
        ViewGroup parent = (ViewGroup)imageView.getParent();
        if(parent != null){
            parent.removeAllViews();
        }
        container.addView(imageView);
        return imageView;
    }



}
