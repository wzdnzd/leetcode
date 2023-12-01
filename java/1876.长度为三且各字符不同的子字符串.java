/*
 * @lc app=leetcode.cn id=1876 lang=java
 *
 * [1876] 长度为三且各字符不同的子字符串
 *
 * https://leetcode.cn/problems/substrings-of-size-three-with-distinct-characters/description/
 *
 * algorithms
 * Easy (70.48%)
 * Likes:    29
 * Dislikes: 0
 * Total Accepted:    18.5K
 * Total Submissions: 26.2K
 * Testcase Example:  '"xyzzaz"'
 *
 * 如果一个字符串不含有任何重复字符，我们称这个字符串为 好 字符串。
 * 
 * 给你一个字符串 s ，请你返回 s 中长度为 3 的 好子字符串 的数量。
 * 
 * 注意，如果相同的好子字符串出现多次，每一次都应该被记入答案之中。
 * 
 * 子字符串 是一个字符串中连续的字符序列。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "xyzzaz"
 * 输出：1
 * 解释：总共有 4 个长度为 3 的子字符串："xyz"，"yzz"，"zza" 和 "zaz" 。
 * 唯一的长度为 3 的好子字符串是 "xyz" 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "aababcabc"
 * 输出：4
 * 解释：总共有 7 个长度为 3 的子字符串："aab"，"aba"，"bab"，"abc"，"bca"，"cab" 和 "abc" 。
 * 好子字符串包括 "abc"，"bca"，"cab" 和 "abc" 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * s​​​​​​ 只包含小写英文字母。
 * 
 * 
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int countGoodSubstrings(String s) {
        int left = 0, right = 0;
        int n = s.length(), count = 0;
        Map<Character, Integer> window = new HashMap<>();

        while (right < n) {
            char c = s.charAt(right++);
            window.put(c, window.getOrDefault(c, 0) + 1);

            while (right - left == 3) {
                if (window.entrySet().stream().parallel().filter(e -> e.getValue() == 1).count() == 3)
                    count++;

                c = s.charAt(left++);
                window.put(c, window.get(c) - 1);
            }
        }

        return count;
    }
}
// @lc code=end
