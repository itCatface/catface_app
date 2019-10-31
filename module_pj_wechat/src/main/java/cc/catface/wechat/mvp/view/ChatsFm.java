package cc.catface.wechat.mvp.view;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.ctool.context.TToast;
import cc.catface.ctool.view.recyclerview.ItemClickSupport;
import cc.catface.wechat.R;
import cc.catface.wechat.databinding.WechatFragmentChatsBinding;
import cc.catface.wechat.engine.adapter.ChatsAdapter;
import cc.catface.wechat.engine.domain.ChatsBean;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ChatsFm extends LightFm<LightPresenter, WechatFragmentChatsBinding> {

    @Override public int layoutId() {
        return R.layout.wechat_fragment_chats;
    }

    private List<ChatsBean> mDatas = new ArrayList<>();

    @Override protected void initAction() {
        ItemClickSupport.addTo(mBinding.rvChats).setOnItemClickListener((recyclerView, position, view) -> {
            jumpDetail();
        });
        ItemClickSupport.addTo(mBinding.rvChats).setOnItemLongClickListener((recyclerView, position, view) -> {
            TToast.showNormal("chang an");
            return false;
        });
    }

    @Override protected void initData() {
        for (int i = 0; i < 30; i++) {
            mDatas.add(new ChatsBean(0, "", "name" + i, "content" + i, new Date().toString(), true));
        }
    }

    @Override protected void created() {
        mBinding.rvChats.setHasFixedSize(true);
        mBinding.rvChats.setLayoutManager(new LinearLayoutManager(mActivity));
        mBinding.rvChats.setAdapter(new ChatsAdapter(mDatas));
        mBinding.rvChats.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void jumpDetail() {
        Intent intent = new Intent(mActivity, ChattingActivity.class);
        mActivity.startActivity(intent);
    }
}
