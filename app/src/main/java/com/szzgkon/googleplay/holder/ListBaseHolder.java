package com.szzgkon.googleplay.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.szzgkon.googleplay.R;
import com.szzgkon.googleplay.domain.AppInfo;
import com.szzgkon.googleplay.global.GlobalContants;
import com.szzgkon.googleplay.tools.UIUtils;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * ===================================================
 * <p>
 * 版权：深圳市中广控信息科技有限公司 版权所有（c）2016
 * <p>
 * 作者：zhangyongke
 * <p>
 * 版本：1.0
 * <p>
 * 创建日期：16/11/18 上午11:27
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ===================================================
 **/

public class ListBaseHolder extends BaseHolder<AppInfo> {

    ImageView item_icon;
    TextView item_title, item_size, item_bottom;
    RatingBar item_rating;

    @Override
    public View initView() {

        View contentView = View.inflate(UIUtils.getContext(), R.layout.item_app, null);

        this.item_icon = (ImageView) contentView.findViewById(R.id.item_icon);
        this.item_title = (TextView) contentView.findViewById(R.id.item_title);
        this.item_size = (TextView) contentView.findViewById(R.id.item_size);
        this.item_bottom = (TextView) contentView.findViewById(R.id.item_bottom);
        this.item_rating = (RatingBar) contentView.findViewById(R.id.item_rating);

        return contentView;
    }

    @Override
    public void refreshView(AppInfo data) {
        this.item_title.setText(data.getName());
        String size = android.text.format.Formatter.formatFileSize(UIUtils.getContext(), data.getSize());
        this.item_size.setText(size + "M");
        this.item_title.setText(data.getName());
        this.item_bottom.setText(data.getDes());

        float stars = data.getStars();
        this.item_rating.setRating(stars);


        ImageOptions imageOptions = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setCrop(true)
                .setUseMemCache(false)
                .build();


        x.image().bind(this.item_icon, GlobalContants.IMAGE_URL+data.getIconUrl(),imageOptions);
    }




}
