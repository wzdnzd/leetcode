/*
 * @lc app=leetcode.cn id=917 lang=java
 *
 * [917] 仅仅反转字母
 *
 * https://leetcode.cn/problems/reverse-only-letters/description/
 *
 * algorithms
 * Easy (59.31%)
 * Likes:    206
 * Dislikes: 0
 * Total Accepted:    81.1K
 * Total Submissions: 136.7K
 * Testcase Example:  '"ab-cd"'
 *
 * 给你一个字符串 s ，根据下述规则反转字符串：
 * 
 * 
 * 所有非英文字母保留在原有位置。
 * 所有英文字母（小写或大写）位置反转。
 * 
 * 
 * 返回反转后的 s 。
 * 
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "ab-cd"
 * 输出："dc-ba"
 * 
 * 
 * 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 * 
 * 
 * 
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：s = "Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 * 
 * 
 * 
 * 
 * 提示
 * 
 * 
 * 1 <= s.length <= 100
 * s 仅由 ASCII 值在范围 [33, 122] 的字符组成
 * s 不含 '\"' 或 '\\'
 * 
 * 
 */

// @lc code=start
class Solution {
    public String reverseOnlyLetters(String s) {
        int left = 0, right = s.length() - 1;
        char[] chars = s.toCharArray();

        while (true) {
            while (left < right && !Character.isLetter(chars[left]))
                left++;

            while (left < right && !Character.isLetter(chars[right]))
                right--;

            if (left >= right)
                break;

            char c = chars[left];
            chars[left++] = chars[right];
            chars[right--] = c;
        }

        return new String(chars);
    }
}
// @lc code=end
