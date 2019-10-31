package cc.catface.api.view.anim_activity_transfer.activity;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityAnimTransfer1234Binding;
import cc.catface.api.view.anim_activity_transfer.view.SwipeFinishActivityHelper;
import cc.catface.base.core_framework.light_mvp.LightAct;
import cc.catface.base.core_framework.light_mvp.LightPresenter;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class AnimTransferLeftActivity extends LightAct<LightPresenter, ApiActivityAnimTransfer1234Binding> {

    @Override public int layoutId() {
        return R.layout.api_activity_anim_transfer_1_2_3_4;
    }

    @Override protected void initData() {
        String direction = getIntent().getStringExtra(TransferMainActivity.TRANS_KEY);
        mBinding.btJump.setText(direction);
    }

    @Override protected void created() {
        new SwipeFinishActivityHelper(this, SwipeFinishActivityHelper.mode_left);
    }
}
