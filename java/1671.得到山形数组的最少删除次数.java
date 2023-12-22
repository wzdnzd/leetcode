/*
 * @lc app=leetcode.cn id=1671 lang=java
 *
 * [1671] 得到山形数组的最少删除次数
 *
 * https://leetcode.cn/problems/minimum-number-of-removals-to-make-mountain-array/description/
 *
 * algorithms
 * Hard (45.93%)
 * Likes:    61
 * Dislikes: 0
 * Total Accepted:    5.7K
 * Total Submissions: 12K
 * Testcase Example:  '[1,3,1]'
 *
 * 我们定义 arr 是 山形数组 当且仅当它满足：
 * 
 * 
 * arr.length >= 3
 * 存在某个下标 i （从 0 开始） 满足 0 < i < arr.length - 1 且：
 * 
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * 
 * 
 * 
 * 
 * 给你整数数组 nums​ ，请你返回将 nums 变成 山形状数组 的​ 最少 删除次数。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,3,1]
 * 输出：0
 * 解释：数组本身就是山形数组，所以我们不需要删除任何元素。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [2,1,1,5,6,2,3,1]
 * 输出：3
 * 解释：一种方法是将下标为 0，1 和 5 的元素删除，剩余元素为 [1,5,6,3,1] ，是山形数组。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 3 <= nums.length <= 1000
 * 1 <= nums[i] <= 10^9
 * 题目保证 nums 删除一些元素后一定能得到山形数组。
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] left = getLIS(nums);
        int[] right = getLIS(reverse(nums));

        int count = n;
        for (int i = 1; i < n - 1; i++) {
            if (left[i] > 1 && right[n - i - 1] > 1)
                count = Math.min(count, n - (left[i] + right[n - i - 1] - 1));
        }

        return count;
    }

    private int binarySearch(List<Integer> nums, int target) {
        int left = 0, right = nums.size();

        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums.get(mid) < target)
                left = mid + 1;
            else
                right = mid;
        }

        return left;
    }

    private int[] getLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int index = binarySearch(list, nums[i]);
            if (index == list.size()) {
                list.add(nums[i]);
                dp[i] = list.size();
            } else {
                list.set(index, nums[i]);
                dp[i] = index + 1;
            }
        }

        return dp;
    }

    private int[] reverse(int[] nums) {
        int n = nums.length;
        int[] array = new int[n];

        for (int i = 0; i < n; i++)
            array[i] = nums[n - i - 1];

        return array;
    }
}
// @lc code=end
