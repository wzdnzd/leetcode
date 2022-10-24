/*
 * @lc app=leetcode.cn id=709 lang=java
 *
 * [709] 转换成小写字母
 *
 * https://leetcode.cn/problems/to-lower-case/description/
 *
 * algorithms
 * Easy (77.15%)
 * Likes:    216
 * Dislikes: 0
 * Total Accepted:    114.5K
 * Total Submissions: 148.4K
 * Testcase Example:  '"Hello"'
 *
 * 给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "Hello"
 * 输出："hello"
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "here"
 * 输出："here"
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：s = "LOVELY"
 * 输出："lovely"
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * s 由 ASCII 字符集中的可打印字符组成
 * 
 * 
 */

// @lc code=start
class Solution {
    public String toLowerCase(String s) {
        if ("".equals(s))
            return "";

        char[] chars = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 65 && c < 91)
                c += 32;
            chars[i] = (char) c;
        }

        return new String(chars);
    }
}
// @lc code=end
