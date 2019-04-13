package cc.catface.wanandroid.engine.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class NavigationData implements Serializable {
    private List<Data> data = new ArrayList<>();
    private int errorCode;
    private String errorMsg;

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public class Data implements Serializable {

        private List<Articles> articles = new ArrayList<>();
        private int cid;
        private String name;

        public void setArticles(List<Articles> articles) {
            this.articles = articles;
        }

        public List<Articles> getArticles() {
            return articles;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getCid() {
            return cid;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

    public class Articles implements Serializable {

        private String apkLink;
        private String author;
        private int chapterId;
        private String chapterName;
        private boolean collect;
        private int courseId;
        private String desc;
        private String envelopePic;
        private boolean fresh;
        private int id;
        private String link;
        private String niceDate;
        private String origin;
        private String projectLink;
        private long publishTime;
        private int superChapterId;
        private String superChapterName;
        private List<String> tags = new ArrayList<>();
        private String title;
        private int type;
        private int userId;
        private int visible;
        private int zan;

        public void setApkLink(String apkLink) {
            this.apkLink = apkLink;
        }

        public String getApkLink() {
            return apkLink;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthor() {
            return author;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        public boolean getCollect() {
            return collect;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setFresh(boolean fresh) {
            this.fresh = fresh;
        }

        public boolean getFresh() {
            return fresh;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getLink() {
            return link;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getOrigin() {
            return origin;
        }

        public void setProjectLink(String projectLink) {
            this.projectLink = projectLink;
        }

        public String getProjectLink() {
            return projectLink;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setSuperChapterId(int superChapterId) {
            this.superChapterId = superChapterId;
        }

        public int getSuperChapterId() {
            return superChapterId;
        }

        public void setSuperChapterName(String superChapterName) {
            this.superChapterName = superChapterName;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getUserId() {
            return userId;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getVisible() {
            return visible;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public int getZan() {
            return zan;
        }

    }
}
