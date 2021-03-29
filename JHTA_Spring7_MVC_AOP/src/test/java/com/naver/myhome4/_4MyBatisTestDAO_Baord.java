package com.naver.myhome4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
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

import com.naver.myhome4.domain.Board;
import com.naver.myhome4.domain.Member2;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class _4MyBatisTestDAO_Baord {

	private static final Logger logger = LoggerFactory.getLogger(_4MyBatisTestDAO_Baord.class);

	@Autowired
	private SqlSessionTemplate sqlsession;
	
//	@Test
	public void idcheck() {
		Member2 result = sqlsession.selectOne("Members.idcheck", "admin");
		if(result != null) {
			logger.info("아이디가 존재합니다.");
			logger.info(result.getName());
			logger.info(result.getEmail());
		} else {
			logger.info("아이디가 존재하지 않습니다.");
		}
	}
	
//	@Test
//	public void read() {
//		Member result = sqlsession.selectOne("Members.idcheck", "TestId555");
//		if(result != null) {
//			logger.info("아이디가 존재합니다.");
//			logger.info("이름 : " + result.getName());
//			logger.info("메일 : " + result.getEmail());
//			logger.info("나이 : " + result.getAge());
//			logger.info("성별 : " + result.getGender());
//			logger.info("아이디 : " + result.getId());
//			logger.info("비밀번호 : " + result.getPassword());
//		} else {
//			logger.info("아이디가 존재하지 않습니다.");
//		}
//	}
	
//	@Test
	public void insert() {
		Member2 member = new Member2();
		member.setAge("21");
		member.setEmail("test@daum.net");
		member.setGender("m");
		member.setId("TestId555");
		member.setName("김길동");
		member.setPassword("1111");
		
		int result = sqlsession.insert("Members.insert", member);
		
		logger.info("========== " + result + " 개 삽입 ===========");
	}
	
//	@Test
	public void isBoardWriter() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", 4);
		map.put("pass", "1111");
		Board result = sqlsession.selectOne("Boards.boardWriter", map);
		if(result != null) {
			logger.info("해당글의 비번이 일치합니다.");
			logger.info("글번호 : " + result.getBoard_num());
			logger.info("제목 : " + result.getBoard_subject());
			logger.info("작성자 : " + result.getBoard_name());
			logger.info("비밀번호 : " + result.getBoard_pass());
			logger.info("내용 : " + result.getBoard_content());
			logger.info("============");
		} else {
			logger.info("해당글의 비번이 일치하지 않습니다.");
		}
	}
	
	@Test
	public void update() { // update() 수정 후 read() 실행
		Board board = new Board();
		board.setBoard_num(4);
		board.setBoard_name("admin");
		board.setBoard_pass("1111");
		board.setBoard_subject("수정된 제목입니다.");
		board.setBoard_content("수정된 내용입니다.");
		int result = sqlsession.update("Boards.modify", board);
		logger.info("===========" + result + " 개 수정 ===========");
	}
	
	@Test
	public void read() {
		Board result = sqlsession.selectOne("Boards.detail", 4);
		logger.info("글번호 : " + result.getBoard_num());
		logger.info("제목 : " + result.getBoard_subject());
		logger.info("작성자 : " + result.getBoard_name());
		logger.info("비밀번호 : " + result.getBoard_pass());
		logger.info("내용 : " + result.getBoard_content());
		logger.info("================================");
	}
}
