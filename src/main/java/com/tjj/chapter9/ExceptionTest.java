package com.tjj.chapter9;

public class ExceptionTest {
    public static void main(String[] args) {
        try{
            Object o = null;
            o.getClass();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            System.out.println("run in finally");
        }
    }
}
