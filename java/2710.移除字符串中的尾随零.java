/*
 * @lc app=leetcode.cn id=2710 lang=java
 *
 * [2710] 移除字符串中的尾随零
 *
 * https://leetcode.cn/problems/remove-trailing-zeros-from-a-string/description/
 *
 * algorithms
 * Easy (81.69%)
 * Likes:    16
 * Dislikes: 0
 * Total Accepted:    12.7K
 * Total Submissions: 15.3K
 * Testcase Example:  '"51230100"'
 *
 * 给你一个用字符串表示的正整数 num ，请你以字符串形式返回不含尾随零的整数 num 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：num = "51230100"
 * 输出："512301"
 * 解释：整数 "51230100" 有 2 个尾随零，移除并返回整数 "512301" 。
 * 
 * 
 * 示例 2：
 * 
 * 输入：num = "123"
 * 输出："123"
 * 解释：整数 "123" 不含尾随零，返回整数 "123" 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= num.length <= 1000
 * num 仅由数字 0 到 9 组成
 * num 不含前导零
 * 
 * 
 */

// @lc code=start
class Solution {
    public String removeTrailingZeros(String num) {
        int index = num.length() - 1;
        while (index >= 0 && num.charAt(index) == '0')
            index--;

        return num.substring(0, index + 1);
    }
}
// @lc code=end
