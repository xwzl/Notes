package com.java.thread;

import java.util.*;

/**
 * 关系图
 *
 * @author xuweizhi
 * @since 2019-08-15
 */
public class RelationClass {

    public static Map<Integer, String> upTwo = new HashMap<>();

    public static Map<Integer, String> upOne = new HashMap<>();

    public static Map<Integer, String> equalMe = new HashMap<>();

    public static Map<Integer, String> downOne = new HashMap<>();

    public static Map<Integer, String> downTwo = new HashMap<>();

    public static Map<Integer, Map<Integer, String>> familyRelationShip = new HashMap<>();

    static {

        upTwo.put(0, "爷爷");
        upTwo.put(1, "奶奶");

        upOne.put(0, "爸爸");
        upOne.put(1, "妈妈");


        equalMe.put(0, "姐姐");
        equalMe.put(1, "妹妹");


        downOne.put(0, "儿子");
        downOne.put(0, "妹妹");

        downTwo.put(0, "孙子");
        downTwo.put(1, "孙女");

        familyRelationShip.put(0, upTwo);
        familyRelationShip.put(1, upOne);
        familyRelationShip.put(2, equalMe);
        familyRelationShip.put(3, downOne);
        familyRelationShip.put(4, downTwo);
    }

    public static void main(String[] args) {

        // 家庭关系只有三代，不考虑四代
        Map<Integer, List<String>> family = new HashMap<>();

        family.put(0, new ArrayList<>(Arrays.asList("0#0", "0#1")));

        family.put(1, new ArrayList<>(Arrays.asList("1#0", "1#1")));

        // 同代关系处理
        family.put(2, new ArrayList<>(Arrays.asList("2#0", "2#1")));

        List<String> family1 = new ArrayList<>();

        /**
         * 第一个表示监护人添加的代数，第二个表示数据第几代，第三个表示关系
         */
        family1.add("1#0#0");
        family1.add("1#0#1");
        family1.add("0#1#0");
        family1.add("0#1#1");
        family1.add("-1#2#1");
        family1.add("-1#2#0");

        System.out.println(printFamilyRelationShip(family1, 1, 0));

    }


    /**
     * @param family    家庭列表
     * @param role      角色
     * @param generator 代数
     */
    static String printFamilyRelationShip(/*Map<Integer, List<String>> family,*/  List<String> family, Integer role, Integer generator) {
        String result = "";
        switch (generator) {
            case 0:
                for (int i = 0; i < family.size(); i++) {
                    String[] split = family.get(i).split("#");
                    if (split[1].equals("2")&&split[2].equals("1")) {
                        result += " 我";
                    } else {
                        result += " "+familyRelationShip.get(Integer.parseInt(split[1])).get(Integer.parseInt(split[2]));
                    }


                }
                break;
            case 1:

                break;
            case 2:

                break;
            default:
        }
        return result;
    }


}
