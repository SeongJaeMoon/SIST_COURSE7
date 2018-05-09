package com.test.sts;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.service.GuestBookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class GuestBookServiceTest {

	@Inject
	private GuestBookService guestbookService;

	@Test
	public void test() throws Exception {

		Map<String, Object> map = new HashMap<>();
		map.put("key", "ALL");
		map.put("value", "");
		map.put("limit_offset", 0);
		map.put("limit_count", 20);
		
		this.guestbookService.guestBookList(map);

	}

}
