package com.hanyu.healthtree.yabhealthtree.global;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.hanyu.healthtree.yabhealthtree.R;
import com.hanyu.healthtree.yabhealthtree.util.data.SharedPreferencesUtil;

import static com.hanyu.healthtree.yabhealthtree.global.MyApp.mContext;
import static com.hanyu.healthtree.yabhealthtree.util.data.SharedPreferencesUtil.getBooleanData;
import static com.hanyu.healthtree.yabhealthtree.util.data.SharedPreferencesUtil.getStringData;

/**
 * Created by jumpbox on 16/4/19.
 */
public class GlobalParam {


    //获取用户token
    public static void saveUserToken(String user_token) {
        SharedPreferencesUtil.saveStringData(mContext, Constant.TOKEN, user_token);
    }

    //存储用户token
    public static String getUserToken() {
        String user_token = getStringData(mContext, Constant.TOKEN, "");
        return user_token;
    }

    //存储用户 id
    public static void saveUserId(String id) {
        SharedPreferencesUtil.saveStringData(mContext, Constant.ID, id);
    }
    //得到用户 id
    public static String getUserId() {
        String userId = getStringData(mContext, Constant.ID, "");
        return userId;
    }

    //存储用户 密码
    public static void savePass(String pass) {
        SharedPreferencesUtil.saveStringData(mContext, Constant.PASS, pass);
    }
    //得到用户 密码
    public static String getPass() {
        return getStringData(mContext, Constant.PASS, "");
    }

    //存储用户 是否是第一次下载APP
    public static void saveOpen(Boolean id) {
        SharedPreferencesUtil.saveBooleanData(mContext, Constant.OPENITALL, id);
    }
    //得到用户 是否是第一次下载APP
    public static boolean getOpen() {
        boolean userId = getBooleanData(mContext, Constant.OPENITALL, false);
        return userId;
    }

    //存储用户 是否登录
    public static void setLogin(Boolean isLogin) {
        SharedPreferencesUtil.saveBooleanData(mContext, Constant.ISLOGIN, isLogin);
    }
    //得到用户 是否登录
    public static Boolean getLogin() {
        Boolean user_token = getBooleanData(mContext, Constant.ISLOGIN, false);
        return user_token;
    }


    //加载圆形头像的方法
    public static void loadAvatar(final Context context, String url, ImageView view) {
        Glide.with(context).load(url).asBitmap()
                .centerCrop()
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(new BitmapImageViewTarget(view) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        view.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    //加载图片
    public static void loadImg(Context context, String url, ImageView view) {
        Glide.with(context).load(url).placeholder(R.mipmap.ic_launcher).
                error(R.mipmap.ic_launcher).into(view);
    }


}
