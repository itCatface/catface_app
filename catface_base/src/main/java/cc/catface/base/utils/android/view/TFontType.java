package cc.catface.base.utils.android.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TFontType {
    public static void use(TextView tv, String path, String content) {
        tv.setTypeface(Typeface.createFromAsset(tv.getContext().getAssets(), path));
        tv.setText(content);
    }


    /** 维护所有字体 */
    public static class Font {
        public static final String fontawesome_webfont = "fonts/fontawesome_webfont.ttf";
        public static final String hill_house = "fonts/hill_house.ttf";

        public static final String dfhc = "fonts/刀锋黑草.ttf";
        public static final String ldhzt = "fonts/刘德华字体.ttf";
        public static final String qqzt = "fonts/千秋字体.ttf";
        public static final String tkj = "fonts/唐楷简.ttf";
        public static final String tkfm = "fonts/唐楷飞墨.ttf";
        public static final String yqktjt = "fonts/圆趣卡通简体.ttf";
        public static final String ayzt = "fonts/奥运字体.ttf";
        public static final String xjkjt = "fonts/小京楷简体.ttf";
        public static final String xxkt = "fonts/小细楷体.ttf";
        public static final String wyzt = "fonts/微圆字体.ttf";
        public static final String wyt = "fonts/微影体.ttf";
        public static final String xzjt = "fonts/新篆简体.ttf";
        public static final String mbxs = "fonts/毛笔行书.ttf";
        public static final String mbxsjt = "fonts/毛笔行书简体.ttf";
        public static final String jst = "fonts/爵宋体.ttf";
        public static final String tkjt = "fonts/特楷简体.ttf";
        public static final String tskxjt = "fonts/特色空心简体.ttf";
        public static final String tsjt = "fonts/特色简体.ttf";
        public static final String tljt = "fonts/特隶简体.ttf";
        public static final String jfjc = "fonts/疾风草书.ttf";
        public static final String sgt = "fonts/神工体.ttf";
        public static final String kxjt = "fonts/空心简体.ttf";
        public static final String ttjt = "fonts/童体简体.ttf";
        public static final String qmt = "fonts/签名体.ttf";
        public static final String cyls = "fonts/蚕燕隶书.ttf";
        public static final String xsft = "fonts/行书繁体.ttf";
        public static final String gbxs = "fonts/钢笔行书.ttf";
        public static final String fzlsxt = "fonts/非主流手写体.ttf";
        public static final String ffts = "fonts/风帆特色.ttf";
        public static final String hill_house_raw = "raw/hill_house.ttf";

        public static String CURRENT_SYSTEM_TYPE = "fonts/fontawesome_webfont.ttf";

        public static List<String> getAllTypes() {
            List<String> types = new ArrayList<>();
            types.add(fontawesome_webfont);
            types.add(hill_house);
            types.add(dfhc);
            types.add(ldhzt);
            types.add(qqzt);
            types.add(tkj);
            types.add(tkfm);
            types.add(yqktjt);
            types.add(ayzt);
            types.add(xjkjt);
            types.add(xxkt);
            types.add(wyzt);
            types.add(wyt);
            types.add(xzjt);
            types.add(mbxs);
            types.add(mbxsjt);
            types.add(jst);
            types.add(tkjt);
            types.add(tskxjt);
            types.add(tsjt);
            types.add(tljt);
            types.add(jfjc);
            types.add(sgt);
            types.add(kxjt);
            types.add(ttjt);
            types.add(qmt);
            types.add(cyls);
            types.add(xsft);
            types.add(gbxs);
            types.add(fzlsxt);
            types.add(ffts);
            return types;
        }
    }


    /** 替换当前Activity字体 */
    public static void replaceFont(@NonNull Activity context, String fontPath) {
        replaceFont(getRootView(context), fontPath);
    }

    /* 遍历更换当前节点及所有子[TextView类型]节点的字体 */
    private static void replaceFont(@NonNull View root, String fontPath) {
        if(TextUtils.isEmpty(fontPath)) return;

        if(root instanceof TextView) { // If view is TextView or it's subclass, replace it's font
            TextView textView = (TextView) root;
            int style = Typeface.NORMAL;
            if(textView.getTypeface() != null) {
                style = textView.getTypeface().getStyle();
            }
            textView.setTypeface(createTypeface(root.getContext(), fontPath), style);
        } else if(root instanceof ViewGroup) { // If view is ViewGroup, apply this method on it's child views
            ViewGroup viewGroup = (ViewGroup) root;
            for(int i = 0; i < viewGroup.getChildCount(); ++ i) {
                replaceFont(viewGroup.getChildAt(i), fontPath);
            }
        }
    }

    private static Typeface createTypeface(Context context, String fontPath) {
        return Typeface.createFromAsset(context.getAssets(), fontPath);
    }

    /* 获取当前Activity布局的根节点 */
    private static View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }
}
