<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../includes/header.jsp" %>


<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Tables</h1>
   </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<!--  -------------------------------------------------------------------------------------->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
            Board List Page  
            <button id='regBtn' type="button" class="btn btn-xs pull-right">
                 Register   New Board
            </button>        
                    <div class="panel-body">
                        <table width="100%"
                        class="table table-striped table-bordered table-hover">
                        <thead>
                            <tr>
                            <th>#번호</th>   <th>제목</th>   <th>작성자</th>
                            <th>작성일</th>                 <th>수정일</th>
                            </tr>
                        </thead>
                        <c:forEach items="${list }" var="board">
                            <tr>
                            <td><c:out value="${board.bno }" /> </td>
                            <!-- 조회페이지 링크 처리 -->
                            <td><a href='/board/get?bno=<c:out value="${board.bno}"/>'>
                                <c:out value="${board.title}"/></a>
                            </td>                            
                            <td><c:out value="${board.title }" />  </td>    
                            <td><c:out value="${board.writer }" />  </td>  
                            <td>
                                <fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate }" />
                            </td>  
                            <td>
                                <fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate }" />
                            </td>                              
                            </tr>
                        </c:forEach>
                        </table>
            <!-- table 태그 끝 -->
           
<!--페이지 번호 처리  -->  
    <div class='pull-right'>
          <ul class="pagination">
 
            <c:if test="${pageMaker.prev}"> <!-- 이전 버튼 -->
              <li class="paginate_button previous">
                <a href="${pageMaker.startPage - 1}">Previous</a>
              </li>
            </c:if>
           
             <!-- 1~10 버튼 -->
            <c:forEach var="num" begin="${pageMaker.startPage}"
                end="${pageMaker.endPage}">
                <!-- 숫자 URL 처리 -->
              <li class="paginate_button">
                <a href="${num}">${num}</a>
              </li>
             
            </c:forEach>
 
            <c:if test="${pageMaker.next}"> <!-- 다음 버튼 -->
              <li class="paginate_button next">
                <a href="${pageMaker.endPage + 1}">Next</a>
              </li>
            </c:if>
           
          </ul> <!--end ul  -->          
    </div>     <!--  end Pagination -->  
   
 <!--  값저장용 폼-->  
<form id='actionForm' action="/board/list" method='get'>
<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
</form>  
   
<!--페이지 번호 처리  끝-->
         
                           
<!-- Modal  추가 ------------------->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"    
    aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">처리가 완료되었습니다.</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save  changes</button>
            </div>
        </div><!-- /.modal-content -->
    </div>  <!-- /.modal-dialog -->
</div>
<!-- 모달끝/.modal -->    
                     
                    </div> <!-- end panel-body -->
                </div> <!-- end panel panel-default  -->
            </div>    <!-- /.panel -->
        </div>     <!-- /.col-lg-6 -->
    </div>       <!-- /.row -->


 <%@ include file="../includes/footer.jsp" %>
 
 
<!-- javascript -->
<script type="text/javascript">
    $(document)
            .ready(
                    function() {
                        var result = '<c:out value="${result}"/>';
                        checkModal(result);    
                        //뒤로가기문제
                        history.replaceState({}, null, null);
                       
                      //게시글 등록 처리
                        function checkModal(result) {
                            if (result === '' || history.state) {
                                return;
                            }//end if
                            if (parseInt(result) > 0) {
                                $(".modal-body").html(
                                        "게시글 " + parseInt(result)
                                                + " 번이 등록되었습니다.");
                            }//end if
                            $("#myModal").modal("show");
                        }//end checkModel
                       
                      //새글 등록 처리
                        $("#regBtn").on("click", function () {
                            self.location="/board/register";                
                        });//end click
                       
                    //페이징 버튼 처리
                    var actionForm = $("#actionForm"); //폼등록
                    $(".paginate_button a").on( "click",function(e) {
                    e.preventDefault(); //<a> 작동 중지
                    console.log('click');
                actionForm.find("input[name='pageNum']").val($(this).attr("href"));
                    actionForm.submit(); //form submit
                    });//end click

            });//  end ready
</script>
