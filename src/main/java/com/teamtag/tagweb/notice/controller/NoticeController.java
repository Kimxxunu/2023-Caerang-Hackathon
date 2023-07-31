    package com.teamtag.tagweb.notice.controller;

    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.teamtag.tagweb.notice.DTO.*;
    import com.teamtag.tagweb.notice.entity.NoticeEntity;
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
    import java.time.LocalDateTime;
    import java.time.format.DateTimeFormatter;
    import java.util.List;

    @RestController
    @RequestMapping("/api/notice")
    public class NoticeController {
        private final NoticeService noticeService;
        private final NoticeRepository noticeRepository;

        //noticeService와 noticeRepository의 생성자 주입
        @Autowired
        public NoticeController(NoticeService noticeService, NoticeRepository noticeRepository) {
            this.noticeService = noticeService;
            this.noticeRepository = noticeRepository;
        }


        //첨부파일(이미지)와 [글제목,글내용,추가링크] + 이미지경로를 저장하는 메소드
        @PostMapping(value = "/writeNotice", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
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

                    writeDTO.setImageUrl("img/"+fileName);
                    System.out.println(" 이미지경로 = " + writeDTO);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            noticeService.write(writeDTO);
        }
        //공지사항리스트를 반환한다.
        @GetMapping("/list")
        public NoticeListAddPageDTO NoticeList() {
            List<NoticeListDTO> noticeList = noticeService.getNoticeList();
            int totalPage = noticeService.countPage(noticeList.size());
            NoticeListAddPageDTO noticeListAddPageDTO = new NoticeListAddPageDTO(noticeList, totalPage);
            System.out.println("페이지수" + totalPage);
            return noticeListAddPageDTO;
        }

        //공지사항 게시물에 직접 들어갔을 때
        @GetMapping("/{id}")
        public NoticeViewDTO updateViewCountAndReturnNoticeDTO(@PathVariable int id) {
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
                noticeViewDTO.setLink(currentNotice.getLink());
                System.out.println("이미지 주소ㅋ = " + currentNotice.imageUrl);
                return noticeViewDTO;
            }).orElseThrow(() -> {
                System.out.println("해당 게시물의 ID를 찾을 수 없습니다");
                return new RuntimeException("해당 게시물의 ID를 찾을 수 없습니다");
            });
        }

        //수정하기 버튼 클릭시 기존에 있던 내용을 불러오는 메소드
        @GetMapping("/modify/{id}")
        public ModifyDTO getModifyNotice(@PathVariable int id) {
            NoticeEntity notice = noticeRepository.findById(id)
                    .orElseThrow(() -> {
                        System.out.println("해당 게시물의 ID를 찾을 수 없습니다");
                        return new RuntimeException("해당 게시물의 ID를 찾을 수 없습니다");
                    });

            // DB에서 가져온 데이터를 사용하여 ModifyDTO 객체를 생성
            ModifyDTO modifyDTO = new ModifyDTO(
                    notice.getTitle(),
                    notice.getContents(),
                    notice.getLink(),
                    notice.getImageUrl()
            );
            return modifyDTO;
        }

        //수정완료 버튼 클릭시 변경사항을 업데이트하는 메소드
        @PostMapping(value = "/modifySave/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
        public void modifySave(@PathVariable("id") int id, @RequestPart("modifyDTO") String modifyJsonData,
                               @RequestPart(value = "image", required = false) MultipartFile imageData) throws IOException {

            ObjectMapper objectMapper = new ObjectMapper();
            ModifyDTO modifyDTO = objectMapper.readValue(modifyJsonData, ModifyDTO.class);

            NoticeEntity notice = noticeRepository.findById(id)
                    .orElseThrow(() -> {
                        System.out.println("해당 게시물의 ID를 찾을 수 없습니다");
                        return new RuntimeException("해당 게시물의 ID를 찾을 수 없습니다");
                    });

            if (imageData != null && !imageData.isEmpty()) {
                String uploadDir = "src/main/resources/static/img/"; // 이미지를 저장할 디렉토리 경로
                byte[] bytes = imageData.getBytes();
                String fileName = imageData.getOriginalFilename();
                String filePath = uploadDir + fileName;

                // 이미지 파일이 새로운 것일 경우, 이전 파일 삭제 및 새로운 파일 저장
                if (!filePath.equals(notice.getImageUrl())) {
                    // 이전 파일 삭제 로직은 여기에 추가합니다.
                    Files.write(Paths.get(filePath), bytes);
                    notice.setImageUrl("img/"+fileName);
                }
            }

            notice.setTitle(modifyDTO.getTitle());
            notice.setContents(modifyDTO.getContents());
            notice.setLink(modifyDTO.getLink());

            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            notice.setModifyTime(currentTime.format(formatter));
            noticeRepository.save(notice);
        }




//        @PostMapping("/modifySave/{id}")
//        public void modifySave(@PathVariable("id") int id, @RequestBody ModifyDTO modifyDTO,){
//            NoticeEntity notice = noticeRepository.findById(id)
//                    .orElseThrow(() -> {
//                        System.out.println("해당 게시물의 ID를 찾을 수 없습니다");
//                        return new RuntimeException("해당 게시물의 ID를 찾을 수 없습니다");
//                    });
//
//            notice.setTitle(modifyDTO.getTitle());
//            notice.setContents(modifyDTO.getContents());
//            notice.setLink(modifyDTO.getLink());
//            notice.setImageUrl(modifyDTO.getImageUrl());
//
//            LocalDateTime currentTime = LocalDateTime.now();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            notice.setModifyTime(currentTime.format(formatter));
//            noticeRepository.save(notice);
//        }

        //게시글 삭제하는 메소드
        @PostMapping("/delete/{id}")
        public void deleteNotice(@PathVariable int id) {
            NoticeEntity notice = noticeRepository.findById(id)
                    .orElseThrow(() -> {
                        System.out.println("해당 게시물의 ID를 찾을 수 없습니다");
                        return new RuntimeException("해당 게시물의 ID를 찾을 수 없습니다");
                    });
            notice.setDeleteNum(1);
            noticeRepository.save(notice);
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


