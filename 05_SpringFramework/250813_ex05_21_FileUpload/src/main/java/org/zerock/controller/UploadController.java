package org.zerock.controller;

import java.io.File;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UploadController {
	
	//uploadFile 이름 버그 주의
	@PostMapping("/uploadAjaxAction")
	public void uploadAjaxPost(MultipartFile[] uploadFile) {

		//업로드 되는 운영체제 위치
		String uploadFolder = "C:\\upload";			
		log.info(uploadFile);
		log.info(uploadFile.length);
		//여러 파일별로 순환
		for (MultipartFile multipartFile : uploadFile) {
			log.info("-------------------------------------");
			log.info("Upload File Name: " + multipartFile.getOriginalFilename());
			log.info("Upload File Size: " + multipartFile.getSize());
				
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			try {
				//실제 저장된는 부분
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			} // end catch
		} // end for
	}//end uploadAJXpost...

	
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("upload Ajax....");
		
	}//end uploadAj...

	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload from...");		
	}//end upload...
	
	//uploadFile 이름 버그 주의
		@PostMapping("/uploadFormAction")
		public void uploadFormPost(MultipartFile[] uploadFile, Model model) {

			String uploadFolder = "C:\\upload";
			
			log.info(uploadFile);
			log.info(uploadFile.length);

			for (MultipartFile multipartFile : uploadFile) {

				log.info("-------------------------------------");
				log.info("Upload File Name: " + multipartFile.getOriginalFilename());
				log.info("Upload File Size: " + multipartFile.getSize());

				File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());

				try {
					multipartFile.transferTo(saveFile);
				} catch (Exception e) {
					log.error(e.getMessage());
				} // end catch
			} // end for

		}//end uploadpost...


}//end class