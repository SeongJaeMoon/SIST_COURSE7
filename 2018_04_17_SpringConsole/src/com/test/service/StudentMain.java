package com.test.service;

import java.util.*;
import org.springframework.context.ApplicationContext;

public class StudentMain {

	//(��������) ���� �޴� ����
	public void main(Scanner sc, String id_, ApplicationContext context) {
		
		EmployeeService service = new EmployeeService(context);
		
		// ���� �޴� �׼�
		boolean run = true;
		while (run) {
			System.out.println("-------------------------");
			System.out.printf("��������v2.0 (������:%s)%n", id_);
			System.out.println("-------------------------");
			System.out.println("������ ���� ȭ���Դϴ�.");
			System.out.println("--------------------------------------------");
			System.out.print("����(0 quit)?");

			int m = sc.nextInt();
			sc.nextLine();

			switch (m) {
			case 0:	run = false; break;
			}

		}
		System.out.println("�α׾ƿ��Ǿ����ϴ�.");
		
	}

}
