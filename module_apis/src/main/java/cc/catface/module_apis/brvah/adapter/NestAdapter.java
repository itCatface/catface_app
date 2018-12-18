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
import cc.catface.module_apis.brvah.engine.DataServer;
import cc.catface.module_apis.brvah.engine.SpannableStringUtils;

/**
 * 文 件 名: AnimationAdapter
 * 创 建 人: Allen
 * 创建日期: 16/12/24 15:33
 * 邮   箱: AllenCoder@126.com
 * 修改时间：
 * 修改备注：
 */
public class NestAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {
    public NestAdapter() {
        super(R.layout.brvah_item_nest_item, DataServer.getSampleData(20));
    }

    @Override protected void convert(BaseViewHolder helper, Status item) {
        helper.addOnClickListener(R.id.tweetText);
        switch (helper.getLayoutPosition() % 3) {
            case 0:
                helper.setImageResource(R.id.img, R.mipmap.brvah_animation_img1);
                break;
            case 1:
                helper.setImageResource(R.id.img, R.mipmap.brvah_animation_img2);
                break;
            case 2:
                helper.setImageResource(R.id.img, R.mipmap.brvah_animation_img3);
                break;
        }
        helper.setText(R.id.tweetName, "Hoteis in Rio de Janeiro");
        String msg = "\"He was one of Australia's most of distinguished artistes, renowned for his portraits\"";
        ((TextView) helper.getView(R.id.tweetText)).setText(SpannableStringUtils.getBuilder(msg).append("landscapes and nedes").setClickSpan(clickableSpan).create());
        ((TextView) helper.getView(R.id.tweetText)).setMovementMethod(LinkMovementMethod.getInstance());
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
