package cc.catface.module_apis.brvah.adapter;

import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cc.catface.app_base.ARouterApp;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.domain.Status;
import cc.catface.module_apis.brvah.engine.SpannableStringUtils;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class PullToRefreshAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {
    public PullToRefreshAdapter() {
        super(R.layout.brvah_item_animation, null);
    }

    @Override protected void convert(BaseViewHolder helper, Status item) {
        switch (helper.getLayoutPosition() % 3) {
            case 0:
                helper.setImageResource(R.id.iv_icon, R.mipmap.brvah_animation_img1);
                break;
            case 1:
                helper.setImageResource(R.id.iv_icon, R.mipmap.brvah_animation_img2);
                break;
            case 2:
                helper.setImageResource(R.id.iv_icon, R.mipmap.brvah_animation_img3);
                break;
        }
        helper.setText(R.id.tv_title, "Hoteis in Rio de Janeiro");
        String msg = "\"He was one of Australia's most of distinguished artistes, renowned for his portraits\"";
        ((TextView) helper.getView(R.id.tv_content)).setText(SpannableStringUtils.getBuilder(msg).append("landscapes and nedes").setClickSpan(clickableSpan).create());
        ((TextView) helper.getView(R.id.tv_content)).setMovementMethod(LinkMovementMethod.getInstance());
    }

    ClickableSpan clickableSpan = new ClickableSpan() {
        @Override public void onClick(View widget) {
            Toast.makeText(mContext, "事件触发了 landscapes and nedes", Toast.LENGTH_SHORT).show();
        }

        @Override public void updateDrawState(TextPaint ds) {
            ds.setColor(ARouterApp.getContext().getResources().getColor(R.color.clickspan_color));
            ds.setUnderlineText(true);
        }
    };

}
