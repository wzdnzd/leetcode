/*
 * @lc app=leetcode.cn id=2981 lang=java
 *
 * [2981] 找出出现至少三次的最长特殊子字符串 I
 *
 * https://leetcode.cn/problems/find-longest-special-substring-that-occurs-thrice-i/description/
 *
 * algorithms
 * Medium (48.11%)
 * Likes:    7
 * Dislikes: 0
 * Total Accepted:    5.3K
 * Total Submissions: 10.2K
 * Testcase Example:  '"aaaa"'
 *
 * 给你一个仅由小写英文字母组成的字符串 s 。
 * 
 * 如果一个字符串仅由单一字符组成，那么它被称为 特殊 字符串。例如，字符串 "abc" 不是特殊字符串，而字符串 "ddd"、"zz" 和 "f"
 * 是特殊字符串。
 * 
 * 返回在 s 中出现 至少三次 的 最长特殊子字符串 的长度，如果不存在出现至少三次的特殊子字符串，则返回 -1 。
 * 
 * 子字符串 是字符串中的一个连续 非空 字符序列。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "aaaa"
 * 输出：2
 * 解释：出现三次的最长特殊子字符串是 "aa" ：子字符串 "aaaa"、"aaaa" 和 "aaaa"。
 * 可以证明最大长度是 2 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "abcdef"
 * 输出：-1
 * 解释：不存在出现至少三次的特殊子字符串。因此返回 -1 。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：s = "abcaba"
 * 输出：1
 * 解释：出现三次的最长特殊子字符串是 "a" ：子字符串 "abcaba"、"abcaba" 和 "abcaba"。
 * 可以证明最大长度是 1 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 3 <= s.length <= 50
 * s 仅由小写英文字母组成。
 * 
 * 
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maximumLength(String s) {
        int result = -1, n = s.length(), consecutive = 0;
        Map<Character, Map<Integer, Integer>> records = new HashMap<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (i > 0 && c == s.charAt(i - 1))
                consecutive++;
            else
                consecutive = 1;

            records.putIfAbsent(c, new HashMap<Integer, Integer>());
            Map<Integer, Integer> counts = records.get(c);
            for (int k = Math.max(consecutive - 2, 1); k <= consecutive; k++) {
                int count = counts.getOrDefault(k, 0) + 1;
                counts.put(k, count);

                if (count >= 3)
                    result = Math.max(result, k);
            }
        }

        return result;
    }
}
// @lc code=end
