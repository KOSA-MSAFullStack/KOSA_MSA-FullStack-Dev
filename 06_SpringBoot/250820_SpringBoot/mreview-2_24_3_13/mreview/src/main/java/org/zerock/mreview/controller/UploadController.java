package org.zerock.mreview.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Log4j2
public class UploadController {

   @PostMapping("/uploadAjax")
   public void uploadFile(MultipartFile[] uploadFiles )  {

       log.info(uploadFiles);
       for( MultipartFile  i : uploadFiles){
           // [IE] 실제 파일 이름 전체 경로가 들어오므로
           // String fileName =
           //       originalName.substring(originalName.lastIndexOf("\\"+1));
           String originalName = i.getOriginalFilename();
           log.info("fileName :" + originalName);
       }//end for   

   }//end void
}//end class