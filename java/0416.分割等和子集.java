/*
 * @lc app=leetcode.cn id=416 lang=java
 *
 * [416] 分割等和子集
 *
 * https://leetcode.cn/problems/partition-equal-subset-sum/description/
 *
 * algorithms
 * Medium (52.12%)
 * Likes:    1518
 * Dislikes: 0
 * Total Accepted:    336.1K
 * Total Submissions: 644.5K
 * Testcase Example:  '[1,5,11,5]'
 *
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 1 
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean canPartition(int[] nums) {
        if (nums.length == 1)
            return false;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0)
            return false;

        int length = nums.length, target = sum / 2;
        // boolean[][] dp = new boolean[length + 1][target + 1];
        // for (int i = 0; i <= length; i++) {
        // dp[i][0] = true;
        // }

        // for (int i = 1; i <= length; i++) {
        // for (int j = 1; j <= target; j++) {
        // if (j < nums[i - 1])
        // dp[i][j] = dp[i - 1][j];
        // else
        // dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
        // }
        // }

        // return dp[length][target];

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 0; i < length; i++) {
            for (int j = target; j >= 0; j--) {
                if (j >= nums[i])
                    dp[j] = dp[j] || dp[j - nums[i]];
            }
        }

        return dp[target];
    }
}
// @lc code=end
