import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=53 lang=java
 *
 * [53] 最大子数组和
 *
 * https://leetcode.cn/problems/maximum-subarray/description/
 *
 * algorithms
 * Medium (54.82%)
 * Likes:    5342
 * Dislikes: 0
 * Total Accepted:    1.2M
 * Total Submissions: 2.2M
 * Testcase Example:  '[-2,1,-3,4,-1,2,1,-5,4]'
 *
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 
 * 子数组 是数组中的一个连续部分。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1]
 * 输出：1
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 
 * 
 * 
 * 
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 * 
 */

// @lc code=start
class Solution {
    public int maxSubArray(int[] nums) {
        // int length = nums.length, ans = nums[0];
        // // 存储以nums[i]结尾的子串的最大和
        // int[] dp = Arrays.copyOf(nums, length);
        // for (int i = 1; i < length; i++) {
        // dp[i] = Math.max(dp[i], dp[i - 1] + nums[i]);
        // ans = Math.max(ans, dp[i]);
        // }

        // return ans;

        int length = nums.length, preMax = nums[0], ans = nums[0], current = 0;
        for (int i = 1; i < length; i++) {
            current = Math.max(nums[i], preMax + nums[i]);
            preMax = current;
            ans = Math.max(ans, current);
        }

        return ans;
    }
}
// @lc code=end
