package com.test.service;

import java.util.*;
import org.springframework.context.ApplicationContext;

public class StudentMain {

	//(수강생용) 메인 메뉴 구성
	public void main(Scanner sc, String id_, ApplicationContext context) {
		
		EmployeeService service = new EmployeeService(context);
		
		// 메인 메뉴 액션
		boolean run = true;
		while (run) {
			System.out.println("-------------------------");
			System.out.printf("직원관리v2.0 (수강생:%s)%n", id_);
			System.out.println("-------------------------");
			System.out.println("수강생 전용 화면입니다.");
			System.out.println("--------------------------------------------");
			System.out.print("선택(0 quit)?");

			int m = sc.nextInt();
			sc.nextLine();

			switch (m) {
			case 0:	run = false; break;
			}

		}
		System.out.println("로그아웃되었습니다.");
		
	}

}
