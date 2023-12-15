/*
 * @lc app=leetcode.cn id=2778 lang=java
 *
 * [2778] 特殊元素平方和
 *
 * https://leetcode.cn/problems/sum-of-squares-of-special-elements/description/
 *
 * algorithms
 * Easy (79.29%)
 * Likes:    4
 * Dislikes: 0
 * Total Accepted:    7.8K
 * Total Submissions: 9.8K
 * Testcase Example:  '[1,2,3,4]'
 *
 * 给你一个下标从 1 开始、长度为 n 的整数数组 nums 。
 * 
 * 对 nums 中的元素 nums[i] 而言，如果 n 能够被 i 整除，即 n % i == 0 ，则认为 num[i] 是一个 特殊元素 。
 * 
 * 返回 nums 中所有 特殊元素 的 平方和 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,2,3,4]
 * 输出：21
 * 解释：nums 中共有 3 个特殊元素：nums[1]，因为 4 被 1 整除；nums[2]，因为 4 被 2 整除；以及 nums[4]，因为 4
 * 被 4 整除。 
 * 因此，nums 中所有特殊元素的平方和等于 nums[1] * nums[1] + nums[2] * nums[2] + nums[4] *
 * nums[4] = 1 * 1 + 2 * 2 + 4 * 4 = 21 。  
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [2,7,1,19,18,3]
 * 输出：63
 * 解释：nums 中共有 4 个特殊元素：nums[1]，因为 6 被 1 整除；nums[2] ，因为 6 被 2 整除；nums[3]，因为 6 被
 * 3 整除；以及 nums[6]，因为 6 被 6 整除。 
 * 因此，nums 中所有特殊元素的平方和等于 nums[1] * nums[1] + nums[2] * nums[2] + nums[3] *
 * nums[3] + nums[6] * nums[6] = 2 * 2 + 7 * 7 + 1 * 1 + 3 * 3 = 63 。 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length == n <= 50
 * 1 <= nums[i] <= 50
 * 
 * 
 */

// @lc code=start
class Solution {
    public int sumOfSquares(int[] nums) {
        int total = 0;
        for (int i = 1; i <= nums.length; i++) {
            if (nums.length % i == 0)
                total += nums[i - 1] * nums[i - 1];
        }

        return total;
    }
}
// @lc code=end
