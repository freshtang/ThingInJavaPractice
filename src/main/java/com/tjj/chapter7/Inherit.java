package com.tjj.chapter7;

/**
 * @description: 7.2.1 (5) 创建A、B两个类，C继承A，C内创建B，不要给C构造器，创建C对象观察结果
 * （7） 修改（5） A、B带参数构造器取代默认，给C构造器，执行所有初始化
 * @author: tangjunjian
 * @create: 2018-07-20 10:19
 **/

class A{
    A() {
        System.out.println("default A Constructor");
    }
    A(int a){
        System.out.println("A Constructor" + a);
    }
}

class B{
    B(int b){
        System.out.println("B Constructor" + b);
    }
}

class C extends A{
    C() {
        System.out.println("C Constructor");
    }
    B b = new B(1);
}

public class Inherit {
    public static void main(String[] args) {
        // 结果是调用A的构造器

        C c2 = new C();
    }
}
