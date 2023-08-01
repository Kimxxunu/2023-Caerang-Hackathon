package com.teamtag.tagweb.domain.notice.entity;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "notice")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @NotNull
    @Column(name = "제목")
    private String title;

    @NotNull
    @Column(name = "글내용")
    private String contents;

    @Column(name = "추가링크")
    private String addlink;

    @Column(name = "이미지경로")
    public String imageUrl;

    @NotNull
    @Column(name = "작성시간")
    private String writeTime;

    @Column(name = "수정시간")
    private String modifyTime;

    @Column(name = "조회수")
    private int viewCount;

    @Column(name = "삭제값") //삭제값이 1이면 삭제된 게시글임
    private int deleteNum = 0;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(String writeTime) {
        this.writeTime = writeTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getDeleteNum() {
        return deleteNum;
    }

    public String getLink() {
        return addlink;
    }

    public void setLink(String link) {
        this.addlink = link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDeleteNum(int deleteNum) {
        this.deleteNum = deleteNum;
    }
}
