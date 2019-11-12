package cc.catface.wechat.engine.adapter;

import android.view.View;

import java.util.List;

import cc.catface.ctool.view.recyclerview.AdapterList;
import cc.catface.wechat.R;
import cc.catface.wechat.databinding.WechatItemChatsBinding;
import cc.catface.wechat.engine.domain.ChatsBean;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ChatsAdapter extends AdapterList<ChatsBean, WechatItemChatsBinding> {

    public ChatsAdapter(List<ChatsBean> datas) {
        super(datas);
    }

    @Override public int layoutId() {
        return R.layout.wechat_item_chats;
    }

    @Override public void onBindHolder(WechatItemChatsBinding binding, int position) {
        ChatsBean data = getDatas().get(position);
        binding.tvName.setText(data.getName());
        binding.tvContent.setText(data.getContent());
        if (position == getDatas().size() -1) {
            binding.vLine.setVisibility(View.INVISIBLE);
        } else {
            binding.vLine.setVisibility(View.VISIBLE);
        }
    }

}
