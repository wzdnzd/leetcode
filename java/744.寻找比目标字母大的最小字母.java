/*
 * @lc app=leetcode.cn id=744 lang=java
 *
 * [744] 寻找比目标字母大的最小字母
 *
 * https://leetcode.cn/problems/find-smallest-letter-greater-than-target/description/
 *
 * algorithms
 * Easy (48.44%)
 * Likes:    283
 * Dislikes: 0
 * Total Accepted:    117K
 * Total Submissions: 241.1K
 * Testcase Example:  '["c","f","j"]\n"a"'
 *
 * 给你一个字符数组 letters，该数组按非递减顺序排序，以及一个字符 target。letters 里至少有两个不同的字符。
 * 
 * 返回 letters 中大于 target 的最小的字符。如果不存在这样的字符，则返回 letters 的第一个字符。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入: letters = ["c", "f", "j"]，target = "a"
 * 输出: "c"
 * 解释：letters 中字典上比 'a' 大的最小字符是 'c'。
 * 
 * 示例 2:
 * 
 * 
 * 输入: letters = ["c","f","j"], target = "c"
 * 输出: "f"
 * 解释：letters 中字典顺序上大于 'c' 的最小字符是 'f'。
 * 
 * 示例 3:
 * 
 * 
 * 输入: letters = ["x","x","y","y"], target = "z"
 * 输出: "x"
 * 解释：letters 中没有一个字符在字典上大于 'z'，所以我们返回 letters[0]。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 2 <= letters.length <= 10^4
 * letters[i] 是一个小写字母
 * letters 按非递减顺序排序
 * letters 最少包含两个不同的字母
 * target 是一个小写字母
 * 
 * 
 */

// @lc code=start
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;

        if (target >= letters[n - 1])
            return letters[0];

        int left = 0, right = n - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (letters[mid] > target)
                right = mid;
            else
                left = mid + 1;
        }

        return letters[left];
    }
}
// @lc code=end
