package com.java.base.url;


import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FilePath {

    public static void main(String[] args) throws IOException {

        File file = new File(FilePath.class.getResource("/").getPath());
        File file1 = new File(FilePath.class.getResource("").getPath());
        System.out.println(file);
        System.out.println(file1);

        //参数为空
        File directory = new File("");
        String courseFile = directory.getCanonicalPath();
        System.out.println("当前工程路径：" + courseFile);

        FilePath filePath = new FilePath();

        System.out.println(filePath.getSrcFilePath("io/f.text"));

        File file2 = new File("src/f.text");
        System.out.println(file2.getPath());
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("java.class.path"));

        File file3 = new File("ioa");
        if (!file3.exists()) {
            file3.mkdirs();
        }

    }

    public URL getSrcFilePath(String fileName) {
        return this.getClass().getClassLoader().getResource(fileName);
    }
}
