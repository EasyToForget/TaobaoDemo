package com.smile.taobaodemo.model.entity;

import com.smile.taobaodemo.base.Type;

import java.util.List;

/**
 * @author Smile Wei
 * @since 2016/10/8.
 */

public class LivePlay {

    private String state;
    private String msg;

    private int totalPage;

    private List<Data> data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {

        private int type = Type.TYPE_SHOW;
        private String livePlayId;
        private String flvPlayUrl;
        private String rtmpPlayUrl;
        private String hlsPlayUrl;
        private String title;
        private String picture;
        private String groupId;
        private String photo;
        private String name;
        private int watchNumber;
        private int likeNumber;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getRtmpPlayUrl() {
            return rtmpPlayUrl;
        }

        public void setRtmpPlayUrl(String rtmpPlayUrl) {
            this.rtmpPlayUrl = rtmpPlayUrl;
        }

        public String getLivePlayId() {
            return livePlayId;
        }

        public void setLivePlayId(String livePlayId) {
            this.livePlayId = livePlayId;
        }

        public String getFlvPlayUrl() {
            return flvPlayUrl;
        }

        public void setFlvPlayUrl(String flvPlayUrl) {
            this.flvPlayUrl = flvPlayUrl;
        }

        public String getHlsPlayUrl() {
            return hlsPlayUrl;
        }

        public void setHlsPlayUrl(String hlsPlayUrl) {
            this.hlsPlayUrl = hlsPlayUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getWatchNumber() {
            return watchNumber;
        }

        public void setWatchNumber(int watchNumber) {
            this.watchNumber = watchNumber;
        }

        public int getLikeNumber() {
            return likeNumber;
        }

        public void setLikeNumber(int likeNumber) {
            this.likeNumber = likeNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}
