package com.tjj.chapter7;

/**
 * @description: 10.1 （1, 4, 5） 创建并初始化一个内部类
 * @author: tangjunjian
 * @create: 2018-07-20 14:31
 **/

public class Outer {
    private String s = "Outer String";
    class Inter {
        Inter() {
            System.out.println("Inter init");
        }
        public void print() {
            System.out.println(s);
        }
    }

    public Inter getInter() {
        return new Inter();
    }

    public static void main(String[] args) {
        Outer o = new Outer();
//        Outer.Inter i = o.getInter();
        Outer.Inter i = o.new Inter();
        i.print();
    }
}
