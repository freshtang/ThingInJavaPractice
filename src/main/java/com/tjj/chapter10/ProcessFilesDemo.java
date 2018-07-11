package com.tjj.chapter10;

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
