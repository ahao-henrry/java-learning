package com.ahao.java.exception;

import java.io.*;

/**
 * Exception 相关的用法
 * @author ahao
 * @version 2019-04-07 20:12:19
 * */
public class MyException {
	public static void main(String[] args) {
		MyException myException = new MyException();
		myException.openStreamNew();
	}

	/**
	 * 读取和写入文件字节流流的时候的异常处理
	 * */
	private void openStream() {
		File fileIn = new File("./resources/in.txt");
		File fileOut = new File("./resources/out.txt");
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		BufferedInputStream bufferedInputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		byte[] bytes = new byte[1024];
		int len;
		try {
			fileInputStream = new FileInputStream(fileIn);
			fileOutputStream = new FileOutputStream(fileOut);
			bufferedInputStream = new BufferedInputStream(fileInputStream);
			bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
			while ((len = bufferedInputStream.read(bytes)) != -1) {
				/* 注意这里的写入的时候，最好不要使用只有一个 bytes 参数的那个方法，
				因为有可能会多写数据进文件。因为在写最后一次 buffer 的时候 buffer 并没有清空，
				如果数据不是刚好 buffer 的长度那么大的话，上次 buffer 的数据会被写进文件 */
				bufferedOutputStream.write(bytes, 0, len);
			}
			bufferedOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedOutputStream != null) {
				try {
					bufferedOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (bufferedInputStream != null) {
				try {
					bufferedInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * <p>使用 JDK 1.7 提供的 try with resources 方式来读取和写入文件流
	 * <p>从 class 反编译的代码来看， JVM 对 try with resources 的处理方式是
	 * 嵌套 try catch 语句，关闭顺序为①bufferedOutputStream；②bufferedInputStream；
	 * ③fileOutputStream；④fileInputStream
	 * */
	private void openStreamNew() {
		File fileIn = new File("./resources/in.txt");
		File fileOut = new File("./resources/out.txt");
		byte[] bytes = new byte[1024];
		int len;
		try (FileInputStream fileInputStream = new FileInputStream(fileIn);
		     FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
		     BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		     BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {

			while ((len = bufferedInputStream.read(bytes)) != -1) {
				bufferedOutputStream.write(bytes, 0, len);
			}
			bufferedOutputStream.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}