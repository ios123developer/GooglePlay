package com.szzgkon.googleplay.holder;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.szzgkon.googleplay.R;
import com.szzgkon.googleplay.domain.AppInfo;
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
 * 创建日期：16/11/28 下午4:28
 *
 * 描述：
 *
 * 修订历史：
 *
 * ===================================================
 **/

public class DetailDesHolder extends BaseHolder<AppInfo> implements View.OnClickListener {

    @ViewInject(R.id.des_content)

    private TextView des_content;

    @ViewInject(R.id.des_author)

    private TextView des_author;

    @ViewInject(R.id.des_arrow)

    private ImageView des_arrow;

    @ViewInject(R.id.des_layout)

    private RelativeLayout des_layout;

    ScrollView scrollView;



    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.detail_des);

        ViewUtils.inject(this,view);

        return view;
    }

    @Override
    public void refreshView(AppInfo data) {
        des_content.setText(data.getDes());
        des_author.setText("作者:" + data.getAuthor());
        des_layout.setOnClickListener(this);

        //des_content起始高度7行的高度
        ViewGroup.LayoutParams layoutParams = des_content.getLayoutParams();
        layoutParams.height = getShortMeasureHeight();
        des_content.setLayoutParams(layoutParams);

    }

    /**
     * 获取textView自己本身的高度
     * @return
     */
    public int getLongMeatureHeight(){

        int width = des_content.getMeasuredWidth();//开始的宽度
        des_content.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 参数1 大小  参数2 测量控件mode
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(android.R.attr.width, View.MeasureSpec.EXACTLY);//mode + size
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(1000,View.MeasureSpec.AT_MOST);

        //测量的规则：宽度是一个精确的值width,高度最大是1000，以实际为准
        des_content.measure(widthMeasureSpec,heightMeasureSpec);

        return des_content.getMeasuredHeight();
    }

    /**
     * 获取7行文本的高度
     * @return
     */
    public int getShortMeasureHeight(){
        //复制一个新的Textview用来测量，最好不要在之前的Textview上测量，有可能影响其他代码执行
        TextView textView = new TextView(UIUtils.getContext());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
        textView.setMaxLines(7);
        textView.setLines(7);//强制有7行+
        int width = des_content.getMeasuredWidth();//开始的宽度
        // 参数1 大小  参数2 测量控件mode
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(android.R.attr.width, View.MeasureSpec.EXACTLY);//mode + size
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(1000,View.MeasureSpec.AT_MOST);

        //测量的规则：宽度是一个精确的值width,高度最大是1000，以实际为准
        textView.measure(widthMeasureSpec,heightMeasureSpec);

        return textView.getMeasuredHeight();

    }

    boolean flag;//true 展开了，false没有展开
    @Override
    public void onClick(View v) {
        expand();
    }

    private void expand() {

        scrollView = getScrollView(des_layout);

         int startHeight;
         int targetHeight;
        if(!flag){
            flag = true;
            startHeight = getShortMeasureHeight();
            targetHeight = getLongMeatureHeight();
        }else {
            flag = false;
            startHeight = getLongMeatureHeight();
            targetHeight = getShortMeasureHeight();
        }

        ValueAnimator animator = ValueAnimator.ofInt(startHeight,targetHeight);
        final ViewGroup.LayoutParams layoutParams = des_content.getLayoutParams();

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int value = (Integer) animator.getAnimatedValue();
                layoutParams.height = value;
                des_content.setLayoutParams(layoutParams);
                scrollView.scrollTo(0,scrollView.getMeasuredHeight());//让scrollview

            }
        });

        animator.setDuration(200);
        animator.start();

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(flag){
                    des_arrow.setImageResource(R.mipmap.arrow_up);
                }else {
                    des_arrow.setImageResource(R.mipmap.arrow_down);

                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 获取到scrollview
     * @param view
     * @return
     */
    public ScrollView getScrollView(View view){
        ViewParent parent = view.getParent();
        if(parent instanceof ViewGroup){
            ViewGroup group = (ViewGroup) parent;
            if(group instanceof ScrollView){
             return (ScrollView) group;
            }else {
                return getScrollView(group);//递归调用
            }
        }else {
            return null;
        }
    }
}
