package cc.catface.module_apis.pdf;

import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.barteksc.pdfviewer.PDFView;

import cc.catface.base.core_framework.base_normal.NormalBaseActivity;
import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.module_apis.R;


/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = "/apis/pdf")
public class PDFActivity extends NormalBaseActivity implements View.OnClickListener {
    @Override public int layoutId() {
        return R.layout.apis_activity_pdf;
    }

    private TextView tv_pdf_page;
    private PDFView pdfv;
    private ProgressBar pb_pdf;
    private EditText et_page;


    @Override public void onClick(View view) {
        if (R.id.bt_pre_page == view.getId()) {
            if (mCurrentPDFPage > 0) pdfv.jumpTo(mCurrentPDFPage - 1, true);
            else pdfv.jumpTo(0, true);
        } else if (R.id.bt_sub_page == view.getId()) {
            if (mCurrentPDFPage < mTotalPDFPages - 1) pdfv.jumpTo(mCurrentPDFPage + 1, true);
            else pdfv.jumpTo(mTotalPDFPages, true);
        } else if (R.id.bt_to_top == view.getId()) {
            pdfv.jumpTo(0, true);
        } else if (R.id.bt_jump == view.getId()) {
            try {
                Integer page = Integer.valueOf(et_page.getText().toString().trim());
                if (page >= 0 || page <= mTotalPDFPages) pdfv.jumpTo(page, true);
            } catch (Exception e) {
                TToast.get(this).showShortNormal("输入页数不合理...");
            }
        } else if (R.id.bt_search_pdf == view.getId()) {

        }
    }

    private PDFView.Configurator mPDFConfigure;
    private int mTotalPDFPages, mCurrentPDFPage;

    @Override public void create() {
        tv_pdf_page = (TextView) findViewById(R.id.tv_pdf_page);
        pdfv = (PDFView) findViewById(R.id.pdfv);
        pb_pdf = (ProgressBar) findViewById(R.id.pb_pdf);
        et_page = (EditText) findViewById(R.id.et_page);
        findViewById(R.id.bt_pre_page).setOnClickListener(this);
        findViewById(R.id.bt_sub_page).setOnClickListener(this);
        findViewById(R.id.bt_to_top).setOnClickListener(this);
        findViewById(R.id.bt_jump).setOnClickListener(this);
        findViewById(R.id.bt_search_pdf).setOnClickListener(this);

        loadInitPDF();
        loadInitView();
    }

    private void loadInitPDF() {
        mPDFConfigure = pdfv.fromAsset("pdfs/android-阳哥面试宝典V3.0.pdf");
        mPDFConfigure.onLoad(nbPages -> mTotalPDFPages = nbPages).onPageScroll((page, positionOffset) -> {
            mCurrentPDFPage = page;
            tv_pdf_page.setText(page + "/" + mTotalPDFPages);
            tv_pdf_page.setVisibility(View.VISIBLE);
            pb_pdf.setVisibility(View.GONE);
        });
        mPDFConfigure.load();
    }

    private void loadInitView() {
        tv_pdf_page.setText("1/" + mTotalPDFPages);
    }


}
