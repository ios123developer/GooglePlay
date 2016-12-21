package com.szzgkon.googleplay.holder;

import android.view.View;
import android.widget.RelativeLayout;

import com.szzgkon.googleplay.R;
import com.szzgkon.googleplay.adapter.DefaultAdapter;
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


  private boolean hasMore;

  private RelativeLayout rl_more_loading,rl_more_error;

    /**
     * 当Holder显示的时候 显示什么样子
     * @return
     */
    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.load_more);
      rl_more_loading = (RelativeLayout) view.findViewById(R.id.rl_more_loading);
      rl_more_error = (RelativeLayout) view.findViewById(R.id.rl_more_error);
        return view;
    }


  /**
   * 根据数据做界面的修改
   * @param data
     */
    @Override
    public void refreshView(Integer data) {


     rl_more_error.setVisibility(data == LOAD_ERR?View.VISIBLE:View.GONE);

      rl_more_loading.setVisibility(data == HAS_MORE?View.VISIBLE:View.GONE);


    }

  @Override
  public View getContentView() {
      loadMore();
    return super.getContentView();
  }

  private void loadMore() {
    //请求服务器 加载下一批数据
    //交给Adapter 让adapter去加载更多数据

    adapter.loadMore();
  }

  private DefaultAdapter adapter;
  public MoreHolder(DefaultAdapter adapter, Boolean hasMore) {
    super();
    this.adapter = adapter;
    this.hasMore =  hasMore;

    if(!hasMore){
        setData(0);
    }

  }
}
