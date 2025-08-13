package com.mycompany.webapp.controller;

import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@RestController
public class REST_FileUploadController {

	@Value("${aws.bucket.name}") // properties에서 주입
	private String bucket;

	@Autowired
	private AmazonS3 s3Client; // 인터페이스 사용

	@PostConstruct
	public void init() {
		System.out.println("S3 Upload Controller Initialized with bucket: " + bucket);
	}

	private String awsUpload(MultipartFile multipartFile) throws IOException {
		ObjectMetadata objMeta = new ObjectMetadata();
		objMeta.setContentLength(multipartFile.getSize());
		objMeta.setContentType(multipartFile.getContentType());

		s3Client.putObject(bucket, multipartFile.getOriginalFilename(), multipartFile.getInputStream(), objMeta);

		return s3Client.getUrl(bucket, multipartFile.getOriginalFilename()).toString();
	}

	// 파라미터값 images
	@PostMapping("/upload2")
	public String uploadFile2(@RequestParam("images") MultipartFile multipartFile) throws IOException {
		return awsUpload(multipartFile);
	}// end uploadFile

	// 파라미터값 MultipartFile
	@PostMapping("/upload")
	public String uploadFile(@RequestPart MultipartFile multipartFile) throws IOException {
		return awsUpload(multipartFile);
	}// end uploadFile

}
