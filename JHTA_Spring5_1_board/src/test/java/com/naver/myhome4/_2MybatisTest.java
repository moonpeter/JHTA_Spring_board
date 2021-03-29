package com.naver.myhome4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class _2MybatisTest {

	private static final Logger logger = LoggerFactory.getLogger(_2MybatisTest.class);

	@Autowired
	private SqlSessionFactory sqlSessionFactory; // root-context.xml 에 설정된 dataSource를 자동으로 주입

	@Test // 현재 메서드를 테스트 대상으로 지정하는 어노테이션
	public void testSqlSessionFactory() {
		logger.info("~~~~~~~sqlSessionFactory : " + sqlSessionFactory);
	}
	
	@Test
	public void testSqlSession() {
		try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
			logger.info("~~~~~~~sqlSession : " + sqlSession);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
