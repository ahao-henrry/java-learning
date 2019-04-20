package com.ahao.java.generic;

import com.ahao.java.map.MapIterator;

public class GenericMain {
	public static void main(String[] args) {
		Gen<MapIterator> objectGen = new Gen<>(new MapIterator());
		objectGen.getClassMethods();
	}
}
