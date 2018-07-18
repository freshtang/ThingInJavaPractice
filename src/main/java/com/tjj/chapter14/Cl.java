package com.tjj.chapter14;

public class Cl {
    public static int count;
    public final int id = count++;
    public String toString() {
        return "Cl id : " + id;
    }
}
