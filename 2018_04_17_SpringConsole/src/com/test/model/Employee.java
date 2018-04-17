package com.test.model;

import java.time.*;

public class Employee {
	
	private String reg_id, reg_name, dept_id, dept_name, job_id, job_title;
	private String eid, name_, ssn, phone;
	private LocalDate hiredate;
	private int basicpay, extrapay, pay;
	private int deleteCheck;
	
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getReg_name() {
		return reg_name;
	}
	public void setReg_name(String reg_name) {
		this.reg_name = reg_name;
	}
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	public String getJob_title() {
		return job_title;
	}
	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getName_() {
		return name_;
	}
	public void setName_(String name_) {
		this.name_ = name_;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public LocalDate getHiredate() {
		return hiredate;
	}
	public void setHiredate(LocalDate hiredate) {
		this.hiredate = hiredate;
	}
	public int getBasicpay() {
		return basicpay;
	}
	public void setBasicpay(int basicpay) {
		this.basicpay = basicpay;
	}
	public int getExtrapay() {
		return extrapay;
	}
	public void setExtrapay(int extrapay) {
		this.extrapay = extrapay;
	}
	public int getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	public int getDeleteCheck() {
		return deleteCheck;
	}
	public void setDeleteCheck(int deleteCheck) {
		this.deleteCheck = deleteCheck;
	}
	//지역, 부서, 직위 번호 범위 검색을 위한 toString() 오버라이딩 메소드
	@Override
	public String toString() {
		return String.format("%s/ %s/ %s/"
				, this.getReg_id(), this.getDept_id(), this.getJob_id());
	}
	
}
