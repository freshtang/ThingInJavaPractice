package com.tjj.charpter3;

public class Fibonacci {
    public Fibonacci (int n) {
        int[] buf = new int[n];
        for (int i = 0; i < n; i++) {
            if(i<2) {
                buf[i] = 1;
                System.out.println(buf[i] + "、");
            } else {
                buf[i] = buf[i-1] + buf[i-2];
                System.out.println(buf[i] + "、");
            }
        }
    }

    public static void main(String[] args) {
        new Fibonacci(5);
    }
}
