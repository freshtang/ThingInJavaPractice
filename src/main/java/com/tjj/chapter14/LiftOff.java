package com.tjj.chapter14;

import java.util.concurrent.Callable;

public class LiftOff implements Callable<String> {
    protected int countDown = 10; // Default
    private static int taskCount = 0;
    private final int id = taskCount++;
    public LiftOff() {}
    public LiftOff(int countDown) {
        this.countDown = countDown;
    }
    public String call() {
        return "call" + id;
    }
}
