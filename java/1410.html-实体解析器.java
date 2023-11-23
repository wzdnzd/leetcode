/*
 * @lc app=leetcode.cn id=1410 lang=java
 *
 * [1410] HTML 实体解析器
 *
 * https://leetcode.cn/problems/html-entity-parser/description/
 *
 * algorithms
 * Medium (47.14%)
 * Likes:    36
 * Dislikes: 0
 * Total Accepted:    12K
 * Total Submissions: 24.5K
 * Testcase Example:  '"&amp; is an HTML entity but &ambassador; is not."'
 *
 * 「HTML 实体解析器」 是一种特殊的解析器，它将 HTML 代码作为输入，并用字符本身替换掉所有这些特殊的字符实体。
 * 
 * HTML 里这些特殊字符和它们对应的字符实体包括：
 * 
 * 
 * 双引号：字符实体为 " ，对应的字符是 " 。
 * 单引号：字符实体为 ' ，对应的字符是 ' 。
 * 与符号：字符实体为 & ，对应对的字符是 & 。
 * 大于号：字符实体为 > ，对应的字符是 > 。
 * 小于号：字符实体为 < ，对应的字符是 < 。
 * 斜线号：字符实体为 ⁄ ，对应的字符是 / 。
 * 
 * 
 * 给你输入字符串 text ，请你实现一个 HTML 实体解析器，返回解析器解析后的结果。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：text = "& is an HTML entity but &ambassador; is not."
 * 输出："& is an HTML entity but &ambassador; is not."
 * 解释：解析器把字符实体 & 用 & 替换
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：text = "and I quote: "...""
 * 输出："and I quote: \"...\""
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：text = "Stay home! Practice on Leetcode :)"
 * 输出："Stay home! Practice on Leetcode :)"
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：text = "x > y && x < y is always false"
 * 输出："x > y && x < y is always false"
 * 
 * 
 * 示例 5：
 * 
 * 
 * 输入：text = "leetcode.com⁄problemset⁄all"
 * 输出："leetcode.com/problemset/all"
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= text.length <= 10^5
 * 字符串可能包含 256 个ASCII 字符中的任意字符。
 * 
 * 
 */

// @lc code=start

import java.util.Map;

class Solution {
    public String entityParser(String text) {
        Map<String, String> map = Map.of(
                "&quot;", "\"",
                "&apos;", "'",
                "&amp;", "&",
                "&gt;", ">",
                "&lt;", "<",
                "&frasl;", "/");

        int n = text.length();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n;) {
            if (text.charAt(i) == '&') {
                int j = i + 1;
                while (j < n && j - i < 6 && text.charAt(j) != ';')
                    j++;
                String slice = text.substring(i, Math.min(j + 1, n));
                if (map.containsKey(slice)) {
                    sb.append(map.get(slice));
                    i = j + 1;
                    continue;
                }
            }

            sb.append(text.charAt(i++));
        }

        return sb.toString();
    }
}
// @lc code=end
