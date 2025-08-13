<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
</head>
<body>
<input name="uploadFiles" type="file" multiple>
<button class="uploadBtn">Upload</button>
<!-- 그림 출력 시작 -->
<div class="uploadResult">
</div>
<!-- 그림 출력 끝 -->
<!-- J query CND -->
<script
      src="https://code.jquery.com/jquery-3.5.1.min.js"
      integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
      crossorigin="anonymous">
</script>
<!-- 자바 스크립터 시작 -->
<script>
  $('.uploadBtn').click(function( ) {
      //form 생성
      var formData = new FormData();
      //input 저장
      var inputFile = $("input[type='file']");
      // 사용자가 입력한 이미지 저장
      var files = inputFile[0].files;
      for (var i = 0; i < files.length; i++) {
          console.log(files[i]);
          //form에 추가 파라미터값
          formData.append("images", files[i]);
      } //end for           
    
      //실제 업로드 부분
      //upload ajax
      $.ajax({
   	   type: 'POST',
          url: '/upload2',          
          data: formData,
          enctype: 'multipart/form-data',
          processData: false,
          contentType: false,
          cache: false,
          success: function(result){
              console.log(result);
            
          },
          error: function(jqXHR, textStatus, errorThrown){
              console.log(textStatus);
          }
      }); //end $.ajax
   }); // uploadBtn end click
 
 </script>
<!-- 자바 스크립터  끝 -->
</body>
</html>