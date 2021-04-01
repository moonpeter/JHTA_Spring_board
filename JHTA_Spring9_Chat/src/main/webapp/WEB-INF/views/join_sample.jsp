<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
body {font-family: Arial, Helvetica, sans-serif;}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

/* Set a style for all buttons */
button {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity: 0.8;
}

/* Extra styles for the cancel button */
.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

/* Center the image and position the close button */
.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
  position: relative;
}

img.avatar {
  width: 40%;
  border-radius: 50%;
}

.container {
  padding: 16px;
}

span.psw {
  float: right;
  padding-top: 16px;
}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
  padding-top: 60px;
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button (x) */
.close {
  position: absolute;
  right: 25px;
  top: 0;
  color: #000;
  font-size: 35px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: red;
  cursor: pointer;
}

/* Add Zoom Animation */
.animate {
  -webkit-animation: animatezoom 0.6s;
  animation: animatezoom 0.6s
}

@-webkit-keyframes animatezoom {
  from {-webkit-transform: scale(0)} 
  to {-webkit-transform: scale(1)}
}
  
@keyframes animatezoom {
  from {transform: scale(0)} 
  to {transform: scale(1)}
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn {
     width: 100%;
  }
}
</style>
</head>
<body>

  <form class="modal-content animate" action="joinProcess" method="post" enctype="multipart/form-data">
    <div class="imgcontainer">
     <label>
     	<input type="file" name="uploadfile" accept="image/gif, image/jpeg, image/png" style="display:none">
     	<img src="resources/image/default.png" alt="Avatar" class="avatar">
     </label>
    </div>

    <div class="container">
      <label for="uname"><b>Username</b><span id="message"></span></label>
      <input type="text" placeholder="Enter Username" name="id" id="id" required>

      <label for="psw"><b>Password</b></label>
      <input type="password" placeholder="Enter Password" name="password" id="password" required>
        
      <button type="submit">회원가입</button>
    </div>
  </form>
    <script>
        $(function () {
            var checkid = false;
            var checkemail = false;
            $('form').submit(function () {
                if (!checkid) {
                    alert("사용 가능한 id로 입력하세요");
                    $("input:eq(1)").val('').focus();
                    return false;
                }
            });
            

        $("input:eq(1)").on('keyup', function () {
                checkid = true;
                $("#message").empty();
                var pattern = /^\w{4,12}$/;  // \w = [A-Za-z0-9_]
                var id = $("#id").val();
                if (!pattern.test(id)) {
                    $("#message").css('color', 'red').html("영문자 숫자 _로 4~12자 가능합니다.");
                    checkid = false;
                    return;
                }

                $.ajax({
                    url: "idcheck",
                    data: {"id" : id},
                    success: function (resp) {
                        if( resp == -1) {
                            $("#message").css('color', 'green').html("사용가능한 아이디 입니다.");
                            checkid = true;
                        } else {
                            $("#message").css('color', 'blue').html("사용중인 아이디 입니다.");
                            checkid = false;
                        }
                    }
                })
            })
        })
    </script>
</body>
</html>
