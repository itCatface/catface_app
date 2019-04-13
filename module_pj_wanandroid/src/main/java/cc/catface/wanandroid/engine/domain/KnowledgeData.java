package cc.catface.wanandroid.engine.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KnowledgeData implements Serializable {

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

        private List<Children> children = new ArrayList<>();
        private int courseId;
        private int id;
        private String name;
        private int order;
        private int parentChapterId;
        private boolean userControlSetTop;
        private int visible;

        public void setChildren(List<Children> children) {
            this.children = children;
        }

        public List<Children> getChildren() {
            return children;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getOrder() {
            return order;
        }

        public void setParentChapterId(int parentChapterId) {
            this.parentChapterId = parentChapterId;
        }

        public int getParentChapterId() {
            return parentChapterId;
        }

        public void setUserControlSetTop(boolean userControlSetTop) {
            this.userControlSetTop = userControlSetTop;
        }

        public boolean getUserControlSetTop() {
            return userControlSetTop;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getVisible() {
            return visible;
        }

        public class Children implements Serializable {

            private List<String> children = new ArrayList<>();
            private int courseId;
            private int id;
            private String name;
            private int order;
            private int parentChapterId;
            private boolean userControlSetTop;
            private int visible;

            public void setChildren(List<String> children) {
                this.children = children;
            }

            public List<String> getChildren() {
                return children;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getId() {
                return id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public int getOrder() {
                return order;
            }

            public void setParentChapterId(int parentChapterId) {
                this.parentChapterId = parentChapterId;
            }

            public int getParentChapterId() {
                return parentChapterId;
            }

            public void setUserControlSetTop(boolean userControlSetTop) {
                this.userControlSetTop = userControlSetTop;
            }

            public boolean getUserControlSetTop() {
                return userControlSetTop;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public int getVisible() {
                return visible;
            }

        }
    }
}