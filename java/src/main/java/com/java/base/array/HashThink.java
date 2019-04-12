package com.java.base.array;

/**
 * @author xuweizhi
 * @date 2019/03/14 13:21
 * @description 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引，如果不存在，则返回-1
 * <p>
 * s = "leetcode";
 * 返回0；
 * <p>
 * s = "loveleetcode";
 * <p>
 * 注意事项：您可以建设该字符串只包含小写字母
 * <p>
 * 这个题背后就隐藏着哈希表这种数据结构的思想
 */
public class HashThink {

    /**
     * 它的本质就是把我们真正关心的那个内容，即字符（ch）转换成一个索引（index），然后直接用一个数组
     * 来存储相应的内容，由于我们数组本身是支持随机访问的，所以我们可以使用O(1)的时间复杂度来完成操作。
     * <p>
     * 在哈希表中，我们是可以存储各种类型的数据，对于每种数据类型，我们都需要一个方法把它转换成索引，相
     * 应的我们关注的这个类型转换成索引的这个函数就称为哈希函数。很多时候哈希函数并不是像上一题那样容易.
     */
    public static int getUniqChar(String str) {
        int[] rep = new int[26];
        for (int i = 0; i < str.length(); i++) {
            rep[str.charAt(i) - 'a']++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (rep[str.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(getUniqChar("abcd"));
    }

    void methodLoad(int x, String y) {
    }

    void methodLoad(String x, int y) {
    }

}
