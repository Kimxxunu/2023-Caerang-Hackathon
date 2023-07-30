package com.teamtag.tagweb.board.entity;

import com.sun.istack.NotNull;
import com.teamtag.tagweb.board.dto.BoardDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "board")
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String article;

    private String url;

    public String getTitle() {
        return title;
    }

    public String getArticle() {
        return article;
    }

    public String getUrl() {
        return url;
    }

}
