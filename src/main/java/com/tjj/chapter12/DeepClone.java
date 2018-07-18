package com.tjj.chapter12;

import java.util.Random;
import java.util.Vector;

class Battery implements Cloneable  {
    private int num;
    Battery(int n) {this.num = n;}
    public String toString() {return "" + this.num;}
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Battery can't clone");
        }
        return o;
    }
}

class Toy {
    Vector<Battery> baVector = new Vector<Battery>();

    Toy() {
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            this.baVector.addElement(new Battery(r.nextInt(10)));
        }
    }

    Toy(Vector<Battery> v) {
        this.baVector = v;
    }

    public Toy clone() {
        Vector<Battery> newV = new Vector<Battery>();
        for (int i = 0; i < this.baVector.size(); i++) {
            newV.addElement((Battery)this.baVector.get(i).clone());
        }
        return new Toy(newV);
    }

    public String toString() {
        String s = new String();
        for (int i = 0; i < this.baVector.size(); i++) {
            s = s.concat(baVector.get(i).toString() + "、");
        }
        return s;
    }
}

public class DeepClone {
    public static void main(String[] args) {
        Toy t = new Toy();
        System.out.println(t.toString());
        System.out.println(t.clone().toString());
    }
}
