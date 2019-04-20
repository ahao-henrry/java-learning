package com.ahao.java.binary;

/**
 * 二进制相关的一些验证
 * @author ahao
 * @version 2019-04-03 23:47:36
 * */
public class Binary {
	public static void main(String[] args) {
		System.out.println("~2=" + (~2));
		System.out.println("2&3=" + (2&3));
		System.out.println("2|3=" + (2|3));
		System.out.println("~-5=" + (~-5));
		System.out.println("13&7=" + (13&7));
		System.out.println("5|-4=" + (5|-4));
		System.out.println("-3^3=" + (-3^3));
		System.out.println("1>>2=" + (1>>2));
		System.out.println("-1>>2=" + (-1>>2));
		System.out.println("1<<2=" + (1<<2));
		System.out.println("-1<<2=" + (-1<<2));
		System.out.println("3>>>4=" + (3>>>4));
		System.out.println("-3>>>29=" + (-3>>>29));
	}
}
