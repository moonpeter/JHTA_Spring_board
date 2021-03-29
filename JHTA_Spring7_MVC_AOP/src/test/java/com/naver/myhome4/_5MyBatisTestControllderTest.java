package com.naver.myhome4;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.naver.myhome4.domain.Board;
import com.naver.myhome4.domain.Member2;


@RunWith(SpringJUnit4ClassRunner.class)

// WAS 없이 MVC 패턴의 Controller를 단위 테스트하기위해서는 @WebAppConfiguration을 사용해야만 합니다.
// @WebAppConfiguration 은 스프링 MVC 테스트 할 때 사용하는 것으로서 이를 통해 WebApplicationContext를 @Autowired 하면 서블릿 설정들을 가져올수 있게 됩니다.
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class _5MyBatisTestControllderTest {

	private static final Logger logger = LoggerFactory.getLogger(_5MyBatisTestControllderTest.class);
	
	@Autowired
	private WebApplicationContext wac;
	
	// MockMvc 는 웹 애플리케이션을 애플리케이션 서버에 배포하지 않고도 스프링 MVC의 동작을 재현할 수 있는 클래스
	private MockMvc mockMvc;
	
	// 매번 테스트를 진행할 때 마다 테스트 하기전 MockMvc mockMvc 객체를 만들어 주기위해
	// @Before 에노테이션으로 setup() 메소드를 생성
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		logger.info("setup~~~~~~~~~~~~~~~~~~~~~");
	}
	
	@Test
	public void testidcheck() throws Exception {
		logger.info("===== testidehck() 메소드 시작 =====");
		
		try {
			mockMvc.perform(
					get("/member/idcheck").param("id", "1admin")
					).andDo(print()) /// 처리되어진 내용 출력
			.andExpect(status().isOk());
			
			logger.info(">>> 테스트 수행 성공<<<");
		} catch(Exception e) {
			logger.error(">>> 테스트 수행 실패<<<");
		}
	}

}
