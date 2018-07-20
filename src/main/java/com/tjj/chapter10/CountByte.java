package com.tjj.chapter10;

/**
 * @description: 18.7 (19)统计字节出现的次数
 * @author: tangjunjian
 * @create: 2018-07-13 16:54
 **/

import net.mindview.util.BinaryFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CountByte {
    public static void main(String[] args) {
        BinaryFile bf = new BinaryFile();
        try {
            byte[] b = bf.read("test.txt");
            Map<Byte, Integer> map = new HashMap<Byte, Integer>();
            for (int i = 0; i < b.length; i++) {
                byte c = b[i];
                if(map.containsKey(c)){
                    map.put(c, map.get(c) + 1);
                } else {
                    map.put(c, 1);
                }
            }

            for (Byte by : map.keySet()) {
                Integer count = map.get(by);//得到每个key多对用value的值
                System.out.println(by + " " + count);
            }
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }
}
