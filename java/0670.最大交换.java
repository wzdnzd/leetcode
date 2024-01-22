/*
 * @lc app=leetcode.cn id=670 lang=java
 *
 * [670] 最大交换
 *
 * https://leetcode.cn/problems/maximum-swap/description/
 *
 * algorithms
 * Medium (48.01%)
 * Likes:    450
 * Dislikes: 0
 * Total Accepted:    81.4K
 * Total Submissions: 167K
 * Testcase Example:  '2736'
 *
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 * 
 * 示例 1 :
 * 
 * 
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 * 
 * 
 * 示例 2 :
 * 
 * 
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 * 
 * 
 * 注意:
 * 
 * 
 * 给定数字的范围是 [0, 10^8]
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maximumSwap(int num) {
        int[] digits = new int[10];
        char[] chars = String.valueOf(num).toCharArray();

        for (int i = 0; i < chars.length; i++)
            digits[chars[i] - '0'] = i;

        for (int i = 0; i < chars.length; i++) {
            for (int j = 9; j > chars[i] - '0'; j--) {
                if (digits[j] > i) {
                    char temp = chars[i];
                    chars[i] = chars[digits[j]];
                    chars[digits[j]] = temp;
                    return Integer.parseInt(new String(chars));
                }
            }
        }

        return num;
    }
}
// @lc code=end
