package com.example.wsr.myapplication.bean;

import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by cnsunrun on 2016/10/18.
 * 发现页下面的
 */
public class RedBean {

    /**
     * status : 1
     * msg : OK
     * info : {"lable_result":[{"id":"304","name":"美女","activity":"5"},{"id":"311","name":"景物","activity":"5"},{"id":"310","name":"动物","activity":"3"},{"id":"301","name":"美图","activity":"3"},{"id":"307","name":"女神","activity":"2"},{"id":"308","name":"美食","activity":"2"}],"talent_result":[{"id":"253","nickname":"菩提道心","icon":"http://qzapp.qlogo.cn/qzapp/1105373679/DB1E31E952FFEF224C89E6201C2FA467/100","attention_count":"1","fans_count":"4","feed_count":"1","fond_count":"1","talent_txt":null},{"id":"257","nickname":"天才在左，疯子在右","icon":"http://q.qlogo.cn/qqapp/1105299997/8A9AA39C54864FB63E3C65A33782CCE7/100","attention_count":"0","fans_count":"3","feed_count":"0","fond_count":"0","talent_txt":null},{"id":"269","nickname":"杨霄羽 · Michael Yang","icon":"http://wx.qlogo.cn/mmopen/jj4e65x0Px24HqBXGmsgqTm2x8OictY3ciaiatuHbicCFibyxmBmPBABrre5DwkmWoTUKlribCUku6EsR1lW2tYnwyH0KY1DUqOGwNWyNWAEseHrk/0","attention_count":"0","fans_count":"3","feed_count":"0","fond_count":"0","talent_txt":null},{"id":"252","nickname":"小猫咪","icon":"http://test.cnsunrun.com/qbk/Uploads/head/20160803/57a1644cea00c.jpg","attention_count":"3","fans_count":"2","feed_count":"10","fond_count":"7","talent_txt":"美女达人"},{"id":"256","nickname":"淡定","icon":"http://test.cnsunrun.com/qbk/Uploads/head/20160729/579b59654b08f.jpg","attention_count":"1","fans_count":"2","feed_count":"2","fond_count":"4","talent_txt":null},{"id":"238","nickname":"淡定的程序猿","icon":"http://test.cnsunrun.com/qbk/Uploads/head/20160606/5754f48acb847.jpg","attention_count":"3","fans_count":"2","feed_count":"3","fond_count":"3","talent_txt":null}]}
     */

    private String status;
    private String msg;
    private InfoBean info;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        @Override
        public String toString() {
            return "InfoBean{" +
                    "lable_result=" + lable_result +
                    ", talent_result=" + talent_result +
                    '}';
        }

        /**
         * id : 304
         * name : 美女
         * activity : 5
         */

        private List<LableResultBean> lable_result;
        /**
         * id : 253
         * nickname : 菩提道心
         * icon : http://qzapp.qlogo.cn/qzapp/1105373679/DB1E31E952FFEF224C89E6201C2FA467/100
         * attention_count : 1
         * fans_count : 4
         * feed_count : 1
         * fond_count : 1
         * talent_txt : null
         */

        private List<TalentResultBean> talent_result;

        public List<LableResultBean> getLable_result() {
            return lable_result;
        }

        public void setLable_result(List<LableResultBean> lable_result) {
            this.lable_result = lable_result;
        }

        public List<TalentResultBean> getTalent_result() {
            return talent_result;
        }

        public void setTalent_result(List<TalentResultBean> talent_result) {
            this.talent_result = talent_result;
        }

        public static class LableResultBean {
            private String id;
            private String name;
            private String activity;

            @Override
            public String toString() {
                return "LableResultBean{" +
                        "id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        ", activity='" + activity + '\'' +
                        '}';
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getActivity() {
                return activity;
            }

            public void setActivity(String activity) {
                this.activity = activity;
            }
        }

        public static class TalentResultBean {
            private String id;
            private String nickname;
            private String icon;
            private String attention_count;
            private String fans_count;
            private String feed_count;
            private String fond_count;
            private Object talent_txt;

            @Override
            public String toString() {
                return "TalentResultBean{" +
                        "id='" + id + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", icon='" + icon + '\'' +
                        ", attention_count='" + attention_count + '\'' +
                        ", fans_count='" + fans_count + '\'' +
                        ", feed_count='" + feed_count + '\'' +
                        ", fond_count='" + fond_count + '\'' +
                        ", talent_txt=" + talent_txt +
                        '}';
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getAttention_count() {
                return attention_count;
            }

            public void setAttention_count(String attention_count) {
                this.attention_count = attention_count;
            }

            public String getFans_count() {
                return fans_count;
            }

            public void setFans_count(String fans_count) {
                this.fans_count = fans_count;
            }

            public String getFeed_count() {
                return feed_count;
            }

            public void setFeed_count(String feed_count) {
                this.feed_count = feed_count;
            }

            public String getFond_count() {
                return fond_count;
            }

            public void setFond_count(String fond_count) {
                this.fond_count = fond_count;
            }

            public Object getTalent_txt() {
                return talent_txt;
            }

            public void setTalent_txt(Object talent_txt) {
                this.talent_txt = talent_txt;
            }
        }
    }

    @Override
    public String toString() {
        return "RedBean{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", info=" + info +
                '}';
    }

    /**
     *模拟网络数据
     * @param ss
     * @return
     */
    public static RedBean getData() {
        String ss = "{\"status\":\"1\",\"msg\":\"OK\",\"info\":{\"lable_result\":[{\"id\":\"304\",\"name\":\"\\u7f8e\\u5973\",\"activity\":\"5\"},{\"id\":\"311\",\"name\":\"\\u666f\\u7269\",\"activity\":\"5\"},{\"id\":\"310\",\"name\":\"\\u52a8\\u7269\",\"activity\":\"3\"},{\"id\":\"301\",\"name\":\"\\u7f8e\\u56fe\",\"activity\":\"3\"},{\"id\":\"307\",\"name\":\"\\u5973\\u795e\",\"activity\":\"2\"},{\"id\":\"308\",\"name\":\"\\u7f8e\\u98df\",\"activity\":\"2\"}],\"talent_result\":[{\"id\":\"253\",\"nickname\":\"\\u83e9\\u63d0\\u9053\\u5fc3\",\"icon\":\"http:\\/\\/qzapp.qlogo.cn\\/qzapp\\/1105373679\\/DB1E31E952FFEF224C89E6201C2FA467\\/100\",\"attention_count\":\"1\",\"fans_count\":\"4\",\"feed_count\":\"1\",\"fond_count\":\"1\",\"talent_txt\":null},{\"id\":\"257\",\"nickname\":\"\\u5929\\u624d\\u5728\\u5de6\\uff0c\\u75af\\u5b50\\u5728\\u53f3\",\"icon\":\"http:\\/\\/q.qlogo.cn\\/qqapp\\/1105299997\\/8A9AA39C54864FB63E3C65A33782CCE7\\/100\",\"attention_count\":\"0\",\"fans_count\":\"3\",\"feed_count\":\"0\",\"fond_count\":\"0\",\"talent_txt\":null},{\"id\":\"269\",\"nickname\":\"\\u6768\\u9704\\u7fbd \\u00b7 Michael Yang\",\"icon\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/jj4e65x0Px24HqBXGmsgqTm2x8OictY3ciaiatuHbicCFibyxmBmPBABrre5DwkmWoTUKlribCUku6EsR1lW2tYnwyH0KY1DUqOGwNWyNWAEseHrk\\/0\",\"attention_count\":\"0\",\"fans_count\":\"3\",\"feed_count\":\"0\",\"fond_count\":\"0\",\"talent_txt\":null},{\"id\":\"252\",\"nickname\":\"\\u5c0f\\u732b\\u54aa\",\"icon\":\"http:\\/\\/test.cnsunrun.com\\/qbk\\/Uploads\\/head\\/20160803\\/57a1644cea00c.jpg\",\"attention_count\":\"3\",\"fans_count\":\"2\",\"feed_count\":\"10\",\"fond_count\":\"7\",\"talent_txt\":\"\\u7f8e\\u5973\\u8fbe\\u4eba\"},{\"id\":\"256\",\"nickname\":\"\\u6de1\\u5b9a\",\"icon\":\"http:\\/\\/test.cnsunrun.com\\/qbk\\/Uploads\\/head\\/20160729\\/579b59654b08f.jpg\",\"attention_count\":\"1\",\"fans_count\":\"2\",\"feed_count\":\"2\",\"fond_count\":\"4\",\"talent_txt\":null},{\"id\":\"238\",\"nickname\":\"\\u6de1\\u5b9a\\u7684\\u7a0b\\u5e8f\\u733f\",\"icon\":\"http:\\/\\/test.cnsunrun.com\\/qbk\\/Uploads\\/head\\/20160606\\/5754f48acb847.jpg\",\"attention_count\":\"3\",\"fans_count\":\"2\",\"feed_count\":\"3\",\"fond_count\":\"3\",\"talent_txt\":null}]}}";
        try {
            RedBean redBean = new Gson().fromJson(ss, RedBean.class);
            Log.e("wsr","解析成功:"+redBean.toString());
            return redBean;
        } catch (Exception e) {
            Log.e("wsr", "解析错误");
            return null;
        }
    }

    public static RedBean getData(String ss) {
        try {
            RedBean redBean = new Gson().fromJson(ss, RedBean.class);
            Log.e("wsr","解析成功:"+redBean.toString());
            return redBean;
        } catch (Exception e) {
            Log.e("wsr", "解析错误");
            return null;
        }
    }
}
