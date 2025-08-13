package com.mycompany.webapp.controller2;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
public class AwsS3Service {

	@Value("${aws.bucket.name}") // aws.properties에서 주입
	private String bucket;
	
	@Value("${aws.region}") // aws.properties에서 주입
	private String region;
   //https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/services/s3/AmazonS3Client.html
 	//Provides the client for accessing the Amazon S3 web service.
   private final AmazonS3 amazonS3;
  
   private String getFileExtension(String fileName) {
   	// file 형식이 잘못된 경우를 확인하기 위해 만들어진 로직이며,
   	//파일 타입과 상관없이 업로드할 수 있게 하기 위해 .의 존재 유무만 판단
       try {
           return fileName.substring(fileName.lastIndexOf("."));
       } catch (StringIndexOutOfBoundsException e) {
           throw new ResponseStatusException(
           		HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
       }//end try
   }//end getFileExtension
  
   ///이미지 파일 이름 세팅
   private String createFileName(String fileName) {
   	// 먼저 파일 업로드 시, 파일명을 난수화하기 위해 random으로 돌립니다.
       return UUID.randomUUID().toString().concat(getFileExtension(fileName));
   }//createFileName
  
   //URL 세팅 나중에 이미지 경로 처리위해
   public List<String> getUrl(List<String> fileNameList) {
       System.out.println(fileNameList);
       List<String> result = new ArrayList<>();
       for (int i = 0; i < fileNameList.size(); i++) {
           String temp = "https://" + bucket + ".s3."
           + region + ".amazonaws.com/" + fileNameList.get(i);
           result.add(temp);
       }//end for
       return result;
   }//end getUrl
   public List<String> uploadFile(List<MultipartFile> multipartFile) {
       List<String> fileNameList = new ArrayList<>();
       // forEach 구문을 통해 multipartFile로 넘어온 파일들 하나씩 fileNameList에 추가
       multipartFile.forEach(file -> {
       	
           String fileName = createFileName(file.getOriginalFilename());
           //https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/services/s3/model/ObjectMetadata.html
   		//Represents the object metadata that is stored with Amazon S3.
           ObjectMetadata objectMetadata = new ObjectMetadata();
           objectMetadata.setContentLength(file.getSize());
           objectMetadata.setContentType(file.getContentType());
           try (InputStream inputStream = file.getInputStream()) {
               amazonS3.putObject(
               		new PutObjectRequest(
               				bucket, fileName, inputStream, objectMetadata)
               		//S3 객체에 PublicRead 권한부여
                       .withCannedAcl(CannedAccessControlList.PublicRead));
           } catch (IOException e) {
               throw new ResponseStatusException(
               		HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
           }//end try
           fileNameList.add(fileName);
       });//end forEach
       return getUrl(fileNameList);
   }
   //aws S3삭제
   public void deleteFile(String fileName) {
       amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
   }//end deleteFile
 }//end class



