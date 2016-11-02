package com.example.wsr.myapplication.bean;

import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by cnsunrun on 2016/10/18.
 */
public class SpecialBean {
    /**
     * {"status":"1","msg":"OK","info":[{"id":"47","title":"\u7f8e\u5973","role_uid":"0","sort":"50","type":"1","addtime":"1470195736","special_lable":"304,308,307,306,305,279,","group_id":"0"},{"id":"50","title":"\u840c\u7269","role_uid":"0","sort":"3","type":"1","addtime":"1470195759","special_lable":"310,","group_id":"0"},{"id":"48","title":"\u641e\u7b11","role_uid":"","sort":"2","type":"1","addtime":"1470195739","special_lable":"309,308,","group_id":"0"},{"id":"49","title":"\u5c1a\u8f6f\u8bf4","role_uid":"","sort":"2","type":"1","addtime":"1470195744","special_lable":null,"group_id":"0"},{"id":"51","title":"\u7f8e\u666f","role_uid":"0","sort":"1","type":"1","addtime":"1470195771","special_lable":"311,","group_id":"0"}]}
     * <p/>
     * status : 1
     * msg : OK
     * info : [{"id":"47","title":"美女","role_uid":"0","sort":"50","type":"1","addtime":"1470195736","special_lable":"304,308,307,306,305,279,","group_id":"0"},{"id":"50","title":"萌物","role_uid":"0","sort":"3","type":"1","addtime":"1470195759","special_lable":"310,","group_id":"0"},{"id":"48","title":"搞笑","role_uid":"","sort":"2","type":"1","addtime":"1470195739","special_lable":"309,308,","group_id":"0"},{"id":"49","title":"尚软说","role_uid":"","sort":"2","type":"1","addtime":"1470195744","special_lable":null,"group_id":"0"},{"id":"51","title":"美景","role_uid":"0","sort":"1","type":"1","addtime":"1470195771","special_lable":"311,","group_id":"0"}]
     */

    private String status;
    private String msg;
    /**
     * id : 47
     * title : 美女
     * role_uid : 0
     * sort : 50
     * type : 1
     * addtime : 1470195736
     * special_lable : 304,308,307,306,305,279,
     * group_id : 0
     */

    private List<InfoBean> info;

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

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        private String id;
        private String title;
        private String role_uid;
        private String sort;
        private String type;
        private String addtime;
        private String special_lable;
        private String group_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getRole_uid() {
            return role_uid;
        }

        public void setRole_uid(String role_uid) {
            this.role_uid = role_uid;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getSpecial_lable() {
            return special_lable;
        }

        public void setSpecial_lable(String special_lable) {
            this.special_lable = special_lable;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }
    }

    @Override
    public String toString() {
        return "SpecialBean{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", info=" + info +
                '}';
    }


    public static SpecialBean getData() {
        String ss = "{\"status\":\"1\",\"msg\":\"OK\",\"info\":[{\"id\":\"47\",\"title\":\"\\u7f8e\\u5973\",\"role_uid\":\"0\",\"sort\":\"50\",\"type\":\"1\",\"addtime\":\"1470195736\",\"special_lable\":\"304,308,307,306,305,279,\",\"group_id\":\"0\"},{\"id\":\"50\",\"title\":\"\\u840c\\u7269\",\"role_uid\":\"0\",\"sort\":\"3\",\"type\":\"1\",\"addtime\":\"1470195759\",\"special_lable\":\"310,\",\"group_id\":\"0\"},{\"id\":\"48\",\"title\":\"\\u641e\\u7b11\",\"role_uid\":\"\",\"sort\":\"2\",\"type\":\"1\",\"addtime\":\"1470195739\",\"special_lable\":\"309,308,\",\"group_id\":\"0\"},{\"id\":\"49\",\"title\":\"\\u5c1a\\u8f6f\\u8bf4\",\"role_uid\":\"\",\"sort\":\"2\",\"type\":\"1\",\"addtime\":\"1470195744\",\"special_lable\":null,\"group_id\":\"0\"},{\"id\":\"51\",\"title\":\"\\u7f8e\\u666f\",\"role_uid\":\"0\",\"sort\":\"1\",\"type\":\"1\",\"addtime\":\"1470195771\",\"special_lable\":\"311,\",\"group_id\":\"0\"}]}";
        try {
            SpecialBean specialBeans = new Gson().fromJson(ss, SpecialBean.class);
            return specialBeans;
        } catch (Exception e) {
            Log.e("wsr", "解析错误");
            return null;
        }
    }

    public static SpecialBean getData(String ss) {
        try {
            SpecialBean specialBeans = new Gson().fromJson(ss, SpecialBean.class);
            return specialBeans;
        } catch (Exception e) {
            Log.e("wsr", "解析错误");
            return null;
        }
    }
}
