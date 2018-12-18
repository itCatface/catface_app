package cc.catface.api.toutiao.domain;

public class ToutiaoCommonBean {
    public int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int pic;

    private TopText topText;
    private TextImage1 textImage1;
    private TextImage3 textImage3;
    private TextVideo1 textVideo1;
    private AdImage1Bottom adImage1Bottom;
    private MoreSplendid moreSplendid;

    public TopText getTopText() {
        return topText;
    }

    public void setTopText(TopText topText) {
        this.topText = topText;
    }

    public TextImage1 getTextImage1() {
        return textImage1;
    }

    public void setTextImage1(TextImage1 textImage1) {
        this.textImage1 = textImage1;
    }

    public TextImage3 getTextImage3() {
        return textImage3;
    }

    public void setTextImage3(TextImage3 textImage3) {
        this.textImage3 = textImage3;
    }

    public TextVideo1 getTextVideo1() {
        return textVideo1;
    }

    public void setTextVideo1(TextVideo1 textVideo1) {
        this.textVideo1 = textVideo1;
    }

    public AdImage1Bottom getAdImage1Bottom() {
        return adImage1Bottom;
    }

    public void setAdImage1Bottom(AdImage1Bottom adImage1Bottom) {
        this.adImage1Bottom = adImage1Bottom;
    }

    public MoreSplendid getMoreSplendid() {
        return moreSplendid;
    }

    public void setMoreSplendid(MoreSplendid moreSplendid) {
        this.moreSplendid = moreSplendid;
    }

    public static class TopText {
        private String title;
        private String Text;
        private String mediaSrc;
        private int commentTimes;
        private String time;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return Text;
        }

        public void setText(String text) {
            Text = text;
        }

        public String getMediaSrc() {
            return mediaSrc;
        }

        public void setMediaSrc(String mediaSrc) {
            this.mediaSrc = mediaSrc;
        }

        public int getCommentTimes() {
            return commentTimes;
        }

        public void setCommentTimes(int commentTimes) {
            this.commentTimes = commentTimes;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class TextImage1 {
        private String title;
        private String imageUrl;
        private String mediaSrc;
        private int commentTimes;
        private String time;


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getMediaSrc() {
            return mediaSrc;
        }

        public void setMediaSrc(String mediaSrc) {
            this.mediaSrc = mediaSrc;
        }

        public int getCommentTimes() {
            return commentTimes;
        }

        public void setCommentTimes(int commentTimes) {
            this.commentTimes = commentTimes;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class TextImage3 {
        private String title;
        private String url1, url2, url3;
        private String mediaSrc;
        private int commentTimes;
        private String time;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl1() {
            return url1;
        }

        public void setUrl1(String url1) {
            this.url1 = url1;
        }

        public String getUrl2() {
            return url2;
        }

        public void setUrl2(String url2) {
            this.url2 = url2;
        }

        public String getUrl3() {
            return url3;
        }

        public void setUrl3(String url3) {
            this.url3 = url3;
        }

        public String getMediaSrc() {
            return mediaSrc;
        }

        public void setMediaSrc(String mediaSrc) {
            this.mediaSrc = mediaSrc;
        }

        public int getCommentTimes() {
            return commentTimes;
        }

        public void setCommentTimes(int commentTimes) {
            this.commentTimes = commentTimes;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class TextVideo1 {
        private String title;
        private String url;
        private String mediaSrc;
        private int commentTimes;
        private String time;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getMediaSrc() {
            return mediaSrc;
        }

        public void setMediaSrc(String mediaSrc) {
            this.mediaSrc = mediaSrc;
        }

        public int getCommentTimes() {
            return commentTimes;
        }

        public void setCommentTimes(int commentTimes) {
            this.commentTimes = commentTimes;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class AdImage1Bottom {
        private String title;
        private String label;
        private String imageUrl;
        private String time;


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class MoreSplendid {
        private String[] urls;

        public String[] getUrls() {
            return urls;
        }

        public void setUrls(String[] urls) {
            this.urls = urls;
        }
    }
}

