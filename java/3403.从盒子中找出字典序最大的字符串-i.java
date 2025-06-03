/*
 * @lc app=leetcode.cn id=3403 lang=java
 *
 * [3403] 从盒子中找出字典序最大的字符串 I
 *
 * https://leetcode.cn/problems/find-the-lexicographically-largest-string-from-the-box-i/description/
 *
 * algorithms
 * Medium (29.77%)
 * Likes:    2
 * Dislikes: 0
 * Total Accepted:    3.3K
 * Total Submissions: 11K
 * Testcase Example:  '"dbca"\n2'
 *
 * 给你一个字符串 word 和一个整数 numFriends。
 * 
 * Alice 正在为她的 numFriends 位朋友组织一个游戏。游戏分为多个回合，在每一回合中：
 * 
 * 
 * word 被分割成 numFriends 个 非空 字符串，且该分割方式与之前的任意回合所采用的都 不完全相同 。
 * 所有分割出的字符串都会被放入一个盒子中。
 * 
 * 
 * 在所有回合结束后，找出盒子中 字典序最大的 字符串。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入: word = "dbca", numFriends = 2
 * 
 * 输出: "dbc"
 * 
 * 解释: 
 * 
 * 所有可能的分割方式为：
 * 
 * 
 * "d" 和 "bca"。
 * "db" 和 "ca"。
 * "dbc" 和 "a"。
 * 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入: word = "gggg", numFriends = 4
 * 
 * 输出: "g"
 * 
 * 解释: 
 * 
 * 唯一可能的分割方式为："g", "g", "g", 和 "g"。
 * 
 * 
 * 
 * 
 * 提示:
 * 
 * 
 * 1 <= word.length <= 5 * 10^3
 * word 仅由小写英文字母组成。
 * 1 <= numFriends <= word.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public String answerString(String word, int numFriends) {
        if (numFriends == 1)
            return word;
        
        int n = word.length();
        int left = 0, right = 1, step = 0;
        while (right + step < n) {
            if (word.charAt(left + step) == word.charAt(right + step))
                step++;
            else {
                if (word.charAt(left + step) < word.charAt(right + step))
                    left += step + 1;
                else
                    right += step + 1;

                step = 0;
                right = Math.max(right, left + 1);
            }
        }
        
        int largestLength = Math.min(n - numFriends + 1, n - left);
        return word.substring(left, left + largestLength);
    }
}
// @lc code=end

