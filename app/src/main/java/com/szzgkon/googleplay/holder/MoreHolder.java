package com.szzgkon.googleplay.holder;

import android.view.View;

import com.szzgkon.googleplay.R;
import com.szzgkon.googleplay.tools.UIUtils;

/**
 * ===================================================
 *
 * 版权：深圳市中广控信息科技有限公司 版权所有（c）2016
 *
 * 作者：张勇柯
 *
 * 版本：1.0
 *
 * 创建日期：16/11/18 下午2:21
 *
 * 描述：加载更多的holder
 *
 * 修订历史：
 *
 * ===================================================
 **/


public class MoreHolder extends BaseHolder<Integer> {
  public static final int HAS_NO_MORE = 0;//没有额外数据了
  public static final int LOAD_ERR = 1;//加载失败
  public static final int HAS_MORE = 2;//有额外数据


    /**
     * 当Holder显示的时候 显示什么样子
     * @return
     */
    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.load_more);
        return view;
    }

    @Override
    public void refreshView(Integer data) {

    }
}
