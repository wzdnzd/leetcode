import java.util.Stack;

/*
 * @lc app=leetcode.cn id=415 lang=java
 *
 * [415] 字符串相加
 *
 * https://leetcode.cn/problems/add-strings/description/
 *
 * algorithms
 * Easy (55.01%)
 * Likes:    627
 * Dislikes: 0
 * Total Accepted:    236.1K
 * Total Submissions: 429.1K
 * Testcase Example:  '"11"\n"123"'
 *
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 * 
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：num1 = "456", num2 = "77"
 * 输出："533"
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：num1 = "0", num2 = "0"
 * 输出："0"
 * 
 * 
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= num1.length, num2.length <= 10^4
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 * 
 * 
 */

// @lc code=start
class Solution {
    public String addStrings(String num1, String num2) {
        Stack<Character> stack = new Stack<>();
        int m = num1.length() - 1, n = num2.length() - 1, carry = 0;
        while (m >= 0 || n >= 0 || carry != 0) {
            if (m >= 0)
                carry += num1.charAt(m--) - '0';
            if (n >= 0)
                carry += num2.charAt(n--) - '0';
            stack.add((char) ((carry % 10) + '0'));
            carry /= 10;
        }

        char[] chars = new char[stack.size()];
        for (int i = 0; i < chars.length; i++)
            chars[i] = stack.pop();

        return new String(chars);
    }
}
// @lc code=end
