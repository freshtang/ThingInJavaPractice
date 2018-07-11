package com.tjj.chapter10;


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
    }
}
