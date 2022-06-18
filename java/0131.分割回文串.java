import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
 * @lc app=leetcode.cn id=131 lang=java
 *
 * [131] 分割回文串
 *
 * https://leetcode.cn/problems/palindrome-partitioning/description/
 *
 * algorithms
 * Medium (72.97%)
 * Likes:    1156
 * Dislikes: 0
 * Total Accepted:    201.2K
 * Total Submissions: 275.8K
 * Testcase Example:  '"aab"'
 *
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * 
 * 回文串 是正着读和反着读都一样的字符串。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "a"
 * 输出：[["a"]]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * s 仅由小写英文字母组成
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> arrays = new ArrayList<>();
        int length = s.length();

        if (length == 0) {
            return arrays;
        }

        boolean[][] dp = new boolean[length][length];
        char[] chars = s.toCharArray();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j <= i; j++) {
                if (chars[i] == chars[j] && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                }
            }
        }

        Deque<String> stack = new ArrayDeque<>();
        dfs(s, 0, length, dp, stack, arrays);

        return arrays;
    }

    private void dfs(String s, int start, int end, boolean[][] dp, Deque<String> path, List<List<String>> result) {
        if (start == end) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < end; i++) {
            if (dp[start][i]) {
                path.addLast(s.substring(start, i + 1));
                dfs(s, i + 1, end, dp, path, result);
                path.removeLast();
            }
        }
    }
}
// @lc code=end