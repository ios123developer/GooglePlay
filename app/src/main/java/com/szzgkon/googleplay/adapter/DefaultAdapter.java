package com.szzgkon.googleplay.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.szzgkon.googleplay.holder.BaseHolder;
import com.szzgkon.googleplay.holder.MoreHolder;
import com.szzgkon.googleplay.manager.ThreadManager;
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
 * 创建日期：16/11/18 下午6:01
 *
 * 描述：所有adapter的基类
 *
 * 修订历史：
 *
 * ===================================================
 **/


public abstract class DefaultAdapter<T> extends BaseAdapter implements AdapterView.OnItemClickListener {
    private static final int DEFAULT_ITEM = 0;
    private static final int MORE_ITEM = 1;

    private ListView lv;

    protected List<T> datas;

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public DefaultAdapter(List<T> datas, ListView lv) {
        this.datas = datas;
        //给listview设置条目点击事件
        lv.setOnItemClickListener(this);
        this.lv = lv;
    }

    @Override
    public int getCount() {
        return datas.size() + 1;//最后的一个条目，就是加载更多的条目
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    /**
     * 根据位置 判断当前条目是什么类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        if (position == datas.size()) {
            return MORE_ITEM;
        }

        return getInnerItemViewType(position);//如果不是最后一个位置，就显示默认的条目

    }

    private int getInnerItemViewType(int position) {
        return DEFAULT_ITEM;
    }


    /**
     * 当前ListView有几种不同的条目类型
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;//有两种不同类型的条目
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       /*

        BaseHolder holder = null;

        if (convertView == null) {

            if (getItemViewType(position) == MORE_ITEM) {
                holder = getMoreHolder();
            } else {
                holder = getHolder();//普通条目的holder

            }

        } else {
            if (getItemViewType(position) == DEFAULT_ITEM) {
                holder = (BaseHolder) convertView.getTag();//复用
            } else {
                holder = getMoreHolder();
            }
        }
        if(getItemViewType(position) == DEFAULT_ITEM){
          // "T" 为泛型（各个界面每个条目的对象的应用的集合）

            T info = datas.get(position);
            holder.setData(info);
        }

*/
        //谷歌官方推荐写法
        BaseHolder holder = null;

        switch (getItemViewType(position)) {
            case MORE_ITEM:
                if (convertView == null) {
                    holder = getMoreHolder();
                } else {
                    holder = (BaseHolder) convertView.getTag();
                }
                break;

            case DEFAULT_ITEM:
                if (convertView == null) {
                    holder = getHolder();
                } else {
                    holder = (BaseHolder) convertView.getTag();
                }
                if (position < datas.size()) {
                    holder.setData(datas.get(position));
                }
                break;
        }

        return holder.getContentView();//调用此方法的时候，如果返回的是MoreHolder,此时就要去服务器请求数据
    }

    private MoreHolder holder;

    private BaseHolder getMoreHolder() {

        if (holder != null) {
            return holder;
        } else {
            holder = new MoreHolder(this);
        }

        return new MoreHolder(this);

    }

    protected abstract BaseHolder<T> getHolder();

    //当加载更多条目显示的时候会调用此方法
    public void loadMore() {
        ThreadManager.getInstance().creatLongPool().execute(new Runnable() {
            @Override
            public void run() {
                //在子线程中加载更多
                final List<T> newData = onload();

                UIUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (newData == null) {
                            //TODO 连接服务器失败
                            holder.setData(MoreHolder.LOAD_ERR);

                        } else if (newData.size() == 0) {
                            //TODO 服务器已经没有额外数据了
                            holder.setData(MoreHolder.HAS_NO_MORE);
                        } else {
                            //成功了
                            holder.setData(MoreHolder.HAS_MORE);
                            datas.addAll(newData);//将请求回来的数据添加到之前的界面上

                        }

                        notifyDataSetChanged();

                    }
                });


            }
        });
    }

    /**
     * 加载更多数据
     * ，交给子类去实现
     */
    protected abstract List<T> onload();

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(UIUtils.getContext(),"position" +  position,Toast.LENGTH_SHORT).show();
      position = position - lv.getHeaderViewsCount();//获取到顶部条目的数量,修正每个界面listview的条目的位置

        onInnerItemClick(position);


    }

    /**
     * 修正过之后的position，在这个方法中处理点击事件
     * @param position
     */
    public void onInnerItemClick(int position) {

    }
}
