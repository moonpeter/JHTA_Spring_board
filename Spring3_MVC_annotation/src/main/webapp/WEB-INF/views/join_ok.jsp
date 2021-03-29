<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        tr>td:nth-child(odd){font-weight: bold}
        td{text-align: center}
        .container{width: 50%}
    </style>
</head>
<body>

<div class="container">
<table class="table table-bordered">
    <tr>
        <td>아이디</td>
        <td>${memberBean.id}</td>
    </tr>
    <tr>
        <td>비밀번호</td>
        <td>${memberBean.pass}</td>
    </tr>
    <tr>
        <td>이름</td>
        <td>${memberBean.name}</td>
    </tr>
    <tr>
        <td>나이</td>
        <td>${memberBean.age}</td>
    </tr>
    <tr>
        <td>성별</td>
        <td>${memberBean.gender}</td>
    </tr>
    <tr>
        <td>이메일 주소</td>
        <td>${memberBean.email}</td>
    </tr>
    <tr>
        <td colspan="2"><a href="memberList.net">리스트로 돌아가기</a></td>
    </tr>
</table>
</div>

</body>
</html>