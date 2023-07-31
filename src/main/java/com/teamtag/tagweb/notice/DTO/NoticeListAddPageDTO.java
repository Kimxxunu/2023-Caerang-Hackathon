package com.teamtag.tagweb.notice.DTO;

import java.util.List;

public class NoticeListAddPageDTO {
    private List<NoticeListDTO> noticeListDTOS;

    private int totalPage;

    public NoticeListAddPageDTO(List<NoticeListDTO> noticeListDTOS, int totalPage) {
        this.noticeListDTOS = noticeListDTOS;
        this.totalPage = totalPage;
    }

    public List<NoticeListDTO> getNoticeListDTOS() {
        return noticeListDTOS;
    }

    public void setNoticeListDTOS(List<NoticeListDTO> noticeListDTOS) {
        this.noticeListDTOS = noticeListDTOS;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
