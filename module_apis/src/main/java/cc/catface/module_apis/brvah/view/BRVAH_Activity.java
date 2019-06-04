package cc.catface.module_apis.brvah.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpActivity;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.presenter.BRVAH_PresenterImp;
import cc.catface.module_apis.databinding.BrvahActivityBrvahBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.apis_brvah)
@CreatePresenter(BRVAH_PresenterImp.class)
public class BRVAH_Activity extends MvpActivity<BRVAH_View, BRVAH_PresenterImp, BrvahActivityBrvahBinding> implements BRVAH_View {
    @Override public int layoutId() {
        return R.layout.brvah_activity_brvah;
    }

    @Override public void create() {
        initToolBar();
        replaceFragment(R.id.fl_brvah, mFms[0]);
    }


    /** brvah's demo列表 */
    private PopupWindow mPopup;
    private Fragment[] mFms = {new BRVAH_AnimationFm(), new BRVAH_MultiFm(), new BRVAH_MultiRVAdapterFm(), new BRVAH_HeadFootFm(), new BRVAH_PullToRefreshFm(), new BRVAH_SectionFm(), new
            BRVAH_EmptyViewFm(), new BRVAH_DragSwipeFm(), new BRVAH_ItemClickFm(), new BRVAH_ExpandableFm(), new BRVAH_DataBindingFm(), new BRVAH_UpFetchFm(), new BRVAH_SectionMultiFm()};

    private void popup(View view) {
        if (null == mPopup) {
            ListView lv = new ListView(this);
            lv.setBackgroundColor(Color.WHITE);
            String[] TITLE = {"Animation", "MultipleItem", "MultipleItem_RVAdapter", "Header/Footer", "PullToRefresh", "Section", "EmptyView", "DragAndSwipe", "ItemClick", "ExpandableItem",
                    "DataBinding", "UpFetchData", "SectionMultipleItem"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, TITLE);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener((adapterView, view1, i, l) -> {
                mPopup.dismiss();
                mBar.setTitle(TITLE[i]);
                replaceFragment(R.id.fl_brvah, mFms[i]);
            });
            mPopup = new PopupWindow(lv, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mPopup.setBackgroundDrawable(new ColorDrawable());
        }
        //        mPopup.showAsDropDown(view, 40, view.getHeight());
        mPopup.showAsDropDown(view, 0, 0);
    }


    /** tool bar */
    private ActionBar mBar;

    private void initToolBar() {
        Toolbar toolbar = mBinding.iTbApis.tbTitle;
        setSupportActionBar(toolbar);
        mBar = getSupportActionBar();
        if (null != mBar) {
            mBar.setDisplayShowHomeEnabled(true);
            mBar.setTitle("BRVAH组件使用");
        }
        toolbar.setNavigationIcon(R.mipmap.flaticon_back);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_menu, menu);
        menu.findItem(R.id.menu_normal).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.findItem(R.id.menu_normal).setVisible(true);
        menu.findItem(R.id.menu_normal).setTitle("动画");
        return super.onCreateOptionsMenu(menu);
    }


    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (R.id.menu_normal == item.getItemId()) {
            popup(findViewById(R.id.menu_normal));
        }
        return super.onOptionsItemSelected(item);
    }
}
