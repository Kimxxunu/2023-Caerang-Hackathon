package com.teamtag.tagweb.notice.service;

import com.teamtag.tagweb.notice.DTO.NoticeListDTO;
import com.teamtag.tagweb.notice.DTO.WriteDTO;
import com.teamtag.tagweb.notice.entity.NoticeEntity;
import com.teamtag.tagweb.notice.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {

    @Autowired
    NoticeRepository noticeRepository;

    //게시물 작성시 디비로 저장하는 메소드
    public void write(WriteDTO writeDTO){
        NoticeEntity notice = new NoticeEntity();
        notice.setTitle(writeDTO.getTitle());
        notice.setContents(writeDTO.getContents());
        notice.setLink(writeDTO.getLink());
        notice.setImageUrl(writeDTO.getImageUrl());
        notice.setViewCount(writeDTO.getViewCount());
        notice.setWriteTime(writeDTO.getWriteTime());
        noticeRepository.save(notice);
    }

    //각 게시글에 번호를 매긴후 반환한다(+프론트에서 페이징 작업을 하기 위해 게시글의 총 개수도 반환함).
    public List<NoticeListDTO> getNoticeList() {
        List<NoticeListDTO> boardList = noticeRepository.findAllBoardList();
        int listCount = noticeRepository.countAllBoardList();
        for (int i = 0; i < boardList.size(); i++) {
            NoticeListDTO dto = boardList.get(i);
            dto.serialNum = i+1;
        }
        return boardList;
    }

    public int countPage(int listCount) {
        int totalPage = (int) Math.ceil((double) listCount / 10);
        return totalPage;
    }

//
//    public NoticeListDTO CurrentNotice(NoticeListDTO noticeListDTO){
//        Optional<NoticeEntity> NowNotice = noticeRepository.findById()
//        noticeListDTO.ID =
//    }
}
