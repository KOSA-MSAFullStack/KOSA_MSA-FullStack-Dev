<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>

<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">Board Modify Page</h1>
  </div>
  <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">
      <div class="panel-heading">Board Read Page</div>
      <!-- /.panel-heading -->
      <div class="panel-body">
        <form role="form" action="/board/modify" method="post">
          <div class="form-group">
            <label>Bno</label>
            <input
              class="form-control"
              name="bno"
              value='<c:out value="${board.bno }"/>'
              readonly="readonly"
            />
          </div>

          <div class="form-group">
            <label>Title</label>
            <input
              class="form-control"
              name="title"
              value='<c:out value="${board.title }"/>'
            />
          </div>

          <div class="form-group">
            <label>Text area</label>
            <textarea class="form-control" rows="3" name="content">
          <c:out value="${board.content}" />
          </textarea
            >
          </div>

          <div class="form-group">
            <label>Writer</label>
            <input
              class="form-control"
              name="writer"
              value='<c:out value="${board.writer }"/>'
              readonly="readonly"
            />
          </div>

          <!-- 버튼 추가 -->
          <button data-oper="modify" class="btn btn-default">Modify</button>
          <button type="submit" data-oper="remove" class="btn btn-danger">
            Remove
          </button>
          <button data-oper="list" class="btn btn-info">
            <a href="/board/list">List</a>
          </button>
          <!-- 버튼 끝 -->
        </form>
        <!--폼끝  -->
      </div>
      <!--  end panel-body -->
    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->

<%@include file="../includes/footer.jsp"%>

<!--버튼 동작 설정 JS  -->
<script type="text/javascript">
  $(document).ready(function () {
    var formObj = $("form"); //폼할당

    $("button").on("click", function (e) {
      e.preventDefault(); //기본동작 막기[기본이 submit]
      var operation = $(this).data("oper");
      console.log(operation); //내용 출력

      if (operation === "remove") {
        formObj.attr("action", "/board/remove");
      } else if (operation === "list") {
        //move to list
        formObj.attr("action", "/board/list").attr("method", "get");
        formObj.empty();
      } //end if

      formObj.submit(); //form
    }); //end click
  }); //end ready
</script>
