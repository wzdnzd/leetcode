/*
 * @lc app=leetcode.cn id=680 lang=java
 *
 * [680] 验证回文串 II
 *
 * https://leetcode.cn/problems/valid-palindrome-ii/description/
 *
 * algorithms
 * Easy (40.13%)
 * Likes:    629
 * Dislikes: 0
 * Total Accepted:    144K
 * Total Submissions: 358.8K
 * Testcase Example:  '"aba"'
 *
 * 给你一个字符串 s，最多 可以从中删除一个字符。
 * 
 * 请你判断 s 是否能成为回文字符串：如果能，返回 true ；否则，返回 false 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "aba"
 * 输出：true
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "abca"
 * 输出：true
 * 解释：你可以删除字符 'c' 。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：s = "abc"
 * 输出：false
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s.length <= 10^5
 * s 由小写英文字母组成
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean validPalindrome(String s) {
        int n = s.length();
        int l = 0, r = n - 1;

        while (l < r && s.charAt(l) == s.charAt(r)) {
            l++;
            r--;
        }

        if (l >= r)
            return true;

        return isPalindrome(s, l + 1, r) || isPalindrome(s, l, r - 1);
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l < r && s.charAt(l) == s.charAt(r)) {
            l++;
            r--;
        }

        return l >= r;
    }
}
// @lc code=end
