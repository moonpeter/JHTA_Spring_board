<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="login_ok1.do" name="myform" method="post">
    <table border="1">
        <tr>
            <td>ID</td>
            <td><input type="text" name="id"></td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td><input type="password" name="pass"></td>
        </tr>
        <tr>
            <td colspan="2" style="background-color: lightgreen">
                <input type="submit" value="로그인">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
