package com.szzgkon.googleplay.fragment;


import android.support.v4.app.Fragment;
import android.view.View;

import com.szzgkon.googleplay.view.LoadingPage;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppFragment extends BaseFragment {


    @Override
    public View createSuccessView() {
        return null;
    }

    @Override
    protected LoadingPage.LoadResult load() {
        return LoadingPage.LoadResult.error;
    }
}
