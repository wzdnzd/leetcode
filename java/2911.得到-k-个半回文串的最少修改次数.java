/*
 * @lc app=leetcode.cn id=2911 lang=java
 *
 * [2911] 得到 K 个半回文串的最少修改次数
 *
 * https://leetcode.cn/problems/minimum-changes-to-make-k-semi-palindromes/description/
 *
 * algorithms
 * Hard (49.00%)
 * Likes:    29
 * Dislikes: 0
 * Total Accepted:    4.3K
 * Total Submissions: 7.7K
 * Testcase Example:  '"abcac"\n2'
 *
 * 给你一个字符串 s 和一个整数 k，请将 s 划分为 k 个 非空子串 ，使得将每个子串变为 半回文串 所需的字符修改次数之和最小。
 * 
 * 返回所需的 最少 字符修改次数。
 * 
 * 半回文串 是一类特殊的字符串：它可以按照某种重复模式拆分后，使每一组都成为 回文串 。判断一个字符串是否为半回文串的方法如下：
 * 
 * 
 * 选择该字符串长度的一个正因数 d。其中，d 的取值范围是从 1 到严格小于字符串长度的所有正因数。对于长度为 1
 * 的字符串，根据这一定义，它不存在合法的因数，因为唯一的因数就是其长度本身，而这是不允许的。
 * 对于给定的因数 d，将字符串按长度为 d 的重复模式分组。具体来说，第 1 组由位置 1、1 + d、1 + 2d、…… 上的字符组成；第 2 组由位置
 * 2、2 + d、2 + 2d、…… 上的字符组成；以此类推。
 * 如果这些分组中的每一组都是回文串，则该字符串被视为半回文串。
 * 
 * 
 * 以字符串 "abcabc" 为例：
 * 
 * 
 * "abcabc" 的长度为 6。合法的因数有 1、2 和 3。
 * 当 d = 1 时：整个字符串 "abcabc" 构成一组。它不是回文串。
 * 当 d = 2 时：
 * 
 * 第 1 组（位置 1, 3, 5）："acb"
 * 第 2 组（位置 2, 4, 6）："bac"
 * 这两组都不是回文串。
 * 
 * 
 * 当 d = 3 时：
 * 
 * 第 1 组（位置 1, 4）："aa"
 * 第 2 组（位置 2, 5）："bb"
 * 第 3 组（位置 3, 6）："cc"
 * 所有分组都是回文串。因此，"abcabc" 是一个半回文串。
 * 
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：  s = "abcac", k = 2 
 * 
 * 输出：  1 
 * 
 * 解释： 将 s 划分为 "ab" 和 "cac"。"cac" 本身已经是半回文串。将 "ab" 改为 "aa" 后，它在 d = 1
 * 时成为半回文串。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：  s = "abcdef", k = 2 
 * 
 * 输出：  2 
 * 
 * 解释： 将其划分为子串 "abc" 和 "def"。这两个子串各自都需要修改 1 个字符才能变成半回文串。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：  s = "aabbaa", k = 3 
 * 
 * 输出：  0 
 * 
 * 解释： 将其划分为子串 "aa"、"bb" 和 "aa"。它们都已经是半回文串。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 2 <= s.length <= 200
 * 1 <= k <= s.length / 2
 * s 仅由小写英文字母组成。
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    private static final int INFINITY = Integer.MAX_VALUE / 2;

    public int minimumChanges(String s, int k) {
        int n = s.length();

        int[][] minChanges = new int[n - 1][n];
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++)
                minChanges[i][j] = calculateMinChanges(s, i, j);
        }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, INFINITY);
        dp[0] = 0;

        for (int i = 1; i <= k; i++) {
            for (int j = n; j >= i * 2; j--) {
                dp[j] = INFINITY;

                int minStart = 2 * (i - 1), maxStart = j - 2;
                for (int p = minStart; p <= maxStart; p++)
                    dp[j] = Math.min(dp[j], dp[p] + minChanges[p][j - 1]);
            }
        }

        return dp[n];
    }

    private int calculateMinChanges(String s, int start, int end) {
        int subLength = end - start + 1, minChanges = subLength;

        for (int d = 1; d * d <= subLength; d++) {
            if (subLength % d != 0)
                continue;

            minChanges = Math.min(minChanges, calculateMinChangesWithGroups(s, start, end, d));
            if (d > 1)
                minChanges = Math.min(minChanges, calculateMinChangesWithGroups(s, start, end, subLength / d));
        }

        return minChanges;
    }

    private int calculateMinChangesWithGroups(String s, int start, int end, int groups) {
        int minChanges = 0, subLength = end - start + 1;
        for (int first = 0, last = subLength - groups; first < groups; first++, last++) {
            for (int left = first, right = last; left < right; left += groups, right -= groups) {
                if (s.charAt(start + left) != s.charAt(start + right))
                    minChanges++;
            }
        }

        return minChanges;
    }
}

// @lc code=end
