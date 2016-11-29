package com.szzgkon.googleplay.holder;

import android.view.View;
import android.widget.ImageView;

import com.szzgkon.googleplay.R;
import com.szzgkon.googleplay.domain.AppInfo;
import com.szzgkon.googleplay.http.HttpHelper;
import com.szzgkon.googleplay.tools.UIUtils;

import java.util.List;

/**
 * ===================================================
 *
 * 版权：深圳市中广控信息科技有限公司 版权所有（c）2016
 *
 * 作者：zhangyongke
 *
 * 版本：1.0
 *
 * 创建日期：16/11/28 上午10:51
 *
 * 描述：
 *
 * 修订历史：
 *
 * ===================================================
 **/

public class DetailScreenHolder extends BaseHolder<AppInfo> {
    private ImageView[] ivs;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.detail_screen);
        ivs = new ImageView[5];
        ivs[0] = (ImageView) view.findViewById(R.id.screen_1);
        ivs[1] = (ImageView) view.findViewById(R.id.screen_2);
        ivs[2] = (ImageView) view.findViewById(R.id.screen_3);
        ivs[3] = (ImageView) view.findViewById(R.id.screen_4);
        ivs[4] = (ImageView) view.findViewById(R.id.screen_5);


        return view;
    }

    @Override
    public void refreshView(AppInfo data) {
        List<String> screen = data.getScreen();//集合有可能小于5
        for (int i = 0; i < 5; i++) {
            if(i < screen.size()){
                ivs[i].setVisibility(View.VISIBLE);
                bitmapUtils.display(ivs[i], HttpHelper.URL + "image?name=" + screen.get(i));
            }else {
                ivs[i].setVisibility(View.GONE);
            }
        }
    }
}
