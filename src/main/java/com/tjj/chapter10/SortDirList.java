package com.tjj.chapter10;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class SortDirList {
    public String[] list() {
        File path = new File(".");
        return path.list();
    }

    public String[] list(final String pattern) {
        File path = new File(".");
        return path.list(new FilenameFilter() {
            private Pattern r = Pattern.compile(pattern);
            public boolean accept(File dir, String name) {
                return r.matcher(name).matches();
            }
        });
    }

    public static void main(String[] args) {
        SortDirList sl = new SortDirList();
        String[] list = sl.list();
        System.out.println("list without params: ");
        for(String dirItem: list) {
            System.out.println(dirItem);
        }


        list = sl.list("src");
        System.out.println("list with pattern src: ");
        for(String dirItem: list) {
            System.out.println(dirItem);
        }
    }
}
