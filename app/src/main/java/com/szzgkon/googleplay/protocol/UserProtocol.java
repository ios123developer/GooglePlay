package com.szzgkon.googleplay.protocol;

import com.szzgkon.googleplay.domain.UserInfo;

import org.json.JSONObject;

/**
 * ===================================================
 *
 * 版权：深圳市中广控信息科技有限公司 版权所有（c）2016
 *
 * 作者：张勇柯
 *
 * 版本：1.0
 *
 * 创建日期：16/11/21 下午6:32
 *
 * 描述：登录数据处理类
 *
 * 修订历史：
 *
 * ===================================================
 **/


public class UserProtocol extends BaseProtocol<UserInfo>{


    @Override
    public UserInfo paserJson(String json) {
        try {
            JSONObject jsonObj = new JSONObject(json);
            String name = jsonObj.getString("name");
            String email = jsonObj.getString("email");
            String url = jsonObj.getString("url");

            UserInfo info = new UserInfo(name,url,email);
            return info;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getKey() {
        return "user";
    }
}
