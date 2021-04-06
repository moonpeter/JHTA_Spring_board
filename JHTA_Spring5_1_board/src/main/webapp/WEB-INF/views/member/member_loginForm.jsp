<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        * {
            box-sizing: border-box
        }

        input[type=text], input[type=password] {
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: none;
            background: #f1f1f1;
            border-radius: 2px;
        }

        input[type=text]:focus, input[type=password]:focus {
            background-color: #ddd;
            outline: none;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer; /* 손가락 커서 모양 */
            width: 100%;
            opacity: 0.9;
        }

        button:hover {
            opacity: 1;

        }

        button:focus {
            outline: none;
        }

        /* 취소 버튼*/
        .join {
            padding: 14px 20px;
            background-color: #f44336;
        }

        .submitbtn, .join {
            float: left;
            width: 50%;
        }

        form {
            background-color: #fefefe;
            margin: 10% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
            border: 1px solid #888;
            width: 50%; /* Could be more or less, depending on screen size */
            padding: 16px;
        }

        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 25px;
        }


        /* Clear floats */
        .clearfix::after {
            content: "";
            clear: both;
            display: table;
        }

        /* Change styles for cancel button and signup button on extra small screens */
        @media screen and (max-width: 300px) {
            .join {
                width: 100%;
            }
        }
    </style>
    <script>
    
	    var result ="${result}";
		if(result =='joinSuccess') {
			alert("회원가입을 축하합니다.")
		}
		
		result ="${loginFailMsg}"
			if(result) {
			alert(result);
		}
   		
        $(function (){
            $(".join").click(function (){
                location.href="${pageContext.request.contextPath}/member/join";
            });
        })
    </script>
</head>
<body>
<form name="loginform" action="loginProcess" method="post">
    <h1>로그인</h1>
    <hr>

    <div class="form-group">
        <label for="id">아이디:</label>
        <input type="text" class="form-control" id="id" name="id" placeholder="Enter id" required
        	<c:if test="${!empty saveid }">
        		value="${saveid }"
        	</c:if>
        >
        <p id="result"></p>
    </div>
    <div class="form-group">
        <label for="pass">Password:
        <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" required>
        </label>
        
   		<input type="checkbox" name="remember-me" id="remember-me" style="margin-bottom:15px">
   		로그인 유지하기     
    </div>
   
    <div class="clearfix">
        <button type="submit" class="submitbtn">로그인</button>
        <button type="reset" class="join">회원가입</button>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    
</form>
</body>
</html>
