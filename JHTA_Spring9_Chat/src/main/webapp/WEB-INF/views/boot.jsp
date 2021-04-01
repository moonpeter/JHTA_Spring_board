<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<meta charset='UTF-8'>
<meta name="robots" content="noindex">
<link rel="shortcut icon" type="image/x-icon"
	href="//production-assets.codepen.io/assets/favicon/favicon-8ea04875e70c4b0bb41da869e81236e54394d63638a1ef12fa558a4a835f1164.ico" />
<link rel='stylesheet prefetch'
	href='https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css'>
<link rel='stylesheet prefetch'
	href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.2/css/font-awesome.min.css'>
<link rel="stylesheet" href="resources/css/chat.css">
<style>
#frame .content {
	width: 100%
}

sup {
	position: relative;
	top: -10px;
	right: -95%;
	font-size: 3px;
}

.inout {
	text-align: center;
}
</style>
<title>
   boot.jsp
</title>
</head>
<body>
	<div id="frame">
		<div class="content">
			<div class="contact-profile">			
				<img src="resources/upload${filename}" alt="나의 아바타" />
				<p>${name}</p> 
				<div class="social-media">
					<i class="fa fa-facebook" aria-hidden="true"></i> 
					<i class="fa fa-twitter" aria-hidden="true"></i> 
					<i class="fa fa-instagram" aria-hidden="true"></i>
				</div>
			</div>
			<div class="messages">
				<ul>

				</ul>
			</div>
			<div class="message-input">
				
			</div>
		</div>
	</div>
	
	<script>
	
	
		
		function newMessage() {
			
			
			return message;
		};

		$('.exit').click(function() {
			
		});

		$(window).on('keyup', function(e) {
			
		});

         var url ;
         
    	ws = new WebSocket(url);        		 
    		
		//웹 소켓이 연결되었을 때 호출되는 이벤트
		ws.onopen = function(event) {
		};

		//서버에서 전송하는 데이터를 받으려면 message이벤트를 구현하면 됩니다.
		//웹 소켓에서 메시지가 날라왔을 때 호출되는 이벤트입니다.
		ws.onmessage = function(event) {
			
		};

		//웹 소켓이 닫혔을 때 호출되는 이벤트입니다.
		ws.onclose = function(event) {
			location.href = "logout";
		}
		
		function send(message) {			
			ws.send(message);//웹 소켓으로 message를 보냅니다.
		}

		function response(text) {
		
			moveScroll();
		}
		
		function moveScroll(){
			$('.messages').scrollTop($('.messages')[0].scrollHeight)
		}
		
	</script>
</body>
</html>