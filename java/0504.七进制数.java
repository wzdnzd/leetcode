/*
 * @lc app=leetcode.cn id=504 lang=java
 *
 * [504] 七进制数
 *
 * https://leetcode.cn/problems/base-7/description/
 *
 * algorithms
 * Easy (51.77%)
 * Likes:    210
 * Dislikes: 0
 * Total Accepted:    89.6K
 * Total Submissions: 173.2K
 * Testcase Example:  '100'
 *
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 
 * 输入: num = 100
 * 输出: "202"
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: num = -7
 * 输出: "-10"
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * -10^7 <= num <= 10^7
 * 
 * 
 */

// @lc code=start
class Solution {
    public String convertToBase7(int num) {
        if (num == 0)
            return "0";

        boolean negative = num < 0;
        num = negative ? -num : num;

        StringBuffer digits = new StringBuffer();
        while (num > 0) {
            digits.append(num % 7);
            num /= 7;
        }

        if (negative)
            digits.append('-');

        return digits.reverse().toString();
    }
}
// @lc code=end
