package com.java.base.regex;

import org.junit.Test;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 *
 * @author xuweizhi
 * @date 2019/04/12 22:11
 */
public class MyRegex {

    /**
     * 最后附一下用到的零宽断言
     * <p>
     * 宽断言
     * (?=exp)	匹配exp前面的位置
     * (?<=exp)	匹配exp后面的位置
     * (?!exp)	匹配后面跟的不是exp的位置
     * (?<!exp)	匹配前面不是exp的位置
     */
    public static void main(String[] args) {
        //MyRegex myRegex = new MyRegex();
        //myRegex.test2();
        String value = "insert into user (u_id,address,role) values (null,#{address},#{role})";
        //String regex = "#\{\\W+\}";
        Pattern pattern = Pattern.compile("(?<=\\()(.+?)(?=\\))");
        Matcher match2 = pattern.matcher(value);
        while (match2.find()) {
            System.out.println(match2.group());
        }
        Pattern p = Pattern.compile("\\#\\{(.+?)\\}");
        Matcher match1 = pattern.matcher(value);
        while (match1.find()) {
            System.out.println(match1.group());
        }

    }

    void define() {
        //字符
        //    x 字符 x。举例：'a'表示字符a
        //    \\ 反斜线字符。
        //    \n 新行（换行）符 'u000A'
        //    \r 回车符 'u000D'
        //
        //字符类
        //    [abc] a、b 或 c（简单类）
        //    [^abc] 任何字符，除了 a、b 或 c（否定）
        //    [a-zA-Z] a到 z 或 A到 Z，两头的字母包括在内（范围）
        //    [0-9] 0到9的字符都包括
        //
        //预定义字符类
        //    . 任何字符。我的就是.字符本身，怎么表示呢? \.
        //    \d 数字：[0-9]
        //    \D 非数字:[^\d]/[^0-9]
        //    \w 单词字符：[a-zA-Z_0-9]
        //　　 \W 非字符[^\w]
        //
        //边界匹配器
        //    ^ 行的开头
        //    $ 行的结尾
        //    \b 单词边界， 就是不是单词字符的地方。
        //
        //Greedy 数量词
        //    X? X，一次或一次也没有
        //    X* X，零次或多次
        //    X+ X，一次或多次
        //    X{n} X，恰好 n 次
        //    X{n,} X，至少 n 次
        //    X{n,m} X，至少 n 次，但是不超过 m 次
        //　运算符
        //　　XY 　　	X后跟 Y
        //　　X|Y 　　X 或 Y
        //　　(X) 　　X，作为捕获组
    }

    @Test
    void test21() {
        String str = "efe12dfa213fdsfa23434gvs34123";
    }

    void test1() {
        //以空格分割
        String str1 = "1 2 3          4 54       5 6";
        String[] numbers = str1.split(" +");
        for (String temp : numbers) {
            System.out.println(temp);
        }

        // 替换，替换所有的数字为*
        String str2 = "abd123:adad46587:asdadasadsfgi#%^^9090";
        System.out.println(str2.replaceAll("[0-9]", "*"));
        System.out.println(str2.replaceAll("\\d", "*"));

        // 匹配匹配邮箱
        String mail1 = "ababc@asa.com";
        String mail2 = "ababc@asa.com.cn";
        String mail3 = "ababc@asa";
        //        String mainRegex = "[0-9a-zA-Z_]+@[0-9a-zA-Z_]++(\\.[0-9a-zA-Z_]+{2,4})+";
        String mainRegex = "\\w+@\\w+(\\.\\w{2,4})+";

        System.out.println(mail1.matches(mainRegex));//true
        System.out.println(mail2.matches(mainRegex));//true
        System.out.println(mail3.matches(mainRegex));//false
    }

    /**
     * java中正则匹配的对象：
     * pattern:
     * 　　	Pattern 　　Pattern.complie(regexString)
     * 　　	Macther 　　Pattern.matches(regexString)
     * Matcher：
     * 　　	boolean 　  matcher.find() //查找下一个匹配对象
     * 　　	String 　　 matcher.guorp() //返回整个匹配模式匹配到的结果
     * 　　	boolean 　  matcher.matches() //尝试将整个区域与模式匹配
     * 　　 int 　　　　	matcher.groupCount() //返回匹配规则的分组，如：(aa)(bb)：这表示两组
     * 　　	String        matcher.group(int group)	//返回匹配对象对应分组的匹配结果
     * 　　	MatcheResult  matcher.toMatchResult()	//将匹配结果一MatchResult的形式返回
     * 第一种使用场景：仅仅使用Matcher对象来匹配想要的字符串
     */
    void test2() {
        // 匹配出3个字符的字符串
        String str = "abc   ewqeq qeqe   qeqe   qeqe  aaaa  fs fsdfs d    sf sf sf  sf sfada dss dee ad a f s f sa a'lfsd;'l";
        Pattern pt = Pattern.compile("\\b\\w{3}\\b");
        Matcher match = pt.matcher(str);
        while (match.find()) {
            System.out.println(match.group());
        }
        // 匹配出邮箱地址
        String str2 = "dadaadad   da da   dasK[PWEOO-123- DASJAD@DHSJK.COM DADA@DAD.CN =0KFPOS9IR23J0IS ADHAJ@565@ADA.COM.CN shuqi@162.com UFSFJSFI-SI- ";
        Pattern pet2 = Pattern.compile("\\b\\w+@\\w+(\\.\\w{2,4})+\\b");
        Matcher match2 = pet2.matcher(str2);
        while (match2.find()) {
            System.out.println(match2.group());
        }
    }

    /**
     * 第二种使用场景：匹配规则中包含匹配组，要求匹配得到相应的匹配组的数据：
     */
    void test3() {
        String sr = "dada ada adad adsda ad asdda adr3 fas daf fas fdsf 234 adda";
        //包含两个匹配组，一个是三个字符，一个是匹配四个字符
        Pattern pet = Pattern.compile("\\b(\\w{3}) *(\\w{4})\\b");
        Matcher match = pet.matcher(sr);
        int countAll = match.groupCount();//2
        while (match.find()) {
            System.out.print("匹配组结果：");
            for (int i = 0; i < countAll; i++) {
                System.out.print(String.format("\n\t第%s组的结果是:%s", i + 1, match.group(i + 1)));
            }
            System.out.print("\n匹配的整个结果:");
            System.out.println(match.group());
        }
    }

    /**
     * 第三中场景:将每次得到的结果使用MatcheResult保存
     */
    void test4() {
        String sr = "dada ada adad adsda ad asdda adr3 fas daf fas fdsf 234 adda";
        Pattern pet = Pattern.compile("\\b(\\w{3}) *(\\w{4})\\b");
        Matcher match = pet.matcher(sr);
        MatchResult ms = null;
        while (match.find()) {
            ms = match.toMatchResult();
            System.out.print("匹配对象的组结果：");
            for (int i = 0; i < ms.groupCount(); i++) {
                System.out.print(String.format("\n\t第%s组的结果是:%s", i + 1, ms.group(i + 1)));
            }
            System.out.print("\n匹配的整个结果:");
            System.out.println(ms.group());
        }
    }
}
