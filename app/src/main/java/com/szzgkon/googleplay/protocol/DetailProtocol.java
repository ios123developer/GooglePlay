package com.szzgkon.googleplay.protocol;

import com.szzgkon.googleplay.domain.AppInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * ===================================================
 *
 * 版权：深圳市中广控信息科技有限公司 版权所有（c）2016
 *
 * 作者：zhangyongke
 *
 * 版本：1.0
 *
 * 创建日期：16/11/25 上午11:30
 *
 * 描述：
 *
 * 修订历史：
 *
 * ===================================================
 **/

public class DetailProtocol extends BaseProtocol<AppInfo>{

    String packageName;

    public DetailProtocol(String packageName) {
        this.packageName = packageName;
    }

    @Override

    public AppInfo paserJson(String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            long id = jsonObject.getLong("id");
            String name = jsonObject.getString("name");
            String packageName = jsonObject.getString("packageName");
            String iconUrl = jsonObject.getString("iconUrl");
            float stars = Float.parseFloat(jsonObject.getString("stars"));
            long size = jsonObject.getLong("size");
            String downloadUrl = jsonObject.getString("downloadUrl");
            String des = jsonObject.getString("des");
            String downloadNum = jsonObject.getString("downloadNum");
            String version = jsonObject.getString("version");
            String date = jsonObject.getString("date");
            String author = jsonObject.getString("author");
            List<String> screen = new ArrayList<String>();
            JSONArray screenArray = jsonObject.getJSONArray("screen");
            for (int i = 0; i < screenArray.length(); i++) {
                screen.add(screenArray.getString(i));
            }

             List<String> safeUrl = new ArrayList<String>();
             List<String> safeDesUrl = new ArrayList<String>();
             List<String> safeDes = new ArrayList<String>();
             List<Integer> safeDesColor = new ArrayList<Integer>();

            JSONArray jsonArray = jsonObject.getJSONArray("safe");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                safeUrl.add(jsonObject1.getString("safeUrl"));
                safeDesUrl.add(jsonObject1.getString("safeDesUrl"));
                safeDes.add(jsonObject1.getString("safeDes"));
                safeDesColor.add(jsonObject1.getInt("safeDesColor"));
            }

            AppInfo appInfo = new AppInfo(id,name,packageName,iconUrl,
                             stars,size,downloadUrl,des,downloadNum,version,date,
                             author,screen,safeUrl,safeDesUrl,safeDes,safeDesColor);

            return appInfo;

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
 }

    @Override
    public String getKey() {
        return "detail";
    }

    /**
     * 额外带的参数
     * @return
     */
    @Override
    protected String getParams() {
        return "&packageName=" + packageName;
    }
}
