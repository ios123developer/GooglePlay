package com.szzgkon.googleplay.tools;

import android.os.Environment;

import java.io.File;

/**
 * ===================================================
 *
 * 版权：深圳市中广控信息科技有限公司 版权所有（c）2016
 *
 * 作者：张勇柯
 *
 * 版本：1.0
 *
 * 创建日期：16/11/14 下午2:54
 *
 * 描述：文件夹管理工具类
 *
 * 修订历史：
 *
 * ===================================================
 **/


public  class FileUtils {
    public static final String CACHE = "cache";

    public static final String ROOT = "GooglePlay";
    public static File getDir(String str){
       StringBuilder path = new StringBuilder();

        if(isSDAvaliable()){
            path.append(Environment.getExternalStorageDirectory().getAbsolutePath());
            path.append(File.separator);//添加'/'
            path.append(ROOT);// /mnt/sdcard/GooglePlay/cache
            path.append(File.separator);
            path.append(str);
        }else {
            File fileDir = UIUtils.getContext().getCacheDir();
            path.append(fileDir.getAbsolutePath());//data/data/com.szzgkon.googleplay/cache
            path.append(File.separator);
            path.append(str);
        }


        File file = new File(path.toString());

        System.out.println(file.toString());

        if(file.exists() || !file.isDirectory()){
            file.mkdirs();//创建文件夹
        }
        return file;

    }

    private static boolean isSDAvaliable() {
       if( Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
           return true;

       }else {
           return false;
       }
    }

    public static File getCacheDir(){

        return getDir(CACHE);
    }


}
