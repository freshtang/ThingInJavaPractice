package com.tjj.chapter11;

public class Hello {
    public static void main(String[] args) {
        for(String s: args) {
            System.out.println("程序运行参数：" + s);
        }
    }
}
