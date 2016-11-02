package com.example.wsr.myapplication.bean;

import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by cnsunrun on 2016/10/19.
 */
public class BennerBean {
    /**
     * status : 1
     * msg : OK
     * info : [{"id":"909","type":1,"image":"http://test.cnsunrun.com/qbk/Uploads/banner/3757a15a5f5d399.jpg","title":"尚软说","name":"尚软说"}]
     */

    private String status;
    private String msg;
    /**
     * id : 909
     * type : 1
     * image : http://test.cnsunrun.com/qbk/Uploads/banner/3757a15a5f5d399.jpg
     * title : 尚软说
     * name : 尚软说
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
        private int type;
        private String image;
        private String title;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 模拟网络数据
     *
     * @param ss
     * @return
     */
//    public static BennerBean getData() {
//        String ss = "{\"status\":\"1\",\"msg\":\"OK\",\"info\":[{\"id\":\"909\",\"type\":1,\"image\":\"http:\\/\\/test.cnsunrun.com\\/qbk\\/Uploads\\/banner\\/3757a15a5f5d399.jpg\",\"title\":\"\\u5c1a\\u8f6f\\u8bf4\",\"name\":\"\\u5c1a\\u8f6f\\u8bf4\"}]}";
//        try {
//            BennerBean bennerBean = new Gson().fromJson(ss, BennerBean.class);
//            Log.e("wsr", "解析成功:" + bennerBean.toString());
//            return bennerBean;
//        } catch (Exception e) {
//            Log.e("wsr", "解析错误");
//            return null;
//        }
//
//    }
    public static BennerBean getData(String ss) {
        try {
            BennerBean bennerBean = new Gson().fromJson(ss, BennerBean.class);
            Log.e("wsr", "解析成功:" + bennerBean.toString());
            return bennerBean;
        } catch (Exception e) {
            Log.e("wsr", "解析错误");
            return null;
        }

    }
}
