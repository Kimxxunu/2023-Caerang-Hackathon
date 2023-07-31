package com.teamtag.tagweb.notice.DTO;

public class ModifyDTO {

    private String title;
    private String contents;
    private String link;
    private String imageUrl;

    public ModifyDTO(String title, String contents, String link, String imageUrl) {
        this.title = title;
        this.contents = contents;
        this.link = link;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
