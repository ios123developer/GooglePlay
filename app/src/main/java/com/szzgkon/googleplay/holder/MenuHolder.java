package com.szzgkon.googleplay.holder;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.szzgkon.googleplay.R;
import com.szzgkon.googleplay.domain.UserInfo;
import com.szzgkon.googleplay.http.HttpHelper;
import com.szzgkon.googleplay.manager.ThreadManager;
import com.szzgkon.googleplay.protocol.UserProtocol;
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
 * 创建日期：16/11/21 下午5:05
 *
 * 描述：管理侧边栏菜单界面的类
 *
 * 修订历史：
 *
 * ===================================================
 **/

public class MenuHolder extends BaseHolder<UserInfo> implements View.OnTouchListener {
    @ViewInject(R.id.photo_layout)

    private RelativeLayout photo_layout;

    @ViewInject(R.id.image_photo)

    private ImageView image_photo;

    @ViewInject(R.id.user_name)

    private TextView user_name;

    @ViewInject(R.id.user_email)

    private TextView user_email;




    @Override
    public View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.menu_holder, null);
        ViewUtils.inject(this,view);
        photo_layout.setOnTouchListener(this);

        return view;
    }

    @Override
    public void refreshView(UserInfo data) {
        user_name.setText(data.getName());
        user_email.setText(data.getEmail());
        bitmapUtils.display(image_photo, HttpHelper.URL + "image?name=" + data.getUrl());
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
         switch(v.getId()){
         case R.id.photo_layout:
         //连接服务器...登录,要在子线程中实现
             ThreadManager.getInstance().creatLongPool().execute(new Runnable() {
                 @Override
                 public void run() {
                     UserProtocol protocol = new UserProtocol();
                     final UserInfo load = protocol.load(0);
                     UIUtils.runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             setData(load);//当调用该方法的时候，就会调用refreshview
                         }
                     });
                 }
             });



              break;

         }
        return false;
    }
}
