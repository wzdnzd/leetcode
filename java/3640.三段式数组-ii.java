/*
 * @lc app=leetcode.cn id=3640 lang=java
 *
 * [3640] 三段式数组 II
 *
 * https://leetcode.cn/problems/trionic-array-ii/description/
 *
 * algorithms
 * Hard (43.20%)
 * Likes:    5
 * Dislikes: 0
 * Total Accepted:    3K
 * Total Submissions: 6K
 * Testcase Example:  '[0,-2,-1,-3,0,2,-1]'
 *
 * 给你一个长度为 n 的整数数组 nums。
 * 
 * 三段式子数组 是一个连续子数组 nums[l...r]（满足 0 <= l < r < n），并且存在下标 l < p < q <
 * r，使得：
 * 
 * 
 * nums[l...p] 严格 递增，
 * nums[p...q] 严格 递减，
 * nums[q...r] 严格 递增。
 * 
 * 
 * 请你从数组 nums 的所有三段式子数组中找出和最大的那个，并返回其 最大 和。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [0,-2,-1,-3,0,2,-1]
 * 
 * 输出：-4
 * 
 * 解释：
 * 
 * 选择 l = 1, p = 2, q = 3, r = 5：
 * 
 * 
 * nums[l...p] = nums[1...2] = [-2, -1] 严格递增 (-2 < -1)。
 * nums[p...q] = nums[2...3] = [-1, -3] 严格递减 (-1 > -3)。
 * nums[q...r] = nums[3...5] = [-3, 0, 2] 严格递增 (-3 < 0 < 2)。
 * 和 = (-2) + (-1) + (-3) + 0 + 2 = -4。
 * 
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: nums = [1,4,2,7]
 * 
 * 输出: 14
 * 
 * 解释:
 * 
 * 选择 l = 0, p = 1, q = 2, r = 3：
 * 
 * 
 * nums[l...p] = nums[0...1] = [1, 4] 严格递增 (1 < 4)。
 * nums[p...q] = nums[1...2] = [4, 2] 严格递减 (4 > 2)。
 * nums[q...r] = nums[2...3] = [2, 7] 严格递增 (2 < 7)。
 * 和 = 1 + 4 + 2 + 7 = 14。
 * 
 * 
 * 
 * 
 * 
 * 提示:
 * 
 * 
 * 4 <= n = nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 保证至少存在一个三段式子数组。
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    private static final long INFINITY = Long.MIN_VALUE / 2;

    public long maxSumTrionic(int[] nums) {
        long maxSum = INFINITY;
        int n = nums.length;

        long[] dp = new long[4];
        Arrays.fill(dp, INFINITY);
        dp[0] = nums[0];

        for (int i = 1; i < n; i++) {
            for (int j = 3, direction = 1; j > 0; j--, direction *= -1) {
                if ((nums[i] - nums[i - 1]) * direction > 0)
                    dp[j] = Math.max(dp[j - 1], dp[j]) + nums[i];
                else
                    dp[j] = INFINITY;
            }

            dp[0] = nums[i];
            maxSum = Math.max(maxSum, dp[3]);
        }

        return maxSum;
    }
}
// @lc code=end
