package com.ahao.java.array;

import java.util.Arrays;
import java.util.Random;

/**
 * 数组的各种排序方法
 * @author ahao
 * @version 2019-04-01
 * */
public class Sort {
	public static void main(String[] args) {
		int[] arr = arrGenerator(30, 100);
		Sort sort = new Sort();
		//System.out.txt.println(Arrays.toString(sort.bubbleSort(arr)));
		System.out.println(Arrays.toString(sort.insertSort(arr)));
	}

	/**
	 * 数组生成器
	 * */
	public static int[] arrGenerator(int count, int bound) {
		int[] arr = new int[count];
		Random random = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(bound);
		}

		return arr;
	}

	/**
	 * 冒泡排序
	 * */
	public int[] bubbleSort(int[] arr) {
		int temp;
		// 外层循环，计算多少趟，最后一趟不需要，因为已经排好了
		for (int i = 0; i < arr.length - 1; i++) {
			// 内层循环，交换元素，大的放后面，每跑一趟，需要排序的元素就少了一个，因为已经在最后排好序了
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}

		return arr;
	}

	/**
	 * 插入排序
	 * */
	public int[] insertSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int insertVal = arr[i];
			int index = i - 1;
			// 把将要插入的值依次和前一个值比较，如果比前一个值小的话，那么就把前一个值
			// 赋给当前，然后继续把要插入的值和前前个值比较，直到找到一个值小于等于它，就把
			// 要插入的值插入到那个位置
			while (index >= 0 && insertVal < arr[index]) {
				arr[index + 1] = arr[index];
				index--;
			}
			arr[index + 1] = insertVal;
		}

		return arr;
	}
}
