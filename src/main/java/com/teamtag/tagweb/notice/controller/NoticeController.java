    package com.teamtag.tagweb.notice.controller;

    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.teamtag.tagweb.notice.DTO.NoticeListDTO;
    import com.teamtag.tagweb.notice.DTO.NoticeViewDTO;
    import com.teamtag.tagweb.notice.DTO.WriteDTO;
    import com.teamtag.tagweb.notice.DTO.aaaDTO;
    import com.teamtag.tagweb.notice.repository.NoticeRepository;
    import com.teamtag.tagweb.notice.service.NoticeService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
    import org.springframework.http.MediaType;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Paths;
    import java.util.List;

    @RestController
    public class NoticeController {
        private final NoticeService noticeService;
        private final NoticeRepository noticeRepository;
        @Autowired
        public NoticeController(NoticeService noticeService, NoticeRepository noticeRepository) {
            this.noticeService = noticeService;
            this.noticeRepository = noticeRepository;
        }


        //첨부파일(이미지)와 [글제목,글내용,추가링크] + 이미지경로를 저장하는 메소드
        @PostMapping(value = "/api/notice/writeNotice", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
        public void writeNotice(@RequestParam("jsonData") String jsonData,
                                @RequestPart(value = "image", required = false) MultipartFile imageData) throws IOException {
            WriteDTO writeDTO = new ObjectMapper().readValue(jsonData, WriteDTO.class);
            if (imageData != null && !imageData.isEmpty()) {
                try {
                    String uploadDir = "src/main/resources/static/img/"; // 이미지를 저장할 디렉토리 경로
                    byte[] bytes = imageData.getBytes();
                    String fileName = imageData.getOriginalFilename();
                    String filePath = uploadDir + fileName;
                    Files.write(Paths.get(filePath), bytes);
                    writeDTO.setImageUrl(filePath);
                    System.out.println(" 이미지경로 = " + writeDTO);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            noticeService.write(writeDTO);
        }
        //공지사항리스트를 반환한다.
        @GetMapping("/NoticeList")
        public List<NoticeListDTO> NoticeList() {
            List<NoticeListDTO> noticeList = noticeService.getNoticeList();
            System.out.println(noticeList);
            return noticeList;
        }

        //공지사항 게시물에 직접 들어갔을 때
        @PostMapping("/NoticeView/{serialNum}")
        public NoticeViewDTO updateViewCountAndReturnNoticeDTO(@PathVariable int serialNum, @PathVariable int id) {
            return noticeRepository.findById(id).map(currentNotice -> {
                currentNotice.setViewCount(currentNotice.getViewCount() + 1);
                noticeRepository.save(currentNotice);

                NoticeViewDTO noticeViewDTO = new NoticeViewDTO();
                noticeViewDTO.setTitle(currentNotice.getTitle());
                noticeViewDTO.setWriteTime(currentNotice.getWriteTime());
                noticeViewDTO.setModifyTime(currentNotice.getModifyTime());
                noticeViewDTO.setViewCount(currentNotice.getViewCount());
                noticeViewDTO.setImageUrl(currentNotice.getImageUrl());
                noticeViewDTO.setContents(currentNotice.getContents());
                return noticeViewDTO;
            }).orElseThrow(() -> {
                System.out.println("Sample not found with serialNum");
                return new RuntimeException("Sample not found with serialNum");
            });
        }

    }








        //기훈이랑 연습한거
//        @GetMapping("/api/board/backlist")
//        public List<aaaDTO> frontend() {
//            System.out.println("boardRepository = " + boardRepository.findAllBoardList());
//            return boardRepository.findAllBoardList();
//        }


    //    @PostMapping("/list")
    //    public NoticeListDTO putNoticeList(@RequestBody NoticeListDTO NoticeIDPost){
    //        NoticeListDTO noticeListDTO;
    //        noticeService.CurrentNotice(noticeListDTO);
    //
    //    }


