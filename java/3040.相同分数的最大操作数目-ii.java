/*
 * @lc app=leetcode.cn id=3040 lang=java
 *
 * [3040] 相同分数的最大操作数目 II
 *
 * https://leetcode.cn/problems/maximum-number-of-operations-with-the-same-score-ii/description/
 *
 * algorithms
 * Medium (34.35%)
 * Likes:    13
 * Dislikes: 0
 * Total Accepted:    4.8K
 * Total Submissions: 12.1K
 * Testcase Example:  '[3,2,1,2,3,4]'
 *
 * 给你一个整数数组 nums ，如果 nums 至少 包含 2 个元素，你可以执行以下操作中的 任意 一个：
 * 
 * 
 * 选择 nums 中最前面两个元素并且删除它们。
 * 选择 nums 中最后两个元素并且删除它们。
 * 选择 nums 中第一个和最后一个元素并且删除它们。
 * 
 * 
 * 一次操作的 分数 是被删除元素的和。
 * 
 * 在确保 所有操作分数相同 的前提下，请你求出 最多 能进行多少次操作。
 * 
 * 请你返回按照上述要求 最多 可以进行的操作次数。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [3,2,1,2,3,4]
 * 输出：3
 * 解释：我们执行以下操作：
 * - 删除前两个元素，分数为 3 + 2 = 5 ，nums = [1,2,3,4] 。
 * - 删除第一个元素和最后一个元素，分数为 1 + 4 = 5 ，nums = [2,3] 。
 * - 删除第一个元素和最后一个元素，分数为 2 + 3 = 5 ，nums = [] 。
 * 由于 nums 为空，我们无法继续进行任何操作。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [3,2,6,1,4]
 * 输出：2
 * 解释：我们执行以下操作：
 * - 删除前两个元素，分数为 3 + 2 = 5 ，nums = [6,1,4] 。
 * - 删除最后两个元素，分数为 1 + 4 = 5 ，nums = [6] 。
 * 至多进行 2 次操作。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 2 <= nums.length <= 2000
 * 1 <= nums[i] <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxOperations(int[] nums) {
        int n = nums.length;
        int x = helper(nums, 2, n - 1, nums[0] + nums[1]);
        int y = helper(nums, 0, n - 3, nums[n - 2] + nums[n - 1]);
        int z = helper(nums, 1, n - 2, nums[0] + nums[n - 1]);

        return Math.max(x, Math.max(y, z)) + 1;
    }

    private int helper(int[] nums, int left, int right, int target) {
        int n = nums.length;
        int[][] dp = new int[n + 1][n + 1];

        for (int i = right - 1; i >= left; i--) {
            for (int j = i + 1; j <= right; j++) {
                if (nums[i] + nums[i + 1] == target)
                    dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i + 2][j + 1] + 1);
                if (nums[j - 1] + nums[j] == target)
                    dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i][j - 1] + 1);
                if (nums[i] + nums[j] == target)
                    dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j] + 1);
            }
        }

        return dp[left][right + 1];
    }
}
// @lc code=end
