package com.szzgkon.googleplay.adapter;

import android.content.Intent;
import android.widget.ListView;
import android.widget.Toast;

import com.szzgkon.googleplay.DetailActivity;
import com.szzgkon.googleplay.domain.AppInfo;
import com.szzgkon.googleplay.holder.BaseHolder;
import com.szzgkon.googleplay.holder.ListBaseHolder;
import com.szzgkon.googleplay.tools.UIUtils;

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

    public ListBaseAdapter(List<AppInfo> datas,ListView lv) {
        super(datas,lv);
    }

    @Override
    protected BaseHolder<AppInfo> getHolder() {

        return new ListBaseHolder();
    }

    @Override
    protected abstract List<AppInfo> onload();

    @Override
    public void onInnerItemClick(int position) {
        super.onInnerItemClick(position);
        Toast.makeText(UIUtils.getContext(),"position:"+ position,Toast.LENGTH_SHORT).show();

        AppInfo appInfo = datas.get(position);

        Intent intent = new Intent(UIUtils.getContext(),DetailActivity.class);

        intent.putExtra("packageName",appInfo.getPackageName());

        UIUtils.startActivity(intent);

    }


}
