    package com.teamtag.tagweb.domain.notice.service;
    import com.teamtag.tagweb.domain.notice.DTO.NoticeListDTO;
    import com.teamtag.tagweb.domain.notice.DTO.WriteDTO;
    import com.teamtag.tagweb.domain.notice.repository.NoticeRepository;
    import com.teamtag.tagweb.domain.notice.entity.NoticeEntity;
    import org.springframework.data.domain.*;
    import org.springframework.stereotype.Service;
    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    public class NoticeService {
        NoticeRepository noticeRepository;
        public NoticeService(NoticeRepository noticeRepository) {
            this.noticeRepository = noticeRepository;
        }
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
        //전체게시물을 페이지에 맞게 반환하는 메소드
        public Page<NoticeListDTO> getNoticeList(int page) {
            int size = 10; // 페이지 당 항목 수를 정의
            Pageable pageable = PageRequest.of(page, size, Sort.by("ID").ascending());
            Page<NoticeListDTO> pageResult = noticeRepository.findAllBoardList(pageable);

            // 페이지를 리스트로 변환하고 일련번호를 추가.
            List<NoticeListDTO> boardList = pageResult.getContent();
            for (int i = 0; i < boardList.size(); i++) {
                NoticeListDTO dto = boardList.get(i);
                // 각 페이지의 첫 번째 항목에서 일련번호가 1부터 시작한다고 가정
                dto.setSerialNum(size * page + i + 1);
            }
            // 새 리스트로 페이지 업데이트.
            Page<NoticeListDTO> updatedPage = new PageImpl<>(boardList, pageable, pageResult.getTotalElements());
            return updatedPage;
        }
        //제목 검색시 일치하는 게시물을 반환하는 메소드
        public Page<NoticeListDTO> getNoticeListByTitle(int page, String title) {
            int size = 10;
            Pageable pageable = PageRequest.of(page, size, Sort.by("ID").ascending());
            Page<NoticeEntity> pageResult = noticeRepository.findByTitleContaining(title, pageable);
            return toNoticeListDTOPage(pageResult, pageable);
        }
        //내용 검색시 일치하는 게시물을 반환하는 메소드
        public Page<NoticeListDTO> getNoticeListByContent(int page, String content) {
            int size = 10;
            Pageable pageable = PageRequest.of(page, size, Sort.by("ID").ascending());
            Page<NoticeEntity> pageResult = noticeRepository.findByContentsContaining(content, pageable);
            return toNoticeListDTOPage(pageResult, pageable);
        }
        //각 DTO에 일련번호를 부여
        private Page<NoticeListDTO> toNoticeListDTOPage(Page<NoticeEntity> noticeEntities, Pageable pageable) {
            List<NoticeListDTO> dtos = noticeEntities.stream().map(entity -> new NoticeListDTO(entity.getID(), entity.getTitle(), entity.getViewCount(), entity.getWriteTime())).collect(Collectors.toList());
            for (int i = 0; i < dtos.size(); i++) {
                NoticeListDTO dto = dtos.get(i);
                dto.setSerialNum(pageable.getPageSize() * pageable.getPageNumber() + i + 1);
            }
            return new PageImpl<>(dtos, pageable, noticeEntities.getTotalElements());
        }
    }



