package com.tjj.chapter7;

/**
 * @description: 7.4 （13）创建一个类有三个重载方法，继承产生新类，有一个重载，三个方法都可以用
 * @author: tangjunjian
 * @create: 2018-07-20 10:51
 **/



public class OverLoadDemo {
    static class A{
        int method(int i) {
            System.out.println("method(int) from A");
            return i;
        }
        char method(char i) {
            System.out.println("method(char) from A");
            return i;
        }
        String method(String i) {
            System.out.println("method(String) from A");
            return i;
        }
    }
    static class B extends A{
        float method(float f) {
            System.out.println("method(float) from B");
            return f;
        }
    }

    public static void main(String[] args) {
        B b = new B();
        b.method(new Float(2.1));
        b.method(2);
        b.method(new Character('c'));
        b.method("sadasd");
    }
}
