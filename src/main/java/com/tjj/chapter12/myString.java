package com.tjj.chapter12;

public class myString implements Cloneable {
    private String string;
    myString(String s) {this.string = s;}
    public String toString() { return string;}
    public void concatenate(String s) { this.string = this.string.concat(s); }
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("myString can't clone");
        }
        return o;
    }
    public static void testOne(myString x) {
        System.out.println("myString:" + x.toString());
        x.concatenate("test");
        System.out.println("After concatenating myString:" + x.toString());
    }

    public static void testTwo(myString x) {
        System.out.println("myString:" + x.toString());
        myString newX = (myString)x.clone();
        newX.concatenate("test");
        System.out.println("After concatenating myString:" + x.toString());
    }

    public static void main(String[] args) {
        myString ms = new myString("mystring 1");
        myString ms2 = new myString("mystring 2");
        myString newS = new myString("new mystring");
        ms.testOne(newS);
        ms2.testTwo(newS);
    }
}
