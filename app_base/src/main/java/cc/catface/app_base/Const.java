package cc.catface.app_base;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class Const {

    public static final boolean IS_DEBUG = true;


    /** 统一管理ARouter的所有路径url */
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
        public static final String api_holder = "/api/api";
        public static final String api_demo_view_holder = "/api/demo_view_holder";

        /* module_apis */
        public static final String apis_activity_holder = "/apis/activity/holder";
        public static final String apis_pdf = "/apis/pdf";
        public static final String apis_brvah = "/apis/brvah";
        public static final String apis_flow_layout = "/apis/flow_layout";
        public static final String apis_sticky_list = "/apis/sticky_list";
        public static final String apis_nano = "/apis/nano";
        public static final String apis_iflytek = "/apis/iflytek";
        public static final String apis_memo = "/apis/memo";
        public static final String apis_loadImg = "/apis/loadImg";

        /* module_work_demo */
        public static final String work_demo_main = "/work/demo/main";
        public static final String work_demo_holder = "/work/demo/holder";
        //
        public static final int fm_id_work_demo_ifly_swipe_change_page = -0x500001;

        /* module_pj_showapi */
        public static final String pj_showapi_main = "/pj_showapi/main";

        /* module_pj_wanandroid */
        public static final String pj_wanandroid_main = "/pj_wanandroid/main";

        /* module_pj_iflytek */
        public static final String pj_iflytek_main = "/pj_iflytek/main";

        /* module_pj_wechat */
        public static final String pj_wechat_main = "/pj_wechat/main";

        /** fragment's id */
        public static final String fm_id_key = "fm_id_key";
        public static final int fm_id_frame = -0x99;
        public static final int fm_id_font_type = -0x98;
        public static final int fm_id_toast = -0x97;
        public static final int fm_id_dialog = -0x96;
        public static final int fm_id_popup = -0x95;
        public static final int fm_id_view_anim = -0x94;
        public static final int fm_id_hardware = -0x93;
        public static final int fm_id_rv_toutiao = -0x9301;
        public static final int fm_id_rv_banner = -0x9302;
        public static final int fm_id_eleme = -0x92;
        public static final int fm_id_load_large_image = -0x90;
        public static final int fm_id_system_info = -0x89;
        public static final int fm_id_multi_touch = -0x88;
        public static final int fm_id_textview_serial = -0x87;
        public static final int fm_id_imageview_serial = -0x86;
        public static final int fm_id_constraintlayout = -0x85;
        public static final int fm_id_data_binding = -0x84;
        public static final int fm_id_room = -0x83;
        public static final int fm_id_crash = -0x82;
        public static final int fm_id_toolbar = -0x82001;
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

        public static final int fm_id_view_loading_spinkit = -0x110001;
        public static final int fm_id_view_loading_smile = -0x110002;
        public static final int fm_id_view_loading_round_progress = -0x110003;

        public static final int fm_id_apis_test_retrofit = -0x190001;
        public static final int fm_id_apis_half_scroll = -0x190002;
        public static final int fm_id_apis_lottie = -0x190003;
    }
}
