package com.example.xyzreader.data;

/**
 * Created by xayru on 2/7/2018.
 */

public class Article {
    private String id;
    private String serverId;
    private String title;
    private String author;
    private String body;
    private String thumbUrl;
    private String photoUrl;
    private String aspectRatio;
    private String publishedDate;

    public Article(String id, String serverId, String title, String author, String body, String thumbUrl, String photoUrl, String aspectRatio, String publishedDate) {
        this.id = id;
        this.serverId = serverId;
        this.title = title;
        this.author = author;
        this.body = body;
        this.thumbUrl = thumbUrl;
        this.photoUrl = photoUrl;
        this.aspectRatio = aspectRatio;
        this.publishedDate = publishedDate;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }
}
