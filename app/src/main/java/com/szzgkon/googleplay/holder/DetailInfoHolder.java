package com.szzgkon.googleplay.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.szzgkon.googleplay.R;
import com.szzgkon.googleplay.domain.AppInfo;
import com.szzgkon.googleplay.http.HttpHelper;
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
    @ViewInject(R.id.item_icon)
    private ImageView item_icon;

    @ViewInject(R.id.item_title)

    private TextView item_title;

    @ViewInject(R.id.item_rating)

    private RatingBar item_rating;

    @ViewInject(R.id.item_download)

    private TextView item_download;

    @ViewInject(R.id.item_version)

    private TextView item_version;

    @ViewInject(R.id.item_date)

    private TextView item_date;

    @ViewInject(R.id.item_size)

    private TextView item_size;

    /**
     * 实例化控件和布局
     * @return
     */
    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.detail_app_info);
        com.lidroid.xutils.ViewUtils.inject(this,view);

        return view;
    }

    /**
     *
     * @param data
     */
    @Override
    public void refreshView( AppInfo data) {

        bitmapUtils.display(item_icon, HttpHelper.URL + "image?name=" + data.getIconUrl());
        item_title.setText(data.getName());
        item_rating.setRating(data.getStars());
        item_download.setText("下载：" + data.getDownloadNum());
        item_version.setText("版本：" + data.getVersion());
        item_date.setText("时间：" + data.getDate());
        item_size.setText("大小：" + Formatter.formatFileSize(UIUtils.getContext(),data.getSize()));

    }
}
