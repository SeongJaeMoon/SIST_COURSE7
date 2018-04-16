package com.test.ex020;

import org.springframework.beans.factory.annotation.Autowired;

public class Student {
	
	private Integer age;
	private String name;

	@Autowired(required=false)
	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return age;
	}

	@Autowired
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}