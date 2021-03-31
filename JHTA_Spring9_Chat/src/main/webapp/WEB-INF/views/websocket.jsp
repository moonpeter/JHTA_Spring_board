<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	button:disabled{background-color:darkgray; color:gray}
	button {
		margin:1em 0 0 0;
		width: 50px;
		height: 35px;
		background-color: green;
		color: white;
		border:none;
		outline:none
	}
	inpuyt{
		vertical-align: bottom;
		height:2em;
		width: 50%;
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.slim.js" integrity="sha256-HwWONEZrpuoh951cQD1ov2HUK5zA5DwJ1DNUXaM6FsY=" crossorigin="anonymous"></script>
</head>
<body>
	<div>
		<input type="text" id="messageinput"> <b>(${name})</b>
	</div>
	<div>
		<button>open</button>
		<button>send</button>
		<button>close</button>
	</div>
	
	<div id="messages"> </div>
	<script>
		var ws; 
		$(document).ready(function(){
   			$('button:eq(2)').prop("disabled", true)
   			$('button:eq(1)').prop("disabled", true)
		});

		// open 클릭한 경우 
		$('button:eq(0)').click(function(){
			if(ws!=undefined && ws.readyState !== WebSocket.CLOSED){
				writeResponse("WebSocket is already opened.");
				return;
			} // if end
			
			// 웹 소켓이 동작하기 위해서 제일 먼저 서버와 연결이 되어야 합니다. 
			// HTML5에서 제공하는 WebSocket 객체를 통해 서버 연결을 수행합니다. 
			// 프로토콜은 ws를 사용합니다.
			// 웹소켓 객체 만드는 코드 
			// ws = new WebSocket("ws://localhost:9988/myChat/chat/echo.do");
			var url = "ws://${url}/echo.do"
			ws = new WebSocket(url);
			$(this).prop("disabled", true) // open 버튼 비활성
			$('button:eq(2)').prop("disabled", false) // send 버튼 활성화
   			$('button:eq(1)').prop("disabled", false) // close 버튼 활성화
   			
   			// 웹 소켓이 연결되었을 때 호출되는 이벤트
			ws.onopen=function(event){
				
			}; // 웹 소켓이 연결되었을 때 호출되는 이벤트 end
			
			// 서버에서 전송하는 데이터를 받으려면 message이벤트를 구현하면 됩니다. 
			// 웹 소켓에서 메시지가 날라왔을 때 호출되는 이벤트입니다. 
			ws.onmessage=function(event){
				writeResponse(event.data);
			}; // 웹 소켓에서 메시지가 날라왔을 때 호출되는 이벤트 end 
			
			// 웹 소켓이 닫혔을 때 호출되는 이벤트입니다. 
			ws.onclose=function(event){
				writeResponse("Connection closed");
				$('button:eq(0)').prop("disabled", false);
				$('button:eq(1)').prop("disabled", true);
	   			$('button:eq(2)').prop("disabled", true)
			}; // 웹 소켓이 닫혔을 때 호출되는 이벤트 end
		}); // open 클릭 end 
		
		// close 클릭한 경우 
		$('button:eq(2)').click(function(){
			ws.close(); // 웹 소켓을 닫습니다. 
		}); // close 클릭 end 
		
		// send 클릭한 경우 
		$('button:eq(1)').click(send);
			
		function send() {
			// 메시지가 빈 문자열이면 "메시지를 입력하세요"라는 대화상자창이 나타나고 
			// 메시지 입력한 곳에 포커스를 주고 리턴합니다. 
			// 빈 문자열이 아니면 입력한 값과 보낸사람 이름을 ","를 기준으로 웹 소켓으로 보냅니다. 
			// 예) 안녕하세요,홍길동
			if($("#messageinput").val() == ''){
				alert("메시지를 입력하세요");
				$("#messageinput").focus();
				return false;
			}
			var text=$("#messageinput").val() + "," + '${name}';
			// 서버와 연결이 되면 이제부터 데이터를 주고 받을 수 있습니다. 
			// send메서드를 이용해서 데이터를 서버로 보낼 수 있습니다. 
			ws.send(text); // 웹소켓으로 text를 보냅니다. 보내는 형식(내용, 보낸사람) 
			
			// 보낸 후 메시지 입력란은 지웁니다. 
			$("#messageinput").val('');
		}
		
		// 메시지영역에 서버에서 온 메시지를 계속 추가합니다. 
		function writeResponse(rtext) {
			$("#messages").append("<div></div>");
			$("#messages").find("div").last().text(rtext);
		}

	</script>
</body>
</html>