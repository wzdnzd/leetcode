/*
 * @lc app=leetcode.cn id=3144 lang=java
 *
 * [3144] 分割字符频率相等的最少子字符串
 *
 * https://leetcode.cn/problems/minimum-substring-partition-of-equal-character-frequency/description/
 *
 * algorithms
 * Medium (50.31%)
 * Likes:    6
 * Dislikes: 0
 * Total Accepted:    3.4K
 * Total Submissions: 6.4K
 * Testcase Example:  '"fabccddg"'
 *
 * 给你一个字符串 s ，你需要将它分割成一个或者更多的 平衡 子字符串。比方说，s == "ababcc" 那么 ("abab", "c", "c")
 * ，("ab", "abc", "c") 和 ("ababcc") 都是合法分割，但是 ("a", "bab", "cc") ，("aba", "bc",
 * "c") 和 ("ab", "abcc") 不是，不平衡的子字符串用粗体表示。
 * 
 * 请你返回 s 最少 能分割成多少个平衡子字符串。
 * 
 * 注意：一个 平衡 字符串指的是字符串中所有字符出现的次数都相同。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "fabccddg"
 * 
 * 输出：3
 * 
 * 解释：
 * 
 * 我们可以将 s 分割成 3 个子字符串：("fab, "ccdd", "g") 或者 ("fabc", "cd", "dg") 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "abababaccddb"
 * 
 * 输出：2
 * 
 * 解释：
 * 
 * 我们可以将 s 分割成 2 个子字符串：("abab", "abaccddb") 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s.length <= 1000
 * s 只包含小写英文字母。
 * 
 * 
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minimumSubstringsInPartition(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int minPartitions = i;
            Map<Character, Integer> counts = new HashMap<>();
            int maxCount = 0, maxCountCharacters = 0;

            for (int j = i - 1; j >= 0; j--) {
                char c = s.charAt(j);
                counts.put(c, counts.getOrDefault(c, 0) + 1);
                int count = counts.get(c);
                if (count == maxCount)
                    maxCountCharacters++;
                else if (count > maxCount) {
                    maxCount = count;
                    maxCountCharacters = 1;
                }

                if (maxCount * maxCountCharacters == i - j)
                    minPartitions = Math.min(minPartitions, dp[j] + 1);
            }

            dp[i] = minPartitions;
        }

        return dp[n];
    }
}
// @lc code=end
