package com.tjj.chapter10;

import com.bruceeckel.tools.P;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class DirList {
    public static void main(final String[] args) {
        File path = new File(".");
        String [] list;
        if(args.length == 0) {
            list = path.list();
        } else {
            list = path.list(new FilenameFilter() {
                private Pattern patten = Pattern.compile(args[0]);
                public boolean accept(File dir, String name) {
                    return patten.matcher(name).matches();
                }
            });
        }
        long lengthSum = 0;
        for(String dirItem: list) {
            File f= new File(dirItem);
            lengthSum += f.length();
            System.out.println(dirItem);
            System.out.println("大小为：" + f.length());
        }
        System.out.println("文件的总和是：" + lengthSum + "B");
    }
}
