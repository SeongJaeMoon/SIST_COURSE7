package com.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.dao.GuestBookJDBCTemplate;
import com.test.domain.GuestBook;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
	
	@Autowired
	private GuestBookJDBCTemplate jdbcTemplate;
	
	@RequestMapping("/list")
	public String list(Model model, String key, String value) {
		
		if (key == null) {
			key = "ALL";
			value = "";
		}
		
		List<GuestBook> gbList  = this.jdbcTemplate.guestBookList(key, value);
		int count = gbList.size();
		
		List<GuestBook> picList  = this.jdbcTemplate.picList();
		int picListLength = picList.size();
		
		int totalCount = this.jdbcTemplate.totalCount();
		
		model.addAttribute("gbList", gbList);
		model.addAttribute("count", count);
		model.addAttribute("picList", picList);
		model.addAttribute("picListLength", picListLength);
		model.addAttribute("key", key);
		model.addAttribute("value", value);
		model.addAttribute("totalCount", totalCount);
		
		return "guestbook/guestbook"; ///WEB-INF/views/guestbook/guestbook.jsp
	}
	
	@RequestMapping("/insert")
	public String insert(HttpServletRequest request, GuestBook gb, RedirectAttributes rttr) {
		
		String clientIP = request.getRemoteAddr();
		gb.setClientIP(clientIP);
		
		int result = 0;
		try {
			result = this.jdbcTemplate.guestBookAdd(gb);
		}catch(DataAccessException e) {
			System.out.println(e.getMessage());
		}
		
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/guestbook/list";
	}
	
	
	@RequestMapping("/delete")
	public String delete(GuestBook gb, RedirectAttributes rttr) {
		
		int result = 0;
		try {
			result = this.jdbcTemplate.guestbookRemove(gb);
		}catch(DataAccessException e) {
			System.out.println(e.getMessage());
		}
		
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/guestbook/list";
	}

}
