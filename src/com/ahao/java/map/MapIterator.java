package com.ahao.java.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * HashMap 的遍历方式
 * @author ahao
 * @version 2019-04-05 08:07:32
 * */
public class MapIterator {
	public static void main(String[] args) {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("name", "ahao");
		hashMap.put("age", 18);

		MapIterator mapIterator = new MapIterator();
		mapIterator.keyIterator(hashMap);
		mapIterator.entryIterator(hashMap);
		mapIterator.entryEach(hashMap);
		mapIterator.lambdaEach(hashMap);

	}

	/**
	 * 键的迭代方式遍历
	 * */
	private void keyIterator(Map<String, Object> map) {
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Object value = map.get(key);
			System.out.println("key = " + key + "; value = " + value);
		}
	}

	/**
	 * entry 的迭代方式遍历
	 * */
	private void entryIterator(Map<String, Object> map) {
		Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = iterator.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			System.out.println("key = " + key + "; value = " + value);
		}
	}

	/**
	 * entry 的 for each 方式遍历
	 * */
	private void entryEach(Map<String, Object> map) {
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			System.out.println("key = " + key + "; value = " + value);
		}
	}

	/**
	 * 以 lambda 的方式遍历（Java 1.8）
	 * 其实看Map接口的{@link java.util.Map#forEach}默认实现方法也是使用entrySet来遍历的
	 * */
	private void lambdaEach(Map<String, Object> map) {
		map.forEach((key, value) ->
			System.out.println("key = " + key + "; value = " + value)
		);
	}
}
