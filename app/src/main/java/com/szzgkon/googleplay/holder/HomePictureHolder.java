package com.szzgkon.googleplay.holder;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.szzgkon.googleplay.R;
import com.szzgkon.googleplay.global.GlobalContants;
import com.szzgkon.googleplay.tools.UIUtils;
import com.szzgkon.googleplay.tools.ViewPagerScroller;

import java.util.LinkedList;
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
 * 创建日期：16/11/21 上午9:36
 *
 * 描述：homeFragment 界面中的大图模块
 *
 * 修订历史：
 *
 * ===================================================
 **/

public class HomePictureHolder extends BaseHolder<List<String>> {
     /* 当new HomePictureHolder（）就会调用该方法*/

    private ViewPager viewPager;
    private List<String> datas;
    private AutoRunTask runTask;

    @Override
    public View initView() {

        FrameLayout frameLayout = new FrameLayout(UIUtils.getContext());

        frameLayout.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        viewPager = new ViewPager(UIUtils.getContext());

        viewPager.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                UIUtils.getDimens(R.dimen.home_pic_height)));

        ViewPagerScroller scroller = new ViewPagerScroller(UIUtils.getContext());
        scroller.setScrollDuration(2000);
        scroller.initViewPagerScroll(viewPager);

        frameLayout.addView(viewPager);
        return frameLayout;
    }
     /* holder.setData才会调用*/
    @Override
    public void refreshView(List<String> datas) {
        this.datas = datas;
         viewPager.setAdapter(new HomeAdapter());

        viewPager.setCurrentItem(1000*datas.size());

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:

                    runTask.stop();
                     break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    runTask.start();
                    break;

                default:
                     break;
                }
                return false;//这里如果是true的话，viewPager就不能正常滑动
            }
        });

        runTask = new AutoRunTask();
        runTask.start();

    }

    class HomeAdapter extends PagerAdapter{

        LinkedList<ImageView> convertView = new LinkedList<ImageView>();


        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        /**
         * 判断返回的对象和加载view对象的关系
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            int index = position % datas.size();

            ImageView view;
            if(convertView.size() > 0){

                view = convertView.remove(0);

            }else {

                view = new ImageView(UIUtils.getContext());

            }

            bitmapUtils.display(view, GlobalContants.IMAGE_URL + datas.get(index));

            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           ImageView view = (ImageView)object;
           convertView.add(view);//把移除的对象 添加到缓存集合中
            container.removeView(view);
        }
    }

   private boolean flag;

    public class AutoRunTask implements Runnable{


        @Override
        public void run() {
            if(flag){
                UIUtils.cancel(this);//取消之前的任务

                int currentItem = viewPager.getCurrentItem();
                currentItem++;
                viewPager.setCurrentItem(currentItem);
                //延迟执行当前的任务
                UIUtils.postDelayed(this,5000);//递归调用
            }

        }

        public void start(){
            if(!flag){
                UIUtils.cancel(this);
                flag = true;
                UIUtils.postDelayed(this,5000);//递归调用
            }

        }

        public void stop(){
            if(flag){
                flag = false;
                UIUtils.cancel(this);
            }
        }
    }


}
