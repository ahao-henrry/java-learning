package com.ahao.java.base;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 打印金字塔
 * @author ahao
 * @version 2019年3月31日
 */
public class Pyramid {
	public static void main(String[] args) {
		Pyramid pyramid = new Pyramid();
		pyramid.printPyramid();
		pyramid.printHollowPyramid();
	}

	/**
	 * 打印金字塔
	 * */
	private void printPyramid() {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("请输入数字：");
			String arg = bufferedReader.readLine();
			int level = Integer.parseInt(arg);
			if (0 != level%2) {
				level++;
			}

			for (int i = 0; i <= level; i++) {
				// 隔行输出，因为我不想元素挨得太近，挨得太近不好看
				if (0 != i%2) {
					continue;
				}
				StringBuilder stringBuilder = new StringBuilder();
				// 打印每行前面的空格
				for (int k = 0; k < (level - i)/2; k++) {
					stringBuilder.append(" ");
				}
				// 打印*
				for (int j = 0; j <= i; j += 2) {
					if (0 == j) {
						stringBuilder.append("*");
					} else {
						stringBuilder.append(" *");
					}
				}
				System.out.println(stringBuilder.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 打印镂空金字塔
	 * */
	private void printHollowPyramid() {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("请输入数字：");
			String arg = bufferedReader.readLine();
			int level = Integer.parseInt(arg);
			if (0 != level%2) {
				level++;
			}
			
			for (int i = 0; i <= level; i++) {
				// 打印最后一行
				if (i == level) {
					StringBuilder stringBuilder = new StringBuilder();
					for (int l = 0; l <= i/2; l++) {
						stringBuilder.append("* ");
					}
					System.out.println(stringBuilder.toString());
					continue;
				}
				if (0 != i%2) {
					continue;
				}
				StringBuilder stringBuilder = new StringBuilder();
				for (int k = 0; k < (level - i)/2; k++) {
					stringBuilder.append(" ");
				}
				// 除了每一行应该打印*的开始和最后都打印空格就好了
				for (int j = 0; j <= i; j += 2) {
					if (0 == j) {
						stringBuilder.append("*");
					} else if (i == j) {
						stringBuilder.append(" *");
					} else {
						stringBuilder.append("  ");
					}
				}
				System.out.println(stringBuilder.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
