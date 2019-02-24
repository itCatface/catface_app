package cc.catface.module_apis.pdf;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.barteksc.pdfviewer.PDFView;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.module_apis.R;
import cc.catface.module_apis.databinding.ApisActivityPdfBinding;


/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.AROUTER.apis_pdf)
public class PDFActivity extends NormalActivity<ApisActivityPdfBinding> implements View.OnClickListener {
    @Override public int layoutId() {
        return R.layout.apis_activity_pdf;
    }

    @Override public void onClick(View view) {
        if(R.id.bt_pre_page == view.getId()) {
            if(mCurrentPDFPage > 0) mBinding.pdfv.jumpTo(mCurrentPDFPage - 1, true);
            else mBinding.pdfv.jumpTo(0, true);
        } else if(R.id.bt_sub_page == view.getId()) {
            if(mCurrentPDFPage < mTotalPDFPages - 1)
                mBinding.pdfv.jumpTo(mCurrentPDFPage + 1, true);
            else mBinding.pdfv.jumpTo(mTotalPDFPages, true);
        } else if(R.id.bt_to_top == view.getId()) {
            mBinding.pdfv.jumpTo(0, true);
        } else if(R.id.bt_jump == view.getId()) {
            try {
                Integer page = Integer.valueOf(mBinding.etPage.getText().toString().trim());
                if(page >= 0 || page <= mTotalPDFPages) mBinding.pdfv.jumpTo(page, true);
            } catch(Exception e) {
                TToast.get(this).showShortNormal("输入页数不合理...");
            }
        } else if(R.id.bt_search_pdf == view.getId()) {

        }
    }

    @Override protected void initAction() {
        mBinding.btPrePage.setOnClickListener(this);
        mBinding.btSubPage.setOnClickListener(this);
        mBinding.btToTop.setOnClickListener(this);
        mBinding.btJump.setOnClickListener(this);
        mBinding.btSearchPdf.setOnClickListener(this);
    }

    private PDFView.Configurator mPDFConfigure;
    private int mTotalPDFPages, mCurrentPDFPage;

    @Override public void create() {
        title();
        loadInitPDF();
        loadInitView();
    }

    private void title() {
        mBinding.tfa.setTitle(getIntent().getStringExtra(Const.AROUTER.DEFAULT_STRING_KEY)).setIcon1(R.string.fa_chevron_left);
    }

    private void loadInitPDF() {
        mPDFConfigure = mBinding.pdfv.fromAsset("pdfs/android-阳哥面试宝典V3.0.pdf");
        mPDFConfigure.onLoad(nbPages -> mTotalPDFPages = nbPages).onPageScroll((page, positionOffset) -> {
            mCurrentPDFPage = page;
            mBinding.tvPdfPage.setText(page + "/" + mTotalPDFPages);
            mBinding.tvPdfPage.setVisibility(View.VISIBLE);
            mBinding.pbPdf.setVisibility(View.GONE);
        });
        mPDFConfigure.load();
    }

    private void loadInitView() {
        mBinding.tvPdfPage.setText("1/" + mTotalPDFPages);
    }


}
