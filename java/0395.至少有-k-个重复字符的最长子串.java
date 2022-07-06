/*
 * @lc app=leetcode.cn id=395 lang=java
 *
 * [395] 至少有 K 个重复字符的最长子串
 *
 * https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/description/
 *
 * algorithms
 * Medium (52.51%)
 * Likes:    703
 * Dislikes: 0
 * Total Accepted:    68.1K
 * Total Submissions: 129.6K
 * Testcase Example:  '"aaabb"\n3'
 *
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "aaabb", k = 3
 * 输出：3
 * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "ababbc", k = 2
 * 输出：5
 * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * s 仅由小写英文字母组成
 * 1 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int longestSubstring(String s, int k) {
        return dfs(s, 0, s.length() - 1, k);
    }

    private int dfs(String s, int left, int right, int k) {
        int[] charsCount = new int[26];
        for (int i = left; i <= right; i++) {
            int index = s.charAt(i) - 'a';
            charsCount[index] += 1;
        }

        char c = 0;
        for (int i = 0; i < charsCount.length; i++) {
            if (charsCount[i] > 0 && charsCount[i] < k) {
                c = (char) (i + 'a');
                break;
            }
        }

        if (c == 0)
            return right - left + 1;

        int i = left, ans = 0;
        while (i <= right) {
            while (i <= right && s.charAt(i) == c)
                i++;
            if (i > right)
                break;
            int start = i;
            while (i <= right && s.charAt(i) != c)
                i++;
            ans = Math.max(ans, dfs(s, start, i - 1, k));
        }

        return ans;
    }
}
// @lc code=end
