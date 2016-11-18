package com.szzgkon.googleplay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.szzgkon.googleplay.R;
import com.szzgkon.googleplay.tools.UIUtils;


/**
 * ===================================================
 * <p>
 * 版权：深圳市中广控信息科技有限公司 版权所有（c）2016
 * <p>
 * 作者：zhangyongke
 * <p>
 * 版本：1.0
 * <p>
 * 创建日期：16/11/17 下午5:53
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ===================================================
 **/

public class BaseListView extends ListView {

    public BaseListView(Context context) {
        super(context);
        init();
    }

    public BaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public BaseListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        this.setSelector(R.mipmap.nothing);//点击listview时的颜色
//        this.setCacheColorHint(R.mipmap.nothing);//拖拽的颜色
        this.setDivider(UIUtils.getDrawalbe(R.mipmap.nothing));//分割线
    }
}
