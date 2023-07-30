package com.teamtag.tagweb.board.controller;

import com.teamtag.tagweb.board.dto.BoardDto;
import com.teamtag.tagweb.board.entity.BoardEntity;
import com.teamtag.tagweb.board.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardServiceImpl boardService;

    /**
     * 2가지 기능 구현 필요
     * 전체 리스트 받아오기 boardlist = id / title / content 다 딸려갈것이고
     * 프론트에서는 이 정보로 title / content 보여주고 Link 로 board/id 줘서 다시 겟매핑보내는거지
     * 상세 게시글 받아오기 board/{?id}
     */

    @GetMapping("/frontlist")
    public ResponseEntity<List<BoardDto>> getfrontList() {
        // list 를 가져오는 로직
        List<BoardEntity> boardList = boardService.getBoardList();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (BoardEntity board : boardList) {
            BoardDto boardDto = new BoardDto();
            boardDto.setTitle(board.getTitle());
            boardDto.setArticle(board.getArticle());
            boardDtoList.add(boardDto);
        }

        // 엄청간다하게 한것. 에러처리같은거 처리해주시면 좋음 try / catch 하고 error로 오면 ResoposeEntity.badRequest() 로 리턴해주도록
        return ResponseEntity.ok().body(boardDtoList);
    }

    @GetMapping("/backlist")
    public ResponseEntity<List<Map<String, String>>> getbackList() {
        // list 를 가져오는 로직
        List<BoardEntity> boardList = boardService.getBoardList();
        List<Map<String, String>> data = new ArrayList<>();

        for (BoardEntity board : boardList) {
            Map<String, String> boardData = new HashMap<>();
            boardData.put("title", board.getTitle());
            boardData.put("url",board.getUrl());
            boardData.put("article", board.getArticle());
            data.add(boardData);
        }

        // 엄청간다하게 한것. 에러처리같은거 처리해주시면 좋음 try / catch 하고 error로 오면 ResoposeEntity.badRequest() 로 리턴해주도록
        return ResponseEntity.ok().body(data);
    }
}

