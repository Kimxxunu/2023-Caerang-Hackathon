package com.teamtag.tagweb.notice.DTO;

public class aaaDTO {
    private String title;
    private String url;
    private String article;

    public aaaDTO(String title, String url, String article) {
        this.title = title;
        this.url = url;
        this.article = article;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }
}
