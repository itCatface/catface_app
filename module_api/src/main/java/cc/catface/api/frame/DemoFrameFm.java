package cc.catface.api.frame;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.HashMap;
import java.util.Map;

import androidx.fragment.app.Fragment;
import cc.catface.api.ApiHolderActivity;
import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityDemoFrameBinding;
import cc.catface.api.frame.mvc.FrameMVCFm;
import cc.catface.api.frame.mvp.FrameMVPFm;
import cc.catface.api.frame.mvvm.FrameMVVMFm;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.base.utils.android.common_print.dialog.normal.TDialogNormal;
import cc.catface.base.utils.android.common_title.TitleFontAwesome;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.api_frame)
public class DemoFrameFm extends NormalFragment<ApiActivityDemoFrameBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_demo_frame;
    }

    @Override public void createView() {

        title();
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

    private void title() {
        mBinding.tfa.setTitle("开发框架").setIcon1(R.string.fa_chevron_left).setIcon4("模式").setOnClickListener((TitleFontAwesome.OnClickListener) view -> {
            if (view.getId() == R.id.ttv4) {
                TDialogNormal.get(mActivity).list(R.mipmap.ic_launcher_round, "选择模式", mTags, chosenPosition -> {
                    ((ApiHolderActivity) mActivity).replaceFragment(R.id.fl_frame, fragments.get(mTags[chosenPosition]));
                });

            }
        });
    }
}
