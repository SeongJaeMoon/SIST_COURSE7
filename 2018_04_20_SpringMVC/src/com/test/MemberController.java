package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberJDBCTemplate memberJDBCTemplate; 
	
	@RequestMapping("/list")
	public String list(Model model, String key, String value) {
		
		if(key == null) {			
			key = "ALL";
			value = "";
		}
		
		List<Member> list = this.memberJDBCTemplate.list(key, value);
		List<Member> deptList = this.memberJDBCTemplate.deptList();
		int count = list.size();
		int totalCount = this.memberJDBCTemplate.totalCount();
		
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("key", key);
		model.addAttribute("value", value);
		model.addAttribute("deptList", deptList);
		return "member"; // /WEB-INF/views/member.jsp -> InternalResourceViewResolver가 자동으로 연결.
	}
	
	@RequestMapping("/insert")
	public String insert(Member m) {
		
		int success = this.memberJDBCTemplate.memberAdd(m);
		
		return String.format("redirect:/member/list?success=%d", success);
	}
	
	@RequestMapping("/delete")
	public String delete(String mid_) {
		Member m = new Member();
		m.setMid_(mid_);
		
		int success = this.memberJDBCTemplate.memberRemove(mid_);
		
		return String.format("redirect:/member/list?success=%d", success);
		
	}
	
	@RequestMapping("/update")
	public String update(Member m) {
		
	
		int success = this.memberJDBCTemplate.memberModify(m);
		
		return String.format("redirect:/member/list?success=%d", success);
		
	}

}
