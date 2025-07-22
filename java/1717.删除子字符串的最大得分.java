/*
 * @lc app=leetcode.cn id=1717 lang=java
 *
 * [1717] 删除子字符串的最大得分
 *
 * https://leetcode.cn/problems/maximum-score-from-removing-substrings/description/
 *
 * algorithms
 * Medium (50.35%)
 * Likes:    41
 * Dislikes: 0
 * Total Accepted:    5.9K
 * Total Submissions: 11.6K
 * Testcase Example:  '"cdbcbbaaabab"\n4\n5'
 *
 * 给你一个字符串 s 和两个整数 x 和 y 。你可以执行下面两种操作任意次。
 * 
 * 
 * 删除子字符串 "ab" 并得到 x 分。
 * 
 * 
 * 比方说，从 "cabxbae" 删除 ab ，得到 "cxbae" 。
 * 
 * 
 * 删除子字符串"ba" 并得到 y 分。
 * 
 * 比方说，从 "cabxbae" 删除 ba ，得到 "cabxe" 。
 * 
 * 
 * 
 * 
 * 请返回对 s 字符串执行上面操作若干次能得到的最大得分。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：s = "cdbcbbaaabab", x = 4, y = 5
 * 输出：19
 * 解释：
 * - 删除 "cdbcbbaaabab" 中加粗的 "ba" ，得到 s = "cdbcbbaaab" ，加 5 分。
 * - 删除 "cdbcbbaaab" 中加粗的 "ab" ，得到 s = "cdbcbbaa" ，加 4 分。
 * - 删除 "cdbcbbaa" 中加粗的 "ba" ，得到 s = "cdbcba" ，加 5 分。
 * - 删除 "cdbcba" 中加粗的 "ba" ，得到 s = "cdbc" ，加 5 分。
 * 总得分为 5 + 4 + 5 + 5 = 19 。
 * 
 * 示例 2：
 * 
 * 输入：s = "aabbaaxybbaabb", x = 5, y = 4
 * 输出：20
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s.length <= 10^5
 * 1 <= x, y <= 10^4
 * s 只包含小写英文字母。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maximumGain(String s, int x, int y) {
        int points = 0;
        char c1 = 'a', c2 = 'b';

        if (x < y) {
            c1 = 'b';
            c2 = 'a';

            int temp = x;
            x = y;
            y = temp;
        }

        int count1 = 0, count2 = 0;
        int length = s.length();

        for (int i = 0; i < length; i++) {
            char curr = s.charAt(i);
            if (curr == c1)
                count1++;
            else if (curr == c2) {
                if (count1 > 0) {
                    count1--;
                    points += x;

                } else
                    count2++;
            }

            char next = i < length - 1 ? s.charAt(i + 1) : ' ';
            if (next != c1 && next != c2) {
                points += y * Math.min(count1, count2);
                count1 = 0;
                count2 = 0;
            }
        }

        return points;
    }
}
// @lc code=end
