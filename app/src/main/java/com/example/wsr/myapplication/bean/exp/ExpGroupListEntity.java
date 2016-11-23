package com.example.wsr.myapplication.bean.exp;


import java.util.List;

/**
 * Created by Administrator on 2016/5/5.
 */
public class ExpGroupListEntity {
    private String groupTitle;

    public List<childEntity> getChildEntityList() {
        return childEntityList;
    }

    public void setChildEntityList(List<childEntity> childEntityList) {
        this.childEntityList = childEntityList;
    }

    private List<childEntity> childEntityList;

    public ExpGroupListEntity() {
    }

    public ExpGroupListEntity(String groupTitle, List<childEntity> childEntityList) {
        this.groupTitle = groupTitle;
        this.childEntityList = childEntityList;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }


    public static class childEntity {
        private String childTitle;

        public childEntity() {
        }

        public childEntity(int childImgId, String childTitle, int num) {
            this.childTitle = childTitle;
        }

        public String getChildTitle() {
            return childTitle;
        }

        public void setChildTitle(String childTitle) {
            this.childTitle = childTitle;
        }

    }
}
