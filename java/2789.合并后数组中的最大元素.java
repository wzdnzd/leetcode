/*
 * @lc app=leetcode.cn id=2789 lang=java
 *
 * [2789] 合并后数组中的最大元素
 *
 * https://leetcode.cn/problems/largest-element-in-an-array-after-merge-operations/description/
 *
 * algorithms
 * Medium (49.66%)
 * Likes:    42
 * Dislikes: 0
 * Total Accepted:    14.6K
 * Total Submissions: 25.9K
 * Testcase Example:  '[2,3,7,9,3]'
 *
 * 给你一个下标从 0 开始、由正整数组成的数组 nums 。
 * 
 * 你可以在数组上执行下述操作 任意 次：
 * 
 * 
 * 选中一个同时满足 0 <= i < nums.length - 1 和 nums[i] <= nums[i + 1] 的整数 i 。将元素 nums[i
 * + 1] 替换为 nums[i] + nums[i + 1] ，并从数组中删除元素 nums[i] 。
 * 
 * 
 * 返回你可以从最终数组中获得的 最大 元素的值。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums = [2,3,7,9,3]
 * 输出：21
 * 解释：我们可以在数组上执行下述操作：
 * - 选中 i = 0 ，得到数组 nums = [5,7,9,3] 。
 * - 选中 i = 1 ，得到数组 nums = [5,16,3] 。
 * - 选中 i = 0 ，得到数组 nums = [21,3] 。
 * 最终数组中的最大元素是 21 。可以证明我们无法获得更大的元素。
 * 
 * 
 * 示例 2：
 * 
 * 输入：nums = [5,3,3]
 * 输出：11
 * 解释：我们可以在数组上执行下述操作：
 * - 选中 i = 1 ，得到数组 nums = [5,6] 。
 * - 选中 i = 0 ，得到数组 nums = [11] 。
 * 最终数组中只有一个元素，即 11 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public long maxArrayValue(int[] nums) {
        int n = nums.length;
        long result = nums[n - 1];

        for (int i = n - 2; i >= 0; i--)
            result = nums[i] <= result ? nums[i] + result : nums[i];

        return result;
    }

}
// @lc code=end
