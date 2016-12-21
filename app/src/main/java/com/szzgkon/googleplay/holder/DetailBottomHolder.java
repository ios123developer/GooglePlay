package com.szzgkon.googleplay.holder;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.szzgkon.googleplay.R;
import com.szzgkon.googleplay.domain.AppInfo;
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
 * 创建日期：16/11/28 下午5:57
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ===================================================
 **/

public class DetailBottomHolder extends BaseHolder<AppInfo> implements View.OnClickListener {
    @ViewInject(R.id.bottom_favorites)
    Button bottom_favorites;
    @ViewInject(R.id.bottom_share)
    Button bottom_share;
    @ViewInject(R.id.progress_btn)
    Button progress_btn;


    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.detail_bottom);

        ViewUtils.inject(this, view);

        return view;
    }

    @Override
    public void refreshView(AppInfo data) {
        bottom_favorites.setOnClickListener(this);
        bottom_share.setOnClickListener(this);
        progress_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_favorites:
                Toast.makeText(UIUtils.getContext(),"收藏",Toast.LENGTH_SHORT).show();

                break;
            case R.id.bottom_share:
                Toast.makeText(UIUtils.getContext(),"分享",Toast.LENGTH_SHORT).show();

                break;
            case R.id.progress_btn:
                Toast.makeText(UIUtils.getContext(),"下载",Toast.LENGTH_SHORT).show();

                break;

        }
    }
}
