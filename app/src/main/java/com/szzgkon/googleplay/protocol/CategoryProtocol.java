package com.szzgkon.googleplay.protocol;

import com.szzgkon.googleplay.domain.CategoryInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * ===================================================
 * <p>
 * 版权：深圳市中广控信息科技有限公司 版权所有（c）2016
 * <p>
 * 作者：zhangyongke
 * <p>
 * 版本：1.0
 * <p>
 * 创建日期：16/12/20 下午3:40
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ===================================================
 **/

public class CategoryProtocol extends BaseProtocol<List<CategoryInfo>> {
    @Override
    public List<CategoryInfo> paserJson(String json) {
        List<CategoryInfo> categoryInfos = new ArrayList<CategoryInfo>();

        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                String title = jsonObject.getString("title");
                CategoryInfo categoryInfo = new CategoryInfo();
                categoryInfo.setTitle(title);
                categoryInfo.setIsTitle(true);

                categoryInfos.add(categoryInfo);


                JSONArray jsonArray = jsonObject.getJSONArray("infos");
                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                    String url1 = jsonObject1.getString("url1");
                    String url2 = jsonObject1.getString("url2");
                    String url3 = jsonObject1.getString("url3");
                    String name1 = jsonObject1.getString("name1");
                    String name2 = jsonObject1.getString("name2");
                    String name3 = jsonObject1.getString("name3");

                    CategoryInfo categoryInfo2 = new CategoryInfo(title,url1,url2,url3,name1,name2,name3,false);

                    categoryInfos.add(categoryInfo2);
                }
            }
            return categoryInfos;


        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }

    }

    @Override
    public String getKey() {
        return "category";
    }
}
