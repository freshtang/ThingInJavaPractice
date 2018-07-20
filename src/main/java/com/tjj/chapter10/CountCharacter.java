package com.tjj.chapter10;

/**
 * @description: 18.6 (17)统计字母出现的次数
 * @author: tangjunjian
 * @create: 2018-07-13 16:44
 **/

import net.mindview.util.TextFile;
import net.mindview.util.TextFile.*;

import java.util.HashMap;
import java.util.Map;

public class CountCharacter {
    public static void main(String[] args) {
        TextFile text = new TextFile("test.txt");
        String in = text.read("test.txt");

        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (Character c : map.keySet()) {
            Integer count = map.get(c);//得到每个key多对用value的值
            System.out.println(c + " " + count);
        }
    }
}
