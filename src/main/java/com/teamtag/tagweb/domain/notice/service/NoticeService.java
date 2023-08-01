package com.teamtag.tagweb.domain.notice.service;

import com.teamtag.tagweb.domain.notice.DTO.NoticeListDTO;
import com.teamtag.tagweb.domain.notice.DTO.WriteDTO;
import com.teamtag.tagweb.domain.notice.repository.NoticeRepository;
import com.teamtag.tagweb.domain.notice.entity.NoticeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

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

//    //각 게시글에 번호를 매긴후 반환한다(+프론트에서 페이징 작업을 하기 위해 게시글의 총 개수도 반환함).
//    public List<NoticeListDTO> getNoticeList() {
//        List<NoticeListDTO> boardList = noticeRepository.findAllBoardList();
//        int listCount = noticeRepository.countAllBoardList();
//        for (int i = 0; i < boardList.size(); i++) {
//            NoticeListDTO dto = boardList.get(i);
//            dto.serialNum = i+1;
//        }
//        return boardList;
//    }

    public int countPage(int listCount) {
        int totalPage = (int) Math.ceil((double) listCount / 10);
        return totalPage;
    }

    public Page<NoticeListDTO> getNoticeList(int page) {
        int size = 10; // define how many items per page
        Pageable pageable = PageRequest.of(page, size, Sort.by("ID").ascending());
        Page<NoticeListDTO> pageResult = noticeRepository.findAllBoardList(pageable);

        // Convert the Page to List and add the serial number.
        List<NoticeListDTO> boardList = pageResult.getContent();
        for (int i = 0; i < boardList.size(); i++) {
            NoticeListDTO dto = boardList.get(i);
            // Assuming serial numbers start from 1 for the first item on each page
            dto.setSerialNum(size * page + i + 1);
        }

        // Update the Page with the new List.
        Page<NoticeListDTO> updatedPage = new PageImpl<>(boardList, pageable, pageResult.getTotalElements());

        return updatedPage;
    }

//
//    public NoticeListDTO CurrentNotice(NoticeListDTO noticeListDTO){
//        Optional<NoticeEntity> NowNotice = noticeRepository.findById()
//        noticeListDTO.ID =
//    }
}
