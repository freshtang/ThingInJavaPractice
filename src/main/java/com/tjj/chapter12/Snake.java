package com.tjj.chapter12;

public class Snake implements Cloneable {
    private Snake next;
    private char c;
    // Value of i == number of segments
    Snake(int i, char x) {
        c = x;
        if(--i > 0)
            next = new Snake(i, (char)(x + 1));
    }
    void increment() {
        c++;
        if(next != null)
            next.increment();
    }
    public String toString() {
        String s = ":" + c;
        if(next != null)
            s += next.toString();
        return s;
    }
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {}
        return o;
    }
    public void setNext(Snake s) {
        this.next = s;
    }
    public Snake deepClone() {
        Snake s = (Snake)this.clone();
        if(this.next!= null) {
            s.setNext(this.next.deepClone());
        }
        return s;
    }
    public static void main(String[] args) {
        Snake s = new Snake(5, 'a');
        System.out.println("s = " + s);
        Snake s2 = s.deepClone();
        System.out.println("s2 = " + s2);
        s.increment();
        System.out.println("after s.increment s = " + s);
        System.out.println(
                "after s.increment, s2 = " + s2);
    }
}
