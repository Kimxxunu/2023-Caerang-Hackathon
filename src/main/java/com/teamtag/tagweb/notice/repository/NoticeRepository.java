package com.teamtag.tagweb.notice.repository;

import com.teamtag.tagweb.notice.DTO.NoticeListDTO;
import com.teamtag.tagweb.notice.DTO.aaaDTO;
import com.teamtag.tagweb.notice.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository extends JpaRepository<NoticeEntity,Integer>{

    //delteNum이 '0'인 것들만 ID,제목,조회수,작성시간을 추출하여 리스트로 저장
    @Query("SELECT new com.teamtag.tagweb.notice.DTO.NoticeListDTO(WL.ID, WL.title, WL.viewCount, WL.writeTime) FROM NoticeEntity WL WHERE WL.deleteNum = 0")
    List<NoticeListDTO> findAllBoardList();

    //deleteNum이 '0'인 것들만 가져온후 총 개수를 매김(현재 게시물의 수) - 현재글 번호를 지정해주기 위함
    @Query("SELECT COUNT(WL) FROM NoticeEntity WL WHERE WL.deleteNum = 0")
    int countAllBoardList();


}
