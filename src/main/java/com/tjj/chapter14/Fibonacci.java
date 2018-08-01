package com.tjj.chapter14;

/**
 * @description: 21.2 （2）用线程创建大量这种任务并启动它们
 * @author: tangjunjian
 * @create: 2018-07-31 18:03
 **/

import net.mindview.util.*;

class FibonacciThread implements Runnable {
    protected int countDown = 10;
    private static int taskCount = 0;
    private Fibonacci gen = new Fibonacci();
    private final int id = taskCount++;
    public FibonacciThread() {}
    public FibonacciThread(int countDown) {
        this.countDown = countDown;
    }

    public void run() {
        for (int i = 0; i < countDown; i++) {
            System.out.println(gen.next() + " from id:" + id);
            Thread.yield();
        }
    }
}

public class Fibonacci implements Generator<Integer> {
    private int count = 0;
    public Integer next() { return fib(count++); }
    private int fib(int n) {
        if(n < 2) return 1;
        return fib(n-2) + fib(n-1);
    }
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new FibonacciThread()).start();
        }
        System.out.println("wait for FibonacciThread");
    }
}
