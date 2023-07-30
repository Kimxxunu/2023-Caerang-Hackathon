package com.teamtag.tagweb.notice.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "boardList")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @NotNull
    @Column(name = "제목")
    private String title;

    @NotNull
    @Column(name = "주소")
    private String url;

    @Column(name = "기사")
    private String article;

}
