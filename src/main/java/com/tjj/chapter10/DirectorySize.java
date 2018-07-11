package com.tjj.chapter10;

import net.mindview.util.*;

import java.io.File;

import static net.mindview.util.PPrint.*;
import static net.mindview.util.Print.print;

public class DirectorySize {
    public static void main(String[] args) {
        // all Directory
//        PPrint.pprint(Directory.walk(".").dirs);
        long sizeSum = 0;
        for(File file: Directory.local(".", "(Da|IO).*")) {
            print(file);
            sizeSum += file.length();
        }
        System.out.println("文件的总大小为：" + sizeSum + "B");
    }
}
