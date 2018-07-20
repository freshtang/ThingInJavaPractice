package com.tjj.chapter10;

/**
 * @description: 18.6 (15)利用DataOutputStream、DataInputStream准确存储和读取各个值
 * @author: tangjunjian
 * @create: 2018-07-13 9:32
 **/

import java.io.*;

public class StoringAndRecoveringData {
    public static void main(String[] args)
            throws IOException {
        DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("Data.txt")));
        out.writeDouble(3.14159);
        out.writeUTF("That was pi");
        out.writeShort(123);
        out.writeChar(66);
        out.writeFloat(3.123f);
        out.writeLong(123333336);
        out.writeByte(66);
        out.writeInt(66);
        out.close();
        DataInputStream in = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream("Data.txt")));
        System.out.println(in.readDouble());
        // Only readUTF() will recover the
        // Java-UTF String properly:
        System.out.println(in.readUTF());
        System.out.println(in.readShort());
        System.out.println(in.readChar());
        System.out.println(in.readFloat());
        System.out.println(in.readLong());
        System.out.println(in.readByte());
        System.out.println(in.readInt());
    }
}
