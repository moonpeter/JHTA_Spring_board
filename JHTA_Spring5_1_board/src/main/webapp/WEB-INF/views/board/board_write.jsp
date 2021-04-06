<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <script src="/js/writeform.js"></script>
    <style>
        h1 {
            font-size: 1.5rem;
            text-align: center;
            color: #1a92b9
        }

        .container {
            width: 60%
        }

        label {
            font-weight: bold
        }

        #upfile {
            display: none
        }

        img {
            width: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <form action="add" method="post" enctype="multipart/form-data" name="boardform">
        <h1>MVC 게시판 - write 페이지</h1>

        <div class="form-group">
            <label for="board_name">글쓴이</label>
          	<sec:authentication property="principal" var="pinfo"/>
            <input name="board_name" id="board_name" value="${pinfo.username}" readonly
                   type="text" maxlength="30" class="form-control" placeholder="Enter board_name">
        </div>

        <div class="form-group">
            <label for="board_pass">비밀번호</label>
            <input name="board_pass" id="board_pass" type="password" maxlength="30"
                   class="form-control" placeholder="Enter board_pass">
        </div>

        <div class="form-group">
            <label for="board_subject">제목</label>
            <input name="board_subject" id="board_subject" type="text" maxlength="100"
                   class="form-control" placeholder="Enter board_subject">
        </div>

        <div class="form-group">
            <label for="board_content">내용</label>
            <textarea name="board_content" id="board_content" rows="10"
                      class="form-control"></textarea>
        </div>
        <div class="form-group">
            <label for="board_file">파일 첨부</label>
            <label for="upfile">
                <img src="${pageContext.request.contextPath}/resources/image/attach.png" alt="파일첨부">
            </label>
            <input type="file" id="upfile" name="uploadfile">
            <span id="filevalue"></span>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">등록</button>
            <button type="reset" class="btn btn-danger">취소</button>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }">
    </form>
</div>

</body>
</html>
