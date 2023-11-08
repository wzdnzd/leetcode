/*
 * @lc app=leetcode.cn id=2609 lang=java
 *
 * [2609] 最长平衡子字符串
 *
 * https://leetcode.cn/problems/find-the-longest-balanced-substring-of-a-binary-string/description/
 *
 * algorithms
 * Easy (49.37%)
 * Likes:    31
 * Dislikes: 0
 * Total Accepted:    13.5K
 * Total Submissions: 24.8K
 * Testcase Example:  '"01000111"'
 *
 * 给你一个仅由 0 和 1 组成的二进制字符串 s 。  
 * 
 * 如果子字符串中 所有的 0 都在 1 之前 且其中 0 的数量等于 1 的数量，则认为 s
 * 的这个子字符串是平衡子字符串。请注意，空子字符串也视作平衡子字符串。 
 * 
 * 返回  s 中最长的平衡子字符串长度。
 * 
 * 子字符串是字符串中的一个连续字符序列。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "01000111"
 * 输出：6
 * 解释：最长的平衡子字符串是 "000111" ，长度为 6 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "00111"
 * 输出：4
 * 解释：最长的平衡子字符串是 "0011" ，长度为  4 。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：s = "111"
 * 输出：0
 * 解释：除了空子字符串之外不存在其他平衡子字符串，所以答案为 0 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s.length <= 50
 * '0' <= s[i] <= '1'
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findTheLongestBalancedSubstring(String s) {
        int zero = 0, one = 0, count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                one++;
                count = Math.max(count, Math.min(zero, one));
            } else if (i == 0 || s.charAt(i - 1) == '1') {
                zero = 1;
                one = 0;
            } else
                zero++;
        }

        return count << 1;
    }
}
// @lc code=end
