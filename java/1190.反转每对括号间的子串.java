/*
 * @lc app=leetcode.cn id=1190 lang=java
 *
 * [1190] 反转每对括号间的子串
 *
 * https://leetcode.cn/problems/reverse-substrings-between-each-pair-of-parentheses/description/
 *
 * algorithms
 * Medium (66.01%)
 * Likes:    323
 * Dislikes: 0
 * Total Accepted:    77.6K
 * Total Submissions: 117.5K
 * Testcase Example:  '"(abcd)"'
 *
 * 给出一个字符串 s（仅含有小写英文字母和括号）。
 * 
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 * 
 * 注意，您的结果中 不应 包含任何括号。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "(abcd)"
 * 输出："dcba"
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "(u(love)i)"
 * 输出："iloveu"
 * 解释：先反转子字符串 "love" ，然后反转整个字符串。
 * 
 * 示例 3：
 * 
 * 
 * 输入：s = "(ed(et(oc))el)"
 * 输出："leetcode"
 * 解释：先反转子字符串 "oc" ，接着反转 "etco" ，然后反转整个字符串。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s.length <= 2000
 * s 中只有小写英文字母和括号
 * 题目测试用例确保所有括号都是成对出现的
 * 
 * 
 */

// @lc code=start
class Solution {
    public String reverseParentheses(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length, top = -1;
        int[] links = new int[n], stack = new int[n];

        for (int i = 0; i < n; i++) {
            char c = chars[i];
            if (c == '(') {
                stack[++top] = i;
            } else if (c == ')') {
                int j = stack[top--];
                links[i] = j;
                links[j] = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        int step = 1;

        for (int i = 0; i < n; i += step) {
            char c = chars[i];
            if (c == '(' || c == ')') {
                i = links[i];
                step = -step;
            } else
                sb.append(c);
        }

        return sb.toString();
    }
}
// @lc code=end
