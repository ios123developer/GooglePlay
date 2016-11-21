package com.szzgkon.googleplay.adapter;

import com.szzgkon.googleplay.domain.AppInfo;
import com.szzgkon.googleplay.holder.BaseHolder;
import com.szzgkon.googleplay.holder.ListBaseHolder;

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
 * 创建日期：16/11/18 上午11:33
 *
 * 描述：home和app界面抽取的adapter基类
 *
 * 修订历史：
 *
 * ===================================================
 **/


public abstract class ListBaseAdapter extends DefaultAdapter<AppInfo> {

    public ListBaseAdapter(List<AppInfo> datas) {
        super(datas);
    }

    @Override
    protected BaseHolder<AppInfo> getHolder() {

        return new ListBaseHolder();
    }

    @Override
    protected abstract List<AppInfo> onload();
}
