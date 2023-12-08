/*
 * @lc app=leetcode.cn id=1556 lang=java
 *
 * [1556] 千位分隔数
 *
 * https://leetcode.cn/problems/thousand-separator/description/
 *
 * algorithms
 * Easy (56.17%)
 * Likes:    30
 * Dislikes: 0
 * Total Accepted:    21.6K
 * Total Submissions: 38.5K
 * Testcase Example:  '987'
 *
 * 给你一个整数 n，请你每隔三位添加点（即 "." 符号）作为千位分隔符，并将结果以字符串格式返回。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：n = 987
 * 输出："987"
 * 
 * 
 * 示例 2：
 * 
 * 输入：n = 1234
 * 输出："1.234"
 * 
 * 
 * 示例 3：
 * 
 * 输入：n = 123456789
 * 输出："123.456.789"
 * 
 * 
 * 示例 4：
 * 
 * 输入：n = 0
 * 输出："0"
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 <= n < 2^31
 * 
 * 
 */

// @lc code=start
class Solution {
    public String thousandSeparator(int n) {
        int count = 0;
        String text = String.valueOf(n);
        StringBuilder sb = new StringBuilder();

        for (int i = text.length() - 1; i >= 0; i--) {
            count++;
            sb.append(text.charAt(i));
            if (count % 3 == 0 && i != 0)
                sb.append('.');
        }

        return sb.reverse().toString();
    }
}
// @lc code=end
