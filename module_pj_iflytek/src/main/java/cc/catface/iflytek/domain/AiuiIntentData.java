package cc.catface.iflytek.domain;


import java.util.List;

public class AiuiIntentData {

    @Override public String toString() {
        return "AiuiIntentData{" +
                "onIntent=" + intent +
                '}';
    }

    private Intent intent;

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public Intent getIntent() {
        return this.intent;
    }

    public class Intent {

        @Override public String toString() {
            return "Intent{" +
                    "category='" + category + '\'' +
                    ", intentType='" + intentType + '\'' +
                    ", rc=" + rc +
                    ", semanticType=" + semanticType +
                    ", service='" + service + '\'' +
                    ", uuid='" + uuid + '\'' +
                    ", vendor='" + vendor + '\'' +
                    ", version='" + version + '\'' +
                    ", semantic=" + semantic +
                    ", onState='" + state + '\'' +
                    ", sid='" + sid + '\'' +
                    ", text='" + text + '\'' +
                    '}';
        }

        private String category;

        private String intentType;

        private int rc;

        private int semanticType;

        private String service;

        private String uuid;

        private String vendor;

        private String version;

        private List<Semantic> semantic;

        private String state;

        private String sid;

        private String text;

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCategory() {
            return this.category;
        }

        public void setIntentType(String intentType) {
            this.intentType = intentType;
        }

        public String getIntentType() {
            return this.intentType;
        }

        public void setRc(int rc) {
            this.rc = rc;
        }

        public int getRc() {
            return this.rc;
        }

        public void setSemanticType(int semanticType) {
            this.semanticType = semanticType;
        }

        public int getSemanticType() {
            return this.semanticType;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getService() {
            return this.service;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getUuid() {
            return this.uuid;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public String getVendor() {
            return this.vendor;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getVersion() {
            return this.version;
        }

        public void setSemantic(List<Semantic> semantic) {
            this.semantic = semantic;
        }

        public List<Semantic> getSemantic() {
            return this.semantic;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getState() {
            return this.state;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getSid() {
            return this.sid;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        public class Semantic {

            @Override public String toString() {
                return "Semantic{" +
                        "entrypoint='" + entrypoint + '\'' +
                        ", hazard=" + hazard +
                        ", onIntent='" + intent + '\'' +
                        ", score=" + score +
                        ", slots=" + slots +
                        ", template='" + template + '\'' +
                        '}';
            }

            private String entrypoint;

            private boolean hazard;

            private String intent;

            private int score;

            private List<Slots> slots;

            private String template;

            public void setEntrypoint(String entrypoint) {
                this.entrypoint = entrypoint;
            }

            public String getEntrypoint() {
                return this.entrypoint;
            }

            public void setHazard(boolean hazard) {
                this.hazard = hazard;
            }

            public boolean getHazard() {
                return this.hazard;
            }

            public void setIntent(String intent) {
                this.intent = intent;
            }

            public String getIntent() {
                return this.intent;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public int getScore() {
                return this.score;
            }

            public void setSlots(List<Slots> slots) {
                this.slots = slots;
            }

            public List<Slots> getSlots() {
                return this.slots;
            }

            public void setTemplate(String template) {
                this.template = template;
            }

            public String getTemplate() {
                return this.template;
            }

            public class Slots {

                @Override public String toString() {
                    return "Slots{" +
                            "begin=" + begin +
                            ", end=" + end +
                            ", name='" + name + '\'' +
                            ", normValue='" + normValue + '\'' +
                            ", value='" + value + '\'' +
                            '}';
                }

                private int begin;

                private int end;

                private String name;

                private String normValue;

                private String value;

                public void setBegin(int begin) {
                    this.begin = begin;
                }

                public int getBegin() {
                    return this.begin;
                }

                public void setEnd(int end) {
                    this.end = end;
                }

                public int getEnd() {
                    return this.end;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getName() {
                    return this.name;
                }

                public void setNormValue(String normValue) {
                    this.normValue = normValue;
                }

                public String getNormValue() {
                    return this.normValue;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getValue() {
                    return this.value;
                }

            }
        }
    }

}