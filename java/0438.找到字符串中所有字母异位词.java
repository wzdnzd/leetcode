import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=438 lang=java
 *
 * [438] 找到字符串中所有字母异位词
 *
 * https://leetcode.cn/problems/find-all-anagrams-in-a-string/description/
 *
 * algorithms
 * Medium (54.78%)
 * Likes:    994
 * Dislikes: 0
 * Total Accepted:    226.3K
 * Total Submissions: 413.3K
 * Testcase Example:  '"cbaebabacd"\n"abc"'
 *
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * 
 * 
 * 
 * 
 * 提示:
 * 
 * 
 * 1 <= s.length, p.length <= 3 * 10^4
 * s 和 p 仅包含小写字母
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            int count = map.getOrDefault(c, 0);
            map.put(c, count + 1);
        }

        int left = 0, right = 0, valid = 0;
        List<Integer> list = new ArrayList<>();
        while (right < s.length()) {
            char c = s.charAt(right++);
            if (map.containsKey(c)) {
                int count = window.getOrDefault(c, 0) + 1;
                window.put(c, count);
                if (count == map.get(c))
                    valid++;
            }

            if (right - left >= p.length()) {
                if (valid == map.size())
                    list.add(left);
                c = s.charAt(left++);
                if (map.containsKey(c)) {
                    int count = window.getOrDefault(c, 0);
                    if (count == map.get(c))
                        valid--;
                    window.put(c, count - 1);
                }
            }
        }

        return list;
    }
}
// @lc code=end
