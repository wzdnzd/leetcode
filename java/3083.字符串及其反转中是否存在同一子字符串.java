/*
 * @lc app=leetcode.cn id=3083 lang=java
 *
 * [3083] 字符串及其反转中是否存在同一子字符串
 *
 * https://leetcode.cn/problems/existence-of-a-substring-in-a-string-and-its-reverse/description/
 *
 * algorithms
 * Easy (71.14%)
 * Likes:    16
 * Dislikes: 0
 * Total Accepted:    13.8K
 * Total Submissions: 18.6K
 * Testcase Example:  '"leetcode"'
 *
 * 给你一个字符串 s ，请你判断字符串 s 是否存在一个长度为 2 的子字符串，在其反转后的字符串中也出现。
 * 
 * 如果存在这样的子字符串，返回 true；如果不存在，返回 false 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "leetcode"
 * 
 * 输出：true
 * 
 * 解释：子字符串 "ee" 的长度为 2，它也出现在 reverse(s) == "edocteel" 中。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "abcba"
 * 
 * 输出：true
 * 
 * 解释：所有长度为 2 的子字符串 "ab"、"bc"、"cb"、"ba" 也都出现在 reverse(s) == "abcba" 中。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：s = "abcd"
 * 
 * 输出：false
 * 
 * 解释：字符串 s 中不存在满足「在其反转后的字符串中也出现」且长度为 2 的子字符串。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s.length <= 100
 * 字符串 s 仅由小写英文字母组成。
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isSubstringPresent(String s) {
        int[] records = new int[26];

        for (int i = 0; i + 1 < s.length(); i++) {
            int x = s.charAt(i) - 'a';
            int y = s.charAt(i + 1) - 'a';
            records[x] |= 1 << y;

            if ((records[y] >> x & 1) != 0)
                return true;

        }

        return false;
    }
}
// @lc code=end
