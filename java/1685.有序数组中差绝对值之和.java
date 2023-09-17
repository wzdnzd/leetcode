/*
 * @lc app=leetcode.cn id=1685 lang=java
 *
 * [1685] 有序数组中差绝对值之和
 *
 * https://leetcode.cn/problems/sum-of-absolute-differences-in-a-sorted-array/description/
 *
 * algorithms
 * Medium (65.04%)
 * Likes:    47
 * Dislikes: 0
 * Total Accepted:    8.2K
 * Total Submissions: 12.6K
 * Testcase Example:  '[2,3,5]'
 *
 * 给你一个 非递减 有序整数数组 nums 。
 * 
 * 请你建立并返回一个整数数组 result，它跟 nums 长度相同，且result[i] 等于 nums[i] 与数组中所有其他元素差的绝对值之和。
 * 
 * 换句话说， result[i] 等于 sum(|nums[i]-nums[j]|) ，其中 0  且 j != i （下标从 0 开始）。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [2,3,5]
 * 输出：[4,3,5]
 * 解释：假设数组下标从 0 开始，那么
 * result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4，
 * result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3，
 * result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1,4,6,8,10]
 * 输出：[24,15,13,15,21]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 2 
 * 1 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length, total = 0;
        int[] prefixSum = new int[n];
        for (int i = 0; i < n; i++) {
            total += nums[i];
            prefixSum[i] = total;
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int left = (i + 1) * nums[i] - prefixSum[i];
            int right = prefixSum[n - 1] - prefixSum[i] - nums[i] * (n - i - 1);
            result[i] = left + right;
        }

        return result;
    }
}
// @lc code=end
