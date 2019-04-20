package com.ahao.java.base;

/**
 * 约瑟夫问题
 * @author ahao
 * */
public class Josephus {
	public static void main(String[] args) {
		CycleList cycleList = new CycleList(41, 1, 3);
		cycleList.initList();
		cycleList.play();
	}
}
