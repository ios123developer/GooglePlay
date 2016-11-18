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
 * 作者：张勇柯
 *
 * 版本：1.0
 *
 * 创建日期：16/11/18 下午1:51
 *
 * 描述：游戏界面的数据请求
 *
 * 修订历史：
 *
 * ===================================================
 **/


public class GameProtocol extends BaseProtocol<List<AppInfo>> {

  List<AppInfo> appInfos =  new ArrayList<AppInfo>();

    @Override
    public List<AppInfo> paserJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                long id = jsonObject.getLong("id");
                String name = jsonObject.getString("name");
                String packageName = jsonObject.getString("packageName");
                String iconUrl = jsonObject.getString("iconUrl");
                float stars = Float.parseFloat(jsonObject.getString("stars"));
                long size = jsonObject.getLong("size");
                String downloadUrl = jsonObject.getString("downloadUrl");
                String des = jsonObject.getString("des");

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
        return "game";
    }
}
