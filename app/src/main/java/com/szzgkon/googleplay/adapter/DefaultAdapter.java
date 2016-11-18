package com.szzgkon.googleplay.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.szzgkon.googleplay.holder.BaseHolder;

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
 * 创建日期：16/11/18 上午9:12
 *
 * 描述：列表适配器抽取的基类
 *
 * 修订历史：
 *
 * ===================================================
 **/

public abstract class DefaultAdapter<T> extends BaseAdapter {
    protected List<T> datas;

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public DefaultAdapter(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();//最后的一个条目，就是加载更多的条目
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public  View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder<T> holder = null;
        if(convertView == null){

            holder = getHolder();//普通条目的holder


        }else{

            holder = (BaseHolder) convertView.getTag();

        }
        T info = datas.get(position);
        holder.setData(info);
        return holder.getContentView();
    }

    protected abstract BaseHolder<T> getHolder();
}
