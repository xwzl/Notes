package com.java.base.jdk12;

/**
 * @author xuweizhi
 * @date 2019/03/21 11:28
 */
public class SwitchNewFeaturesTest {


    /**
     * 在下面的代码中，许多break语句使它不必要地冗长，并且这种视觉噪声经常掩盖难以调试的错误，
     * 其中缺失的break语句意味着发生意外的掉落。
     * <p>
     * 我们建议引入一种新形式的开关标签，写成“ case L ->”表示如果标签匹配，则只执行标签右侧的代码。
     */
    public static void main(String[] args) {
        //int k = 1;
        //switch (k) {
        //    case 1 -> System.out.println("one");
        //    case 2 -> System.out.println("two");
        //    case 3 -> System.out.println("many");
        //}

        //T result = switch (1) {
        //    case L1 -> e1;
        //    case L2 -> e2;
        //    default -> e3;
        //};


        //int j = switch (day) {
        //    case MONDAY  -> 0;
        //    case TUESDAY -> 1;
        //    default      -> {
        //        int k = day.toString().length();
        //        int result = f(k);
        //        break result;
        //    }
        //};
    }

}
