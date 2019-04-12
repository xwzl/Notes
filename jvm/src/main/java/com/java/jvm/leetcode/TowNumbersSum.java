package com.java.jvm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuweizhi
 * @date 2019/03/12 15:55
 * @description 计算两数之和
 * <p>
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class TowNumbersSum {

    public static void main(String[] args) {
        int[] ints = {3, 6, 8, 17, 18, 23};
        System.out.println(twoSum(ints, 20)[0]);
        System.out.println(twoSum(ints, 20)[1]);
    }

    /**
     * 方法一：暴力法
     * 暴力法很简单。遍历每个元素 xx，并查找是否存在一个值与 target - xtarget−x 相等的目标元素。
     *
     * 复杂度分析：
     * 时间复杂度：O(n^2)， 对于每个元素，我们试图通过遍历数组的其余部分来寻找它所对应的目标元素，
     * 这将耗费 O(n)的时间。因此时间复杂度为 O(n^2)。
     *
     * 空间复杂度：O(1)。
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 方法三：一遍哈希表
     * 事实证明，我们可以一次完成。在进行迭代并将元素插入到表中的同时，我们还会回过头来检查表中是否已经存在当前元素所对
     * 应的目标元素。如果它存在，那我们已经找到了对应解，并立即将其返回。
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
