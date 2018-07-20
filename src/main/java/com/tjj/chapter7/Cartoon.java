package com.tjj.chapter7;

/**
 * @description: 7.2.3 (3) 证明基类会被调用，而且在类构造器前被调用
 * @author: tangjunjian
 * @create: 2018-07-20 10:06
 **/

class Art{
    Art() {
        System.out.println("Art Constructor");
    }
}

class Drawing extends Art{
    Drawing() {
        System.out.println("Drawing Constructor");
    }
}

public class Cartoon extends Drawing {

    Cartoon() {
        System.out.println("Cartoon Constructor");
    }

    public static void main(String[] args) {
        // 证明基类会被调用，而且在类构造器前被调用
        System.out.println(new Cartoon() + "Class out");


    }
}
