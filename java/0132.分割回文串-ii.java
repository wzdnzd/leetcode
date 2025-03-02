/*
 * @lc app=leetcode.cn id=132 lang=java
 *
 * [132] 分割回文串 II
 *
 * https://leetcode.cn/problems/palindrome-partitioning-ii/description/
 *
 * algorithms
 * Hard (49.85%)
 * Likes:    779
 * Dislikes: 0
 * Total Accepted:    98.1K
 * Total Submissions: 196.1K
 * Testcase Example:  '"aab"'
 *
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串。
 * 
 * 返回符合要求的 最少分割次数 。
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "a"
 * 输出：0
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：s = "ab"
 * 输出：1
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 * 
 * 
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int minCut(String S) {
        char[] s = S.toCharArray();
        int n = s.length;

        boolean[][] isPalindrome = new boolean[n][n];
        for (boolean[] row : isPalindrome)
            Arrays.fill(row, true);

        for (int l = n - 2; l >= 0; l--) {
            for (int r = l + 1; r < n; r++)
                isPalindrome[l][r] = s[l] == s[r] && isPalindrome[l + 1][r - 1];
        }

        int[] dp = new int[n];
        for (int r = 0; r < n; r++) {
            if (isPalindrome[0][r])
                continue;

            int result = Integer.MAX_VALUE;
            for (int l = 1; l <= r; l++) {
                if (isPalindrome[l][r])
                    result = Math.min(result, dp[l - 1] + 1);

            }

            dp[r] = result;
        }

        return dp[n - 1];
    }
}
// @lc code=end
