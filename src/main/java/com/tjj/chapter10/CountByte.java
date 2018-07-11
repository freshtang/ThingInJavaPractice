package com.tjj.chapter10;

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
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }
}
