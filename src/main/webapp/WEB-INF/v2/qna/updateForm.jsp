<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="kr">
<head>
    <%@ include file="/include/v2/headerV2.jspf" %>
</head>
<body>
<%@ include file="/include/v2/navigationV2.jspf" %>

<div class="container" id="main">
    <div class="col-md-12 col-sm-12 col-lg-10 col-lg-offset-1">
        <div class="panel panel-default content-main">

            <form name="question" method="post" action="/v2/qna/update">

                <input type="hidden" name="questionId" value="${question.questionId}"/>

                <div class="form-group">
                    <label>작성자</label>
                    ${question.writer}
                </div>

                <div class="form-group">
                    <label for="title">제목</label>
                    <input type="text" class="form-control" id="title" name="title"
                           placeholder="제목" value="${question.title}"/>
                </div>

                <div class="form-group">
                    <label for="contents">내용</label>
                    <textarea name="contents" id="contents" rows="5"
                              class="form-control">${question.contents}</textarea>
                </div>

                <button type="submit" class="btn btn-success clearfix pull-right">수정하기</button>
                <div class="clearfix"/>
            </form>

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="../../js/scriptsV2.js"></script>
</body>
</html>
