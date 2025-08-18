/*
 * @lc app=leetcode.cn id=2348 lang=java
 *
 * [2348] 全 0 子数组的数目
 *
 * https://leetcode.cn/problems/number-of-zero-filled-subarrays/description/
 *
 * algorithms
 * Medium (58.11%)
 * Likes:    31
 * Dislikes: 0
 * Total Accepted:    12.6K
 * Total Submissions: 21K
 * Testcase Example:  '[1,3,0,0,2,0,0,4]'
 *
 * 给你一个整数数组 nums ，返回全部为 0 的 子数组 数目。
 * 
 * 子数组 是一个数组中一段连续非空元素组成的序列。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums = [1,3,0,0,2,0,0,4]
 * 输出：6
 * 解释：
 * 子数组 [0] 出现了 4 次。
 * 子数组 [0,0] 出现了 2 次。
 * 不存在长度大于 2 的全 0 子数组，所以我们返回 6 。
 * 
 * 示例 2：
 * 
 * 输入：nums = [0,0,0,2,0,0]
 * 输出：9
 * 解释：
 * 子数组 [0] 出现了 5 次。
 * 子数组 [0,0] 出现了 3 次。
 * 子数组 [0,0,0] 出现了 1 次。
 * 不存在长度大于 3 的全 0 子数组，所以我们返回 9 。
 * 
 * 
 * 示例 3：
 * 
 * 输入：nums = [2,10,2019]
 * 输出：0
 * 解释：没有全 0 子数组，所以我们返回 0 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long count = 0;
        int zeros = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeros++;
                count += zeros;
            } else
                zeros = 0;
        }

        return count;
    }
}
// @lc code=end
