package cc.catface.api.view.anim_activity_transfer.activity;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityAnimTransfer1234Binding;
import cc.catface.api.view.anim_activity_transfer.view.SwipeFinishActivityHelper;
import cc.catface.api.view.anim_activity_transfer.view.SwipeFinishActivityHelperEdit;
import cc.catface.base.core_framework.base_normal.NormalActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class AnimTransferBottomActivity extends NormalActivity<ApiActivityAnimTransfer1234Binding> {
    @Override public int layoutId() {
        return R.layout.api_activity_anim_transfer_1_2_3_4;
    }

    @Override protected void initData() {
        String direction = getIntent().getStringExtra(TransferMainActivity.TRANS_KEY);
        mBinding.btJump.setText(direction);
    }

    @Override public void create() {
        new SwipeFinishActivityHelper(this, SwipeFinishActivityHelper.mode_bottom);
    }
}
