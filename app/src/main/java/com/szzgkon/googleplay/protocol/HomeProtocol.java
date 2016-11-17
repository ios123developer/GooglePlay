package com.szzgkon.googleplay.protocol;


import com.szzgkon.googleplay.domain.AppInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeProtocol extends BaseProtocol<List<AppInfo>>{


    //1.把整个json文件写到一个本地文件中
    public List<AppInfo> paserJson(String json) {
        List<AppInfo> appInfos = new ArrayList<AppInfo>();


        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("list");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                long id = jsonObj.getLong("id");
                String name = jsonObj.getString("name");
                String packageName = jsonObj.getString("packageName");
                String iconUrl = jsonObj.getString("iconUrl");
                float stars = Float.parseFloat(jsonObj.getString("stars"));
                long size = jsonObj.getLong("size");
                String downloadUrl = jsonObj.getString("downloadUrl");
                String des = jsonObj.getString("des");

                AppInfo info = new AppInfo(id, name, packageName, iconUrl,
                        stars, size, downloadUrl, des);
                appInfos.add(info);
            }

            return appInfos;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String getKey() {
        return "home";
    }


}
