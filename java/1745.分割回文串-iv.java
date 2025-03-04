/*
 * @lc app=leetcode.cn id=1745 lang=java
 *
 * [1745] 分割回文串 IV
 *
 * https://leetcode.cn/problems/palindrome-partitioning-iv/description/
 *
 * algorithms
 * Hard (52.70%)
 * Likes:    62
 * Dislikes: 0
 * Total Accepted:    10.8K
 * Total Submissions: 19.7K
 * Testcase Example:  '"abcbdd"'
 *
 * 给你一个字符串 s ，如果可以将它分割成三个 非空 回文子字符串，那么返回 true ，否则返回 false 。
 * 
 * 当一个字符串正着读和反着读是一模一样的，就称其为 回文字符串 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "abcbdd"
 * 输出：true
 * 解释："abcbdd" = "a" + "bcb" + "dd"，三个子字符串都是回文的。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "bcbddxy"
 * 输出：false
 * 解释：s 没办法被分割成 3 个回文子字符串。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 3 
 * s​​​​​​ 只包含小写英文字母。
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean checkPartitioning(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        for (int i = 0; i < n; i++)
            isPalindrome[i][i] = true;

        for (int i = 0; i < n - 1; i++)
            isPalindrome[i][i + 1] = s.charAt(i) == s.charAt(i + 1);

        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++)
                isPalindrome[i][j] = s.charAt(i) == s.charAt(j) && isPalindrome[i + 1][j - 1];
        }

        for (int i = 1; i < n; i++) {
            if (isPalindrome[0][i - 1]) {
                for (int j = i + 1; j < n; j++) {
                    if (isPalindrome[i][j - 1] && isPalindrome[j][n - 1])
                        return true;
                }
            }
        }

        return false;
    }
}
// @lc code=end
