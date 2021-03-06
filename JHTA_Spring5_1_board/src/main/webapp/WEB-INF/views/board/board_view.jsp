<%--
  Created by IntelliJ IDEA.
  User: moonpeter
  Date: 2021/02/02
  Time: 4:15 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Title</title>
<jsp:include page="header.jsp" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/view.js"></script>
<style>
body > div > table > tbody >tr:nth-child(1) {
	text-align: center
}

td:nth-child(1) {
	width: 20%
}

a {
	color: white
}

body > div > table > tbody >tr:nth-child(5)>td:nth-child(2)>a {
	color: black
}

body > div > table > tbody tr:last-child {
	text-align: center;
}

.btn-primary {
	background-color: #4f97e5
}



#myModal {
	display: none
}

#comment > table > tbody > tr > td:nth-child(2){
 width:60%
}

textarea{resize:none}
</style>
<script>
	var result = "${result}";
	if (result == 'passFail') {
		alert("비밀번호가 일치하지 않습니다.")
	}
</script>
</head>
<body>
	<input type="hidden" id="loginid" value="${id}" name="loginid">
	<div class="container">
		<table class="table">
			<tr>
				<th colspan="2">MVC 게시판 - view 페이지</th>
			</tr>
			<tr>
				<td>
					<div>글쓴이</div>
				</td>
				<td>
					<div>${boarddata.board_name}</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>제목</div>
				</td>
				<td><c:out value="${boarddata.board_subject}" /></td>
			</tr>
			<tr>
				<td>
					<div>내용</div>
				</td>
				<td style="padding-right: 0px"><textarea class="form-control"
						rows="5" readonly>${boarddata.board_content}</textarea></td>
			</tr>

			<c:if test="${boarddata.board_re_lev==0}">
				<tr>
					<td>
						<div>첨부파일</div>
					</td>
					<c:if test="${!empty boarddata.board_file}">
						<td><img
							src="${pageContext.request.contextPath}/resources/image/down.png"
							width="10px"> <a
							href="down?filename=${boarddata.board_file}&original=${boarddata.board_original}">
								${boarddata.board_original} </a></td>
					</c:if>
					<c:if test="${empty boarddata.board_file}">
						<td></td>
					</c:if>
				</tr>
			</c:if>

			<tr>
				<td colspan="2" class="center">
					<button class="btn btn-primary start">
						댓글 <span id="count" style="color:yellow">${count}</span>
					</button> <c:if test="${boarddata.board_name == id || id == 'admin' }">
						<a href="modifyView?num=${boarddata.board_num}">
							<button class="btn btn-warning">수정</button>
						</a>
						<a href="#">
							<button class="btn btn-danger" data-toggle="modal"
								data-target="#myModal">삭제</button>
						</a>
					</c:if> <a href="replyView?num=${boarddata.board_num}">
						<button class="btn btn-info">답변</button>
				</a> <a href="list">
						<button class="btn btn-secondary">목록</button>
				</a>
				</td>
			</tr>
		</table>

		<div class="modal" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<form name="deleteForm" action="delete" method="post">
							<input type="hidden" name="num" value="${param.num}" id="board_num">
							<div class="form-group">
								<label for="pwd">비밀번호</label> <input type="password"
									class="form-control" placeholder="Enter password"
									name="board_pass" id="board_pass">
							</div>
							<button type="submit" class="btn btn-primary">전송</button>
							<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
						</form>
					</div>
				</div>
			</div>
		</div>

		<div id="comment">
			<button class="btn btn-info float-left">총 50자까지 가능합니다.</button>
			<button id="write" class="btn btn-info float-right">등록</button>
			<textarea row=3 class="form-control" id="content" maxLength="50"></textarea>
			<table class="table table_striped">
				<thead>
					<tr>
						<td>아이디</td>
						<td>내용</td>
						<td>날짜</td>
					</tr>
				</thead>

				<tbody>

				</tbody>
			</table>
			<div id="message"></div>
		</div>
	</div>
</body>
</html>
