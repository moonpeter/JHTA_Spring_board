package com.naver.myhome7.common;


import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

// 공통으로 처리할 로직을 BeforeAdvice 클래스에 beforeLog()메서드로 구현합니다. 
// Advice : 횡단 관심에 해당하는 공통 기능을 의미하며 독립된 클래스의 메서드로 작성됩니다. 
@Service
@Aspect
public class AfterThrowingAdvice {
	private static final Logger logger = LoggerFactory.getLogger(AfterThrowingAdvice.class);
	@AfterThrowing(pointcut="execution(* com.naver.myhome4..*Impl.get*(..))", throwing = "exp")
	public void afterThrowingLog(Throwable exp) {
		
		logger.info("================");
		logger.info("[AfterThrowing] : 비즈니스 로직 수행 중 오류가 " + "발생하면 동작입니다.");
		logger.info("ex : " + exp.toString());
		logger.info("================");
	}
}
