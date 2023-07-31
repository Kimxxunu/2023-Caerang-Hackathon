package com.teamtag.tagweb.notice.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class WriteDTO {

    //제목
    private String title;
    //내용물
    private String contents;
    //추가받은 링크
    private String link;
    //이미지경로(필수사항 아님)
    private String imageUrl;
    //조회수
    private int viewCount = 0;



    //작성시간
    LocalDateTime currentTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String writeTime = currentTime.format(formatter);



}
