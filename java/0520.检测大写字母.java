/*
 * @lc app=leetcode.cn id=520 lang=java
 *
 * [520] 检测大写字母
 *
 * https://leetcode.cn/problems/detect-capital/description/
 *
 * algorithms
 * Easy (56.77%)
 * Likes:    253
 * Dislikes: 0
 * Total Accepted:    91.5K
 * Total Submissions: 161.4K
 * Testcase Example:  '"USA"'
 *
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 * 
 * 
 * 全部字母都是大写，比如 "USA" 。
 * 单词中所有字母都不是大写，比如 "leetcode" 。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。
 * 
 * 
 * 给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：word = "USA"
 * 输出：true
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：word = "FlaG"
 * 输出：false
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= word.length <= 100
 * word 由小写和大写英文字母组成
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean detectCapitalUse(String word) {
        if (word.length() >= 2 && Character.isLowerCase(word.charAt(0))
                && Character.isUpperCase(word.charAt(1)))
            return false;

        for (int i = 2; i < word.length(); ++i) {
            if (Character.isLowerCase(word.charAt(i)) ^ Character.isLowerCase(word.charAt(1)))
                return false;
        }

        return true;
    }
}
// @lc code=end
