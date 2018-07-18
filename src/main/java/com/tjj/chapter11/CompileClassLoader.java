package com.tjj.chapter11;

import javax.xml.transform.Source;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class CompileClassLoader extends ClassLoader {
    private byte[] getBytes(String filename) throws IOException {
        File file = new File(filename);
        long len = file.length();
        byte[] raw = new byte[(int)len];
        FileInputStream fin  = new FileInputStream(file);
        int r = fin.read(raw);
        if(r != len) {
            throw new IOException("无法读取全部文件:" + r + "!=" + len);
        } else {
            return raw;
        }
    }

    private boolean compile(String javaFile) throws IOException {
        System.out.println("CompileClassLoader正在编译" + javaFile + "...");
        Process p = Runtime.getRuntime().exec("javac " + javaFile);
        try{
            p.waitFor();
        } catch (InterruptedException ie) {
            System.out.println(ie);
        }
        int ret = p.exitValue();
        return ret == 0;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;
        String fileSub = name.replace(".", "/");
        String javaFileName = fileSub + ".java";
        String classFileName = fileSub + ".class";
        File javaFile = new File(javaFileName);
        File classFile = new File(classFileName);

        // 需要编译java文件为class
        if(javaFile.exists() && !classFile.exists()) {
            try{
                if(!compile(javaFileName) || (!classFile.exists())) {
                    throw new ClassNotFoundException("ClassNotFoundException" + javaFileName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 如果class文件存在，则编译成class类
        if(classFile.exists()) {
            try{
                byte [] raw = getBytes(classFileName);
                clazz = defineClass(name, raw, 0, raw.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(clazz == null) {
            throw new ClassNotFoundException("ClassNotFoundException" + name);
        }
        return clazz;
    }

    public static void main(String[] args) throws Exception{
        if(args.length < 1) {
            System.out.println("缺少目标类，请按如下格式运行Java源文件：");
            System.out.println("java CompileClassLoader ClassName");
        }
        String[] progArgs = new String[args.length - 1];
        System.arraycopy(args, 1, progArgs, 0, progArgs.length);

        CompileClassLoader ccl = new CompileClassLoader();
        Class<?> clazz =  ccl.loadClass(args[0]);

        Method main = clazz.getMethod("main", (new String[0]).getClass());
        Object argsArray[] = {progArgs};
        main.invoke(null, argsArray);
    }
}
