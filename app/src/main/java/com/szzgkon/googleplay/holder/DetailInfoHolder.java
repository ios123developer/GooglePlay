package com.szzgkon.googleplay.holder;

import android.view.View;
import android.widget.TextView;

import com.szzgkon.googleplay.domain.AppInfo;
import com.szzgkon.googleplay.tools.UIUtils;

/**
 * ===================================================
 *
 * 版权：深圳市中广控信息科技有限公司 版权所有（c）2016
 *
 * 作者：zhangyongke
 *
 * 版本：1.0
 *
 * 创建日期：16/11/25 下午3:57
 *
 * 描述：
 *
 * 修订历史：
 *
 * ===================================================
 **/

public class DetailInfoHolder extends BaseHolder<AppInfo>{
    public DetailInfoHolder() {
        super();
    }

    private TextView tv;

    /**
     * 实例化控件和布局
     * @return
     */
    @Override
    public View initView() {
        tv = new TextView(UIUtils.getContext());

        return tv;
    }

    @Override
    public void refreshView(AppInfo data) {

        String name = data.getName();
            tv.setText(name);
    }
}
