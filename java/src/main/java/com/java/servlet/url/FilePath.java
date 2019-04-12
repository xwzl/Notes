package com.java.servlet.url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author xuweizhi
 * @date 2019/04/11 17:17
 */
public class FilePath {

    public static void main(String[] args) throws IOException {
        // classpath目录下
        URL resource = FilePath.class.getResource("/");
        // classpath+该类所在路径
        URL resource1 = FilePath.class.getResource("");
        // classpath目录下
        URL resource2 = FilePath.class.getClassLoader().getResource("");
        // null
        URL resource3 = FilePath.class.getClassLoader().getResource("/");
        //file:/D:/sourceCode/demo4/target/classes/
        System.out.println(resource);
        //file:/D:/sourceCode/demo4/target/classes/com/example/demo4/asm/
        System.out.println(resource1);
        //file:/D:/sourceCode/demo4/target/classes/
        System.out.println(resource2);
        //null
        System.out.println(resource3);

        // classpath下code.txt
        InputStream asStream = FilePath.class.getClassLoader().getResourceAsStream("application.yml");
        System.out.println(asStream);

        // 加 / 表示获取classpath下code.txt,不加/返回为null
        InputStream asStream1 = FilePath.class.getResourceAsStream("/application.yml");
        System.out.println(asStream1);

        // 获取classpath下code.txt文件
        //InputStream asStream2 = FilePath.class.getClassLoader().getSystemResourceAsStream("code.txt");
        //System.out.println(asStream2);
        //System.out.println("--------------------------------------");

        // 使用工具类ResourceUtils获取classpath下文件
        //File file1 = ResourceUtils.getFile("classpath:code.txt");
        //FileInputStream is = new FileInputStream(file1);
        //System.out.println(is);
        //System.out.println("---------使用URL对象打开文件流,例子如下---------");
        //InputStream inputStream = FilePath.class.getResource("/code.txt").openStream();
        //FileOutputStream fos = new FileOutputStream("C:\\Users\\18030501\\Desktop\\aaa.txt");
        //byte[] bys = new byte[1024];
        //int len;
        //while ((len = inputStream.read(bys)) != -1) {
        //    fos.write(bys, 0, len);
        //    fos.flush();
        //}
        //inputStream.close();
        //fos.close();

    }
}
