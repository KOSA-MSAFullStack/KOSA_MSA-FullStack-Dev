<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Upload With Ajax</h1>

<div class="uploadDiv">
<input type="file" name="uploadFile" multiple="multiple">
</div>
<button id="uploadBtn"> Upload</button>


<script
  src="https://code.jquery.com/jquery-3.3.1.min.js"
  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  crossorigin="anonymous"></script>


<script type="text/javascript">
$(document).ready(function() {
	//버튼 클릭
	$('#uploadBtn').on("click", function(e) {
		//가상 form 생성
		var formData = new FormData();
		//현재 input 태그 저장
		var inputFile = $("input[name='uploadFile']");
		//input 태그에서 선택한 파일
		var files = inputFile[0].files;		
		console.log(files);
		
		//파일 formdata에 추가
		for(var i =0; i < files.length; i++){
			formData.append("uploadFile", files[i])
		}//end for
		
		//post 로 호출
		$.ajax({
			url: "/uploadAjaxAction",
			processData: false,
			contentType: false,
			data: formData,
			type:"post",
			success:function(result){
				alert("Upload");
			}//end suce..			
		});//end ajax		
		
	});//end btn click	
});//end ready
</script>
</body>
</html>