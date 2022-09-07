import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=567 lang=java
 *
 * [567] 字符串的排列
 *
 * https://leetcode.cn/problems/permutation-in-string/description/
 *
 * algorithms
 * Medium (44.17%)
 * Likes:    756
 * Dislikes: 0
 * Total Accepted:    219.3K
 * Total Submissions: 496.5K
 * Testcase Example:  '"ab"\n"eidbaooo"'
 *
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
 * 
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s1.length, s2.length <= 10^4
 * s1 和 s2 仅包含小写字母
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            int count = map.getOrDefault(c, 0);
            map.put(c, count + 1);
        }

        int left = 0, right = 0, valid = 0;
        while (right < s2.length()) {
            char c = s2.charAt(right++);
            if (map.containsKey(c)) {
                int count = window.getOrDefault(c, 0) + 1;
                window.put(c, count);
                if (count == map.get(c))
                    valid++;
            }

            if (right - left >= s1.length()) {
                if (valid == map.size())
                    return true;
                c = s2.charAt(left++);
                if (map.containsKey(c)) {
                    int count = window.getOrDefault(c, 0);
                    if (count == map.get(c))
                        valid--;
                    window.put(c, count - 1);
                }
            }
        }

        return false;
    }
}
// @lc code=end
