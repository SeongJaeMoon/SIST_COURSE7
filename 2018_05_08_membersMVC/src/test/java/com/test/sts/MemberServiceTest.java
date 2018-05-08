package com.test.sts;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class MemberServiceTest {

	@Inject
	private MemberService memberService;

	@Test
	public void test() throws Exception {

		Map<String, String> map = new HashMap<>();
		map.put("key", "ALL");
		map.put("value", "");

		this.memberService.list(map);

	}

}
