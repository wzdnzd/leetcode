/*
 * @lc app=leetcode.cn id=371 lang=java
 *
 * [371] 两整数之和
 *
 * https://leetcode.cn/problems/sum-of-two-integers/description/
 *
 * algorithms
 * Medium (61.46%)
 * Likes:    618
 * Dislikes: 0
 * Total Accepted:    95.6K
 * Total Submissions: 155.5K
 * Testcase Example:  '1\n2'
 *
 * 给你两个整数 a 和 b ，不使用 运算符 + 和 - ​​​​​​​，计算并返回两整数之和。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：a = 1, b = 2
 * 输出：3
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：a = 2, b = 3
 * 输出：5
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * -1000 <= a, b <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a ^= b;
            b = carry;
        }

        return a;
    }
}
// @lc code=end
