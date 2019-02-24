package cc.catface.module_apis.brvah.adapter;

import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cc.catface.app_base.ARouterApp;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.domain.ClickEntity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ItemClickAdapter extends BaseMultiItemQuickAdapter<ClickEntity, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {


    public ItemClickAdapter(List<ClickEntity> data) {
        super(data);
        addItemType(ClickEntity.CLICK_ITEM_VIEW, R.layout.brvah_item_click_view);
        addItemType(ClickEntity.CLICK_ITEM_CHILD_VIEW, R.layout.brvah_item_click_childview);
        addItemType(ClickEntity.LONG_CLICK_ITEM_VIEW, R.layout.brvah_item_long_click_view);
        addItemType(ClickEntity.LONG_CLICK_ITEM_CHILD_VIEW, R.layout.brvah_item_long_click_childview);
        addItemType(ClickEntity.NEST_CLICK_ITEM_CHILD_VIEW, R.layout.brvah_item_nest_click);
    }


    private NestAdapter mNestAdapter;

    @Override protected void convert(BaseViewHolder helper, ClickEntity item) {
        switch (helper.getItemViewType()) {
            case ClickEntity.CLICK_ITEM_VIEW:
                helper.addOnClickListener(R.id.btn);
                break;
            case ClickEntity.CLICK_ITEM_CHILD_VIEW:
                helper.addOnClickListener(R.id.iv_num_reduce).addOnClickListener(R.id.iv_num_add).addOnLongClickListener(R.id.iv_num_reduce).addOnLongClickListener(R.id.iv_num_add);
                break;
            case ClickEntity.LONG_CLICK_ITEM_VIEW:
                helper.addOnLongClickListener(R.id.btn);
                break;
            case ClickEntity.LONG_CLICK_ITEM_CHILD_VIEW:
                helper.addOnLongClickListener(R.id.iv_num_reduce).addOnLongClickListener(R.id.iv_num_add).addOnClickListener(R.id.iv_num_reduce).addOnClickListener(R.id.iv_num_add);
                break;
            case ClickEntity.NEST_CLICK_ITEM_CHILD_VIEW:
                helper.setNestView(R.id.item_click);
                final RecyclerView recyclerView = helper.getView(R.id.nest_list);
                recyclerView.setLayoutManager(new LinearLayoutManager(helper.itemView.getContext(), LinearLayoutManager.VERTICAL, false));
                recyclerView.setHasFixedSize(true);

                mNestAdapter = new NestAdapter();
                mNestAdapter.setOnItemClickListener(this);
                mNestAdapter.setOnItemChildClickListener(this);
                recyclerView.setAdapter(mNestAdapter);
                break;
        }
    }

    @Override public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Toast.makeText(ARouterApp.getContext(), "childView click", Toast.LENGTH_SHORT).show();
    }

    @Override public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Toast.makeText(ARouterApp.getContext(), "嵌套RecycleView item 收到: " + "点击了第 " + position + " 一次", Toast.LENGTH_SHORT).show();
    }
}
