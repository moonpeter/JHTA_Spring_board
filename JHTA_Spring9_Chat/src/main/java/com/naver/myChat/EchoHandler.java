package com.naver.myChat;

import java.lang.reflect.InvocationTargetException;
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

import com.naver.myChat.bootstrap.domain.Cart;

@Controller
@ServerEndpoint(value="/boot.do") // 클라이언트가 접속할 서버 주소 
public class EchoHandler {
	private static final Logger logger = LoggerFactory.getLogger(EchoHandler.class);
	
	static final List<Cart> sessionList = new ArrayList<Cart>();
		
	public EchoHandler() {
		logger.info("웹소켓(서버) 객체생성");
	}
	
	// @OnOpen 는 클라이언트가 웹소켓에 들어오고 서버에 아무런 문제 없이 들어왔을 때 실행되는 메서드 
	// javax.websocket.Session: 접속자마다 한개의 세션이 생성되어 데이터 통신수단으로 사용되며 접속자 마다 구분됨 
	@OnOpen
	public void onOpen(Session session) {
		logger.info("Open session id : " + session.getId());
		logger.info("session 쿼리 스트링 : " + session.getQueryString());
		
		// id=admin&filename=/2020-1-6/bbs2020163195410.png
		String queryString = session.getQueryString();
		String id=queryString.substring(queryString.indexOf("=")+1, queryString.indexOf("&"));
		String filename=queryString.substring(queryString.indexOf("/"));
		Cart cart = new Cart();
		cart.setSession(session);
		cart.setFilename(filename);
		cart.setId(id);
		sessionList.add(cart);
		
		String message = id + "님이 입장하셨습니다.in";
		sendAllSessionToMessage(session, message);
	}
	
	// 보낸 사람 정보(id와 파일이름) 구하기 
	private String getInfo(Session self) {
		String infomation = "";
		synchronized (sessionList) {
			for(Cart cart : EchoHandler.sessionList) {
				Session s = cart.getSession();
				if (self.getId().equals(s.getId())) {
					infomation = cart.getId() + "&" + cart.getFilename();
					logger.info("보낸 사람의 정보 = " + infomation);
					break;
				}
			}
		}
		return infomation;
	}
	
	// 현재의 세션으로 부터 도착한 메시지를 나를 제외한 모든 사용자에게 메시지를 전달함 
	private void sendAllSessionToMessage(Session self, String message) {
		String info = getInfo(self);
		
		synchronized (sessionList) {
			try {
				for(Cart cart : EchoHandler.sessionList) {
					Session s = cart.getSession();
					if(!self.getId().equals(s.getId())) { // 나를 제외한 사람에게 보냅니다. 
						logger.info("나를 제외한 모든 사람에게 보내는 메시지 : " + info + "&" + message);
						s.getBasicRemote().sendText(info +  "&" + message); 
					}
				}
			}
			catch (Exception e) {
				logger.info("sendAllSessionToMessage 오류 : " + e.getMessage());
			}
		}
	}
	
	// @OnMessage 는 클라이언트에게 메시지가 들어왔을 때, 실행되는 메서드 입니다. 
	// String getMessage에는 jsp의 ws.send()로 보낸 내용이 저장되어 있습니다. 
	@OnMessage
	public void onMessage(String message, Session session) {
		logger.info("Message : " + message);
		sendAllSessionToMessage(session, message); // 나를 제외한 모든 사람에게 보냅니다. 
	}
	
	@OnError
	public void onError(Throwable e, Session session) {
		logger.info("error입니다.");
	}
	
	// @OnClose 는 클라이언트와 웹소켓과의 연결이 끊기면 실행되는 메서드입니다. 
	@OnClose
	public void onClose(Session session) throws InvocationTargetException {
		logger.info("Session " + session.getId() + " has ended");
		remove(session);
	}
	
	private void remove(Session session) {
		synchronized (sessionList) {
			for(int i = 0; i<sessionList.size(); i++) {
				Session s = sessionList.get(i).getSession();
				if (session.getId().equals(s.getId())) {
					sessionList.remove(i);
					return;
				}
			}
		}
	}
}
