package cc.catface.base.core_framework.base_normal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public abstract class NormalBaseFragmentID extends Fragment {

    public Activity mActivity;

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(layoutId(), container, false);
        ButterKnife.bind(this, view);
        ids(view);
        createView();
        return view;
    }

    public abstract void ids(View v);

    public abstract int layoutId();

    public abstract void createView();
}
