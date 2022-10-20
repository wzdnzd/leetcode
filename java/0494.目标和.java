import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=494 lang=java
 *
 * [494] 目标和
 *
 * https://leetcode.cn/problems/target-sum/description/
 *
 * algorithms
 * Medium (49.09%)
 * Likes:    1407
 * Dislikes: 0
 * Total Accepted:    287.1K
 * Total Submissions: 584.4K
 * Testcase Example:  '[1,1,1,1,1]\n3'
 *
 * 给你一个整数数组 nums 和一个整数 target 。
 * 
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 
 * 
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 
 * 
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1], target = 1
 * 输出：1
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 0 
 * 0 
 * -1000 
 * 
 * 
 */

// @lc code=start
class Solution {
    // public int findTargetSumWays(int[] nums, int target) {
    // return findTargetSumWaysHelper(nums, target, 0);
    // }

    // private int findTargetSumWaysHelper(int[] nums, int target, int index) {
    // if (index == nums.length - 1)
    // return Math.abs(target) == Math.abs(nums[index]) ? (target == 0 ? 2 : 1) : 0;

    // // 第一个数前面添加 +
    // int positive = findTargetSumWaysHelper(nums, target - nums[index], index +
    // 1);

    // // 第一个数前面添加 -
    // int negative = findTargetSumWaysHelper(nums, target + nums[index], index +
    // 1);

    // return positive + negative;
    // }

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0, diff = 0;
        for (int num : nums)
            sum += num;
        diff = sum - target;
        if (diff < 0 || diff % 2 != 0)
            return 0;

        int negative = diff / 2;
        int[] dp = new int[negative + 1];
        dp[0] = 1;

        for (int num : nums) {
            for (int i = negative; i >= num; i--)
                dp[i] += dp[i - num];
        }

        return dp[negative];
    }
}
// @lc code=end
