package com.ahao.java.io;

import java.io.*;

/**
 * IO 相关的一些功能测试
 * <p>文件流的关闭在 finally 语句块里面，因为如果在 try 块里面关闭的话，如果出异常那么文件流关不掉
 * */
public class IOTest {
    public static void main(String[] args) {
        IOTest ioTest = new IOTest();
        ioTest.bufferedReaderAndWriter();
    }

    /**
     * 带缓冲的 Reader 和 Writer的 io 操作
     * */
    private void bufferedReaderAndWriter() {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileReader = new FileReader("./resources/in.txt");
            bufferedReader = new BufferedReader(fileReader);
            fileWriter = new FileWriter("./resources/out.txt");
            bufferedWriter = new BufferedWriter(fileWriter);
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                bufferedWriter.write(s);
                // 使用这行来进行换行操作，一般不使用"\r\n"这种硬编码操作，因为不同系统的换行符不一样
                bufferedWriter.newLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fileReader) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fileWriter) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Reader 和 Writer 的 io 操作
     * */
    private void readerAndWriter() {
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader("./resources/in.txt");
            fileWriter = new FileWriter("./resources/out.txt");
            char[] chars = new char[1024];
            int charLen;
            while ((charLen = fileReader.read(chars)) != -1) {
                fileWriter.write(chars, 0, charLen);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fileReader) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fileWriter) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
