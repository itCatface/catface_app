package cc.catface.module_apis.brvah.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.alibaba.android.arouter.facade.annotation.Route;

import androidx.fragment.app.Fragment;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpActivity;
import cc.catface.base.utils.android.common_title.TitleFontAwesome;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.presenter.BRVAH_PresenterImp;
import cc.catface.module_apis.databinding.BrvahActivityBrvahBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.AROUTER.apis_brvah)
@CreatePresenter(BRVAH_PresenterImp.class)
public class BRVAH_Activity extends MvpActivity<BRVAH_View, BRVAH_PresenterImp, BrvahActivityBrvahBinding> implements BRVAH_View {
    @Override public int layoutId() {
        return R.layout.brvah_activity_brvah;
    }

    @Override protected void initAction() {
        mBinding.tfa.setOnClickListener((TitleFontAwesome.OnClickListener) view -> {
            if(R.id.ttv1 == view.getId()) finish();
            else if(R.id.ttv4 == view.getId()) popup(view);
        });
    }

    @Override public void create() {
        title();
        replaceFragment(R.id.fl_brvah, mFms[0]);
    }

    private void title() {
        mBinding.tfa.setTitle(getIntent().getStringExtra(Const.AROUTER.DEFAULT_STRING_KEY)).setIcon1(R.string.fa_chevron_left).setIcon4(R.string.fa_reorder);
    }


    /** brvah's demo列表 */
    private PopupWindow mPopup;
    private Fragment[] mFms = {new BRVAH_AnimationFm(), new BRVAH_MultiFm(), new BRVAH_MultiRVAdapterFm(), new BRVAH_HeadFootFm(), new BRVAH_PullToRefreshFm(), new BRVAH_SectionFm(), new
            BRVAH_EmptyViewFm(), new BRVAH_DragSwipeFm(), new BRVAH_ItemClickFm(), new BRVAH_ExpandableFm(), new BRVAH_DataBindingFm(), new BRVAH_UpFetchFm(), new BRVAH_SectionMultiFm()};

    private void popup(View view) {
        if(null == mPopup) {
            ListView lv = new ListView(this);
            lv.setBackgroundColor(Color.WHITE);
            String[] TITLE = {"Animation", "MultipleItem", "MultipleItem_RVAdapter", "Header/Footer", "PullToRefresh", "Section", "EmptyView", "DragAndSwipe", "ItemClick", "ExpandableItem",
                    "DataBinding", "UpFetchData", "SectionMultipleItem"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, TITLE);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener((adapterView, view1, i, l) -> {
                mPopup.dismiss();
                mBinding.tfa.setTitle(TITLE[i]);
                replaceFragment(R.id.fl_brvah, mFms[i]);
            });
            mPopup = new PopupWindow(lv, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mPopup.setBackgroundDrawable(new ColorDrawable());
        }
        mPopup.showAsDropDown(view, 40, view.getHeight());
    }
}
