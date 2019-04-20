package com.ahao.java.generic;

import java.lang.reflect.Method;

/**
 * 泛型与反射机制相关
 * @author ahao
 * @version 2019-04-05 09:24:37
 * */
public class Gen<T> {
	private T object;

	public Gen(T object) {
		this.object = object;
	}

	/**
	 * 获取类的方法
	 * */
	public void getClassMethods() {
		Method[] declaredMethods = this.object.getClass().getDeclaredMethods();
		System.out.println("get methods of class " + this.object.getClass().getName() + " are: ");
		for (Method method : declaredMethods) {
			System.out.println(method.getName());
		}
	}
}
