package com.example.lenovo.jdappzyf.utils;

/**
 * Created by lenovo on 2018/11/7.
 */

public class ImgUtils {

    public static String getImg(String url){
        String replace = url.replace("https","http");
        String[] split = replace.split("\\|");
        return split[0];
    }
}
