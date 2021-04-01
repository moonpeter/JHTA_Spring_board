package com.naver.myChat.bootstrap.domain;

import org.springframework.web.multipart.MultipartFile;

public class Member {
	
	private String id;
	private String password;
	private MultipartFile uploadfile;
	private String savefile="/default.png";	// 선택한 이미지가 없는 경우 기본이미지 
	private String originalfile="/default.png";
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public MultipartFile getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
	}
	public String getSavefile() {
		return savefile;
	}
	public void setSavefile(String savefile) {
		this.savefile = savefile;
	}
	public String getOriginalfile() {
		return originalfile;
	}
	public void setOriginalfile(String originalfile) {
		this.originalfile = originalfile;
	}
	
}
