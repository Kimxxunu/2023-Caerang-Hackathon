package com.teamtag.tagweb.domain.notice.DTO;

import java.util.List;

public class NoticeListAddPageDTO {
    private List<NoticeListDTO> noticeListDTOS;

    private int totalPages  ;

    public NoticeListAddPageDTO(List<NoticeListDTO> noticeListDTOS, int totalPages) {
        this.noticeListDTOS = noticeListDTOS;
        this.totalPages = totalPages;
    }

    public List<NoticeListDTO> getNoticeListDTOS() {
        return noticeListDTOS;
    }

    public void setNoticeListDTOS(List<NoticeListDTO> noticeListDTOS) {
        this.noticeListDTOS = noticeListDTOS;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
