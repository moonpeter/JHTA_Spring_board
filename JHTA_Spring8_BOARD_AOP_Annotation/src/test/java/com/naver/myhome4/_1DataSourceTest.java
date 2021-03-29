package com.naver.myhome4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class _1DataSourceTest {

	private static final Logger logger = LoggerFactory.getLogger(_1DataSourceTest.class);

	@Autowired
	private DataSource dataSource; // root-context.xml 에 설정된 dataSource를 자동으로 주입

	@Test // 현재 메서드를 테스트 대상으로 지정하는 어노테이션
	public void testConnection() {
		try (Connection conn = dataSource.getConnection()) {
			logger.info("확인용 conn : " + conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQuery() {
		try(Connection conn= dataSource.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement("select to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') from dual");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			logger.info("현재시간 : " + rs.getString(1));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
