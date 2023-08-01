package com.teamtag.tagweb.board.dto;

import com.teamtag.tagweb.board.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BoardDto {
    private Long id;

    private String title;
    private String article;
    private String url;


}
