package com.teamtag.tagweb.domain.notice.repository;

import com.teamtag.tagweb.domain.notice.DTO.NoticeListDTO;
import com.teamtag.tagweb.domain.notice.entity.NoticeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface NoticeRepository extends JpaRepository<NoticeEntity,Integer>{

    //delteNum이 '0'인 것들만 ID,제목,조회수,작성시간을 추출하여 리스트로 저장
    @Query("SELECT new com.teamtag.tagweb.domain.notice.DTO.NoticeListDTO(WL.ID, WL.title, WL.viewCount, WL.writeTime) FROM NoticeEntity WL WHERE WL.deleteNum = 0")
    List<NoticeListDTO> findAllBoardList();

    //deleteNum이 '0'인 것들만 가져온후 총 개수를 매김(현재 게시물의 수) - 현재글 번호를 지정해주기 위함
    @Query("SELECT COUNT(WL) FROM NoticeEntity WL WHERE WL.deleteNum = 0")
    int countAllBoardList();

    //deleteNum 값이 '0'인 NoticeEntity 객체들을 반환
    @Query("SELECT new com.teamtag.tagweb.domain.notice.DTO.NoticeListDTO(WL.ID, WL.title, WL.viewCount, WL.writeTime) FROM NoticeEntity WL WHERE WL.deleteNum = 0")
    Page<NoticeListDTO> findAllBoardList(Pageable pageable);
    //제목에 매개변수로 전달받은 NoticeEntity 객체를 페이징후 반환
    Page<NoticeEntity> findByTitleContaining(String title, Pageable pageable);
    //내용에 매개변수로 전달받은 NoticeEntity 객체를 페이징후 반환
    Page<NoticeEntity> findByContentsContaining(String contents, Pageable pageable);
    //ID가 매개변수로 전달받은 id와 일치하는 NoticeEntity 객체를 Optional 형태로 반환
    Optional<NoticeEntity> findById(int id);


}
