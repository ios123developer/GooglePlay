package com.szzgkon.googleplay.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.szzgkon.googleplay.R;
import com.szzgkon.googleplay.domain.AppInfo;
import com.szzgkon.googleplay.global.GlobalContants;
import com.szzgkon.googleplay.protocol.HomeProtocol;
import com.szzgkon.googleplay.tools.UIUtils;
import com.szzgkon.googleplay.view.LoadingPage;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    private List<AppInfo> datas;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    public View createSuccessView() {
        ListView listView = new ListView(UIUtils.getContext());
        listView.setAdapter(new HomeAdapter());

        return listView;
    }


    private class HomeAdapter extends BaseAdapter {


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
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(UIUtils.getContext(), R.layout.item_app, null);
                holder.item_icon = (ImageView) convertView.findViewById(R.id.item_icon);
                holder.item_title = (TextView) convertView.findViewById(R.id.item_title);
                holder.item_size = (TextView) convertView.findViewById(R.id.item_size);
                holder.item_bottom = (TextView) convertView.findViewById(R.id.item_bottom);
                holder.item_rating = (RatingBar) convertView.findViewById(R.id.item_rating);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            AppInfo info = datas.get(position);

            holder.item_title.setText(info.getName());
            String size = android.text.format.Formatter.formatFileSize(UIUtils.getContext(), info.getSize());
            holder.item_size.setText(size + "M");
            holder.item_title.setText(info.getName());
            holder.item_bottom.setText(info.getDes());

            float stars = info.getStars();
            holder.item_rating.setRating(stars);

            System.out.println("图片的网址是" + info.getIconUrl());

            ImageOptions imageOptions = new ImageOptions.Builder()
                    .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                    .setCrop(true)
                    .setUseMemCache(false)
                    .build();


            x.image().bind(holder.item_icon, GlobalContants.IMAGE_URL+info.getIconUrl(),imageOptions);

            return convertView;
        }
    }

    static class ViewHolder {
        ImageView item_icon;
        TextView item_title, item_size, item_bottom;
        RatingBar item_rating;

    }


    public LoadingPage.LoadResult load() {

        HomeProtocol protocol = new HomeProtocol();


        datas = protocol.load(0);


        return checkData(datas);
    }


}
