/*
 * @lc app=leetcode.cn id=213 lang=java
 *
 * [213] 打家劫舍 II
 *
 * https://leetcode.cn/problems/house-robber-ii/description/
 *
 * algorithms
 * Medium (43.92%)
 * Likes:    1169
 * Dislikes: 0
 * Total Accepted:    287.9K
 * Total Submissions: 655.4K
 * Testcase Example:  '[2,3,2]'
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈
 * ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [1,2,3]
 * 输出：3
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1)
            return nums[0];
        if (length == 2)
            return Math.max(nums[0], nums[1]);

        return Math.max(robRange(nums, 0, length - 2), robRange(nums, 1, length - 1));
    }

    private int robRange(int[] nums, int start, int end) {
        if (nums == null || nums.length == 0 || start < 0 || end < start)
            return 0;

        int p1 = 0, p2 = 0, amount = 0;
        for (int i = end; i >= start; i--) {
            amount = Math.max(p1 + nums[i], p2);
            p1 = p2;
            p2 = amount;
        }

        return amount;
    }
}
// @lc code=end
