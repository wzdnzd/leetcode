import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=76 lang=java
 *
 * [76] 最小覆盖子串
 *
 * https://leetcode.cn/problems/minimum-window-substring/description/
 *
 * algorithms
 * Hard (44.79%)
 * Likes:    2121
 * Dislikes: 0
 * Total Accepted:    342.3K
 * Total Submissions: 764.1K
 * Testcase Example:  '"ADOBECODEBANC"\n"ABC"'
 *
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 ""
 * 。
 * 
 * 
 * 
 * 注意：
 * 
 * 
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 
 * 
 * 示例 3:
 * 
 * 
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * s 和 t 由英文字母组成
 * 
 * 
 * 
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 */

// @lc code=start
class Solution {
    public String minWindow(String s, String t) {
        if ("".equals(s) || "".equals(t))
            return "";
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int count = map.getOrDefault(c, 0);
            map.put(c, count + 1);
        }

        int left = 0, right = 0;
        int valid = 0, start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right++);
            if (map.containsKey(c)) {
                int count = window.getOrDefault(c, 0) + 1;
                window.put(c, count);
                if (count == map.get(c))
                    valid++;
            }

            while (valid == map.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                c = s.charAt(left++);
                if (map.containsKey(c)) {
                    int count = window.getOrDefault(c, 0);
                    if (count == map.get(c))
                        valid--;
                    window.put(c, count - 1);
                }
            }
        }

        return len == Integer.MAX_VALUE ? "" : s.substring(start, len + start);
    }
}
// @lc code=end
