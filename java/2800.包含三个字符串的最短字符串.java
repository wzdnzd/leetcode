/*
 * @lc app=leetcode.cn id=2800 lang=java
 *
 * [2800] 包含三个字符串的最短字符串
 *
 * https://leetcode.cn/problems/shortest-string-that-contains-three-strings/description/
 *
 * algorithms
 * Medium (34.95%)
 * Likes:    20
 * Dislikes: 0
 * Total Accepted:    4.6K
 * Total Submissions: 13.3K
 * Testcase Example:  '"abc"\n"bca"\n"aaa"'
 *
 * 给你三个字符串 a ，b 和 c ， 你的任务是找到长度 最短 的字符串，且这三个字符串都是它的 子字符串 。
 * 如果有多个这样的字符串，请你返回 字典序最小 的一个。
 * 
 * 请你返回满足题目要求的字符串。
 * 
 * 注意：
 * 
 * 
 * 两个长度相同的字符串 a 和 b ，如果在第一个不相同的字符处，a 的字母在字母表中比 b 的字母 靠前 ，那么字符串 a 比字符串 b 字典序小
 * 。
 * 子字符串 是一个字符串中一段连续的字符序列。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：a = "abc", b = "bca", c = "aaa"
 * 输出："aaabca"
 * 解释：字符串 "aaabca" 包含所有三个字符串：a = ans[2...4] ，b = ans[3..5] ，c = ans[0..2]
 * 。结果字符串的长度至少为 6 ，且"aaabca" 是字典序最小的一个。
 * 
 * 示例 2：
 * 
 * 输入：a = "ab", b = "ba", c = "aba"
 * 输出："aba"
 * 解释：字符串 "aba" 包含所有三个字符串：a = ans[0..1] ，b = ans[1..2] ，c = ans[0..2] 。由于 c
 * 的长度为 3 ，结果字符串的长度至少为 3 。"aba" 是字典序最小的一个。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= a.length, b.length, c.length <= 100
 * a ，b ，c 只包含小写英文字母。
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public String minimumString(String a, String b, String c) {
        List<String> ls = new ArrayList<>();
        ls.add(concat(a, b, c));
        ls.add(concat(a, c, b));
        ls.add(concat(b, a, c));
        ls.add(concat(b, c, a));
        ls.add(concat(c, a, b));
        ls.add(concat(c, b, a));

        Collections.sort(ls, (x, y) -> {
            int l1 = x.length(), l2 = y.length();
            return l1 != l2 ? l1 - l2 : x.compareTo(y);
        });

        return ls.get(0);
    }

    public String concat(String a, String b, String c) {
        return concatTwo(concatTwo(a, b), c);
    }

    public String concatTwo(String a, String b) {
        for (int i = 0; i <= b.length(); ++i) {
            String s = a + b.substring(b.length() - i, b.length());
            if (s.indexOf(b) != -1)
                return s;
        }

        return "";
    }
}
// @lc code=end
