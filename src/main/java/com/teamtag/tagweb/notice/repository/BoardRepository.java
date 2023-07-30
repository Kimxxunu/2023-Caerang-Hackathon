package com.teamtag.tagweb.notice.repository;

import com.teamtag.tagweb.notice.DTO.aaaDTO;
import com.teamtag.tagweb.notice.entity.BoardListEntity;
import com.teamtag.tagweb.notice.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardListEntity,Integer> {

    @Query("SELECT new com.teamtag.tagweb.notice.DTO.aaaDTO(n.title, n.url, n.article) FROM BoardListEntity n")
    List<aaaDTO> findAllBoardList();

}
