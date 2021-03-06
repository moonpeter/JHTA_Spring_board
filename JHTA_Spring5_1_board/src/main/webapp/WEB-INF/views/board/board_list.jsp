<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <style>
        select.form-control {
            width: auto; margin-bottom: 2em; display: inline-block;
        }
        .rows{text-align: right}
        .gray {
            color: gray
        }

        body > div > table > thead > tr:nth-child(2) > th:nth-child(1) {
            width: 8%
        }

        body > div > table > thead > tr:nth-child(2) > th:nth-child(2) {
            width: 50%
        }

        body > div > table > thead > tr:nth-child(2) > th:nth-child(3) {
            width: 14%
        }

        body > div > table > thead > tr:nth-child(2) > th:nth-child(4) {
            width: 17%
        }

        body > div > table > thead > tr:nth-child(2) > th:nth-child(5) {
            width: 11%
        }
    </style>
    <script src="${pageContext.request.contextPath}/resources/js/list.js"></script>
   <script>
   	var result = "${result}";
   	if(result == 'deleteSuccess') {
   		alert("삭제 성공!!!")
   	} else if(result == 'updateSuccess') {
   		alert("회원 정보 수정 완료");
   	}
   </script>

    <title>MVC 게시판</title>
</head>
<body>
<div class="container">
    <c:if test="${listcount > 0}">
        <div class="rows">
            <span>줄보기</span>
            <select class="form-control" id="viewcount">
                <option value="1">1</option>
                <option value="3">3</option>
                <option value="5">5</option>
                <option value="7">7</option>
                <option value="10" selected>10</option>
            </select>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th colspan="3">MVC 게시판 - list</th>
                <th colspan="2">
                    <font size="3">글 개수 : ${listcount}</font>
                </th>
            </tr>
            <tr>
                <th>
                    <div>번호</div>
                </th>
                <th>
                    <div>제목</div>
                </th>
                <th>
                    <div>작성자</div>
                </th>
                <th>
                    <div>날짜</div>
                </th>
                <th>
                    <div>조회수</div>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:set var="num" value="${listcount-(page-1)*limit}"/>
            <c:forEach var="b" items="${boardlist}">
                <tr>
                    <td>
                        <c:out value="${num}"/>
                        <c:set var="num" value="${num-1}"/>
                    </td>
                    <td>
                        <div>
                            <c:if test="${b.board_re_lev != 0}">
                                <c:forEach var="a" begin="0" end="${b.board_re_lev*2}" step="1">
                                    &nbsp;
                                </c:forEach>
                                <img src="/image/line.gif" alt="">
                            </c:if>

                            <c:if test="${b.board_re_lev ==0}">
                                &nbsp;
                            </c:if>

                            <a href="detail?num=${b.board_num}">
                                <c:out value="${b.board_subject}" escapeXml="true"/>
                            </a>
                        </div>
                    </td>
                    <td>
                        <div>${b.board_name}</div>
                    </td>
                    <td>
                        <div>${b.board_date}</div>
                    </td>
                    <td>
                        <div>${b.board_readcount}</div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="center-block">
            <ul class="pagination justify-content-center">
                <c:if test="${page <=1 }">
                    <li class="page-item">
                        <a class="page-link gray">이전&nbsp;</a>
                    </li>
                </c:if>
                <c:if test="${page>1}">
                    <li class="page-item">
                        <a href="list?page=${page-1}" class="page-link">이전&nbsp;</a>
                    </li>
                </c:if>

                <c:forEach var="a" begin="${startpage}" end="${endpage}">
                    <c:if test="${a == page}">
                        <li class="page-item">
                            <a class="page-link gray">${a}</a>
                        </li>
                    </c:if>
                    <c:if test="${a != page}">
                        <li class="page-item">
                            <a href="list?page=${a}" class="page-link">${a}</a>
                        </li>
                    </c:if>
                </c:forEach>

                <c:if test="${page >= maxpage }">
                    <li class="page-item">
                        <a class="page-link gray">&nbsp;다음</a>
                    </li>
                </c:if>
                <c:if test="${page < maxpage }">
                    <li class="page-item">
                        <a href="list?page=${page+1}" class="page-link">&nbsp;다음</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </c:if>

    <c:if test="${listocunt == 0}">
        <font size="5">등록된 글이 없습니다.</font>
    </c:if>

    <button type="button" class="btn btn-info float-right">글 쓰 기</button>
</div>
</body>
</html>
