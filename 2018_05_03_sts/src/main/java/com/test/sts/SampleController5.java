package com.test.sts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController5 {

	@RequestMapping("/doJSON")
	public @ResponseBody ProductVO doJSON(){
		
		ProductVO vo = new ProductVO("基敲惑前",30000);
		
		return vo; //{"name":"基敲惑前", "price":3000}
		
	}
}
