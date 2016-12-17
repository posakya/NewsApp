package com.example.roshan.cinqsnipe;

/**
 * Created by roshan on 12/5/16.
 */

public class BlogDetail {

    private String id;
    private String title;
    private String description;
    private String link;
    private String author;
    private String category;
    private String image;
    private String pubDate;
    private String source;
    private String sitename;

    public BlogDetail(String id, String title, String description, String link, String author, String category, String image, String pubDate, String source, String sitename) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.link = link;
        this.author = author;
        this.category = category;
        this.image = image;
        this.pubDate = pubDate;
        this.source = source;
        this.sitename = sitename;
    }

    public BlogDetail() {

    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }
}


