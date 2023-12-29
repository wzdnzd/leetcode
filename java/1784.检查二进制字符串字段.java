/*
 * @lc app=leetcode.cn id=1784 lang=java
 *
 * [1784] 检查二进制字符串字段
 *
 * https://leetcode.cn/problems/check-if-binary-string-has-at-most-one-segment-of-ones/description/
 *
 * algorithms
 * Easy (59.42%)
 * Likes:    75
 * Dislikes: 0
 * Total Accepted:    40.7K
 * Total Submissions: 68.5K
 * Testcase Example:  '"1001"'
 *
 * 给你一个二进制字符串 s ，该字符串 不含前导零 。
 * 
 * 如果 s 包含 零个或一个由连续的 '1' 组成的字段 ，返回 true​​​ 。否则，返回 false 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "1001"
 * 输出：false
 * 解释：由连续若干个 '1' 组成的字段数量为 2，返回 false
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "110"
 * 输出：true
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s.length <= 100
 * s[i]​​​​ 为 '0' 或 '1'
 * s[0] 为 '1'
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean checkOnesSegment(String s) {
        boolean flag = false;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0')
                flag = true;
            else if (flag && s.charAt(i) == '1')
                return false;
        }

        return true;
    }
}
// @lc code=end
