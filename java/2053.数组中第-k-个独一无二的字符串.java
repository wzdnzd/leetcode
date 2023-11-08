/*
 * @lc app=leetcode.cn id=2053 lang=java
 *
 * [2053] 数组中第 K 个独一无二的字符串
 *
 * https://leetcode.cn/problems/kth-distinct-string-in-an-array/description/
 *
 * algorithms
 * Easy (71.47%)
 * Likes:    10
 * Dislikes: 0
 * Total Accepted:    10.9K
 * Total Submissions: 15.3K
 * Testcase Example:  '["d","b","c","b","c","a"]\n2'
 *
 * 独一无二的字符串 指的是在一个数组中只出现过 一次 的字符串。
 * 
 * 给你一个字符串数组 arr 和一个整数 k ，请你返回 arr 中第 k 个 独一无二的字符串 。如果 少于 k 个独一无二的字符串，那么返回 空字符串
 * "" 。
 * 
 * 注意，按照字符串在原数组中的 顺序 找到第 k 个独一无二字符串。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入：arr = ["d","b","c","b","c","a"], k = 2
 * 输出："a"
 * 解释：
 * arr 中独一无二字符串包括 "d" 和 "a" 。
 * "d" 首先出现，所以它是第 1 个独一无二字符串。
 * "a" 第二个出现，所以它是 2 个独一无二字符串。
 * 由于 k == 2 ，返回 "a" 。
 * 
 * 
 * 示例 2:
 * 
 * 输入：arr = ["aaa","aa","a"], k = 1
 * 输出："aaa"
 * 解释：
 * arr 中所有字符串都是独一无二的，所以返回第 1 个字符串 "aaa" 。
 * 
 * 
 * 示例 3：
 * 
 * 输入：arr = ["a","b","a"], k = 3
 * 输出：""
 * 解释：
 * 唯一一个独一无二字符串是 "b" 。由于少于 3 个独一无二字符串，我们返回空字符串 "" 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= k <= arr.length <= 1000
 * 1 <= arr[i].length <= 5
 * arr[i] 只包含小写英文字母。
 * 
 * 
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution {
    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> map = new HashMap<>();

        for (String word : arr)
            map.put(word, map.getOrDefault(word, 0) + 1);

        for (String word : arr) {
            if (map.get(word) == 1)
                k--;
            if (k == 0)
                return word;
        }

        return "";
    }
}
// @lc code=end
