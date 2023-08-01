package com.teamtag.tagweb.domain.board.entity;


import com.teamtag.tagweb.domain.board.enumtype.BoardType;
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

    @Column(length = 10000)
    private String article;

    private String url;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

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
