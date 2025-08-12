<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 모달 스타일부분 시작 -->
<style>
#modDiv {	width: 300px;	height: 100px;	
	background-color: gray;	position: absolute;
	top: 50%;	left: 50%;	margin-top: -50px;	margin-left: -150px;
	padding: 10px;	z-index: 1000;}
.pagination {  width: 100%;}
.pagination li{
  list-style: none;  float: left; 
  padding: 3px;   border: 1px solid blue;  margin:3px;  
}
.pagination li a{  margin: 3px;  text-decoration: none;  }
</style>
<!-- 모달 스타일부분 끝 -->
</head>
<body>
크롬 에서 실행
인너브라우저는 getAllList();호출 문제
<!-- jQuery 2.1.4 -->
<script src="http://code.jquery.com/jquery-2.2.3.min.js"> </script>

<!-- 모달 부분 시작 -->
	<div id='modDiv' style="display: none;">
		<div class='modal-title'></div>
		<div>
			<input type='text' id='replytext'>
		</div>
		<div>
			<button type="button" id="replyModBtn">Modify</button>
			<button type="button" id="replyDelBtn">DELETE</button>
			<button type="button" id="closeBtn">Close</button>
		</div>
	</div>
<!-- 모달 부분 시작 끝-->

	<h2>Ajax Test Page</h2>
	<div>
		<div>
			REPLYER <input type='text' name='replyer' id='newReplyWriter'>
		</div>
		<div>
			REPLY TEXT <input type='text' name='replytext' id='newReplyText'>
		</div>
		<button id="replyAddBtn">ADD REPLY</button>
	</div>

<!-- 댓글이 추가되는 부분 -->
	<ul id="replies">
	</ul>
<!-- 댓글이 추가되는 부분 끝-->

<ul class='pagination'> </ul>		
	
	
<script>
  var bno = 1; //기본값
  getAllList();	 //페이지 시작시 함수 호출

  function getAllList() {
	$.getJSON(	"/replies/all/" + bno,
	function(data) {
		console.log(data);
    	var str = "";
			$(data).each(
			function() {
			str += "<li data-rno='"+this.rno+"' class='replyLi'>"
				+ this.rno	+ ":"
			+ this.replytext
			+ "<button>MOD</button></li>";
				});
		console.log(str);
		$("#replies").html(str);});
		$("#newReplyWriter").val('');
	    $("#newReplyText").val('');
	}//end getAllList()

  //추가 버튼 클릭
  $("#replyAddBtn").on("click", function() {
	var replyer = $("#newReplyWriter").val();
	var replytext = $("#newReplyText").val();
	$.ajax({
		type : 'post',
		url : '/replies',
		headers : {
		"Content-Type" : "application/json",
    	"X-HTTP-Method-Override" : "POST"
		},
		dataType : 'text',
		data : JSON.stringify({
			bno : bno,
			replyer : replyer,
			replytext : replytext
		}),
		success : function(result) {
			console.log(result);
			if (result == 'SUCCESS') {
	    	  alert("등록 되었습니다.");
			  getAllList();
		  }//end if
		}//end sucess..
	 });//end ajax
	});//$("#replyAddBtn").on("click"

	//mod 버튼 눌렀을때 모달 보이기기능
	$("#replies").on("click", ".replyLi button", function() {
		var reply = $(this).parent();
		var rno = reply.attr("data-rno");
		var replytext = reply.text();
		$(".modal-title").html(rno);
		$("#replytext").val(replytext);
		$("#modDiv").show("slow");
	});	//end	$("#replies").on("click"
		
    //삭제버튼 클릭
	$("#replyDelBtn").on("click", function() {
		var rno = $(".modal-title").html();
		var replytext = $("#replytext").val();
		$.ajax({
			type : 'delete',
			url : '/replies/' + rno,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "DELETE"
			},
			dataType : 'text',
			success : function(result) {
				console.log("result: " + result);
				if (result == 'SUCCESS') {
					alert("삭제 되었습니다.");
					$("#modDiv").hide("slow");
					getAllList();
				}//end if
			}//end sucess..
			});//end ajax
	});//end $("#replyDelBtn").on("click
		
	//수정버튼 클릭	
	$("#replyModBtn").on("click",function(){			  
	  var rno = $(".modal-title").html();
	  var replytext = $("#replytext").val();			  
	  $.ajax({
			type:'put',
			url:'/replies/'+rno,
			headers: { 
			      "Content-Type": "application/json",
			      "X-HTTP-Method-Override": "PUT" },
				data:JSON.stringify({replytext:replytext}), 
				dataType:'text', 
				success:function(result){
				console.log("result: " + result);
				if(result == 'SUCCESS'){
					alert("수정 되었습니다.");
					 $("#modDiv").hide("slow");
					getAllList();							
				}//ebd if
		}}); //end ajax
	});	//end $("#replyModBtn").on("click"
		
	//모달 닫기버튼 클릭
	$("#closeBtn").on("click",function(){
		$("#modDiv").hide("slow");
	});   //$("#closeBtn").on("click"		
	  		
</script>
</body>
</html>

