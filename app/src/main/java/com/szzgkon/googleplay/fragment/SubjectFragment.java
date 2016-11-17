package com.szzgkon.googleplay.fragment;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.szzgkon.googleplay.R;
import com.szzgkon.googleplay.domain.SubjectInfo;
import com.szzgkon.googleplay.http.HttpHelper;
import com.szzgkon.googleplay.protocol.SubjectProtocol;
import com.szzgkon.googleplay.tools.UIUtils;
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
        ListView listView = new ListView(UIUtils.getContext());
        listView.setAdapter(new SubjectAdapter());
        return listView;
    }
    private class SubjectAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView!=null){

                holder=(ViewHolder) convertView.getTag();

            }else{
                convertView = UIUtils.inflate(R.layout.item_subject);
                holder=new ViewHolder();
                holder.item_icon=(ImageView) convertView.findViewById(R.id.item_icon);
                holder.item_txt=(TextView) convertView.findViewById(R.id.item_txt);
                convertView.setTag(holder);
            }
            SubjectInfo info=datas.get(position);
            holder.item_txt.setText(info.getDes());
            bitmapUtils.display(holder.item_icon, HttpHelper.URL+"image?name="+info.getUrl());
            return convertView;
        }

    }
    class ViewHolder{
        ImageView item_icon;
        TextView item_txt;
    }

    @Override
    protected LoadingPage.LoadResult load() {
        SubjectProtocol protocol=new SubjectProtocol();
        datas = protocol.load(0);
        return checkData(datas);
    }
}
