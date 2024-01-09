/*
 * @lc app=leetcode.cn id=2707 lang=java
 *
 * [2707] 字符串中的额外字符
 *
 * https://leetcode.cn/problems/extra-characters-in-a-string/description/
 *
 * algorithms
 * Medium (46.01%)
 * Likes:    40
 * Dislikes: 0
 * Total Accepted:    5.4K
 * Total Submissions: 10.7K
 * Testcase Example:  '"leetscode"\n["leet","code","leetcode"]'
 *
 * 给你一个下标从 0 开始的字符串 s 和一个单词字典 dictionary 。你需要将 s 分割成若干个 互不重叠 的子字符串，每个子字符串都在
 * dictionary 中出现过。s 中可能会有一些 额外的字符 不在任何子字符串中。
 * 
 * 请你采取最优策略分割 s ，使剩下的字符 最少 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：s = "leetscode", dictionary = ["leet","code","leetcode"]
 * 输出：1
 * 解释：将 s 分成两个子字符串：下标从 0 到 3 的 "leet" 和下标从 5 到 8 的 "code" 。只有 1 个字符没有使用（下标为
 * 4），所以我们返回 1 。
 * 
 * 
 * 示例 2：
 * 
 * 输入：s = "sayhelloworld", dictionary = ["hello","world"]
 * 输出：3
 * 解释：将 s 分成两个子字符串：下标从 3 到 7 的 "hello" 和下标从 8 到 12 的 "world" 。下标为 0 ，1 和 2
 * 的字符没有使用，所以我们返回 3 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s.length <= 50
 * 1 <= dictionary.length <= 50
 * 1 <= dictionary[i].length <= 50
 * dictionary[i] 和 s 只包含小写英文字母。
 * dictionary 中的单词互不相同。
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int minExtraChar(String s, String[] dictionary) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            for (String word : dictionary) {
                int len = word.length();
                if (i + len <= n && s.substring(i, i + len).equals(word))
                    dp[i + len] = Math.min(dp[i + len], dp[i]);
            }

            dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
        }

        return dp[n];
    }
}
// @lc code=end
