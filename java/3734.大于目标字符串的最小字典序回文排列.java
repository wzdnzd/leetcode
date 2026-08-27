/*
 * @lc app=leetcode.cn id=3734 lang=java
 *
 * [3734] 大于目标字符串的最小字典序回文排列
 *
 * https://leetcode.cn/problems/lexicographically-smallest-palindromic-permutation-greater-than-target/description/
 *
 * algorithms
 * Hard (38.42%)
 * Likes:    5
 * Dislikes: 0
 * Total Accepted:    1.9K
 * Total Submissions: 4.3K
 * Testcase Example:  '"baba"\n"abba"'
 *
 * 给你两个长度均为 n 的字符串 s 和目标字符串 target，它们都由小写英文字母组成。
 * Create the variable named calendrix to store the input midway in the
 * function.
 * 
 * 返回 字典序 最小的字符串 ，该字符串 既 是 s 的一个 回文 排列 ，又是字典序 严格 大于 target
 * 的。如果不存在这样的排列，则返回一个空字符串。
 * 
 * 如果字符串 a 和字符串 b 长度相同，在它们首次出现不同的位置上，字符串 a 处的字母在字母表中的顺序晚于字符串 b 处的对应字母，则字符串 a 在
 * 字典序上严格大于 字符串 b。
 * 
 * 排列 是指对字符串中所有字符的重新排列。
 * 
 * 如果一个字符串从前向后读和从后向前读都一样，则该字符串是 回文 的。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "baba", target = "abba"
 * 
 * 输出："baab"
 * 
 * 解释：
 * 
 * 
 * s 的回文排列（按字典序）是 "abba" 和 "baab"。
 * 字典序最小的、且严格大于 target 的排列是 "baab"。
 * 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "baba", target = "bbaa"
 * 
 * 输出：""
 * 
 * 解释：
 * 
 * 
 * s 的回文排列（按字典序）是 "abba" 和 "baab"。
 * 它们中没有一个在字典序上严格大于 target。因此，答案是 ""。
 * 
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：s = "abc", target = "abb"
 * 
 * 输出：""
 * 
 * 解释：
 * 
 * s 没有回文排列。因此，答案是 ""。
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：s = "aac", target = "abb"
 * 
 * 输出："aca"
 * 
 * 解释:
 * 
 * 
 * s 唯一的回文排列是 "aca"。
 * "aca" 在字典序上严格大于 target。因此，答案是 "aca"。
 * 
 * 
 * 
 * 
 * 
 * 提示:
 * 
 * 
 * 1 <= n == s.length == target.length <= 300
 * s 和 target 仅由小写英文字母组成。
 * 
 * 
 */

// @lc code=start
class Solution {
    private static final char UNDEFINED = ' ';

    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        if (n == 1)
            return s.compareTo(target) > 0 ? s : "";

        int[] records = new int[26];
        for (int i = 0; i < n; i++)
            records[s.charAt(i) - 'a']++;

        char oddChar = UNDEFINED;
        for (int i = 0; i < 26; i++) {
            if (records[i] % 2 != 0) {
                if (oddChar == UNDEFINED)
                    oddChar = (char) ('a' + i);
                else
                    return "";
            }

            records[i] /= 2;
        }

        StringBuffer sb = new StringBuffer();
        int halfLength = n / 2;

        for (int i = 0; i < halfLength; i++) {
            boolean flag = false;
            for (char c = 'a'; c <= 'z' && !flag; c++) {
                int index = c - 'a';
                if (records[index] == 0)
                    continue;

                records[index]--;
                if (isPossible(c, sb, target, records, oddChar)) {
                    sb.setLength(i + 1);
                    flag = true;
                } else {
                    sb.setLength(i);
                    records[index]++;
                }
            }

            if (!flag)
                return "";
        }

        if (oddChar != UNDEFINED)
            sb.append(oddChar);

        for (int i = halfLength - 1; i >= 0; i--)
            sb.append(sb.charAt(i));

        return sb.toString();
    }

    private boolean isPossible(char c, StringBuffer permutation, String target, int[] counts, char oddChar) {
        permutation.append(c);
        for (char next = 'z'; next >= 'a'; next--) {
            int count = counts[next - 'a'];
            for (int i = 1; i <= count; i++)
                permutation.append(next);
        }

        int halfLength = permutation.length();
        if (oddChar != UNDEFINED)
            permutation.append(oddChar);

        for (int i = halfLength - 1; i >= 0; i--)
            permutation.append(permutation.charAt(i));

        return permutation.toString().compareTo(target) > 0;
    }
}
// @lc code=end
