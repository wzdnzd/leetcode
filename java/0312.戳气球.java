/*
 * @lc app=leetcode.cn id=312 lang=java
 *
 * [312] 戳气球
 *
 * https://leetcode.cn/problems/burst-balloons/description/
 *
 * algorithms
 * Hard (69.96%)
 * Likes:    1349
 * Dislikes: 0
 * Total Accepted:    119.2K
 * Total Submissions: 170.2K
 * Testcase Example:  '[3,1,5,8]'
 *
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * 
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i
 * - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 * 
 * 求所能获得硬币的最大数量。
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [3,1,5,8]
 * 输出：167
 * 解释：
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1,5]
 * 输出：10
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * n == nums.length
 * 1 <= n <= 300
 * 0 <= nums[i] <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;

        int[] array = new int[n + 2];
        array[0] = array[n + 1] = 1;
        for (int i = 0; i < n; i++)
            array[i + 1] = nums[i];

        int[][] dp = new int[n + 2][n + 2];
        for (int i = 3; i <= n + 2; i++) {
            for (int l = 0; l + i - 1 < n + 2; l++) {
                int r = l + i - 1;

                for (int k = l + 1; k <= r - 1; k++)
                    dp[l][r] = Math.max(dp[l][r], dp[l][k] + dp[k][r] + array[l] * array[k] * array[r]);
            }
        }

        return dp[0][n + 1];
    }
}
// @lc code=end
