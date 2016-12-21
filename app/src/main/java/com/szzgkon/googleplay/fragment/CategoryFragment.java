package com.szzgkon.googleplay.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.szzgkon.googleplay.adapter.DefaultAdapter;
import com.szzgkon.googleplay.domain.CategoryInfo;
import com.szzgkon.googleplay.holder.BaseHolder;
import com.szzgkon.googleplay.holder.CategoryContentHolder;
import com.szzgkon.googleplay.holder.CategoryTitleHolder;
import com.szzgkon.googleplay.protocol.CategoryProtocol;
import com.szzgkon.googleplay.tools.UIUtils;
import com.szzgkon.googleplay.view.BaseListView;
import com.szzgkon.googleplay.view.LoadingPage;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseFragment {

    private List<CategoryInfo> datas;

    public static int ITEM_TITLE = 10;

    //创建成功的界面
    @Override
    public View createSuccessView() {
        BaseListView listView = new BaseListView(UIUtils.getContext());
        listView.setAdapter(new CategoryAdapter(datas,listView));

        return listView;
    }

    private class CategoryAdapter extends DefaultAdapter<CategoryInfo> {
        private int position;//当前条目位置记录

        public CategoryAdapter(List<CategoryInfo> datas, ListView lv) {
            super(datas, lv);
        }

        //实现每个条目的界面
        @Override
        protected BaseHolder<CategoryInfo> getHolder() {
            if(!datas.get(position).isTitle()){
                return new CategoryContentHolder();

            }else {
                return new CategoryTitleHolder();

            }


        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            return super.getView(position, convertView, parent);
        }

        @Override
        protected List<CategoryInfo> onload() {
            return null;
        }

        @Override
        protected Boolean hasMore() {//当前方法如果为false,onload就不会被调用了
            return false;
        }

        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount() + 1;//又额外多了一种类型
        }

        @Override
        protected int getInnerItemViewType(int position) {
            if(datas.get(position).isTitle()){
                return ITEM_TITLE;

            }else {

                return super.getInnerItemViewType(position);

            }


        }
    }


    //请求服务器
    @Override
    protected LoadingPage.LoadResult load() {
        CategoryProtocol protocol = new CategoryProtocol();

        datas = protocol.load(0);


        return checkData(datas);
    }
}
