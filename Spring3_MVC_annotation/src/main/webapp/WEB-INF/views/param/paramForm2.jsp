<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>입력 폼 입니다.</title>
</head>
<body>
	<form action="param2.do" method=post>
		<h3>paramForm1.jsp</h3>
		<b>나이</b>
		<input type="text" name="age" required placeholder="Enter age">
		<div class="clearfix">
			<button type="submit" class="submitbtn">전송</button>
		</div>
	</form>
</body>
</html>