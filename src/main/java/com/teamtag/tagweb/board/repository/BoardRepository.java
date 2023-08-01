package com.teamtag.tagweb.board.repository;

import com.teamtag.tagweb.board.entity.BoardEntity;
import com.teamtag.tagweb.board.enumtype.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findAllByBoardType(BoardType boardType);
}
