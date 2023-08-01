package com.teamtag.tagweb.domain.board.service;

import com.teamtag.tagweb.domain.board.dto.BoardDto;
import com.teamtag.tagweb.domain.board.entity.BoardEntity;
import com.teamtag.tagweb.domain.board.enumtype.BoardType;
import com.teamtag.tagweb.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private final BoardRepository boardRepository;

    // DB에서 받아오는 건 Entity 인데
    // Service 에서 Controller 로 보낼때는 DTO로 보내야한다.
    // 고래싸움에 새우등터지는 위치.
    // 변환이 필요하다.
    @Override
    public List<BoardDto> getBoardList(BoardType boardType) {
        List<BoardEntity> boardList = boardRepository.findAllByBoardType(boardType);
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (BoardEntity boardEntity : boardList) {
            BoardDto boardDto = EntityToDto(boardEntity);
            boardDtoList.add(boardDto);
        }

        return boardDtoList;
    }


    private BoardDto EntityToDto(BoardEntity boardEntity) {
        BoardDto boardDto = new BoardDto();
        boardDto.setTitle(boardEntity.getTitle());
        boardDto.setUrl(boardEntity.getUrl());
        boardDto.setArticle(boardEntity.getArticle());
        return boardDto;
    }
}
