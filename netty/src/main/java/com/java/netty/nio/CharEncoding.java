package com.java.netty.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 *
 * The CharEncoding description the translation of Charset
 * ASCII American Standard Code For Information Interchange 美国信息交换标准代码
 * 7 bit来表示一个字符，共计可以表示128种字符。
 *
 * ISO-8859-1
 * 8 bit来表示一个字符，即一个字节，共计可以表示256中字符，ASCII的扩展，向下兼容ASCII；
 * 不会出现字节丢失，ASCII 7bit表示，所以会出现位丢失。问题，不能满足更多的国家。
 *
 * gb2312 中国的标准。两个字节表示一个汉字，没有考虑到生僻字。
 *
 * GBK 对生僻字进行了扩展。
 *
 * gb18030 最完整的汉字编码
 *
 * big5 台湾的字符集
 *
 * Unicode标准，表示世界所有国家的字符编码,本质上是一种编码方式。定长
 *
 * 采用了两个字节表示一个字符，然而存储容量增大，对于欧美用字母或者阿拉伯数字的国家，ASCII完全够用。巨大的空间浪费
 *
 * UTF-8 Unicode Translation Format 本身来说是一种存储格式。Unicode编码方式，UTF存储的方式。UTF-8是unicode实现方式。
 * 1-4个字符流动，变长字节表示形式。
 * UTF-8一般三个字节表示中文。
 * 字节序 BOM(Byte Order Mark) 0xFFFE 0xFEFF 文件开头，大部分是由Windows来引起的，特殊不可见的字符。历史遗留问题，Windows系统。
 *
 *
 *
 * UTF-16 LE: little Endia 0xFFFE  小端：适合机器识别，
 *
 * UTF-16 BE: Big Endia    0xFEFF  大端: 适合人来观看，大数字存在高位。
 *
 * Zero With No-Break Space,文件开头0xFEFF。
 * @author xuweizhi
 * @date 2018/12/18 23:47
 */
public class CharEncoding {

    public static void main(String[] args) throws IOException {

        String inputFile = "netty/src/niofile/InputFile.txt";
        String outputFile = "netty/src/niofile/OutputFile.txt";

        RandomAccessFile in = new RandomAccessFile(inputFile, "r");
        RandomAccessFile out = new RandomAccessFile(outputFile, "rw");

        long length = new File(inputFile).length();

        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();

        MappedByteBuffer data = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, length);

        // 创建一个UTF-8编码的对象
        // ISO-8859-1 一个字节表示一个字符，没有位的丢失。
        Charset charset = Charset.forName("ISO-8859-1");
        // 解码:字节数组转换成字符串,将文件转换成字符串的独享。
        CharsetDecoder decoder = charset.newDecoder();
        // 编码:字符串转换成字节或者字节数组。
        CharsetEncoder encoder = charset.newEncoder();

        // 磁盘存储文件存储的字节表示，每一个文件都有编码，存储字节的信息。
        // 因为编码解码各自执行了一次，所以目标文件中的内容并不会乱码。
        CharBuffer charBuffer = decoder.decode(data);

        System.out.println(charBuffer.get(15));
        //charBuffer 中的数据会乱码
        ByteBuffer outputData = encoder.encode(charBuffer);

        outChannel.write(outputData);

        in.close();
        out.close();

    }

}
