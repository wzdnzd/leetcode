/*
 * @lc app=leetcode.cn id=1278 lang=java
 *
 * [1278] 分割回文串 III
 *
 * https://leetcode.cn/problems/palindrome-partitioning-iii/description/
 *
 * algorithms
 * Hard (64.10%)
 * Likes:    156
 * Dislikes: 0
 * Total Accepted:    13.1K
 * Total Submissions: 19.1K
 * Testcase Example:  '"abc"\n2'
 *
 * 给你一个由小写字母组成的字符串 s，和一个整数 k。
 * 
 * 请你按下面的要求分割字符串：
 * 
 * 
 * 首先，你可以将 s 中的部分字符修改为其他的小写英文字母。
 * 接着，你需要把 s 分割成 k 个非空且不相交的子串，并且每个子串都是回文串。
 * 
 * 
 * 请返回以这种方式分割字符串所需修改的最少字符数。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：s = "abc", k = 2
 * 输出：1
 * 解释：你可以把字符串分割成 "ab" 和 "c"，并修改 "ab" 中的 1 个字符，将它变成回文串。
 * 
 * 
 * 示例 2：
 * 
 * 输入：s = "aabbc", k = 3
 * 输出：0
 * 解释：你可以把字符串分割成 "aa"、"bb" 和 "c"，它们都是回文串。
 * 
 * 示例 3：
 * 
 * 输入：s = "leetcode", k = 8
 * 输出：0
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= k <= s.length <= 100
 * s 中只含有小写英文字母。
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    static final int INFINITY = Integer.MAX_VALUE / 2;

    public int palindromePartition(String s, int k) {
        int n = s.length();
        int[][] minChanges = new int[n][n];
        for (int i = 0; i < n - 1; i++)
            minChanges[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 0 : 1;

        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++)
                minChanges[i][j] = minChanges[i + 1][j - 1] + (s.charAt(i) == s.charAt(j) ? 0 : 1);
        }

        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], INFINITY);

        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            int maxCount = Math.min(i, k);

            for (int j = 1; j <= maxCount; j++) {
                for (int p = j - 1; p < i; p++)
                    dp[i][j] = Math.min(dp[i][j], dp[p][j - 1] + minChanges[p][i - 1]);

            }
        }

        return dp[n][k];
    }
}
// @lc code=end
