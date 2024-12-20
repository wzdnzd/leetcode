/*
 * @lc app=leetcode.cn id=3138 lang=java
 *
 * [3138] 同位字符串连接的最小长度
 *
 * https://leetcode.cn/problems/minimum-length-of-anagram-concatenation/description/
 *
 * algorithms
 * Medium (36.24%)
 * Likes:    12
 * Dislikes: 0
 * Total Accepted:    5.6K
 * Total Submissions: 14.1K
 * Testcase Example:  '"abba"'
 *
 * 给你一个字符串 s ，它由某个字符串 t 和若干 t  的 同位字符串 连接而成。
 * 
 * 请你返回字符串 t 的 最小 可能长度。
 * 
 * 同位字符串 指的是重新排列一个单词得到的另外一个字符串，原来字符串中的每个字符在新字符串中都恰好只使用一次。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "abba"
 * 
 * 输出：2
 * 
 * 解释：
 * 
 * 一个可能的字符串 t 为 "ba" 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "cdef"
 * 
 * 输出：4
 * 
 * 解释：
 * 
 * 一个可能的字符串 t 为 "cdef" ，注意 t 可能等于 s 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s.length <= 10^5
 * s 只包含小写英文字母。
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int minAnagramLength(String s) {
        int n = s.length();
        for (int i = 1; i < n; i++) {
            if (n % i != 0)
                continue;

            if (check(s, i))
                return i;
        }

        return n;
    }

    private boolean check(String s, int m) {
        int[] template = new int[26];
        for (int j = 0; j < s.length(); j += m) {
            int[] replicas = new int[26];

            for (int k = j; k < j + m; k++)
                replicas[s.charAt(k) - 'a']++;

            if (j > 0 && !Arrays.equals(template, replicas))
                return false;

            template = replicas;
        }

        return true;
    }
}
// @lc code=end
