package cc.catface.module_apis.showapi.domain;

import java.util.List;

public class YYJoke341_1 {

    private int showapi_res_code;
    private String showapi_res_error;
    private Showapi_res_body showapi_res_body;

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_body(Showapi_res_body showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public Showapi_res_body getShowapi_res_body() {
        return showapi_res_body;
    }


    public class Showapi_res_body {

        private int allNum;
        private int allPages;
        private List<Contentlist> contentlist;
        private int currentPage;
        private int maxResult;

        public void setAllNum(int allNum) {
            this.allNum = allNum;
        }

        public int getAllNum() {
            return allNum;
        }

        public void setAllPages(int allPages) {
            this.allPages = allPages;
        }

        public int getAllPages() {
            return allPages;
        }

        public void setContentlist(List<Contentlist> contentlist) {
            this.contentlist = contentlist;
        }

        public List<Contentlist> getContentlist() {
            return contentlist;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setMaxResult(int maxResult) {
            this.maxResult = maxResult;
        }

        public int getMaxResult() {
            return maxResult;
        }


        public class Contentlist {

            private String ct;
            private String text;
            private String title;

            public void setCt(String ct) {
                this.ct = ct;
            }

            public String getCt() {
                return ct;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getText() {
                return text;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTitle() {
                return title;
            }

        }

    }

}