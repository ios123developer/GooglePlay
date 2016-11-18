package com.szzgkon.googleplay.holder;

import android.view.View;

import com.lidroid.xutils.BitmapUtils;
import com.szzgkon.googleplay.tools.BitmapHelper;

/**
 * ===================================================
 *
 * 版权：深圳市中广控信息科技有限公司 版权所有（c）2016
 *
 * 作者：张勇柯
 *
 * 版本：1.0
 *
 * 创建日期： 上午10:26
 *
 * 描述：viewHolder的基类
 *
 * 修订历史：
 *
 * ===================================================
 **/


public abstract class BaseHolder<T> {

   protected BitmapUtils bitmapUtils;

    private View contentView;//

    private T data;

    public BaseHolder() {
        bitmapUtils = BitmapHelper.getBitmapUtils();
         contentView  = initView();
        contentView.setTag(this);
    }

    public abstract View initView();


    public void setData(T data) {
        this.data = data;

        refreshView(data);
    }

    public abstract void refreshView (T data);


    public View getContentView() {
        return contentView;
    }
}
