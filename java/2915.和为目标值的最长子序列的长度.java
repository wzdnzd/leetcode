/*
 * @lc app=leetcode.cn id=2915 lang=java
 *
 * [2915] 和为目标值的最长子序列的长度
 *
 * https://leetcode.cn/problems/length-of-the-longest-subsequence-that-sums-to-target/description/
 *
 * algorithms
 * Medium (35.12%)
 * Likes:    12
 * Dislikes: 0
 * Total Accepted:    3.3K
 * Total Submissions: 8.7K
 * Testcase Example:  '[1,2,3,4,5]\n9'
 *
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 target 。
 * 
 * 返回和为 target 的 nums 子序列中，子序列 长度的最大值 。如果不存在和为 target 的子序列，返回 -1 。
 * 
 * 子序列 指的是从原数组中删除一些或者不删除任何元素后，剩余元素保持原来的顺序构成的数组。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,2,3,4,5], target = 9
 * 输出：3
 * 解释：总共有 3 个子序列的和为 9 ：[4,5] ，[1,3,5] 和 [2,3,4] 。最长的子序列是 [1,3,5] 和 [2,3,4]
 * 。所以答案为 3 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [4,1,3,2,1,5], target = 7
 * 输出：4
 * 解释：总共有 5 个子序列的和为 7 ：[4,3] ，[4,1,2] ，[4,2,1] ，[1,1,5] 和 [1,3,2,1] 。最长子序列为
 * [1,3,2,1] 。所以答案为 4 。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [1,1,5,4,5], target = 3
 * 输出：-1
 * 解释：无法得到和为 3 的子序列。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * 1 <= target <= 1000
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.List;

class Solution {
    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;

        int sum = 0;
        for (int num : nums) {
            sum = Math.min(sum + num, target);
            for (int i = sum; i >= num; i--) {
                dp[i] = Math.max(dp[i], dp[i - num] + 1);
            }
        }

        return dp[target] > 0 ? dp[target] : -1;
    }
}
// @lc code=end
