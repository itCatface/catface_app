package cc.catface.app_base;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class Const {

    public static final boolean IS_DEBUG = true;


    /** 统一管理ARouter的所有路径url */
    public static class AROUTER {
        /*  */
        public static final String DEFAULT_STRING_KEY = "DEFAULT_STRING_KEY";

        public static final String getDefaultIntentStringValue(String text) {
            String result;
            try {
                result = text.substring(text.lastIndexOf("-") + 1);
            } catch(Exception e) {
                result = "";
            }
            return result;
        }


        /* module_arouter_demo */
        public static final String test_arouter = "/test/arouter";


        /* module_start */
        public static final String start_welcome = "/start/welcome";
        public static final String start_login = "/start/login";
        public static final String start_main = "/start/main";


        /* module_api */
        public static final String api_frame = "/api/frame";
        public static final String api_font = "/api/font";
        public static final String api_toast = "/api/toast";
        public static final String api_dialog = "/api/dialog";
        public static final String api_popup = "/api/popup";
        public static final String api_navigation = "/api/view/navigation";
        public static final String api_camera = "/api/camera";
        public static final String api_eleme = "/api/eleme";
        public static final String api_toutiao = "/api/toutiao";
        public static final String api_banner = "/api/banner";
        public static final String api_loadHugeImg = "/api/loadHugeImg";
        public static final String api_appInfo = "/api/appInfo";
        public static final String api_multi_finger = "/api/multi_finger";
        public static final String api_attrs_ivScaleType = "/api/attrs/ivScaleType";
        public static final String api_test_text_view = "/api/test_text_view";
        public static final String api_test_constraint_layout = "/api/test_constraint_layout";
        public static final String api_databinding = "/api/databinding";
        public static final String api_room = "/api/room";
        public static final String api_crash = "/api/crash";


        /* module_apis */
        public static final String apis_pdf = "/apis/pdf";
        public static final String apis_brvah = "/apis/brvah";
        public static final String apis_flow_layout = "/apis/flow_layout";
        public static final String apis_sticky_list = "/apis/sticky_list";
        public static final String apis_nano = "/apis/nano";
        public static final String apis_iflytek = "/apis/iflytek";
        public static final String apis_memo = "/apis/memo";
        public static final String apis_showapi = "/apis/showapi";
        public static final String apis_loadImg = "/apis/loadImg";
    }
}
