/*
 * @lc app=leetcode.cn id=3176 lang=java
 *
 * [3176] 求出最长好子序列 I
 *
 * https://leetcode.cn/problems/find-the-maximum-length-of-a-good-subsequence-i/description/
 *
 * algorithms
 * Medium (30.81%)
 * Likes:    11
 * Dislikes: 0
 * Total Accepted:    3.5K
 * Total Submissions: 9.7K
 * Testcase Example:  '[1,2,1,1,3]\n2'
 *
 * 给你一个整数数组 nums 和一个 非负 整数 k 。如果一个整数序列 seq 满足在范围下标范围 [0, seq.length - 2] 中存在
 * 不超过 k 个下标 i 满足 seq[i] != seq[i + 1] ，那么我们称这个整数序列为 好 序列。
 * 
 * 请你返回 nums 中 好 子序列 的最长长度
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,2,1,1,3], k = 2
 * 
 * 输出：4
 * 
 * 解释：
 * 
 * 最长好子序列为 [1,2,1,1,3] 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1,2,3,4,5,1], k = 0
 * 
 * 输出：2
 * 
 * 解释：
 * 
 * 最长好子序列为 [1,2,3,4,5,1] 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 500
 * 1 <= nums[i] <= 10^9
 * 0 <= k <= min(nums.length, 25)
 * 
 * 
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maximumLength(int[] nums, int k) {
        Map<Integer, int[]> map = new HashMap<>();
        int[] dp = new int[k + 2];

        for (int x : nums) {
            int[] array = map.computeIfAbsent(x, i -> new int[k + 1]);

            for (int j = k; j >= 0; j--) {
                array[j] = Math.max(array[j], dp[j]) + 1;
                dp[j + 1] = Math.max(dp[j + 1], array[j]);
            }
        }

        return dp[k + 1];
    }
}
// @lc code=end
