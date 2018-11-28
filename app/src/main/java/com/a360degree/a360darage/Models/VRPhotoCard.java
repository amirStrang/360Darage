package com.a360degree.a360darage.Models;

public class VRPhotoCard {
    private String logoUrl;
    private String channelName;
    private boolean followed;
    private boolean isAd;
    private String picUrl;
    private String title;
    private boolean isLiked;
    private String time;
    private int likeCount, viewCount;

    public VRPhotoCard() {

    }

    public VRPhotoCard(String logoUrl, String channelName, boolean followed, boolean isAd, String picUrl, String title, boolean isLiked, String time, int likeCount, int viewCount) {
        this.logoUrl = logoUrl;
        this.channelName = channelName;
        this.followed = followed;
        this.isAd = isAd;
        this.picUrl = picUrl;
        this.title = title;
        this.isLiked = isLiked;
        this.time = time;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public boolean isAd() {
        return isAd;
    }

    public void setAd(boolean ad) {
        isAd = ad;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }


}
