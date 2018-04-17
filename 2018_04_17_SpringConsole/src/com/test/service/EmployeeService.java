package com.test.service;

import org.springframework.context.ApplicationContext;

import com.test.dao.EmployeeJDBCTemplate;
import com.test.model.Employee;

import java.util.*;
import java.time.*;

public class EmployeeService {

	
	private EmployeeJDBCTemplate employeeJDBCTemplate;

	public  EmployeeService(ApplicationContext context) {
		this.employeeJDBCTemplate = (EmployeeJDBCTemplate) context.getBean("employeeJDBCTemplate");
	}

	
	//��������v2.0 MySQL > 1.��������
	public void menu_1(Scanner sc) {
		//�޴� �׼�
		boolean run = true;
		while (run) {
			System.out.println("-------------------------------");
			System.out.println("��������v2.0 MySQL > 1.��������");
			System.out.println("-------------------------------");
			System.out.println("1.�����Է�  2.������ü���  3.�����˻�  4.��������");
			System.out.print("����(1~4, 0 quit)?");

			int m = sc.nextInt();
			sc.nextLine();

			switch (m) {
			case 0:	run = false; break;
			case 1: this.menu_11(sc); break;
			case 2: this.menu_12(sc); break;
			case 3: this.menu_13(sc); break;
			case 4: this.menu_14(sc); break;
			}

		}	
	}

	//��������v2.0 MySQL > 1.�������� > 1.�����Է�
	private void menu_11(Scanner sc) {
		
		//�̸�, �ֹι�ȣ, ... ���� �׸��� �ܼ� �Է� �޴´�.
		//����) ����, �μ�, ������ ��ȣ �Է��� �ޱ� ���� ��ȣ�� ������ ����Ѵ�. FK ����.
		//����) �Է½� Ư�� ������ ����ų�, �Է� ������ ����� ��� ���� �߻�.
		
		System.out.print("�̸�:");
		String name_ = sc.nextLine();
		if (name_.equals("0")) return;
		System.out.print("�ֹι�ȣ(YYMMDD-NNNNNNN):");
		String ssn = sc.nextLine();
		if (ssn.equals("0")) return;
		System.out.print("�Ի���(YYYY-MM-DD):");
		String hiredate = sc.nextLine();
		if (hiredate.equals("0")) return;
		System.out.print("��ȭ��ȣ(XXX-XXXX-XXXX):");
		String phone = sc.nextLine();
		if (phone.equals("0")) return;
		
		//���� ��ȣ ������ ����� ��� Ȯ���ϴ� ����
		List<Employee> regionList = this.employeeJDBCTemplate.regionList();
		System.out.println("----------------");
		System.out.println("������ȣ/ ������");
		for(Employee e : regionList) {
			System.out.printf("%s/ %s%n", e.getReg_id(), e.getReg_name());
		}
		String reg_id = "";
		while(true) {
			System.out.print("������ȣ:");
			reg_id = sc.nextLine();
			
			//�Է� �ߴ�
			if (reg_id.equals("0")) {
				//�޼ҵ� �׼� ���࿡ ���� �ߴ�
				return;
			}
			
			//���� �˻� ���� -> ����� ���� �ݺ��� Ż��
			//����) Employees Ŭ�������� toString() �������̵� �޼ҵ� �߰� �ʿ�
			//"[REG01/null/null/REG02/...]"
//			boolean result = false;
			
			/*for (Employee r : regionList) {
				System.out.println(r.getDept_id());
				if(r.getDept_id().contains(reg_id)) result=true;
			}*/
			boolean result = regionList.toString().contains(reg_id);
			System.out.println(regionList.toString());
			if (result) {
				break;
			} else {
				System.out.println("�߸��� ������ ���Դϴ�.");
			}
		}
		
		//�μ� ��ȣ ������ ����� ��� Ȯ���ϴ� ����
		List<Employee> deptList = this.employeeJDBCTemplate.deptList();
		System.out.println("----------------");
		System.out.println("�μ���ȣ/ �μ���");
		for(Employee e : deptList) {
			System.out.printf("%s/ %s%n", e.getDept_id(), e.getDept_name());
		}
		String dept_id = "";
		while(true) {
			System.out.print("�μ���ȣ:");
			dept_id = sc.nextLine();
			
			//�Է� �ߴ�
			if (dept_id.equals("0")) {
				//�޼ҵ� �׼� ���࿡ ���� �ߴ�
				return;
			}
			
			//���� �˻� ���� -> ����� ���� �ݺ��� Ż��
			//����) Employees Ŭ�������� toString() �������̵� �޼ҵ� �߰� �ʿ�
			//"[null/DEPT01/null/null/DEPT02/...]"
			boolean result = deptList.toString().contains(dept_id);
			if (result) {
				break;
			} else {
				System.out.println("�߸��� ������ ���Դϴ�.");
			}
		}

		//���� ��ȣ ������ ����� ��� Ȯ���ϴ� ����
		List<Employee> jobList = this.employeeJDBCTemplate.jobList();
		System.out.println("----------------");
		System.out.println("������ȣ/ ������");
		for(Employee e : jobList) {
			System.out.printf("%s/ %s%n", e.getJob_id(), e.getJob_title());
		}
		String job_id = "";
		while(true) {
			System.out.print("������ȣ:");
			job_id = sc.nextLine();
			
			//�Է� �ߴ�
			if (job_id.equals("0")) {
				//�޼ҵ� �׼� ���࿡ ���� �ߴ�
				return;
			}
			
			//���� �˻� ���� -> ����� ���� �ݺ��� Ż��
			//����) Employees Ŭ�������� toString() �������̵� �޼ҵ� �߰� �ʿ�
			//"[null/null/JOB01/null/null/JOB02/...]"
			boolean result = jobList.toString().contains(job_id);
			if (result) {
				break;
			} else {
				System.out.println("�߸��� ������ ���Դϴ�.");
			}
		}

		
		System.out.print("�⺻��:");
		String basicpay = sc.nextLine();
		if (basicpay.equals("0")) return;
		System.out.print("����:");
		String extrapay = sc.nextLine();
		if (extrapay.equals("0")) return;
		
		Employee emp = new Employee();
		emp.setName_(name_);
		emp.setSsn(ssn);
		emp.setHiredate(LocalDate.parse(hiredate));
		emp.setPhone(phone);
		emp.setReg_id(reg_id);
		emp.setDept_id(dept_id);
		emp.setJob_id(job_id);
		emp.setBasicpay(Integer.parseInt(basicpay));
		emp.setExtrapay(Integer.parseInt(extrapay));
		try {
			int result = this.employeeJDBCTemplate.empAdd(emp);

			System.out.println("1���� ���� �߰��Ǿ����ϴ�.");
		}catch(Exception e) {
			System.out.println("���������� �Էµ��� �ʾҽ��ϴ�.");
		}
	}

	
	//��������v2.0 MySQL > 1.�������� > 2.������ü���
	private void menu_12(Scanner sc) {
		//�޴� �׼�
		boolean run = true;
		while (run) {
			System.out.println("-------------------------------------------------");
			System.out.println("��������v2.0 MySQL > 1.�������� > 2.������ü���");
			System.out.println("-------------------------------------------------");
			System.out.println("1.�������  2.�̸�����  3.��������  4.�μ�����  5.��������");
			System.out.print("����(1~5, 0 quit)?");

			int m = sc.nextInt();
			sc.nextLine();

			switch (m) {
			case 0:	run = false; break;
			case 1: this.menu_121(sc); break;
			case 2: this.menu_122(sc); break;
			case 3: this.menu_123(sc); break;
			case 4: this.menu_124(sc); break;
			case 5: this.menu_125(sc); break;
			}

		}	
	}
	
	//��������v2.0 MySQL > 1.�������� > 2.������ü��� > 1.�������
	private void menu_121(Scanner sc) {
		this.employeesList("eid");
	}
	
	//��������v2.0 MySQL > 1.�������� > 2.������ü��� > 2.�̸�����
	private void menu_122(Scanner sc) {
		this.employeesList("name_");
	}
	
	//��������v2.0 MySQL > 1.�������� > 2.������ü��� > 3.��������
	private void menu_123(Scanner sc) {
		this.employeesList("reg_name");
	}
	
	//��������v2.0 MySQL > 1.�������� > 2.������ü��� > 4.�μ�����
	private void menu_124(Scanner sc) {
		this.employeesList("dept_name");
	}
	
	//��������v2.0 MySQL > 1.�������� > 2.������ü��� > 5.��������
	private void menu_125(Scanner sc) {
		this.employeesList("job_title");
	}
	
	//���� �����Ǵ� ��ü ��� �׼� �޼ҵ�
	private void employeesList(String key) {
		List<Employee> list = this.employeeJDBCTemplate.list(key);
		System.out.printf("��: %d��%n", list.size());
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("���/ �̸�/ �ֹι�ȣ/ �Ի���/ ��ȭ��ȣ/ ������/ �μ���/ ������/ �⺻��/ ����/ �޿�");
		for (Employee e : list) {
			System.out.printf("%s/ %s/ %s/ %s/ %s/ %s/ %s/ %s/ %s/ %s%n"
					, e.getEid(), e.getName_(), e.getHiredate(), e.getPhone(), e.getReg_name()
					, e.getDept_name(), e.getJob_title(), e.getBasicpay(), e.getExtrapay(), e.getPay());
		}
	}
	

	//��������v2.0 MySQL > 1.�������� > 3.�����˻�
	private void menu_13(Scanner sc) {
		//�޴� �׼�
		boolean run = true;
		while (run) {
			System.out.println("--------------------------------------------");
			System.out.println("��������v2.0 MySQL > 1.�������� > 3.�����˻�");
			System.out.println("--------------------------------------------");
			System.out.println("1.�������  2.�̸�����  3.��������  4.�μ�����  5.��������");
			System.out.print("����(1~5, 0 quit)?");

			int m = sc.nextInt();
			sc.nextLine();

			switch (m) {
			case 0:	run = false; break;
			case 1: this.menu_131(sc); break;
			case 2: this.menu_132(sc); break;
			case 3: this.menu_133(sc); break;
			case 4: this.menu_134(sc); break;
			case 5: this.menu_135(sc); break;
			}
		}	
	}
	
	//��������v2.0 MySQL > 1.�������� > 3.�����˻� > 1.�������
	private int menu_131(Scanner sc) {
		int result = 0;
		String key = "eid";
		System.out.print("�˻��� ���:");
		String value = sc.nextLine();
		result = this.employeesSearch(key, value);
		return result;
	}

	//��������v2.0 MySQL > 1.�������� > 3.�����˻� > 2.�̸�����
	private int menu_132(Scanner sc) {
		int result = 0;
		String key = "name_";
		System.out.print("�˻��� �̸�:");
		String value = sc.nextLine();
		result = this.employeesSearch(key, value);
		return result;
	}

	//��������v2.0 MySQL > 1.�������� > 3.�����˻� > 3.��������
	private int menu_133(Scanner sc) {
		int result = 0;
		String key = "reg_name";
		System.out.print("�˻��� ������:");
		String value = sc.nextLine();
		result = this.employeesSearch(key, value);
		return result;
	}

	//��������v2.0 MySQL > 1.�������� > 3.�����˻� > 4.�μ�����
	private int menu_134(Scanner sc) {
		int result = 0;
		String key = "dept_name";
		System.out.print("�˻��� �μ���:");
		String value = sc.nextLine();
		result = this.employeesSearch(key, value);
		return result;
	}

	//��������v2.0 MySQL > 1.�������� > 3.�����˻� > 5.��������
	private int menu_135(Scanner sc) {
		int result = 0;
		String key = "job_title";
		System.out.print("�˻��� ������:");
		String value = sc.nextLine();
		result = this.employeesSearch(key, value);
		return result;
	}
	
	//�˻� �����Ǵ� ��ü ��� �׼� �޼ҵ�
	private int employeesSearch(String key, String value) {
		int result = 0;
		List<Employee> list = this.employeeJDBCTemplate.list(key, value);
		result = list.size();
		System.out.printf("��: %d��%n", result);
		if (result > 0) {
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println("���/ �̸�/ �ֹι�ȣ/ �Ի���/ ��ȭ��ȣ/ ������/ �μ���/ ������/ �⺻��/ ����/ �޿�");
			for (Employee e : list) {
				System.out.printf("%s/ %s/ %s/ %s/ %s/ %s/ %s/ %s/ %s/ %s%n"
						, e.getEid(), e.getName_(), e.getHiredate(), e.getPhone(), e.getReg_name()
						, e.getDept_name(), e.getJob_title(), e.getBasicpay(), e.getExtrapay(), e.getPay());
			}
		} else {
			System.out.println("�˻� ����� �����ϴ�.");
		}
		return result;
	}
	
	
	//��������v2.0 MySQL > 1.�������� > 4.��������
	private void menu_14(Scanner sc) {
		//�̸� �˻��� ���� �����ϰ�
		//�˻� ��� ���
		//����� �����ϴ� ��� ������ ����� �����.
		//����� �������� ���� ����
		//��� �޽��� ���
		
		int result = this.menu_132(sc);
		if (result > 0) {
			System.out.printf("������ ���:");
			String eid = sc.nextLine();
			try {
				int count = this.employeeJDBCTemplate.empDelete(eid);
				System.out.printf("%d���� ���� �����Ǿ����ϴ�.%n", count);
			}catch(Exception e) {
				System.out.println("���������� �������� �ʾҽ��ϴ�.");
			}
		}
		
	}
	
	//��������v2.0 MySQL > 2.������������
	public void menu_2(Scanner sc) {
		//�޴� �׼�
		boolean run = true;
		while (run) {
			System.out.println("-----------------------------------");
			System.out.println("��������v2.0 MySQL > 2.������������");
			System.out.println("-----------------------------------");
			System.out.println("1.�������� 2.�μ����� 3.��������");
			System.out.print("����(1~3, 0 quit)?");

			int m = sc.nextInt();
			sc.nextLine();

			switch (m) {
			case 0:	run = false; break;
			case 1: this.menu_21(sc); break;
			case 2: this.menu_22(sc); break;
			case 3: this.menu_23(sc); break;
			}

		}
	}
	
	//��������v2.0 MySQL > 2.������������ > 1.��������
	private void menu_21(Scanner sc) {
		//�޴� �׼�
		boolean run = true;
		while (run) {
			System.out.println("------------------------------------------------");
			System.out.println("��������v2.0 MySQL > 2.������������ > 1.��������");
			System.out.println("------------------------------------------------");
			System.out.println("1.�����Է� 2.������� 3.��������");
			System.out.print("����(1~3, 0 quit)?");

			int m = sc.nextInt();
			sc.nextLine();

			switch (m) {
			case 0:	run = false; break;
			case 1: this.menu_211(sc); break;
			case 2: this.menu_212(sc); break;
			case 3: this.menu_213(sc); break;
			}

		}		
	}
	
	//��������v2.0 MySQL > 2.������������ > 1.�������� > 1.�����Է�
	private void menu_211(Scanner sc) {
		System.out.println("������ȣ/ �����̸�");
		for (Employee e : this.employeeJDBCTemplate.regionList()) {
			System.out.printf("%s/ %s%n", e.getReg_id(), e.getReg_name());
		}
		System.out.print("�ű� ��� �����̸�:");
		String reg_name = sc.nextLine();
		try {
			int result = this.employeeJDBCTemplate.regionAdd(reg_name);
			System.out.printf("%d���� ���� �߰��Ǿ����ϴ�.%n", result);
		}catch(Exception e) {
			System.out.println("���������� �Էµ��� �ʾҽ��ϴ�.");
		}
	}
	
	//��������v2.0 MySQL > 2.������������ > 1.�������� > 2.�������
	private void menu_212(Scanner sc) {
		System.out.println("������ȣ/ �����̸�");
		for (Employee e : this.employeeJDBCTemplate.regionList()) {
			System.out.printf("%s/ %s%n", e.getReg_id(), e.getReg_name());
		}		
	}

	//��������v2.0 MySQL > 2.������������ > 1.�������� > 3.��������
	private void menu_213(Scanner sc) {
		
		//���� ���� ��� -> ���� ���� ���� ���
		//�����ϰ��� �ϴ� ������ȣ �Է�
		//���� ��û
		//���� ��� �м� �� �޽��� ���
		System.out.println("������ȣ/ �����̸�/ �������ɿ���");
		for (Employee e : this.employeeJDBCTemplate.regionList()) {
			System.out.printf("%s/ %s/ %s%n", e.getReg_id(), e.getReg_name(), (e.getDeleteCheck()==0)?"Y":"N");
		}
		System.out.print("������ ������ ������ȣ:");
		String reg_id = sc.nextLine();
		try {
			int result = this.employeeJDBCTemplate.regionDelete(reg_id);
			System.out.printf("%d���� ���� �����Ǿ����ϴ�.%n", result);
		}catch(Exception e) {
			System.out.println("���������� �������� �ʾҽ��ϴ�.");
		}
	}
	
	/*
	  * �μ�����------------------------------------------
	  */

	 
	 //�μ�����v2.0 MySQL > 2.������������ > 1.�μ�����
	 private void menu_22(Scanner sc) {
	  //�޴� �׼�
	  boolean run = true;
	  while (run) {
	   System.out.println("------------------------------------------------");
	   System.out.println("��������v2.0 MySQL > 2.������������ > 1.�μ�����");
	   System.out.println("------------------------------------------------");
	   System.out.println("1.�μ��Է� 2.�μ���� 3.�μ�����");
	   System.out.print("����(1~3, 0 quit)?");

	   int m = sc.nextInt();
	   sc.nextLine();

	   switch (m) {
	   case 0: run = false; break;
	   case 1: this.menu_221(sc); break;
	   case 2: this.menu_222(sc); break;
	   case 3: this.menu_223(sc); break;
	   }

	  }  
	 }
	 
	 //��������v2.0 MySQL > 2.������������ > 1.�μ����� > 1.�μ��Է�
	 private void menu_221(Scanner sc) {
	  System.out.println("�μ���ȣ/ �μ��̸�");
	  for (Employee e : this.employeeJDBCTemplate.deptList()) {
	   System.out.printf("%s/ %s%n", e.getDept_id(), e.getDept_name());
	  }
	  System.out.print("�ű� ��� �μ��̸�:");
	  String dept_name = sc.nextLine();
	  try {
		  int result = this.employeeJDBCTemplate.deptAdd(dept_name);
		  System.out.printf("%d���� ���� �߰��Ǿ����ϴ�.%n", result);
	  }catch(Exception e) {
		  System.out.println("���������� �Էµ��� �ʾҽ��ϴ�.");
	  }
	 }
	 
	 //��������v2.0 MySQL > 2.������������ > 1.�μ����� > 2.�μ����
	 private void menu_222(Scanner sc) {
	  System.out.println("�μ���ȣ/ �μ��̸�");
	  for (Employee e : this.employeeJDBCTemplate.deptList()) {
	   System.out.printf("%s/ %s%n", e.getDept_id(), e.getDept_name());
	  }  
	 }

	 //��������v2.0 MySQL > 2.������������ > 1.�μ����� > 3.�μ�����
	 private void menu_223(Scanner sc) {
	  
	  //�μ� ���� ��� -> ���� ���� ���� ���
	  //�����ϰ��� �ϴ� �μ���ȣ �Է�
	  //���� ��û
	  //���� ��� �м� �� �޽��� ���
	  System.out.println("�μ���ȣ/ �μ��̸�/ �������ɿ���");
	  for (Employee e : this.employeeJDBCTemplate.deptList()) {
	   System.out.printf("%s/ %s/ %s%n", e.getDept_id(), e.getDept_name(), (e.getDeleteCheck()==0)?"Y":"N");
	  }
	  System.out.print("������ �μ��� �μ���ȣ:");
	  String dept_id = sc.nextLine();
	  try {
		  int result = this.employeeJDBCTemplate.deptDelete(dept_id);
	  	System.out.printf("%d���� ���� �����Ǿ����ϴ�.%n", result);
	  }catch(Exception e) {
		  System.out.println("���������� �������� �ʾҽ��ϴ�.");
	  }
	 }
	 
	 
	 /*
	  * ��������------------------------------------------
	  */

	 
	 //�μ�����v2.0 MySQL > 2.������������ > 1.��������
	 private void menu_23(Scanner sc) {
	  //�޴� �׼�
	  boolean run = true;
	  while (run) {
	   System.out.println("------------------------------------------------");
	   System.out.println("��������v2.0 MySQL > 2.������������ > 1.��������");
	   System.out.println("------------------------------------------------");
	   System.out.println("1.�����Է� 2.������� 3.��������");
	   System.out.print("����(1~3, 0 quit)?");

	   int m = sc.nextInt();
	   sc.nextLine();

	   switch (m) {
	   case 0: run = false; break;
	   case 1: this.menu_231(sc); break;
	   case 2: this.menu_232(sc); break;
	   case 3: this.menu_233(sc); break;
	   }

	  }  
	 }
	 
	 //��������v2.0 MySQL > 2.������������ > 1.�μ����� > 1.�μ��Է�
	 private void menu_231(Scanner sc) {
	  System.out.println("������ȣ/ �����̸�");
	  for (Employee e : this.employeeJDBCTemplate.jobList()) {
	   System.out.printf("%s/ %s%n", e.getJob_id(), e.getJob_title());
	  }
	  System.out.print("�ű� ��� �����̸�:");
	  String job_name = sc.nextLine();
	  
	  int result = this.employeeJDBCTemplate.jobAdd(job_name);
	  System.out.printf("%d���� ���� �߰��Ǿ����ϴ�.%n", result);
	  
	 }
	 
	 //��������v2.0 MySQL > 2.������������ > 1.�������� > 2.�������
	 private void menu_232(Scanner sc) {
	  System.out.println("������ȣ/ �����̸�");
	  for (Employee e : this.employeeJDBCTemplate.jobList()) {
	   System.out.printf("%s/ %s%n", e.getJob_id(), e.getJob_title());
	  }  
	 }

	 //��������v2.0 MySQL > 2.������������ > 1.�������� > 3.��������
	 private void menu_233(Scanner sc) {
	  
	  //���� ���� ��� -> ���� ���� ���� ���
	  //�����ϰ��� �ϴ� �μ���ȣ �Է�
	  //���� ��û
	  //���� ��� �м� �� �޽��� ���
	  System.out.println("������ȣ/ �����̸�/ �������ɿ���");
	  for (Employee e : this.employeeJDBCTemplate.jobList()) {
	   System.out.printf("%s/ %s/ %s%n", e.getJob_id(), e.getJob_title(), (e.getDeleteCheck()==0)?"Y":"N");
	  }
	  System.out.print("������ ������ ������ȣ:");
	  String job_id = sc.nextLine();
	  try {
		  int result = this.employeeJDBCTemplate.jobDelete(job_id);
		  System.out.printf("%d���� ���� �����Ǿ����ϴ�.%n", result);
	  }catch(Exception e) {
		  System.out.println("���������� �������� �ʾҽ��ϴ�.");
	  }
	 }
	
}
