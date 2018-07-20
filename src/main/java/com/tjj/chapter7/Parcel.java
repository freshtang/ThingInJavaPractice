package com.tjj.chapter7;

/**
 * @description: 10.5 (9、10、11、13)在接口里定义一个内部类，在作用域里面也定义一个
 * @author: tangjunjian
 * @create: 2018-07-20 14:44
 **/


interface InterFace {
    String readString();
}

public class Parcel {

    private class PrivateInnerClass implements InterFace {
        private String s = "PrivateInnerClass's String";
        public String readString() {
            return s;
        }
    }

    public InterFace returnInterFace() {
        return new InterFace() {
            private String s = "PInterFace's String";
            public String readString() {
                return s;
            }
        };
    }

    public InterFace getPrivateInnerClass() {
        return new PrivateInnerClass();
    }

    public static void main(String[] args) {
        Parcel p = new Parcel();
        InterFace i = p.returnInterFace();
        System.out.println(i.readString());

        InterFace pi = p.getPrivateInnerClass();
        System.out.println(pi.readString());
    }
}
