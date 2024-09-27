/*
 * @lc app=leetcode.cn id=2516 lang=java
 *
 * [2516] 每种字符至少取 K 个
 *
 * https://leetcode.cn/problems/take-k-of-each-character-from-left-and-right/description/
 *
 * algorithms
 * Medium (41.41%)
 * Likes:    53
 * Dislikes: 0
 * Total Accepted:    10.2K
 * Total Submissions: 23.3K
 * Testcase Example:  '"aabaaaacaabc"\n2'
 *
 * 给你一个由字符 'a'、'b'、'c' 组成的字符串 s 和一个非负整数 k 。每分钟，你可以选择取走 s 最左侧 还是 最右侧 的那个字符。
 * 
 * 你必须取走每种字符 至少 k 个，返回需要的 最少 分钟数；如果无法取到，则返回 -1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "aabaaaacaabc", k = 2
 * 输出：8
 * 解释：
 * 从 s 的左侧取三个字符，现在共取到两个字符 'a' 、一个字符 'b' 。
 * 从 s 的右侧取五个字符，现在共取到四个字符 'a' 、两个字符 'b' 和两个字符 'c' 。
 * 共需要 3 + 5 = 8 分钟。
 * 可以证明需要的最少分钟数是 8 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "a", k = 1
 * 输出：-1
 * 解释：无法取到一个字符 'b' 或者 'c'，所以返回 -1 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s.length <= 10^5
 * s 仅由字母 'a'、'b'、'c' 组成
 * 0 <= k <= s.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int takeCharacters(String s, int k) {
        int n = s.length();
        int[] records = new int[3];

        for (int i = 0; i < n; i++)
            records[s.charAt(i) - 'a']++;

        if (records[0] < k || records[1] < k || records[2] < k)
            return -1;

        int left = 0, count = n;
        for (int right = 0; right < n; right++) {
            records[s.charAt(right) - 'a']--;
            while (left < right && (records[0] < k || records[1] < k || records[2] < k)) {
                records[s.charAt(left) - 'a']++;
                left++;
            }

            if (records[0] >= k && records[1] >= k && records[2] >= k)
                count = Math.min(count, n - (right - left + 1));
        }

        return count;
    }
}
// @lc code=end
