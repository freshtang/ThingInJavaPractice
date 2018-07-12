package com.tjj.chapter11;

import java.lang.reflect.Field;
import java.util.*;

abstract class Shape {
    void draw() { System.out.println(this + ".draw()"); }
    abstract public String toString();
}

class Circle extends Shape {
    public String a = "string";
    public String toString() { return "Circle"; }
}

class Square extends Shape {
    public String toString() { return "Square"; }
}

class Triangle extends Shape {
    public String toString() { return "Triangle"; }
}

public class Shapes {
    public static void rotate(Shape shape) {
        if(shape.getClass().getCanonicalName() == "com.tjj.chapter11.Circle") {
            System.out.println(" run rotate");
        }
    }
    public static void getAllClass(Object o) {
        Class c = o.getClass();
        while(!c.getName().equals("java.lang.Object")) {
            System.out.println(c.getName());
            c = c.getSuperclass();
        }
    }
    public static void getFields(Object c){
        Field[] fields =  c.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getName());
        }
    }
    public static void main(String[] args) {
        List<Shape> shapeList = Arrays.asList(
                new Circle(), new Square(), new Triangle()
        );
        for(Shape shape : shapeList)
        {
            shape.draw();
            rotate(shape);
            getAllClass(shape);
            getFields(shape);
        }
    }
}

