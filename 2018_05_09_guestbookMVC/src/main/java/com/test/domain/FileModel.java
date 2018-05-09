package com.test.domain;

import org.springframework.web.multipart.MultipartFile;

public class FileModel {
	
	private MultipartFile file;
	private String picContent;
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getPicContent() {
		return picContent;
	}
	public void setPicContent(String picContent) {
		this.picContent = picContent;
	}
	
}