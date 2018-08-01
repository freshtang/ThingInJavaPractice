package com.tjj.chapter14;

/**
 * @description: 21.5 （24、25、）观察chef在shutdownNow后从run中ruturn
 * Chef interrupted变成WaitPerson interrupted，chef在return后被WaitPerson调用不了输出WaitPerson interrupted
 * 26 添加一个Busboy类，waitPerson上菜后通知清理
 * @author: tangjunjian
 * @create: 2018-08-01 09:26
 **/

import java.util.concurrent.*;
import static net.mindview.util.Print.*;

class Meal {
    private final int orderNum;
    public Meal(int orderNum) { this.orderNum = orderNum; }
    public String toString() { return "Meal " + orderNum; }
}

class Desk {
    private final int orderNum;
    public Desk(int orderNum) { this.orderNum = orderNum; }
    public String toString() { return "Desk " + orderNum; }
}

class BusBoy implements  Runnable {
    private Restaurant restaurant;
    public BusBoy(Restaurant r) {restaurant = r;}
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    while(restaurant.desk == null) {
                        // ... for the waitPerson to call
                        wait();
                    }
                }
                print("BusBoy clean for");
                synchronized(this) {
                    restaurant.desk = null;
                    TimeUnit.MILLISECONDS.sleep(1);
                }
            }
        } catch(InterruptedException e) {
            print("BusBoy interrupted");
        }
    }
}

class WaitPerson implements Runnable {
    private Restaurant restaurant;
    public WaitPerson(Restaurant r) { restaurant = r; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    while(restaurant.meal == null)
                        wait(); // ... for the chef to produce a meal
                }
                print("Waitperson got " + restaurant.meal);
                synchronized(restaurant.busBoy) {
                    restaurant.desk = new Desk(1);
                    restaurant.busBoy.notifyAll();
                }
                synchronized(restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll(); // Ready for another
                }
            }
        } catch(InterruptedException e) {
            print("WaitPerson interrupted");
        }
    }
}

class Chef implements Runnable {
    private Restaurant restaurant;
    private int count = 0;
    public Chef(Restaurant r) { restaurant = r; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    while(restaurant.meal != null)
                        wait(); // ... for the meal to be taken
                }
                if(++count == 10) {
                    print("Out of food, closing");
                    restaurant.exec.shutdownNow();
                    return;
                }
                printnb("Order up! ");
                synchronized(restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch(InterruptedException e) {
            print("Chef interrupted");
        }
    }
}

public class Restaurant {
    Desk desk;
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson waitPerson = new WaitPerson(this);
    BusBoy busBoy = new BusBoy(this);
    Chef chef = new Chef(this);
    public Restaurant() {
        exec.execute(chef);
        exec.execute(busBoy);
        exec.execute(waitPerson);
    }
    public static void main(String[] args) {
        new Restaurant();
    }
}
