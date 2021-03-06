
package com.naver.myhome4.ResponseEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ResponseEntityController {
	
	private static final Logger logger = LoggerFactory.getLogger(ResponseEntityController.class);
	
	// ResponseEntity 타입은 개발자가 직접 결과 데이터와 HTTP상태 코드를 직접 제어할 수 있는 클래스
	// produces 옵션에 응답 체더로 Context-Type을 지정합니다. 
	
	// localhost 페이지를 찾을 수 없음 
	// 다음 웹 주소(http://localhost:9988/myhome4/entity)에 대해 발견된 웹페이지가 없습니다. 
	// HTTP ERROR 404
	@RequestMapping(value="/entity", method=RequestMethod.GET, produces = "text/html; charset=utf8")
	public ResponseEntity<String> test() {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// 페이지가 작동하지 않습니다. 문제가 계속되면 사이트 소유자에게 문의하세요.
	// HTTP ERROR 400
	@RequestMapping(value="/entityerror", method=RequestMethod.GET, produces = "text/html; charset=utf8")
	public ResponseEntity<String> testerror() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/entity1", method=RequestMethod.GET, produces = "text/html; charset=utf8")
	public ResponseEntity<String> test1() {
		String name = "홍길동";
		return new ResponseEntity<>(name, HttpStatus.OK);
	}
}
