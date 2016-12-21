package com.szzgkon.googleplay.fragment;


import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.szzgkon.googleplay.R;
import com.szzgkon.googleplay.adapter.DefaultAdapter;
import com.szzgkon.googleplay.domain.SubjectInfo;
import com.szzgkon.googleplay.holder.BaseHolder;
import com.szzgkon.googleplay.http.HttpHelper;
import com.szzgkon.googleplay.protocol.SubjectProtocol;
import com.szzgkon.googleplay.tools.UIUtils;
import com.szzgkon.googleplay.view.BaseListView;
import com.szzgkon.googleplay.view.LoadingPage;

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
 * 创建日期：16/11/16 下午3:51
 *
 * 描述：专题界面
 *
 * 修订历史：
 *
 * ===================================================
 **/

public class SubjectFragment extends BaseFragment {

    private List<SubjectInfo> datas;

    @Override
    public View createSuccessView() {
        BaseListView listView = new BaseListView(UIUtils.getContext());
        listView.setAdapter(new SubjectAdapter(datas,listView));
        return listView;
    }
    private class SubjectAdapter extends DefaultAdapter<SubjectInfo> {

        public SubjectAdapter(List<SubjectInfo> datas,ListView lv) {
            super(datas,lv);
        }

        @Override
        protected BaseHolder<SubjectInfo> getHolder() {
            return new SubjectHolder();
        }

        @Override
        protected List<SubjectInfo> onload() {

            SubjectProtocol protocol=new SubjectProtocol();
            List<SubjectInfo> newData = protocol.load(datas.size());
           datas.addAll(newData);
            return newData;
        }

        @Override
        public void onInnerItemClick(int position) {
            super.onInnerItemClick(position);
            Toast.makeText(UIUtils.getContext(),datas.get(position).getDes(),Toast.LENGTH_SHORT).show();

        }
    }
   static class SubjectHolder extends BaseHolder<SubjectInfo> {
        ImageView item_icon;
        TextView item_txt;


       @Override
       public View initView() {
          View contentView = UIUtils.inflate(R.layout.item_subject);

           this.item_icon=(ImageView) contentView.findViewById(R.id.item_icon);
           this.item_txt=(TextView) contentView.findViewById(R.id.item_txt);

           return contentView;
       }



       @Override
       public void refreshView(SubjectInfo data) {
           this.item_txt.setText(data.getDes());
           bitmapUtils.display(this.item_icon, HttpHelper.URL+"image?name="+data.getUrl());
       }

   }

    @Override
    protected LoadingPage.LoadResult load() {
        SubjectProtocol protocol=new SubjectProtocol();
        datas = protocol.load(0);
        return checkData(datas);
    }
}
