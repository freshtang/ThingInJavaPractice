package com.tjj.chapter7;

/**
 * @description: 创建StartShip类，包含一个AlertStatus引用，可以指示三种不同状态
 * @author: tangjunjian
 * @create: 2018-07-20 12:39
 **/

import static net.mindview.util.Print.*;

class Status {
    public void act() {}
}

class Stop extends Status {
    public void act() { print("StopStatus"); }
}

class Run extends Status {
    public void act() { print("RunStatus"); }
}

class Pause extends Status {
    public void act() { print("PauseStatus"); }
}

class Engine {
    private Status status = new Stop();
    public void run() { status = new Run(); }
    public void pause() { status = new Pause(); }
    public void stop() { status = new Stop(); }
    public void performPlay() { status.act(); }
}

public class StarShip {
    public static void main(String[] args) {
        Engine engine = new Engine();
        engine.performPlay();
        engine.run();
        engine.performPlay();
        engine.pause();
        engine.performPlay();
        engine.stop();
        engine.performPlay();
    }
}
