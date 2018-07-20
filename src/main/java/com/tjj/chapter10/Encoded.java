package com.tjj.chapter10;

import java.io.*;

/**
 * @description: 用来测试不同编码存取
 * @author: tangjunjian
 * @create: 2018-07-20 17:38
 **/

public class Encoded {
    public static void main(String[] args) throws IOException {
        String file = "Data.txt";
        String charset = "UTF-8";
        // 写字符换转成字节流
        FileOutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, charset);
        try {
            writer.write("这是要保存的中文字符");
        } finally {
            writer.close();
        }

        System.out.println("finish writting");

        // 读取字节转换成字符
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream, charset);
        BufferedReader bf = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer();
        String s;
        StringBuilder sb = new StringBuilder();
        try {
            while((s = bf.readLine())!= null)
                sb.append(s + "\n");
            bf.close();
            System.out.println(sb.toString());
        } finally {
            reader.close();
        }
    }

}
