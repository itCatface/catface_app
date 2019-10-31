package cc.catface.wechat.mvp.view;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.wechat.R;
import cc.catface.wechat.databinding.WechatFragmentContactsBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ContactsFm extends LightFm<LightPresenter, WechatFragmentContactsBinding> {

    @Override public int layoutId() {
        return R.layout.wechat_fragment_contacts;
    }
}
