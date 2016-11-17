package com.szzgkon.googleplay.domain;

/**
 * ===================================================
 *
 * 版权：深圳市中广控信息科技有限公司 版权所有（c）2016
 *
 * 作者：张勇柯
 *
 * 版本：1.0
 *
 * 创建日期：16/11/16 下午4:20
 *
 * 描述：专题对象信息
 *
 * 修订历史：
 *
 * ===================================================
 **/


public class SubjectInfo {
    private String des;
    private String url;

    public SubjectInfo() {
        super();
    }

    public SubjectInfo(String des, String url) {
        super();
        this.des = des;
        this.url = url;
    }



    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SubjectInfo{" +
                "des='" + des + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
