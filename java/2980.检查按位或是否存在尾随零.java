/*
 * @lc app=leetcode.cn id=2980 lang=java
 *
 * [2980] 检查按位或是否存在尾随零
 *
 * https://leetcode.cn/problems/check-if-bitwise-or-has-trailing-zeros/description/
 *
 * algorithms
 * Easy (75.44%)
 * Likes:    3
 * Dislikes: 0
 * Total Accepted:    3.8K
 * Total Submissions: 5K
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * 给你一个 正整数 数组 nums 。
 * 
 * 你需要检查是否可以从数组中选出 两个或更多 元素，满足这些元素的按位或运算（ OR）结果的二进制表示中 至少 存在一个尾随零。
 * 
 * 例如，数字 5 的二进制表示是 "101"，不存在尾随零，而数字 4 的二进制表示是 "100"，存在两个尾随零。
 * 
 * 如果可以选择两个或更多元素，其按位或运算结果存在尾随零，返回 true；否则，返回 false 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,2,3,4,5]
 * 输出：true
 * 解释：如果选择元素 2 和 4，按位或运算结果是 6，二进制表示为 "110" ，存在一个尾随零。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [2,4,8,16]
 * 输出：true
 * 解释：如果选择元素 2 和 4，按位或运算结果是 6，二进制表示为 "110"，存在一个尾随零。
 * 其他按位或运算结果存在尾随零的可能选择方案包括：(2, 8), (2, 16), (4, 8), (4, 16), (8, 16), (2, 4,
 * 8), (2, 4, 16), (2, 8, 16), (4, 8, 16), 以及 (2, 4, 8, 16) 。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [1,3,5,7,9]
 * 输出：false
 * 解释：不存在按位或运算结果存在尾随零的选择方案。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 2 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean hasTrailingZeros(int[] nums) {
        int count = 0;
        for (int num : nums) {
            if (num % 2 == 0)
                count++;

            if (count >= 2)
                return true;
        }

        return false;
    }
}
// @lc code=end
