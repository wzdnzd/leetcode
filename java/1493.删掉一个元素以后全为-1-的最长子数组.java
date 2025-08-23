/*
 * @lc app=leetcode.cn id=1493 lang=java
 *
 * [1493] 删掉一个元素以后全为 1 的最长子数组
 *
 * https://leetcode.cn/problems/longest-subarray-of-1s-after-deleting-one-element/description/
 *
 * algorithms
 * Medium (67.37%)
 * Likes:    194
 * Dislikes: 0
 * Total Accepted:    71.1K
 * Total Submissions: 104.9K
 * Testcase Example:  '[1,1,0,1]'
 *
 * 给你一个二进制数组 nums ，你需要从中删掉一个元素。
 * 
 * 请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。
 * 
 * 如果不存在这样的子数组，请返回 0 。
 * 
 * 
 * 
 * 提示 1：
 * 
 * 
 * 输入：nums = [1,1,0,1]
 * 输出：3
 * 解释：删掉位置 2 的数后，[1,1,1] 包含 3 个 1 。
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [0,1,1,1,0,1,1,0,1]
 * 输出：5
 * 解释：删掉位置 4 的数字后，[0,1,1,1,1,1,0,1] 的最长全 1 子数组为 [1,1,1,1,1] 。
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [1,1,1]
 * 输出：2
 * 解释：你必须要删除一个元素。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 10^5
 * nums[i] 要么是 0 要么是 1 。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int longestSubarray(int[] nums) {
        int p = 0, q = 0, count = 0;
        for (int num : nums) {
            if (num == 0) {
                q = p;
                p = 0;
            } else {
                ++p;
                ++q;
            }

            count = Math.max(count, q);
        }

        if (count == nums.length)
            --count;

        return count;
    }
}
// @lc code=end
