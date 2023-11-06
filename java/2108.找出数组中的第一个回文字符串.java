/*
 * @lc app=leetcode.cn id=2108 lang=java
 *
 * [2108] 找出数组中的第一个回文字符串
 *
 * https://leetcode.cn/problems/find-first-palindromic-string-in-the-array/description/
 *
 * algorithms
 * Easy (77.70%)
 * Likes:    12
 * Dislikes: 0
 * Total Accepted:    15.2K
 * Total Submissions: 19.6K
 * Testcase Example:  '["abc","car","ada","racecar","cool"]'
 *
 * 给你一个字符串数组 words ，找出并返回数组中的 第一个回文字符串 。如果不存在满足要求的字符串，返回一个 空字符串 "" 。
 * 
 * 回文字符串 的定义为：如果一个字符串正着读和反着读一样，那么该字符串就是一个 回文字符串 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：words = ["abc","car","ada","racecar","cool"]
 * 输出："ada"
 * 解释：第一个回文字符串是 "ada" 。
 * 注意，"racecar" 也是回文字符串，但它不是第一个。
 * 
 * 
 * 示例 2：
 * 
 * 输入：words = ["notapalindrome","racecar"]
 * 输出："racecar"
 * 解释：第一个也是唯一一个回文字符串是 "racecar" 。
 * 
 * 
 * 示例 3：
 * 
 * 输入：words = ["def","ghi"]
 * 输出：""
 * 解释：不存在回文字符串，所以返回一个空字符串。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] 仅由小写英文字母组成
 * 
 * 
 */

// @lc code=start
class Solution {
    public String firstPalindrome(String[] words) {
        for (String word : words) {
            if (isPalindrome(word))
                return word;
        }

        return "";
    }

    private boolean isPalindrome(String word) {
        int n = word.length();
        for (int i = 0; i < n / 2; i++) {
            if (word.charAt(i) != word.charAt(n - i - 1))
                return false;
        }

        return true;
    }

}
// @lc code=end
