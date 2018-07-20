package com.tjj.chapter10;

/**
 * @description: 18.7 (20)统计字节出现的次数
 * @author: tangjunjian
 * @create: 2018-07-13 17:12
 **/

import net.mindview.util.BinaryFile;
import net.mindview.util.Directory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class VerificateStart {

    public static boolean Verificate(File file) {
        byte[] bytes = {112, 97, 99 , 107};
        BinaryFile bf = new BinaryFile();
        try {
            byte[] b = bf.read(file);
            for (int i = 0; i < bytes.length; i++) {
                if(bytes[i] != b[i]) return false;
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        List<File> files = Directory.walk("./src/main/java/com/tjj/chapter7").files;
        for (int i = 0; i < files.size(); i++) {
            String is = Verificate(files.get(i))?"是":"不是";
            System.out.printf( files.get(i).getName() + " " + is + "以十六进制CAFEBABE开头 \n");
        }
    }
}
