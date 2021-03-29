package com.naver.myhome7.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

// 공통으로 처리할 로직을 BeforeAdvice 클래스에 beforeLog()메서드로 구현합니다. 
// Advice : 횡단 관심에 해당하는 공통 기능을 의미하며 독립된 클래스의 메서드로 작성됩니다.
@Service
@Aspect
public class AfterAdvice {
	private static final Logger logger = LoggerFactory.getLogger(AfterAdvice.class);
	
	@After("execution(* com.naver.myhome4..*Impl.get*(..))")
	public void afterLog(JoinPoint proceeding) {
		logger.info("================");
		logger.info("[AfterAdvice] : 비즈니스 로직 수행 후 동작입니다. ");
		logger.info("[AfterAdvice] : "
		//  호출한 비즈니스 메서드를 포함하는 비즈니스 객체를 구합니다. 
		+ proceeding.getTarget().getClass().getName() + "의"
		// 호출되는 메서드에 대한 정보를 구합니다.
		+ proceeding.getSignature().getName() + "() 호출 후 입니다.");
		logger.info("================");
	}
}