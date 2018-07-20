package com.tjj.chapter10;

/**
 * @description: 18.1 (5、3) 用正则表达式代替固定扩展名
 * @author: tangjunjian
 * @create: 2018-07-10 14:34
 **/

import net.mindview.util.Directory;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Pattern;

public class ProcessFiles {
    private net.mindview.util.ProcessFiles.Strategy strategy;
    private Pattern ext;

    public ProcessFiles(net.mindview.util.ProcessFiles.Strategy strategy, String extRegex) {
        this.ext = Pattern.compile(extRegex);
        this.strategy = strategy;
    }

    public void start(String[] args) {
        try {
            if (args.length == 0) {
                this.processDirectoryTree(new File("."));
            } else {
                String[] var5 = args;
                int var4 = args.length;

                for(int var3 = 0; var3 < var4; ++var3) {
                    String arg = var5[var3];
                    File fileArg = new File(arg);
                    if (fileArg.isDirectory()) {
                        this.processDirectoryTree(fileArg);
                    } else {
                        if (!arg.endsWith("." + this.ext)) {
                            arg = arg + "." + this.ext;
                        }

                        this.strategy.process((new File(arg)).getCanonicalFile());
                    }
                }
            }

        } catch (IOException var7) {
            throw new RuntimeException(var7);
        }
    }

    public void processDirectoryTree(File root) throws IOException {
        Iterator var3 = Directory.walk(root.getAbsolutePath(), ".*\\." + this.ext).iterator();

        while(var3.hasNext()) {
            File file = (File)var3.next();
            this.strategy.process(file.getCanonicalFile());
        }

    }

    public static void main(String[] args) {
        (new net.mindview.util.ProcessFiles(new net.mindview.util.ProcessFiles.Strategy() {
            public void process(File file) {
                System.out.println(file);
            }
        }, "(t|o).*")).start(args);
    }

    public interface Strategy {
        void process(File var1);
    }
}

