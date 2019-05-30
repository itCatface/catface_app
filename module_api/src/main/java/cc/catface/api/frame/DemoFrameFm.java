package cc.catface.api.frame;

import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Map;

import cc.catface.api.ApiHolderActivity;
import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityDemoFrameBinding;
import cc.catface.api.frame.mvc.FrameMVCFm;
import cc.catface.api.frame.mvp.FrameMVPFm;
import cc.catface.api.frame.mvvm.FrameMVVMFm;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.base.utils.android.common_print.dialog.normal.TDialogNormal;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DemoFrameFm extends NormalFragment<ApiActivityDemoFrameBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_demo_frame;
    }

    @Override public void createView() {

        initFragment();
        ((ApiHolderActivity) mActivity).replaceFragment(R.id.fl_frame, fragments.get("mvc"));
    }

    private Map<String, Fragment> fragments = new HashMap<>();
    private String[] mTags = {"mvc", "mvp", "mvvm"};

    private void initFragment() {
        fragments.put("mvc", new FrameMVCFm());
        fragments.put("mvp", new FrameMVPFm());
        fragments.put("mvvm", new FrameMVVMFm());
    }


    public void tbShowChoseDialog() {
        TDialogNormal.get(mActivity).list(R.mipmap.ic_launcher_round, "选择模式", mTags, chosenPosition -> {
            ((ApiHolderActivity) mActivity).replaceFragment(R.id.fl_frame, fragments.get(mTags[chosenPosition]));
        });
    }
}
