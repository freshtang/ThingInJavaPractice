package com.tjj.chapter10;

/**
 * @description: 利用ProcessFiles来获取修改日期
 * @author: tangjunjian
 * @create: 2018-07-12 15:44
 **/

import net.mindview.util.ProcessFiles;

import java.io.File;

public class ProcessFilesDemo {

    public static void main(String[] args) {
        (new ProcessFiles(new ProcessFiles.Strategy() {
            public void process(File file) {
                System.out.println(file + "  \n lastModified: " + file.lastModified());
            }
        }, "java")).start(args);
    }
}
