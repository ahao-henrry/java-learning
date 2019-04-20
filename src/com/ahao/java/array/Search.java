package com.ahao.java.array;

import java.util.Arrays;

/**
 * 数组查找
 * @author ahao
 * @version 2019-04-02
 */
public class Search {
	private static int count = 0;

	public static void main(String[] args) {
		int[] arr = Sort.arrGenerator(20, 100);
		System.out.println(Arrays.toString(arr));
		int target = arr[3];
		Sort sort = new Sort();
		int[] sortedArr = sort.insertSort(arr);
		System.out.println(Arrays.toString(sortedArr));

		Search search = new Search();
		int index = search.binarySearch(target, arr);
		// int index = search.binarySearchRecursive(0, arr.length - 1, target, arr);
		System.out.println("index 是: " + index);
		System.out.println("找了 " + count + " 次");
	}

	/**
	 * 二分查找
	 * */
	private int binarySearch(int target, int[] arr) {
		int leftIndex = 0;
		int rightIndex = arr.length - 1;
		while (leftIndex <= rightIndex) {
			count++;
			int middle = (rightIndex + leftIndex) >>> 1 ;
			if (target == arr[middle]) {
				return middle;
			} else if (target < arr[middle]) {
				rightIndex = middle - 1;
			} else {
				leftIndex = middle + 1;
			}
		}

		return -1;
	}

	/**
	 * 递归的二分查找
	 * */
	private int binarySearchRecursive(int leftIndex, int rightIndex, int target, int[] arr) {
		count++;
		int middleIndex = (rightIndex + leftIndex)/2;
		if (leftIndex <= rightIndex) {
			if (target < arr[middleIndex]) {
				return binarySearchRecursive(leftIndex, middleIndex - 1, target, arr);
			} else if (target == arr[middleIndex]) {
				return middleIndex;
			} else {
				return binarySearchRecursive(middleIndex + 1, rightIndex, target, arr);
			}
		} else {
			return -1;
		}
	}
}
