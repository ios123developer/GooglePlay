package com.szzgkon.googleplay.protocol;

import com.szzgkon.googleplay.domain.SubjectInfo;

import org.json.JSONArray;
import org.json.JSONException;
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
 * 创建日期：16/11/16 下午3:56
 *
 * 描述：专题界面协议
 *
 * 修订历史：
 *
 * ===================================================
 **/


public class SubjectProtocol extends BaseProtocol<List<SubjectInfo>>{

    @Override
    public List<SubjectInfo> paserJson(String json) {
        List<SubjectInfo> subjectInfos = new ArrayList<SubjectInfo>();
        try {
            JSONArray jsonArray=new JSONArray(json);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String des=jsonObject.getString("des");
                String url = jsonObject.getString("url");
                SubjectInfo info=new SubjectInfo(des, url);
                subjectInfos.add(info);

            }
            return subjectInfos;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getKey() {
        return "subject";
    }


}

