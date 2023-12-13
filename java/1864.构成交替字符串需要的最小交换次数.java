/*
 * @lc app=leetcode.cn id=1864 lang=java
 *
 * [1864] 构成交替字符串需要的最小交换次数
 *
 * https://leetcode.cn/problems/minimum-number-of-swaps-to-make-the-binary-string-alternating/description/
 *
 * algorithms
 * Medium (40.95%)
 * Likes:    35
 * Dislikes: 0
 * Total Accepted:    7.7K
 * Total Submissions: 18.8K
 * Testcase Example:  '"111000"'
 *
 * 给你一个二进制字符串 s ，现需要将其转化为一个 交替字符串 。请你计算并返回转化所需的 最小 字符交换次数，如果无法完成转化，返回 -1 。
 * 
 * 交替字符串 是指：相邻字符之间不存在相等情况的字符串。例如，字符串 "010" 和 "1010" 属于交替字符串，但 "0100" 不是。
 * 
 * 任意两个字符都可以进行交换，不必相邻 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "111000"
 * 输出：1
 * 解释：交换位置 1 和 4："111000" -> "101010" ，字符串变为交替字符串。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "010"
 * 输出：0
 * 解释：字符串已经是交替字符串了，不需要交换。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：s = "1110"
 * 输出：-1
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * s[i] 的值为 '0' 或 '1'
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minSwaps(String s) {
        int zeros = 0, ones = 0;

        for (char c : s.toCharArray()) {
            if (c == '0')
                zeros++;
            else
                ones++;
        }

        if (Math.abs(zeros - ones) > 1)
            return -1;

        if (zeros > ones)
            return minSwapsHelper(s, '0');
        else if (zeros < ones)
            return minSwapsHelper(s, '1');
        else
            return Math.min(minSwapsHelper(s, '0'), minSwapsHelper(s, '1'));
    }

    private int minSwapsHelper(String s, char t) {
        int count = 0;

        for (char c : s.toCharArray()) {
            if (c != t)
                count++;

            t = t == '0' ? '1' : '0';
        }

        return count / 2;
    }
}
// @lc code=end
