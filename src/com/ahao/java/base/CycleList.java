package com.ahao.java.base;

public class CycleList {
	private Person first;
	private Person next;
	private Person temp;
	// 有多少个人玩
	private Integer count;
	// 从第几个开始数
	private Integer from;
	// 数到第几个退出
	private Integer quitNum;
	
	public CycleList(Integer count, Integer from, Integer quitNum) {
		this.count = count;
		this.from = from;
		this.quitNum = quitNum;
	}

	public Person getFirst() {
		return first;
	}
	public void setFirst(Person first) {
		this.first = first;
	}
	public Person getNext() {
		return next;
	}
	public void setNext(Person next) {
		this.next = next;
	}
	public Person getTemp() {
		return temp;
	}
	public void setTemp(Person temp) {
		this.temp = temp;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

	// 相当于是构建一个链表
	public void initList() {
		for (int i = 1; i <= count; i++) {
			Person person = new Person(i);
			if (i == 1) {
				this.first = person;
				this.temp = person;
			} else if (i == count) {
				this.temp.setNext(person);
				this.temp = person;
				this.temp.setNext(first);
			} else {
				this.temp.setNext(person);
				this.temp = person;
			}
		}
	}
	
	public void play() {
		Person temp = this.first;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("被删除的顺序是：");
		while (count != 1) {
			// 找到第一个开始数的人
			for (int i = 1; i < from; i++) {
				temp = temp.getNext();
			}
			
			// 数quitNum下，找到要退出的那个人
			for (int i = 1; i < quitNum; i++) {
				temp = temp.getNext();
			}
			
			// 把要退出的那个人删除掉（前一个人的next后移），所以又循环找到要删除的人的前一个
			Person prePerson = this.first;
			while (prePerson.getNext() != temp) {
				prePerson = prePerson.getNext();
			}
			prePerson.setNext(temp.getNext());
			
			stringBuilder.append(" " + temp.getNum());
			
			// 将下一个人设置为下一轮开始数数的人
			temp = temp.getNext();
			count--;
		}
		System.out.println(stringBuilder);
	}
}
