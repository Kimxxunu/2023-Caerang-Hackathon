package com.teamtag.tagweb.board.service;

import com.teamtag.tagweb.board.dto.BoardDto;
import com.teamtag.tagweb.board.entity.BoardEntity;
import com.teamtag.tagweb.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    BoardRepository boardRepository;

    // DB에서 받아오는 건 Entity 인데
    // Service 에서 Controller 로 보낼때는 DTO로 보내야한다.
    // 고래싸움에 새우등터지는 위치.
    // 변환이 필요하다.
    @Override
    public List<BoardEntity> getBoardList() {
        List<BoardEntity> boardList = boardRepository.findAll();
        /*List<BoardDto> boardDtoList = new ArrayList<>();
        boardList.forEach(board -> {
            boardDtoList.add(board);
        });
        return boardDtoList;*/
        return boardList;
    }
}
