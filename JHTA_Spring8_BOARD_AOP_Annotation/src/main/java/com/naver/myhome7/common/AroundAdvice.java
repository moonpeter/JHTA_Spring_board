package com.naver.myhome7.common;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;


// 공통으로 처리할 로직을 BeforeAdvice 클래스에 beforeLog()메서드로 구현합니다. 
// Advice : 횡단 관심에 해당하는 공통 기능을 의미하며 독립된 클래스의 메서드로 작성됩니다. 
public class AroundAdvice {
	private static final Logger logger = LoggerFactory.getLogger(AroundAdvice.class);
	
	public Object aroundLog(ProceedingJoinPoint proceeding) throws Throwable {
		
		// ============ 대상 객체 메서드 호출 전 수행할 공통기능 작성하는 곳 
		logger.info("==========================");
		logger.info("[Around Advice의 before]: 비즈니스 메서드 수행 전 입니다.");
		logger.info("==========================");
		StopWatch sw = new StopWatch();
		sw.start();
		
		// 이 코드의 이전과 이후에 공통기능을 위한 코드를 위치 시키면 됨 
		// 대상 객체의 메서드를 호출합니다. 
		Object result = proceeding.proceed();
		
		// ============ 대상 객체 메서드 호출 후 수행할 공통기능 작성하는 곳
		sw.stop();
		logger.info("==========================");
		logger.info("[Around Advice의 after]: 비즈니스 메서드 수행 후 입니다.");
		
		// 호출되는 메서드에 대한 정보를 구합니다. 
		Signature sig = proceeding.getSignature();
		
		// Object[] getArgs() : 클라이언트가 메서드를 호출할 때 넘겨준 인자 목록을 
		// Object 배열로 리턴합니다.
		logger.info("[Around Advice의 after]: " + proceeding.getTarget().getClass().getSimpleName()
				+ sig.getName() + "(" + Arrays.toString(proceeding.getArgs()) + ")");
		
		logger.info("[Around Advice의 after]: " + proceeding.getSignature().getName() +"() 메소드 수행 시간 : "
				+ sw.getTotalTimeMillis() + "(ms)초");
		
		logger.info("[Around Advice의 after]: " + proceeding.getTarget().getClass().getName());
		
		logger.info("proceeding.proceed() 실행 후 반환값 :" + result);
		logger.info("==========================");

		return result;
	}
}
