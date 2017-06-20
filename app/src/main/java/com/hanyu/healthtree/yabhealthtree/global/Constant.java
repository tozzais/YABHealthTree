package com.hanyu.healthtree.yabhealthtree.global;

import android.os.Environment;


/**
 *
 * 全部的常量类。
 * Created by Administrator on 2017/4/15.
 */

public class Constant {



    public static final String BASE_NAME = "ABLtree";
    public static final String ROOT_PATH = Environment.getExternalStorageDirectory() + "/"+BASE_NAME;
    public static String Phone = "400-888-8888";


    public static String TOKEN = BASE_NAME+"_token";  //用户token
    public static String ID = BASE_NAME+"_tuserId"; //用户ID
    public static String VERSION = BASE_NAME+"_version"; //版本号
    public static String PASS = BASE_NAME+"_password"; //用户密码
    public static String OPENITALL = BASE_NAME+"_onpeinstall"; //用户第一次登陆
    public static String ISLOGIN = BASE_NAME+"_isLogin"; //用户是否登录





}
