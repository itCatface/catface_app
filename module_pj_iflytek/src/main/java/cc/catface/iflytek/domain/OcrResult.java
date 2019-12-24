package cc.catface.iflytek.domain;

import java.util.List;

public class OcrResult {

    private String code;
    private Data data;
    private String desc;
    private String sid;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSid() {
        return sid;
    }

    public class Data {

        private List<Block> block;

        public void setBlock(List<Block> block) {
            this.block = block;
        }

        public List<Block> getBlock() {
            return block;
        }

        public class Block {

            private String type;
            private List<Line> line;

            public void setType(String type) {
                this.type = type;
            }

            public String getType() {
                return type;
            }

            public void setLine(List<Line> line) {
                this.line = line;
            }

            public List<Line> getLine() {
                return line;
            }

            public class Line {

                private int confidence;
                private List<Word> word;

                public void setConfidence(int confidence) {
                    this.confidence = confidence;
                }

                public int getConfidence() {
                    return confidence;
                }

                public void setWord(List<Word> word) {
                    this.word = word;
                }

                public List<Word> getWord() {
                    return word;
                }

                public class Word {

                    private String content;

                    public void setContent(String content) {
                        this.content = content;
                    }

                    public String getContent() {
                        return content;
                    }

                }
            }
        }
    }
}