package com.naver.myChat;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ServerEndpoint(value="/echo.do") // 클라이언트가 접속할 서버 주소 
public class EchoHandler {
	private static final Logger logger = LoggerFactory.getLogger(EchoHandler.class);
	
	private static final List<Session> sessionList = new ArrayList<Session>();
		
	public EchoHandler() {
		logger.info("웹소켓(서버) 객체생성");
	}
	
	// @OnOpen 는 클라이언트가 웹소켓에 들어오고 서버에 아무런 문제 없이 들어왔을 때 실행되는 메서드 
	// javax.websocket.Session: 접속자마다 한개의 세션이 생성되어 데이터 통신수단으로 사용되며 접속자 마다 구분됨 
	@OnOpen
	public void onOpen(Session session) {
		logger.info("Open session id : " + session.getId());
		try {
			// 자신과 연결된 session을 통해 문자열을 보냅니다.(즉, 자기 자신에게만 메시지 전달) 
			session.getBasicRemote().sendText("Connection Established");
		} catch (Exception e) {
			e.printStackTrace();
		}
		sessionList.add(session);
	}
	
	// 현재의 세션으로 부터 도착한 메시지를 나를 제외한 모든 사용자에게 메시지를 전달함 
	private void sendAllSessionToMessage(Session self, String sendMessage) {
		try {
			for(Session session : EchoHandler.sessionList) {
				// session 객체들 중에 자기자신을 제외한다.
				if(!self.getId().equals(session.getId())) {
					// session을 통해서 메시지를 전송한다.
					session.getBasicRemote().sendText(sendMessage);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// @OnMessage 는 클라이언트에게 메시지가 들어왔을 때, 실행되는 메서드 입니다. 
	// String getMessage에는 jsp의 ws.send()로 보낸 내용이 저장되어 있습니다. 
	@OnMessage
	public void onMessage(String getMessage, Session session) {
		logger.info("onMessage : " + session.getId());
		logger.info("onMessage : " + getMessage); // 메시지, 보낸사람 
		int index = getMessage.lastIndexOf(",");
		String input = getMessage.substring(0, index);
		String sender = getMessage.substring(index+1);
		logger.info("Message From " +sender + ": " + input);
		try {
			session.getBasicRemote().sendText("나>" + input); // 자신에게 보냅니다. 
		} catch (Exception e) {
			e.printStackTrace();
		}
		String message = sender + ">" + input;
		sendAllSessionToMessage(session, message); // 나를 제외한 모든 사람에게 보냅니다. 
	}
	
	@OnError
	public void onError(Throwable e, Session session) {
		logger.info("error입니다.");
	}
	
	// @OnClose 는 클라이언트와 웹소켓과의 연결이 끊기면 실행되는 메서드입니다. 
	@OnClose
	public void onClose(Session session) {
		logger.info("Session " + session.getId() + " has ended");
		sessionList.remove(session);
	}
}
