package com.teamtag.tagweb.board.controller;

import com.teamtag.tagweb.board.dto.BoardDto;
import com.teamtag.tagweb.board.enumtype.BoardType;
import com.teamtag.tagweb.board.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;



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
    public ResponseEntity<List<BoardDto>> getfrontList() throws Exception{
        List<BoardDto> boardfrontList = boardService.getBoardList(BoardType.Front);
        List<BoardDto> data = new ArrayList<>();
        try{
            for (BoardDto board : boardfrontList) {
                BoardDto boardDto = new BoardDto();
                boardDto.setTitle(board.getTitle());
                boardDto.setUrl(board.getUrl());
                boardDto.setArticle(board.getArticle());
                data.add(boardDto);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(data);
    }


    @GetMapping("/backlist")
    public ResponseEntity<List<BoardDto>> getbackList() throws Exception{
        List<BoardDto> boardbackList = boardService.getBoardList(BoardType.Back);
        List<BoardDto> data = new ArrayList<>();
        try{
            for (BoardDto backboard : boardbackList) {
                BoardDto boardDto = new BoardDto();
                boardDto.setTitle(backboard.getTitle());
                boardDto.setUrl(backboard.getUrl());
                boardDto.setArticle(backboard.getArticle());
                data.add(boardDto);
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(data);
    }

}

