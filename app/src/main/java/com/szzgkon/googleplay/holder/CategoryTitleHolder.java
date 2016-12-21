package com.szzgkon.googleplay.holder;

import android.view.View;
import android.widget.TextView;

import com.szzgkon.googleplay.domain.CategoryInfo;
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
 * 创建日期：16/12/20 下午5:49
 *
 * 描述：
 *
 * 修订历史：
 *
 * ===================================================
 **/

public class CategoryTitleHolder extends BaseHolder<CategoryInfo>{

    private TextView textView;

    @Override
    public View initView() {
        textView = new TextView(UIUtils.getContext());

        return textView;
    }

    @Override
    public void refreshView(CategoryInfo data) {
        textView.setText(data.getTitle());
    }
}
