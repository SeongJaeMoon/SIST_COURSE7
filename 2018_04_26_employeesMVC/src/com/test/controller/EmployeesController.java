package com.test.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.dao.EmployeesJDBCTemplate;
import com.test.domain.Employees;
import com.test.domain.FileModel;

@Controller
@RequestMapping("/employee")
public class EmployeesController {
	
	@Autowired
	private EmployeesJDBCTemplate jdbcTemplate;
	
	@Autowired
	private ServletContext context;
	
	@RequestMapping("/list")
	public String list(Model model, String key, String value) {
		
		List<Employees> list = null;
		
		if (key == null && value == null) {
			list = this.jdbcTemplate.list("eid");
		} else if (key != null && value == null) {
			list = this.jdbcTemplate.list(key);
		} else if (key != null && value != null){
			list = this.jdbcTemplate.list(key, value);
		}

		int count = list.size();
		
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("key", key);
		model.addAttribute("value", value);
		
		return "employees/employeelist"; ///WEB-INF/views/employees/employeelist.jsp
	}
	
	@RequestMapping("/insertform")
	public String insertform(Model model) {
		
		List<Employees> regionList = this.jdbcTemplate.regionList();
		List<Employees> deptList = this.jdbcTemplate.departmentList();
		List<Employees> jobList = this.jdbcTemplate.jobList();
		
		model.addAttribute("regionList", regionList);
		model.addAttribute("deptList", deptList);
		model.addAttribute("jobList", jobList);
		
		return "employees/employeeinsertform";  ///WEB-INF/views/employees/employeeinsertform.jsp
	}
	
	
	/*
	@ResponseBody 
	- 응답 처리를 JSON 형식으로 반환하는 어노테이션
	- jackson-annotations-2.8.4.jar, 	jackson-core-2.8.4.jar,	jackson-databind-2.8.4.jar 라이브러리 추가 필요
	*/
	@RequestMapping("/getminbasicpay")
	public  @ResponseBody Map<String, String> getminbasicpay(String query) {
		
		int result = this.jdbcTemplate.getminbasicpay(query);
		
		Map<String, String> map = new HashMap<>();
		map.put("minBasicPay", String.valueOf(result));
		
		return map;
	}

	@RequestMapping("/insert")
	public String insert(Employees emp, RedirectAttributes rttr) {

		int result = 0;
		try {
			result = this.jdbcTemplate.add(emp);
		}catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
		
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/employee/list";
	}
	
	
	@RequestMapping("/fileUpload")
	public String fileUpload(FileModel file, Model model, Employees emp, RedirectAttributes rttr) throws IOException {
		
		MultipartFile multipartFile = file.getFile();
		String uploadPath = context.getRealPath("") + "resources/pictures" + File.separator;
		System.out.println(uploadPath);
		String newFileName = java.util.UUID.randomUUID()+"-"+multipartFile.getOriginalFilename();
		FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadPath + newFileName));
		
		emp.setEid(file.getEid());
		emp.setPicName(newFileName);
		
		int result = 0;
		try {
			if (file.getPicName().equals("")) {
				result = this.jdbcTemplate.pictureAdd(emp);
			} else {
				result = this.jdbcTemplate.pictureModify(emp);
			}
		}catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
		
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/employee/list";
	}
	
	
	@RequestMapping("/delete")
	public String delete(String eid, RedirectAttributes rttr) {

		int result = 0;
		try {
			
			//사진 정보 삭제 -> 물리적 사진 정보 삭제는 제외
			result = this.jdbcTemplate.pictureRemove(eid);
			
			//직원 정보 삭제
			result = this.jdbcTemplate.remove(eid);
			
			
		}catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
		
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/employee/list";
	}
	
	
}
