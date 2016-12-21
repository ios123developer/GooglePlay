package com.szzgkon.googleplay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * ===================================================
 * <p>
 * 版权：深圳市中广控信息科技有限公司 版权所有（c）2016
 * <p>
 * 作者：zhangyongke
 * <p>
 * 版本：1.0
 * <p>
 * 创建日期：16/12/20 下午2:02
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ===================================================
 **/

public class RatioLayout extends FrameLayout {
    //按照宽高比例去显示
    private float ratio =  2.43f;//比例值

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public RatioLayout(Context context) {
        super(context);
    }

    public RatioLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RatioLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //参数1 命名控件 参数2 属性的名字 参数3 默认的值
        float ratio = attrs.getAttributeFloatValue("http://schemas.android.com/apk/res-auto",
                "ratio", 2.43f);
        setRatio(ratio);
    }

    //测量当前布局
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //widthMeasureSpec 宽度的规则 包含两部分：模式和值

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);//模式

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);//宽度大小 （包含内边距）

        int width = widthSize - getPaddingLeft() - getPaddingRight();//去掉左右两边的padding

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);//模式

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);//高度大小 （包含内边距）

        int height = heightSize - getPaddingTop() - getPaddingBottom();//去掉左右两边的padding

        if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY) {
            //修正一下高度值 让高度按照宽度/比例
            height = (int) (width / ratio + 0.5f); //保证四舍五入，要加0.5

        } else if (widthMode != MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            //由于高度是精确的值，宽度随着高度的变化而变化
            width = (int) ((height * ratio) + 0.5f);
        }


        widthMeasureSpec = MeasureSpec.makeMeasureSpec(width + getPaddingLeft() + getPaddingRight(),
                                                        MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height + getPaddingTop() + getPaddingBottom(),
                                                        MeasureSpec.EXACTLY);


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
