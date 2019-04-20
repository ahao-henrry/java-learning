package com.ahao.java.base;

public class Person {
	private Integer num;
	private Person next;
	
	public Person(Integer num) {
		this.num = num;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Person getNext() {
		return next;
	}

	public void setNext(Person next) {
		this.next = next;
	}
	
}
