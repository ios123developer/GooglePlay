package com.szzgkon.googleplay.fragment;


import android.support.v4.app.Fragment;
import android.view.View;

import com.szzgkon.googleplay.view.LoadingPage;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends BaseFragment {



    @Override
    public LoadingPage.LoadResult load() {
        return LoadingPage.LoadResult.error;
    }

    @Override
    public View createSuccessView() {
        return null;
    }
}
