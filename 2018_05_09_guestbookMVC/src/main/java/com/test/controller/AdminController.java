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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.domain.AdminGuestBook;
import com.test.domain.FileModel;
import com.test.service.AdminGuestBookService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminGuestBookService adminGuestBookService;
	
	@Autowired
	private ServletContext context;
	
	@RequestMapping("/booklist")
	public String booklist(String key, String value, Model model) {
		
		if (key == null) {
			key = "ALL";
			value = "";
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("key", key);
		map.put("value", value);
		
		List<AdminGuestBook> gbList =  this.adminGuestBookService.guestBookList(map);
		int gbListCount = gbList.size();
		int totalCount = this.adminGuestBookService.totalCount();
		
		model.addAttribute("gbList", gbList);
		model.addAttribute("gbListCount", gbListCount);
		model.addAttribute("totalCount", totalCount);
		
		return "admin/adminbooklist";
	}

	@RequestMapping("/blind")
	public String blind(AdminGuestBook gb) {
		
		this.adminGuestBookService.blind(gb);
		
		return "redirect:/admin/booklist";
	}

	@RequestMapping("/picturelist")
	public String picturelist(String key, String value, Model model) {
		
		if (key == null) {
			key = "ALL";
			value = "";
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("key", key);
		map.put("value", value);
		
		List<AdminGuestBook> picList =  this.adminGuestBookService.picList(map);
		model.addAttribute("picList", picList);
		
		return "admin/adminpicturelist";
	}

	@RequestMapping("/pictureinsert")
	public String pictureinsert(FileModel file, AdminGuestBook gb, RedirectAttributes rttr)  throws IOException {
		
		MultipartFile multipartFile = file.getFile();
		String uploadPath = context.getRealPath("") + "resources/pictures" + File.separator;
		System.out.println(uploadPath);
		String newFileName = java.util.UUID.randomUUID()+"-"+multipartFile.getOriginalFilename();
		FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadPath + newFileName));
		
		gb.setPicName(newFileName);
		gb.setPicContent(file.getPicContent());
		
		int result = 0;
		try {
			result = this.adminGuestBookService.pictureAdd(gb);
		}catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
		
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/admin/picturelist";
	}

	@RequestMapping("/picturedelete")
	public String picturedelete(AdminGuestBook gb, RedirectAttributes rttr) {
		int result = 0;
		try {
			
			result = this.adminGuestBookService.pictureRemove(gb);
			
		}catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
		
		rttr.addFlashAttribute("success", result);
		
		return "redirect:/admin/picturelist";
	}

}
