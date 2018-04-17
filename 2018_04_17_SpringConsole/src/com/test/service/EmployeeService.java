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

	
	//직원관리v2.0 MySQL > 1.직원관리
	public void menu_1(Scanner sc) {
		//메뉴 액션
		boolean run = true;
		while (run) {
			System.out.println("-------------------------------");
			System.out.println("직원관리v2.0 MySQL > 1.직원관리");
			System.out.println("-------------------------------");
			System.out.println("1.직원입력  2.직원전체출력  3.직원검색  4.직원삭제");
			System.out.print("선택(1~4, 0 quit)?");

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

	//직원관리v2.0 MySQL > 1.직원관리 > 1.직원입력
	private void menu_11(Scanner sc) {
		
		//이름, 주민번호, ... 등의 항목을 콘솔 입력 받는다.
		//주의) 지역, 부서, 직위는 번호 입력을 받기 전에 번호의 범위를 출력한다. FK 제약.
		//주의) 입력시 특정 패턴을 벗어나거나, 입력 범위를 벗어나는 경우 예외 발생.
		
		System.out.print("이름:");
		String name_ = sc.nextLine();
		if (name_.equals("0")) return;
		System.out.print("주민번호(YYMMDD-NNNNNNN):");
		String ssn = sc.nextLine();
		if (ssn.equals("0")) return;
		System.out.print("입사일(YYYY-MM-DD):");
		String hiredate = sc.nextLine();
		if (hiredate.equals("0")) return;
		System.out.print("전화번호(XXX-XXXX-XXXX):");
		String phone = sc.nextLine();
		if (phone.equals("0")) return;
		
		//지역 번호 범위를 벗어나는 경우 확인하는 과정
		List<Employee> regionList = this.employeeJDBCTemplate.regionList();
		System.out.println("----------------");
		System.out.println("지역번호/ 지역명");
		for(Employee e : regionList) {
			System.out.printf("%s/ %s%n", e.getReg_id(), e.getReg_name());
		}
		String reg_id = "";
		while(true) {
			System.out.print("지역번호:");
			reg_id = sc.nextLine();
			
			//입력 중단
			if (reg_id.equals("0")) {
				//메소드 액션 진행에 대한 중단
				return;
			}
			
			//범위 검사 진행 -> 결과에 따라서 반복문 탈출
			//주의) Employees 클래스에서 toString() 오버라이딩 메소드 추가 필요
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
				System.out.println("잘못된 범위의 값입니다.");
			}
		}
		
		//부서 번호 범위를 벗어나는 경우 확인하는 과정
		List<Employee> deptList = this.employeeJDBCTemplate.deptList();
		System.out.println("----------------");
		System.out.println("부서번호/ 부서명");
		for(Employee e : deptList) {
			System.out.printf("%s/ %s%n", e.getDept_id(), e.getDept_name());
		}
		String dept_id = "";
		while(true) {
			System.out.print("부서번호:");
			dept_id = sc.nextLine();
			
			//입력 중단
			if (dept_id.equals("0")) {
				//메소드 액션 진행에 대한 중단
				return;
			}
			
			//범위 검사 진행 -> 결과에 따라서 반복문 탈출
			//주의) Employees 클래스에서 toString() 오버라이딩 메소드 추가 필요
			//"[null/DEPT01/null/null/DEPT02/...]"
			boolean result = deptList.toString().contains(dept_id);
			if (result) {
				break;
			} else {
				System.out.println("잘못된 범위의 값입니다.");
			}
		}

		//직위 번호 범위를 벗어나는 경우 확인하는 과정
		List<Employee> jobList = this.employeeJDBCTemplate.jobList();
		System.out.println("----------------");
		System.out.println("직위번호/ 직위명");
		for(Employee e : jobList) {
			System.out.printf("%s/ %s%n", e.getJob_id(), e.getJob_title());
		}
		String job_id = "";
		while(true) {
			System.out.print("직위번호:");
			job_id = sc.nextLine();
			
			//입력 중단
			if (job_id.equals("0")) {
				//메소드 액션 진행에 대한 중단
				return;
			}
			
			//범위 검사 진행 -> 결과에 따라서 반복문 탈출
			//주의) Employees 클래스에서 toString() 오버라이딩 메소드 추가 필요
			//"[null/null/JOB01/null/null/JOB02/...]"
			boolean result = jobList.toString().contains(job_id);
			if (result) {
				break;
			} else {
				System.out.println("잘못된 범위의 값입니다.");
			}
		}

		
		System.out.print("기본급:");
		String basicpay = sc.nextLine();
		if (basicpay.equals("0")) return;
		System.out.print("수당:");
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

			System.out.println("1개의 행이 추가되었습니다.");
		}catch(Exception e) {
			System.out.println("정상적으로 입력되지 않았습니다.");
		}
	}

	
	//직원관리v2.0 MySQL > 1.직원관리 > 2.직원전체출력
	private void menu_12(Scanner sc) {
		//메뉴 액션
		boolean run = true;
		while (run) {
			System.out.println("-------------------------------------------------");
			System.out.println("직원관리v2.0 MySQL > 1.직원관리 > 2.직원전체출력");
			System.out.println("-------------------------------------------------");
			System.out.println("1.사번정렬  2.이름정렬  3.지역정렬  4.부서정렬  5.직위정렬");
			System.out.print("선택(1~5, 0 quit)?");

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
	
	//직원관리v2.0 MySQL > 1.직원관리 > 2.직원전체출력 > 1.사번정렬
	private void menu_121(Scanner sc) {
		this.employeesList("eid");
	}
	
	//직원관리v2.0 MySQL > 1.직원관리 > 2.직원전체출력 > 2.이름정렬
	private void menu_122(Scanner sc) {
		this.employeesList("name_");
	}
	
	//직원관리v2.0 MySQL > 1.직원관리 > 2.직원전체출력 > 3.지역정렬
	private void menu_123(Scanner sc) {
		this.employeesList("reg_name");
	}
	
	//직원관리v2.0 MySQL > 1.직원관리 > 2.직원전체출력 > 4.부서정렬
	private void menu_124(Scanner sc) {
		this.employeesList("dept_name");
	}
	
	//직원관리v2.0 MySQL > 1.직원관리 > 2.직원전체출력 > 5.직위정렬
	private void menu_125(Scanner sc) {
		this.employeesList("job_title");
	}
	
	//정렬 지원되는 전체 출력 액션 메소드
	private void employeesList(String key) {
		List<Employee> list = this.employeeJDBCTemplate.list(key);
		System.out.printf("총: %d명%n", list.size());
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("사번/ 이름/ 주민번호/ 입사일/ 전화번호/ 지역명/ 부서명/ 직위명/ 기본급/ 수당/ 급여");
		for (Employee e : list) {
			System.out.printf("%s/ %s/ %s/ %s/ %s/ %s/ %s/ %s/ %s/ %s%n"
					, e.getEid(), e.getName_(), e.getHiredate(), e.getPhone(), e.getReg_name()
					, e.getDept_name(), e.getJob_title(), e.getBasicpay(), e.getExtrapay(), e.getPay());
		}
	}
	

	//직원관리v2.0 MySQL > 1.직원관리 > 3.직원검색
	private void menu_13(Scanner sc) {
		//메뉴 액션
		boolean run = true;
		while (run) {
			System.out.println("--------------------------------------------");
			System.out.println("직원관리v2.0 MySQL > 1.직원관리 > 3.직원검색");
			System.out.println("--------------------------------------------");
			System.out.println("1.사번기준  2.이름기준  3.지역기준  4.부서기준  5.직위기준");
			System.out.print("선택(1~5, 0 quit)?");

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
	
	//직원관리v2.0 MySQL > 1.직원관리 > 3.직원검색 > 1.사번기준
	private int menu_131(Scanner sc) {
		int result = 0;
		String key = "eid";
		System.out.print("검색할 사번:");
		String value = sc.nextLine();
		result = this.employeesSearch(key, value);
		return result;
	}

	//직원관리v2.0 MySQL > 1.직원관리 > 3.직원검색 > 2.이름기준
	private int menu_132(Scanner sc) {
		int result = 0;
		String key = "name_";
		System.out.print("검색할 이름:");
		String value = sc.nextLine();
		result = this.employeesSearch(key, value);
		return result;
	}

	//직원관리v2.0 MySQL > 1.직원관리 > 3.직원검색 > 3.지역기준
	private int menu_133(Scanner sc) {
		int result = 0;
		String key = "reg_name";
		System.out.print("검색할 지역명:");
		String value = sc.nextLine();
		result = this.employeesSearch(key, value);
		return result;
	}

	//직원관리v2.0 MySQL > 1.직원관리 > 3.직원검색 > 4.부서기준
	private int menu_134(Scanner sc) {
		int result = 0;
		String key = "dept_name";
		System.out.print("검색할 부서명:");
		String value = sc.nextLine();
		result = this.employeesSearch(key, value);
		return result;
	}

	//직원관리v2.0 MySQL > 1.직원관리 > 3.직원검색 > 5.직위기준
	private int menu_135(Scanner sc) {
		int result = 0;
		String key = "job_title";
		System.out.print("검색할 직위명:");
		String value = sc.nextLine();
		result = this.employeesSearch(key, value);
		return result;
	}
	
	//검색 지원되는 전체 출력 액션 메소드
	private int employeesSearch(String key, String value) {
		int result = 0;
		List<Employee> list = this.employeeJDBCTemplate.list(key, value);
		result = list.size();
		System.out.printf("총: %d명%n", result);
		if (result > 0) {
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println("사번/ 이름/ 주민번호/ 입사일/ 전화번호/ 지역명/ 부서명/ 직위명/ 기본급/ 수당/ 급여");
			for (Employee e : list) {
				System.out.printf("%s/ %s/ %s/ %s/ %s/ %s/ %s/ %s/ %s/ %s%n"
						, e.getEid(), e.getName_(), e.getHiredate(), e.getPhone(), e.getReg_name()
						, e.getDept_name(), e.getJob_title(), e.getBasicpay(), e.getExtrapay(), e.getPay());
			}
		} else {
			System.out.println("검색 결과가 없습니다.");
		}
		return result;
	}
	
	
	//직원관리v2.0 MySQL > 1.직원관리 > 4.직원삭제
	private void menu_14(Scanner sc) {
		//이름 검색을 먼저 진행하고
		//검색 결과 출력
		//결과가 존재하는 경우 삭제할 사번을 물어본다.
		//사번을 기준으로 삭제 진행
		//결과 메시지 출력
		
		int result = this.menu_132(sc);
		if (result > 0) {
			System.out.printf("삭제할 사번:");
			String eid = sc.nextLine();
			try {
				int count = this.employeeJDBCTemplate.empDelete(eid);
				System.out.printf("%d개의 행이 삭제되었습니다.%n", count);
			}catch(Exception e) {
				System.out.println("정상적으로 삭제되지 않았습니다.");
			}
		}
		
	}
	
	//직원관리v2.0 MySQL > 2.기초정보관리
	public void menu_2(Scanner sc) {
		//메뉴 액션
		boolean run = true;
		while (run) {
			System.out.println("-----------------------------------");
			System.out.println("직원관리v2.0 MySQL > 2.기초정보관리");
			System.out.println("-----------------------------------");
			System.out.println("1.지역관리 2.부서관리 3.직위관리");
			System.out.print("선택(1~3, 0 quit)?");

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
	
	//직원관리v2.0 MySQL > 2.기초정보관리 > 1.지역관리
	private void menu_21(Scanner sc) {
		//메뉴 액션
		boolean run = true;
		while (run) {
			System.out.println("------------------------------------------------");
			System.out.println("직원관리v2.0 MySQL > 2.기초정보관리 > 1.지역관리");
			System.out.println("------------------------------------------------");
			System.out.println("1.지역입력 2.지역출력 3.지역삭제");
			System.out.print("선택(1~3, 0 quit)?");

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
	
	//직원관리v2.0 MySQL > 2.기초정보관리 > 1.지역관리 > 1.지역입력
	private void menu_211(Scanner sc) {
		System.out.println("지역번호/ 지역이름");
		for (Employee e : this.employeeJDBCTemplate.regionList()) {
			System.out.printf("%s/ %s%n", e.getReg_id(), e.getReg_name());
		}
		System.out.print("신규 등록 지역이름:");
		String reg_name = sc.nextLine();
		try {
			int result = this.employeeJDBCTemplate.regionAdd(reg_name);
			System.out.printf("%d개의 행이 추가되었습니다.%n", result);
		}catch(Exception e) {
			System.out.println("정상적으로 입력되지 않았습니다.");
		}
	}
	
	//직원관리v2.0 MySQL > 2.기초정보관리 > 1.지역관리 > 2.지역출력
	private void menu_212(Scanner sc) {
		System.out.println("지역번호/ 지역이름");
		for (Employee e : this.employeeJDBCTemplate.regionList()) {
			System.out.printf("%s/ %s%n", e.getReg_id(), e.getReg_name());
		}		
	}

	//직원관리v2.0 MySQL > 2.기초정보관리 > 1.지역관리 > 3.지역삭제
	private void menu_213(Scanner sc) {
		
		//지역 정보 출력 -> 삭제 가능 여부 출력
		//삭제하고자 하는 지역번호 입력
		//삭제 요청
		//삭제 결과 분석 및 메시지 출력
		System.out.println("지역번호/ 지역이름/ 삭제가능여부");
		for (Employee e : this.employeeJDBCTemplate.regionList()) {
			System.out.printf("%s/ %s/ %s%n", e.getReg_id(), e.getReg_name(), (e.getDeleteCheck()==0)?"Y":"N");
		}
		System.out.print("삭제할 지역의 지역번호:");
		String reg_id = sc.nextLine();
		try {
			int result = this.employeeJDBCTemplate.regionDelete(reg_id);
			System.out.printf("%d개의 행이 삭제되었습니다.%n", result);
		}catch(Exception e) {
			System.out.println("정상적으로 삭제되지 않았습니다.");
		}
	}
	
	/*
	  * 부서관리------------------------------------------
	  */

	 
	 //부서관리v2.0 MySQL > 2.기초정보관리 > 1.부서관리
	 private void menu_22(Scanner sc) {
	  //메뉴 액션
	  boolean run = true;
	  while (run) {
	   System.out.println("------------------------------------------------");
	   System.out.println("직원관리v2.0 MySQL > 2.기초정보관리 > 1.부서관리");
	   System.out.println("------------------------------------------------");
	   System.out.println("1.부서입력 2.부서출력 3.부서삭제");
	   System.out.print("선택(1~3, 0 quit)?");

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
	 
	 //직원관리v2.0 MySQL > 2.기초정보관리 > 1.부서관리 > 1.부서입력
	 private void menu_221(Scanner sc) {
	  System.out.println("부서번호/ 부서이름");
	  for (Employee e : this.employeeJDBCTemplate.deptList()) {
	   System.out.printf("%s/ %s%n", e.getDept_id(), e.getDept_name());
	  }
	  System.out.print("신규 등록 부서이름:");
	  String dept_name = sc.nextLine();
	  try {
		  int result = this.employeeJDBCTemplate.deptAdd(dept_name);
		  System.out.printf("%d개의 행이 추가되었습니다.%n", result);
	  }catch(Exception e) {
		  System.out.println("정상적으로 입력되지 않았습니다.");
	  }
	 }
	 
	 //직원관리v2.0 MySQL > 2.기초정보관리 > 1.부서관리 > 2.부서출력
	 private void menu_222(Scanner sc) {
	  System.out.println("부서번호/ 부서이름");
	  for (Employee e : this.employeeJDBCTemplate.deptList()) {
	   System.out.printf("%s/ %s%n", e.getDept_id(), e.getDept_name());
	  }  
	 }

	 //직원관리v2.0 MySQL > 2.기초정보관리 > 1.부서관리 > 3.부서삭제
	 private void menu_223(Scanner sc) {
	  
	  //부서 정보 출력 -> 삭제 가능 여부 출력
	  //삭제하고자 하는 부서번호 입력
	  //삭제 요청
	  //삭제 결과 분석 및 메시지 출력
	  System.out.println("부서번호/ 부서이름/ 삭제가능여부");
	  for (Employee e : this.employeeJDBCTemplate.deptList()) {
	   System.out.printf("%s/ %s/ %s%n", e.getDept_id(), e.getDept_name(), (e.getDeleteCheck()==0)?"Y":"N");
	  }
	  System.out.print("삭제할 부서의 부서번호:");
	  String dept_id = sc.nextLine();
	  try {
		  int result = this.employeeJDBCTemplate.deptDelete(dept_id);
	  	System.out.printf("%d개의 행이 삭제되었습니다.%n", result);
	  }catch(Exception e) {
		  System.out.println("정상적으로 삭제되지 않았습니다.");
	  }
	 }
	 
	 
	 /*
	  * 직위관리------------------------------------------
	  */

	 
	 //부서관리v2.0 MySQL > 2.기초정보관리 > 1.직위관리
	 private void menu_23(Scanner sc) {
	  //메뉴 액션
	  boolean run = true;
	  while (run) {
	   System.out.println("------------------------------------------------");
	   System.out.println("직원관리v2.0 MySQL > 2.기초정보관리 > 1.직위관리");
	   System.out.println("------------------------------------------------");
	   System.out.println("1.직위입력 2.직위출력 3.직위삭제");
	   System.out.print("선택(1~3, 0 quit)?");

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
	 
	 //직원관리v2.0 MySQL > 2.기초정보관리 > 1.부서관리 > 1.부서입력
	 private void menu_231(Scanner sc) {
	  System.out.println("직위번호/ 직위이름");
	  for (Employee e : this.employeeJDBCTemplate.jobList()) {
	   System.out.printf("%s/ %s%n", e.getJob_id(), e.getJob_title());
	  }
	  System.out.print("신규 등록 직위이름:");
	  String job_name = sc.nextLine();
	  
	  int result = this.employeeJDBCTemplate.jobAdd(job_name);
	  System.out.printf("%d개의 행이 추가되었습니다.%n", result);
	  
	 }
	 
	 //직원관리v2.0 MySQL > 2.기초정보관리 > 1.직위관리 > 2.직위출력
	 private void menu_232(Scanner sc) {
	  System.out.println("직위번호/ 직위이름");
	  for (Employee e : this.employeeJDBCTemplate.jobList()) {
	   System.out.printf("%s/ %s%n", e.getJob_id(), e.getJob_title());
	  }  
	 }

	 //직원관리v2.0 MySQL > 2.기초정보관리 > 1.직위관리 > 3.직위삭제
	 private void menu_233(Scanner sc) {
	  
	  //직위 정보 출력 -> 삭제 가능 여부 출력
	  //삭제하고자 하는 부서번호 입력
	  //삭제 요청
	  //삭제 결과 분석 및 메시지 출력
	  System.out.println("직위번호/ 직위이름/ 삭제가능여부");
	  for (Employee e : this.employeeJDBCTemplate.jobList()) {
	   System.out.printf("%s/ %s/ %s%n", e.getJob_id(), e.getJob_title(), (e.getDeleteCheck()==0)?"Y":"N");
	  }
	  System.out.print("삭제할 직위의 직위번호:");
	  String job_id = sc.nextLine();
	  try {
		  int result = this.employeeJDBCTemplate.jobDelete(job_id);
		  System.out.printf("%d개의 행이 삭제되었습니다.%n", result);
	  }catch(Exception e) {
		  System.out.println("정상적으로 삭제되지 않았습니다.");
	  }
	 }
	
}
