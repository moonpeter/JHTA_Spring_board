<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="_csrf" content="${_csrf.token }">
<meta name="_csrf_header" content="${_csrf.headerName }">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style>
    body > nav.navbar{
        justify-content: flex-end;
    }

    .dropdown-menu{
        min-width: 0rem;
    }

    .navbar {
        background: #096988;
        margin-bottom: 3em;
    }

    .navbar-dark .navbar-nav .nav-link {
        color: rgb(255, 255, 255);
    }

    textarea {
        resize: none;
    }
</style>

<sec:authorize access="isAnonymous()">
    <script>
    	location.href = "${pageContext.request.contextPath}/member/login";
    </script>
</sec:authorize>
<script>
	$(function() {
		$("#logout").click(function(event){
			event.preventDefault();
			$("form").submit();
		})
	})
</script>

<nav class="navbar navbar-expand-sm navbar-dark">
    <ul class="navbar-nav">
        <sec:authorize access="isAuthenticated()">
        	<sec:authentication property="principal" var="pinfo"/>
            <li class="nav-item">
            	<form action="${pageContext.request.contextPath}/member/logout" method="post">
            		<a class="nav-link" href="#" id="logout">
            			<span>${pinfo.username}</span>님(로그아웃)
            		</a>
            	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }">
            	</form>
            </li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/update">정보수정</a></li>

            <c:if test="${pinfo.username == 'admin'}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop"
                       data-toggle="dropdown">관리자</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/member/list">회원정보</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/board/list">게시판</a>
                    </div>
                </li>
            </c:if>
        </sec:authorize>
    </ul>
</nav>
