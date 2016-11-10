package com.szzgkon.googleplay;

import android.support.v4.app.Fragment;

import com.szzgkon.googleplay.fragment.AppFragment;
import com.szzgkon.googleplay.fragment.GameFragment;
import com.szzgkon.googleplay.fragment.HomeFragment;
import com.szzgkon.googleplay.fragment.SubjectFragment;
import com.szzgkon.googleplay.fragment.TopFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * ===================================================
 *
 * 版权：深圳市中广控信息科技有限公司 版权所有（c）2016
 *
 * 作者：张勇柯
 *
 * 版本：1.0
 *
 * 创建日期：16/11/10 下午2:15
 *
 * 描述：用来创建fragment的类
 *
 * 修订历史：
 *
 * ===================================================
 **/

public class FragmentFactory {

    private static Map<Integer,Fragment> mFragments = new HashMap<Integer, Fragment>();

    public static Fragment creatFragment(int position){

        Fragment fragment = null;

        fragment = mFragments.get(position);

        if(fragment == null){//如果在集合中没有取出来，需要重新创建

        if(position == 0){
            fragment = new HomeFragment();
        }else if(position == 1){
            fragment = new AppFragment();

        }else if(position == 2){
            fragment = new GameFragment();

        }else if(position == 3){
            fragment = new SubjectFragment();

        }else if(position == 4){
            fragment = new GameFragment();

        }else {
            fragment = new TopFragment();

        }
        if(fragment == null){
            mFragments.put(position,fragment);//把创建好的Fragment存放到集合中缓存起来

           }

        }
        return fragment;
    }
}
