package com.prpr894.baseioclibrary.ioc;

import android.app.Activity;
import android.view.View;

/**
 * Created by prpr894 on 2018/11/22 0022
 * Description: View.findViewById的辅助类
 */
public class ViewFinder {
    private Activity mActivity;
    private View mView;

    public ViewFinder(Activity activity) {
        mActivity = activity;
    }

    public ViewFinder(View view) {
        mView = view;
    }

    public View findViewById(int viewId) {
        return mActivity != null ? mActivity.findViewById(viewId) : mView.findViewById(viewId);
    }
}
