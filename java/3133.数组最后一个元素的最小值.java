/*
 * @lc app=leetcode.cn id=3133 lang=java
 *
 * [3133] 数组最后一个元素的最小值
 *
 * https://leetcode.cn/problems/minimum-array-end/description/
 *
 * algorithms
 * Medium (43.65%)
 * Likes:    19
 * Dislikes: 0
 * Total Accepted:    5.3K
 * Total Submissions: 11.4K
 * Testcase Example:  '3\n4'
 *
 * 给你两个整数 n 和 x 。你需要构造一个长度为 n 的 正整数 数组 nums ，对于所有 0 <= i < n - 1 ，满足 nums[i +
 * 1] 大于 nums[i] ，并且数组 nums 中所有元素的按位 AND 运算结果为 x 。
 * 
 * 返回 nums[n - 1] 可能的 最小 值。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 3, x = 4
 * 
 * 输出：6
 * 
 * 解释：
 * 
 * 数组 nums 可以是 [4,5,6] ，最后一个元素为 6 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 2, x = 7
 * 
 * 输出：15
 * 
 * 解释：
 * 
 * 数组 nums 可以是 [7,15] ，最后一个元素为 15 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n, x <= 10^8
 * 
 * 
 */

// @lc code=start
class Solution {
    public long minEnd(int n, int x) {
        long result = x, first = x, delta = n - 1;

        for (int i = 0; i < 63 && delta > 0; i++) {
            if (((1l << i) & first) == 0) {
                result += (delta & 1) << i;
                delta >>= 1;
            }
        }

        return result;
    }
}
// @lc code=end
