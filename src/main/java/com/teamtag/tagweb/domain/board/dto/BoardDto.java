package com.teamtag.tagweb.domain.board.dto;

import com.teamtag.tagweb.domain.board.entity.BoardEntity;
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
