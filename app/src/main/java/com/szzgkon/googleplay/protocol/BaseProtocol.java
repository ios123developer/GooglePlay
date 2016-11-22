package com.szzgkon.googleplay.protocol;

import android.os.SystemClock;

import com.lidroid.xutils.util.IOUtils;
import com.szzgkon.googleplay.http.HttpHelper;
import com.szzgkon.googleplay.tools.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringWriter;

/**
 * ===================================================
 *
 * 版权：深圳市中广控信息科技有限公司 版权所有（c）2016
 *
 * 作者：张勇柯
 *
 * 版本：1.0
 *
 * 创建日期：16/11/16 下午4:00
 *
 * 描述：协议的基类
 *
 * 修订历史：
 *
 * ===================================================
 **/


public abstract class BaseProtocol<T> {
    public T load(int index) {
        SystemClock.sleep(1000);

        // 加载本地数据
        String json = loadLocal(index);
        if (json == null) {
            // 请求服务器
            json = loadServer(index);
            if (json != null) {
                saveLocal(json, index);
            }
        }
        if (json != null) {
            return paserJson(json);
        } else {
            return null;
        }
    }

    private String loadServer(int index) {
        HttpHelper.HttpResult httpResult = HttpHelper.get(HttpHelper.URL +getKey()
                + "?index=" + index);
        if(httpResult != null){
            String json = httpResult.getString();
            return json;
        }else {
            return null;
        }
    }
    private void saveLocal(String json, int index) {

        BufferedWriter bw = null;
        try {
            File dir=FileUtils.getCacheDir();
            //在第一行写一个过期时间
            File file = new File(dir, getKey()+"_" + index); // /mnt/sdcard/googlePlay/cache/home_0
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(System.currentTimeMillis() + 1000 * 100 + "");
            bw.newLine();// 换行
            bw.write(json);// 把整个json文件保存起来
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            IOUtils.closeQuietly(bw);
        }
    }

    private String loadLocal(int index) {
        //  如果发现文件已经过期了 就不要再去复用缓存了
        File dir=FileUtils.getCacheDir();// 获取缓存所在的文件夹
        File file = new File(dir, getKey()+"_" + index);
        try {
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            long outOfDate = Long.parseLong(br.readLine());
            if(System.currentTimeMillis()>outOfDate){
                return null;
            }else{
                String str=null;
                StringWriter sw=new StringWriter();
                while((str=br.readLine())!=null){

                    sw.write(str);
                }
                return sw.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析json
     * @param json
     * @return
     */
    public abstract T paserJson(String json);
    /**
     * 说明了关键字
     * @return
     */
    public abstract String getKey();
}
