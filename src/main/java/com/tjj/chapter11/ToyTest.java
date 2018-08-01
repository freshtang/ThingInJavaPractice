package com.tjj.chapter11;

/**
 * @description: 14.2 （1、2） 14.6 (19)
 * @author: tangjunjian
 * @create: 2018-07-18 17:38
 **/

import static net.mindview.util.Print.*;

interface HasBatteries {}
interface Waterproof {}
interface Shoots {}

class Toy {
    // Comment out the following default constructor
    // to see NoSuchMethodError from (*1*)
    Toy() {}
    Toy(int i) {}
}

class FancyToy extends Toy
        implements HasBatteries, Waterproof, Shoots {
    FancyToy() { super(1); }
}

public class ToyTest {
    static void printInfo(Class cc) {
        print("Class name: " + cc.getName() +
                " is interface? [" + cc.isInterface() + "]");
        print("Simple name: " + cc.getSimpleName());
        print("Canonical name : " + cc.getCanonicalName());
    }
    public static void main(String[] args) {
        Class c = null;
        try {
            c = Class.forName("com.tjj.chapter11.FancyToy");
            Object toy = c.newInstance();
        } catch(ClassNotFoundException e) {
            print("Can't find FancyToy");
            System.exit(1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch(InstantiationException e) {
            print("Cannot instantiate");
            System.exit(1);
        }
        printInfo(c);
        for(Class face : c.getInterfaces()) {
            printInfo(face);
        }
        Class up = c.getSuperclass();
        Object obj = null;
        try {
            // Requires default constructor:
            obj = up.newInstance();
        } catch(InstantiationException e) {
            print("Cannot instantiate");
            System.exit(1);
        } catch(IllegalAccessException e) {
            print("Cannot access");
            System.exit(1);
        }
        printInfo(obj.getClass());
    }
}
