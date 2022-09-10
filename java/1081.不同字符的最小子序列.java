import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=1081 lang=java
 *
 * [1081] 不同字符的最小子序列
 *
 * https://leetcode.cn/problems/smallest-subsequence-of-distinct-characters/description/
 *
 * algorithms
 * Medium (58.15%)
 * Likes:    162
 * Dislikes: 0
 * Total Accepted:    22.3K
 * Total Submissions: 38.4K
 * Testcase Example:  '"bcabc"'
 *
 * 返回 s 字典序最小的子序列，该子序列包含 s 的所有不同字符，且只包含一次。
 * 
 * 注意：该题与 316 https://leetcode.com/problems/remove-duplicate-letters/ 相同
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
 * 1 
 * s 由小写英文字母组成
 * 
 * 
 */

// @lc code=start
class Solution {
    public String smallestSubsequence(String s) {
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        Deque<Character> queue = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!set.contains(c)) {
                while (!queue.isEmpty() && queue.peekLast() > c && map.get(queue.peekLast()) > 0)
                    set.remove(queue.pollLast());

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
