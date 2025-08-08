<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>

<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">Board Read</h1>
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
            readonly="readonly"
          />
        </div>

        <div class="form-group">
          <label>Text area</label>
          <textarea
            class="form-control"
            rows="3"
            name="content"
            readonly="readonly"
          >
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

        <button data-oper="list" class="btn btn-info">
          <a href="/board/list">List</a>
        </button>
        <form id="operForm" action="/boad/modify" method="get">
          <input
            type="hidden"
            id="bno"
            name="bno"
            value='<c:out value="${board.bno}"/>'
          />
        </form>
        <!-- 버튼 끝 -->
      </div>
      <!--  end panel-body -->
    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->

<%@include file="../includes/footer.jsp"%>

<!--버튼 JS처리  -->
<script type="text/javascript">
  $(document).ready(function () {
    var operForm = $("#operForm");

    $("button[data-oper='modify']").on("click", function (e) {
      operForm.attr("action", "/board/modify").submit();
    }); //end modify

    $("button[data-oper='list']").on("click", function (e) {
      operForm.find("#bno").remove(); //from 태그에서 값지우기
      operForm.attr("action", "/board/list");
      operForm.submit();
    }); //end list
  }); //end ready
</script>
