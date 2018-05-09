package com.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.domain.GuestBook;
import com.test.service.GuestBookService;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
	
	@Inject
	private GuestBookService guestBookService;
	
	@RequestMapping("/list")
	public String list(Model model, String key, String value, String pageNum) {
		
		if (key == null) {
			key = "ALL";
			value = "";
		}
		
		if (pageNum == null) {
			pageNum = "1";
		}
		
		int limit_count = 20;
		int limit_offset = (limit_count * (Integer.parseInt(pageNum) - 1));
		int previous = Integer.parseInt(pageNum) - 1; 
		int next = Integer.parseInt(pageNum) + 1;
		
		Map<String, Object> map = new HashMap<>();
		map.put("key", key);
		map.put("value", value);
		map.put("limit_offset", limit_offset);
		map.put("limit_count", limit_count);
		
		List<GuestBook> gbList  = this.guestBookService.guestBookList(map);
		int count = gbList.size();
		
		List<GuestBook> picList  = this.guestBookService.picList();
		int picListLength = picList.size();
		
		int totalCount = this.guestBookService.totalCount();
		
		model.addAttribute("gbList", gbList);
		model.addAttribute("count", count);
		model.addAttribute("picList", picList);
		model.addAttribute("picListLength", picListLength);
		model.addAttribute("key", key);
		model.addAttribute("value", value);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("previous", previous);
		model.addAttribute("next", next);
		model.addAttribute("limit_count", limit_count);		
		
		return "guestbook/guestbook"; ///WEB-INF/views/guestbook/guestbook.jsp
	}
	
	@RequestMapping("/insert")
	public String insert(HttpServletRequest request, GuestBook gb, RedirectAttributes rttr) {
		
		String clientIP = request.getRemoteAddr();
		gb.setClientIP(clientIP);
		
		int result = 0;
		try {
			result = this.guestBookService.guestBookAdd(gb);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/guestbook/list";
	}
	
	
	@RequestMapping("/delete")
	public String delete(GuestBook gb, RedirectAttributes rttr) {
		
		int result = 0;
		try {
			result = this.guestBookService.guestbookRemove(gb);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/guestbook/list";
	}

}
