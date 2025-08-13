<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
   <title>1:1 문의 작성</title>
</head>
<!-- J query CND -->
<script
       src="https://code.jquery.com/jquery-3.5.1.min.js"
       integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
       crossorigin="anonymous">
</script>
<div layout:fragment="content">
   <script type="text/javascript">
       var formData = new FormData();
       $("input[type='file']").on("change", function(e){
           let fileInput = $('input[name="uploadFile"]');
           let fileList = fileInput[0].files;
           let fileObj = fileList[0];
           if(!fileCheck(fileObj.name, fileObj.size)){
               return false;
           }
           /* console.log($('input[name="uploadFile"]')); */
           $("#textManToManFile").val( $('input[name="uploadFile"]').val());
           /* console.log($("#textManToManFile").val()); */
           formData.append("uploadFile", fileObj);
       });
       function insertQna() {
           console.log($("#largeClassificationCode").val());
           var qemail;
           if($("#email").val() == "" && $("#emailDomain").val() == "") qemail = "";
           else qemail = $("#email").val() +'@'+ $("#emailDomain").val();
           var data = {
               qtitle : $("#qtitle").val(),
               qcontent : $("#qcontent").val(),
               qkind : $("#largeClassificationCode").val(),
               qemail : qemail
           }
           if(data.qtitle == "") {
               var la = new layerAlert('제목을 입력해주세요.');
               return;
           }
           if(data.qcontent == "") {
               var la = new layerAlert('내용을 입력해주세요.');
               return;
           }
           var fileInput = $('#qimage');
           console.log(fileInput);
           formData.append("qimage",fileInput[0].files[0]);
           formData.append('key', new Blob([ JSON.stringify(data) ], {type : "application/json"}));
           console.log(data);
           $.ajax({
               url: "/s3/file",
               method: "post",
               data: formData,
               contentType: false,               // * 중요 *
               processData: false,               // * 중요 *
               enctype : 'multipart/form-data',  
               cache: false,
               success: function (data) {
                   /// alert 창 띄워주기
                  // location.href="/mypage/qna";
               	 console.log(data);
               },
               error: function (request, status, error) {
                   console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
               }
           });
       }
       $( document ).ready(function() {
           //이메일변경
           $("#uidDomainSel").change(function(){
               //$("#email").val('');
               $("#emailDomain").val($("#uidDomainSel").val());
           });
       });
   </script>
   <!-- bodyWrap -->
   <div id="bodyWrap">
       <h3 class="cnts_title">
           <span>1:1 문의 하기</span>
       </h3>
       <div class="sub_container clearfix">
           <!-- lnbWrap -->
           <div class="iframe_lnb">
               <div class="lnb_wrap">
                   <h4><a href="/mypage/qna">1 : 1 문의</a></h4>
                   <div class="lnb">
                       <dl>
                           <dt>주문조회</dt>
                       </dl>
                       <dl>
                           <dt>혜택관리</dt>
                       </dl>
                       <dl>
		                    <dt>나의 정보관리</dt>
		                
                       </dl>
                       <dl>
                           <dt>나의 활동관리</dt>
                       </dl>
                   </div>
                   <!-- ph_guide -->
                   <div class="ph_guide">
                       <p class="tlt">고객센터 운영 안내</p>
                       <p class="phone" style="font-size:18px;">1800-5700<span style="color: #c69c6d;" class="txt"><br>(유료)</span>
                       </p>
                       <p class="txt">
                           평일(월~금)<br/>
                           am 09:00 ~ pm 18:00<span>토/일, 공휴일 휴무</span>
                       </p>
                   </div>
               </div>
           </div><!-- //lnbWrap -->
           <div class="sub_cnts">
               <div class="title_wrap mt30 clearfix">
                   <h4 class="float_left">1:1 문의 등록</h4>
                   <p class="reqd_txt"><strong class="reqd">*</strong> 표시는 필수항목입니다.</p>
               </div>
              <input type="hidden" id="orderPk" name="orderPk" />
                   <input type="hidden" id="pkInquiry" name="pkInquiry" />
                   <input type="hidden" id="mobilePhoneNumber" name="mobilePhoneNumber" value="" />
                   <input type="hidden" id="emailAddress" name="emailAddress" value="" />
                   <fieldset>
                       <legend>1:1 문의 입력</legend>
                       <div class="tblwrap">
                           <table class="tbl_wtype1">
                               <caption>1:1 문의 입력</caption>
                               <colgroup>
                                   <col style="width:140px"/>
                                   <col />
                               </colgroup>
                               <tbody>
                               <tr>
                                   <th scope="row"><strong class="reqd">*</strong>문의 항목</th>
                                   <td class="storeQue">
                                       <p>더한섬닷컴 문의</p>
                                   </td>
                               </tr>
                               <tr>
                                   <th scope="row"><strong class="reqd">*</strong>문의 종류</th>
                                   <td>
                                       <select id="largeClassificationCode" name="largeClassificationCode" title="대분류 선택" style="width:160px">
                                           <option value="대분류">대분류</option>
                                           <option value="행사/이벤트">행사/이벤트</option>
                                           <option value="포인트문의">포인트문의</option>
                                           <option value="주문/확인취소">주문/확인취소</option>
                                           <option value="상품문의">상품문의</option>
                                           <option value="배송관련">배송관련</option>
                                           <option value="반품">반품</option>
                                           <option value="기타">기타</option>
                                           <option value="교환">교환</option>
                                           <option value="고객정보">고객정보</option>
                                           <option value="결제/환불문의">결제/환불문의</option>
                                       </select>
                                   </td>
                               </tr>
                               <tr>
                                   <th scope="row"><strong class="reqd">*</strong>문의제목</th>
                                   <td>
                                       <input type="hidden" id="mid" name="mid">
                                       <input type="text" id="qtitle" name="qtitle" title="문의제목" class="w_all" />
                                   </td>
                               </tr>
                               <tr>
                                   <th scope="row">
                                       <strong class="reqd">*</strong>문의 내용<span class="com_txt_p">(2000자 이하)</span>
                                   </th>
                                   <td>
                                       <!-- textarea -->
                                       <textarea id="qcontent" name="qcontent" title="문의내용" cols="30" rows="10" placeholder=""></textarea>
                                       <!-- //textarea -->
                                   </td>
                               </tr>
                               <!-- popup end -->
                               <tr>
                                   <th  scope="row" class="th_space">사진등록</th>
                                   <td>
                                       <!-- File upload -->
                                       <div class="file_upload">
<!--                                            <input type="text" id="textManToManFile" class="text" title='파일 첨부하기' readonly="readonly" name="fileText" />-->
                                           <div class="upload_btn">
<!--                                                <button type="button" id="uploadFile" class="img_upload" title='파일찾기'><span>파일찾기</span></button>-->
                               <input type="file" id="qimage" name="file-multiple-input" class="img_upload">
                                           </div>
                                       </div>
                                       <p class="txt_guide">10MB 미만의 jpg,gif,png 파일만 첨부하실 수 있습니다.</p>
                                       <div class="select_img"><img src=""/></div>
                                       <script>
                                           // 첨부파일 미리보기
                                           $("#qimage").change(function() {
                                               if(this.files && this.files[0]) {
                                                   var reader = new FileReader();
                                                   reader.onload = function(data) {
                                                       $(".select_img img").attr("src", data.target.result).width(500);
                                                   }
                                                   reader.readAsDataURL(this.files[0]);
                                               }
                                           });
                                       </script>
                                       <!-- //File upload -->
                                   </td>
                               </tr>
                               <tr>
                                   <th scope="row">이메일 답변받기</th>
                                   <td>
                                       <!-- email -->
                                       <input type="text" id="email" name="email" value="" title="이메일 아이디" class="em_form"/>
                                       <span class="andmail">@</span>
                                       <input type="text" id="emailDomain" name="emailDomain" value="" title="이메일 도메인" class="em_form"/>
                                       <select id="uidDomainSel" name="uidDomainSel" class="em_select">
                                           <option value="">직접입력</option>
                                           <option value="naver.com">naver.com</option>
                                           <option value="daum.net">daum.net</option>
                                           <option value="yahoo.co.kr">yahoo.co.kr</option>
                                           <option value="lycos.co.kr">lycos.co.kr</option>
                                           <option value="nate.com">nate.com</option>
                                           <option value="empas.com">empas.com</option>
                                           <option value="hotmail.com">hotmail.com</option>
                                           <option value="msn.com">msn.com</option>
                                           <option value="gmail.com">gmail.com</option>
                                           <option value="hanmir.com">hanmir.com</option>
                                           <option value="chol.net">chol.net</option>
                                           <option value="korea.com">korea.com</option>
                                           <option value="netsgo.com">netsgo.com</option>
                                           <option value="dreamwiz.com">dreamwiz.com</option>
                                           <option value="hanafos.com">hanafos.com</option>
                                           <option value="freechal.com">freechal.com</option>
                                           <option value="hitel.net">hitel.net</option>
                                       </select>
                                       <!-- //email -->
                                   </td>
                               </tr>
                               </tbody>
                           </table>
                       </div>
                       <!-- btn -->
                       <div class="btnwrap">
                           <input type="button" id="cancleBtn" value="취소" class="btn wt">
                           <input type="button" id="registerBtn" value="등록" onclick="insertQna()" class="btn gray mr0">
                       </div>
                       <!-- //btn -->
                   </fieldset>
                   <div>
                       <input type="hidden" name="CSRFToken" value="b44478b9-4671-4b50-a76d-392ac8ea118d" />
                   </div><ul class="bul_sty01_li mt60">
               <li>문의하신 내용에 대한 답변은 이메일 또는 <a href='/ko/mypage/mymantomaninquiry'><em class="ft_point01">마이페이지 &gt; 1:1 문의내역</em></a>에서 확인하실 수 있습니다. </li>
               <li>SMS 문자와 이메일로 답변 완료 알림을 받아보실 수 있습니다.</li>
           </ul>
           </div>
       </div>
   </div>
</div>
</body>
</html>



    