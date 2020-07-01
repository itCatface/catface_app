package cc.catface.app_base;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class Const {

    public static final boolean IS_DEBUG = true;


    /**
     * 用于测试使用的网络图片的地址
     */
    public static class TestNetImageUrls {
        public static final String url1 = "http://img1.imgtn.bdimg.com/it/u=1297261984,49161429&fm=15&gp=0.jpg";
        public static final String url2 = "http://f.hiphotos.baidu.com/zhidao/pic/item/b17eca8065380cd734a92e71aa44ad34588281f3.jpg";
        public static final String url3 = "http://dimg01.c-ctrip.com/images/fd/tg/g5/M0B/40/34/CggYr1b7SouAM3GtAAChIW1NNkA494_R_1024_10000.jpg";
        public static final String url4 = "http://2d.zol-img.com.cn/product/3_800x600/619/ce1sAtzKyhJ.jpg";

        public static String getRandomOneUrl() {
            int i = new Random().nextInt(4);
            switch (i) {
                case 0:
                    return url1;
                case 1:
                    return url2;
                case 2:
                    return url3;
                default:
                    return url4;
            }
        }
    }

    /**
     * 统一管理ARouter的所有路径url
     */
    public static class ARouter {
        /*  */
        public static final String DEFAULT_STRING_KEY = "DEFAULT_STRING_KEY";

        public static String getDefaultIntentStringValue(String text) {
            String result;
            try {
                result = text.substring(text.lastIndexOf("-") + 1);
            } catch (Exception e) {
                result = "";
            }
            return result;
        }

        /* module_start */
        public static final String start_welcome = "/start/welcome";
        public static final String start_login = "/start/login";
        public static final String start_main = "/start/main";

        /* module_api */
        public static final String api_fm = "/api/fm";
        public static final String api_holder = "/api/api";
        public static final String api_demo_view_holder = "/api/demo_view_holder";

        /* module_apis */
        public static final String apis_fm = "/apis/fm";
        public static final String apis_activity_holder = "/apis/activity/holder";
        public static final String apis_pdf = "/apis/pdf";
        public static final String apis_brvah = "/apis/brvah";
        public static final String apis_flow_layout = "/apis/flow_layout";
        public static final String apis_sticky_list = "/apis/sticky_list";
        public static final String apis_nano = "/apis/nano";
        public static final String apis_iflytek = "/apis/iflytek";
        public static final String apis_memo = "/apis/memo";
        public static final String apis_loadImg = "/apis/loadImg";

        /* module_pj_showapi */
        public static final String pj_showapi_main = "/pj_showapi/main";

        /* module_pj_wanandroid */
        public static final String pj_wanandroid_main = "/pj_wanandroid/main";

        /* module_pj_iflytek */
        public static final String pj_iflytek_main = "/pj_iflytek/main";

        /* module_pj_wechat */
        public static final String pj_wechat_main = "/pj_wechat/main";

        /**
         * fragment's id
         */
        public static final String fm_id_key = "fm_id_key";

        public static final String fm_id_api_small_func = "demo-小功能示例";
        public static final String fm_id_api_frame = "demo-框架-基础框架示例";
        public static final String fm_id_api_font_type = "demo-系统-字体示例";
        public static final String fm_id_api_toast = "demo-系统-toast示例";
        public static final String fm_id_api_prop = "demo-系统-properties示例";
        public static final String fm_id_api_dialog = "demo-系统-dialog示例";
        public static final String fm_id_api_popup = "demo-系统-popup示例";
        public static final String fm_id_api_view_anim = "demo-系统-view&animator示例";
        public static final String fm_id_api_hardware = "demo-系统-硬件示例";
        public static final String fm_id_api_eleme = "demo-功能-饿了么菜单";
        public static final String fm_id_rv_api_toutiao = "demo-rv-(toutiao)";
        public static final String fm_id_rv_api_banner = "demo-rv-banner";
        public static final String fm_id_api_load_large_image = "demo-功能-加载大图片";
        public static final String fm_id_api_system_info = "demo系统-系统信息";
        public static final String fm_id_api_multi_touch = "demo系统-多点触控示例";
        public static final String fm_id_api_textview_serial = "demo-系统控件-TextView效果示例";
        public static final String fm_id_api_imageview_serial = "demo-系统控件-IV-scaleType属性示例";
        public static final String fm_id_api_constraintlayout = "demo-系统-ConstraintLayout示例";
        public static final String fm_id_api_data_binding = "demo-系统-data_binding示例";
        public static final String fm_id_api_room = "demo-系统-room示例";
        public static final String fm_id_api_crash = "demo-系统-crash处理示例";
        public static final String fm_id_api_toolbar = "demo-系统-toolbar使用示例";
        public static final String fm_id_api_viewpager2 = "demo-viewpager2示例";
        public static final String fm_id_api_clipboard = "demo-clipboard示例";

        public static List<String> apiFmApiList() {
            List<String> list = new ArrayList<>();
            list.add(fm_id_api_small_func);
            list.add(fm_id_api_frame);
            list.add(fm_id_api_font_type);
            list.add(fm_id_api_toast);
            list.add(fm_id_api_prop);
            list.add(fm_id_api_dialog);
            list.add(fm_id_api_popup);
            list.add(fm_id_api_view_anim);
            list.add(fm_id_api_hardware);
            list.add(fm_id_api_eleme);
            list.add(fm_id_rv_api_toutiao);
            list.add(fm_id_rv_api_banner);
            list.add(fm_id_api_load_large_image);
            list.add(fm_id_api_system_info);
            list.add(fm_id_api_multi_touch);
            list.add(fm_id_api_textview_serial);
            list.add(fm_id_api_imageview_serial);
            list.add(fm_id_api_constraintlayout);
            list.add(fm_id_api_data_binding);
            list.add(fm_id_api_crash);
            list.add(fm_id_api_toolbar);
            list.add(fm_id_api_viewpager2);
            list.add(fm_id_api_clipboard);
            return list;
        }


        public static final int fm_id_view_astr = -0x100001;
        public static final int fm_id_view_interpolator = -0x100002;
        public static final int fm_id_view_value = -0x100003;
        public static final int fm_id_view_object = -0x100004;
        public static final int fm_id_view_keyframe = -0x100005;
        public static final int fm_id_view_anim_set = -0x100006;
        public static final int fm_id_view_paint_canvas = -0x100007;
        public static final int fm_id_view_path_text = -0x100008;
        public static final int fm_id_view_range = -0x100009;
        public static final int fm_id_view_bezier_main = -0x1000100;
        public static final int fm_id_view_bezier_paint = -0x1000101;
        public static final int fm_id_view_bezier_wave = -0x1000102;

        public static final String fm_id_view_loading_spinkit = "fm_id_view_loading_spinkit";
        public static final String fm_id_view_loading_smile = "fm_id_view_loading_smile";
        public static final String fm_id_view_loading_round_progress = "fm_id_view_loading_round_progress";

        public static final int fm_id_apis_test_retrofit = -0x190001;
        public static final int fm_id_apis_half_scroll = -0x190002;
        public static final int fm_id_apis_lottie = -0x190003;
        public static final int fm_id_apis_mmkv = -0x190004;
    }
}
