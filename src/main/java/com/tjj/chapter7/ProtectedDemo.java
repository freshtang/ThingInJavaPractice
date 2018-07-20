package com.tjj.chapter7;


/**
 * @description: 7.6 (15) 直接在类的内部调用基类的protected方法
 * @author: tangjunjian
 * @create: 2018-07-20 11:05
 **/

class ProtectBase{
    protected void Print() {
        System.out.println("print from ProtectBase ");
    }
}

public class ProtectedDemo extends ProtectBase {
    public void CallPrint() {
        Print();
    }

    public static void main(String[] args) {
        ProtectedDemo pd = new ProtectedDemo();
        pd.CallPrint();
    }
}
