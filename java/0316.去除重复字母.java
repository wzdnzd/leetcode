import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=316 lang=java
 *
 * [316] 去除重复字母
 *
 * https://leetcode.cn/problems/remove-duplicate-letters/description/
 *
 * algorithms
 * Medium (48.00%)
 * Likes:    808
 * Dislikes: 0
 * Total Accepted:    99.8K
 * Total Submissions: 207.9K
 * Testcase Example:  '"bcabc"'
 *
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "bcabc"
 * 输出："abc"
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s.length <= 10^4
 * s 由小写英文字母组成
 * 
 * 
 * 
 * 
 * 注意：该题与 1081
 * https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters
 * 相同
 * 
 */

// @lc code=start
class Solution {
    public String removeDuplicateLetters(String s) {
        Deque<Character> queue = new LinkedList<>();
        Set<Character> set = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int count = map.getOrDefault(c, 0);
            map.put(c, count + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!set.contains(c)) {
                while (!queue.isEmpty() && queue.peekLast() > c && map.get(queue.peekLast()) > 0) {
                    Character last = queue.pollLast();
                    set.remove(last);
                }

                set.add(c);
                queue.addLast(c);
            }

            map.put(c, map.get(c) - 1);
        }

        char[] chars = new char[queue.size()];
        int idx = 0;
        while (!queue.isEmpty())
            chars[idx++] = queue.pollFirst();

        return new String(chars);
    }
}
// @lc code=end
