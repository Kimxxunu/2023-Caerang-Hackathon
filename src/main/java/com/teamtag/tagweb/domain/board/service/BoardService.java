package com.teamtag.tagweb.domain.board.service;

import com.teamtag.tagweb.domain.board.dto.BoardDto;
import com.teamtag.tagweb.domain.board.entity.BoardEntity;
import com.teamtag.tagweb.domain.board.enumtype.BoardType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
    // 구현을 하기 전에 인터페이스에서는 어떤 메소드를 만들 것인가를 정의하고 시작한다.
    // 그 정의가 끝나고 나서야 implement (구현) 체를 만들어서 구현을 시작한다.
    List<BoardDto> getBoardList(BoardType boardType);

}
