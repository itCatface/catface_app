package cc.catface.wechat.mvp.view;

import cc.catface.base.core_framework.light_mvp.LightAct;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.wechat.R;
import cc.catface.wechat.databinding.WechatActivityChattingBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ChattingActivity extends LightAct<LightPresenter, WechatActivityChattingBinding> {

    @Override public int layoutId() {
        return R.layout.wechat_activity_chatting;
    }

}
