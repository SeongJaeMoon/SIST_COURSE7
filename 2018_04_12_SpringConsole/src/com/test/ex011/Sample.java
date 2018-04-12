package com.test.ex011;

public class Sample {
	
	//의존 객체를 주입하기 위한 설정 추가
	//->생성자 or setter
	//->멤버의 자료형은 Interface로 지정
	private Super sub;
	
	
	public Sample() {
	}
	public Sample(Super sub) {
		this.sub = sub;
	}
	
	
	public void method() {
		//인터페이스 Super의 자식 클래스(SubClass01 또는 SubClass02)에 대한 오버라이딩 메소드 호출
		this.sub.method();
	}

}
