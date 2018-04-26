package com.test;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	
	@Autowired
	private ServletContext context;

	@RequestMapping(value = "/fileUploadPage", method = RequestMethod.GET)
	public String fileUploadPage() {
		return "fileUpload";
	}

	@RequestMapping(value = "/fileUploadPage", method = RequestMethod.POST)
	public String fileUpload(FileModel file, ModelMap model) throws IOException {
		MultipartFile multipartFile = file.getFile();
		System.out.println("Fetching file");
		String uploadPath = context.getRealPath("") + "temp" + File.separator;
		System.out.println(uploadPath);
		FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadPath + java.util.UUID.randomUUID()+"-"+multipartFile.getOriginalFilename()));
		String fileName = multipartFile.getOriginalFilename();
		model.addAttribute("fileName", fileName);
		return "success";
	}
}