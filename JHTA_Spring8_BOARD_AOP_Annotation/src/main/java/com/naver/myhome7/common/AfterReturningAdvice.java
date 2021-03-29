package com.naver.myhome7.common;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

// 공통으로 처리할 로직을 BeforeAdvice 클래스에 beforeLog()메서드로 구현합니다. 
// Advice : 횡단 관심에 해당하는 공통 기능을 의미하며 독립된 클래스의 메서드로 작성됩니다.
@Service
@Aspect
public class AfterReturningAdvice {
	private static final Logger logger = LoggerFactory.getLogger(AfterReturningAdvice.class);
	@AfterReturning(pointcut="execution(* com.naver.myhome4..*Impl.get*(..))", returning="obj")
	public void afterReturnLog(Object obj) {
		
		logger.info("================");
		logger.info("[AfterAdvice] : " + obj.toString());
				
		logger.info("================");
	}
}
