package com.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.domain.Member;
import com.test.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Inject
	private MemberService memberService;
	
	@RequestMapping("/list")
	public String list(Model model, String key, String value) {
		
		if (key == null) {
			key = "ALL";
			value = "";
		}
		
		Map<String, String> map = new HashMap<>();
		map.put("key", key);
		map.put("value", value);
		
		List<Member> list = this.memberService.list(map);
		int count = list.size();
		int totalCount = this.memberService.totalCount();
		
		List<Member> deptList = this.memberService.deptList();
		
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("key", key);
		model.addAttribute("value", value);
		model.addAttribute("deptList", deptList);
		
		return "member";
	}
	
	@RequestMapping("/insert")
	public String insert(Member m, RedirectAttributes rttr) {
		
		int result = 0;
		try {
				result = this.memberService.memberAdd(m);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		rttr.addAttribute("success", result);
		
		return "redirect:/member/list";
	}

	
	@RequestMapping("/delete")
	public String delete(Member m, RedirectAttributes rttr) {
		
		int result = 0;
		try {
				result = this.memberService.memberRemove(m);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		rttr.addAttribute("success", result);
		
		return "redirect:/member/list";
	}
	
	@RequestMapping("/update")
	public String update(Member m, RedirectAttributes rttr) {
		
		int result = 0;
		try {
				result = this.memberService.memberModify(m);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		rttr.addAttribute("success", result);
		
		return "redirect:/member/list";
	}
	
}
