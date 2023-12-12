/*
 * @lc app=leetcode.cn id=896 lang=java
 *
 * [896] 单调数列
 *
 * https://leetcode.cn/problems/monotonic-array/description/
 *
 * algorithms
 * Easy (56.97%)
 * Likes:    201
 * Dislikes: 0
 * Total Accepted:    89.4K
 * Total Submissions: 156.8K
 * Testcase Example:  '[1,2,2,3]'
 *
 * 如果数组是单调递增或单调递减的，那么它是 单调 的。
 * 
 * 如果对于所有 i <= j，nums[i] <= nums[j]，那么数组 nums 是单调递增的。 如果对于所有 i <= j，nums[i]> =
 * nums[j]，那么数组 nums 是单调递减的。
 * 
 * 当给定的数组 nums 是单调数组时返回 true，否则返回 false。
 * 
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,2,2,3]
 * 输出：true
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [6,5,4,4]
 * 输出：true
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [1,3,2]
 * 输出：false
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isMonotonic(int[] nums) {
        int n = nums.length;
        if (n <= 2)
            return true;

        int index = 0;
        while (index < n - 1 && nums[index] == nums[index + 1])
            index++;

        if (index == n - 1)
            return true;

        boolean isIncrease = nums[index] < nums[index + 1];
        for (int j = index + 1; j < n - 1; j++) {
            if (isIncrease && nums[j] > nums[j + 1])
                return false;

            if (!isIncrease && nums[j] < nums[j + 1])
                return false;
        }

        return true;
    }
}
// @lc code=end
