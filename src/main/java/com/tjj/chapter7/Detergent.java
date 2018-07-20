package com.tjj.chapter7;

/**
 * @description: 7.3（11）为这个类使用代理
 * @author: tangjunjian
 * @create: 2018-07-20 10:31
 **/

import static net.mindview.util.Print.*;

class Cleanser {
    private String s = "Cleanser";
    public void append(String a) { s += a; }
    public void dilute() { append(" dilute()"); }
    public void apply() { append(" apply()"); }
    public void scrub() { append(" scrub()"); }
    public String toString() { return s; }
    public static void main(String[] args) {
        Cleanser x = new Cleanser();
        x.dilute(); x.apply(); x.scrub();
        print(x);
    }
}

public class Detergent extends Cleanser {
    private Cleanser c = new Cleanser();

    // Change a method:
    public void scrub() { c.scrub(); }
    public void dilute() { c.dilute(); }
    public void apply() { c.apply(); }
    public String toString() { return c.toString(); }
    // Add methods to the interface:
    public void foam() { append(" foam()"); }
    // Test the new class:
    public static void main(String[] args) {
        Detergent x = new Detergent();
        x.dilute();
        x.apply();
        x.scrub();
        x.foam();
        print(x);
        print("Testing base class:");
        Cleanser.main(args);
    }
}
