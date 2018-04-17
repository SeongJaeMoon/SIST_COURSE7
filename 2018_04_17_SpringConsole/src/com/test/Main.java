package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.model.Login;
import com.test.service.EmployeeMain;
import com.test.service.InstructorMain;
import com.test.service.LoginService;
import com.test.service.StudentMain;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/test/beans.xml");
		
		Scanner sc = new Scanner(System.in);
		LoginService service = new LoginService(context);
		
		//�α��� �޴� ����
		boolean run = true;
		while (run) {
			System.out.println("-------------------");
			System.out.println("��������v2.0 MySQL");
			System.out.println("-------------------");
			System.out.println("1.�α���");
			System.out.print("����(1, 0 quit)?");

			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				break;
			} else if (m == 1) {
				//�α��� �޴� ȣ�� �� �α��� ��� �м�
			try {
				Login result = service.login(sc);
					//�α��� ���� �� ��� �м�
					String id_ = result.getId_();
					int grade = result.getGrade();
					if (grade == 0) {
						//������ ���� ���� �޴��� �̵�
						new EmployeeMain().main(sc, id_, context);
					} else if (grade == 1) {
						//���� ���� ���� �޴��� �̵�
						new InstructorMain().main(sc, id_, context);
					} else if (grade == 2) {
						//������ ���� ���� �޴��� �̵�
						new StudentMain().main(sc, id_, context);
					} else {
						System.out.println("�߸��� ��� �����Դϴ�.");
					}
			}catch(Exception e) {
				System.out.println("�α��ο� �����߽��ϴ�.");
			}
		}

		}
		sc.close();		
		((ClassPathXmlApplicationContext)context).close();
		System.out.println("���α׷� ����.");
	}

}
