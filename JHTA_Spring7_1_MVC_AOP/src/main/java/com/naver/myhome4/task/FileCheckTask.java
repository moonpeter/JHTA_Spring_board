package com.naver.myhome4.task;

import java.io.File;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.naver.myhome4.service.BoardService;

import jdk.internal.instrumentation.Logger;

@Service
public class FileCheckTask {
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FileCheckTask.class);
	
	// 스케줄러를 이용해서 주기적으로 매일, 매주, 매월 프로그램 실행을 위한 작업을 실시 
//	@Scheduled(fixedDelay=5000) // 밀리세턴드 단위 : 1초 마다 "test"라는 로그가 나타남 
	public void test() throws Exception{
		logger.info("test");
	}
	
	@Value("${savefoldername}")
	private String saveFolder;			// 첨부파일들이 저정되어있는 폴더경로 
	
	@Autowired
	private BoardService boardService; 
	
	// cron 사용법 
	// seconds(초:0~59) minutes(분:0~59) hours(시:0~23) day(일:1~31) 
	// month(달:1~12) day of week(요일:0~6) year(optional)
//	@Scheduled(cron="0 47 * * * *")
	public void checkFiles() throws Exception {
		
		logger.info("checkFiles");
		// getDeleteFileList()의 결과를 String List 변수에 담는다.
		List<String> deleteFileList = boardService.getDeleteFileList();
		
		// for(String filename : deleteFileList) {
		// 0부터 i가 deleteFileList의 사이즈보다 클때까지 반복 
		for(int i=0; i<deleteFileList.size(); i++) {
			// deleteFileList를 반복하며 filename 변수에 값을 할당
			String filename = deleteFileList.get(i);
			File file = new File(saveFolder + filename);
			
			// file객체가 존재하면 delete 메서드를 실행 
			if(file.exists()) {
				if(file.delete()) {
					logger.info(file.getPath() + " 삭제되었습니다. ");
				}
			//file 객체가 존재하지 않으면 info 해줌 
			} else {
				logger.info(file.getPath() + " 파일이 존재하지 않습니다. ");
			}
		}
	}
}
