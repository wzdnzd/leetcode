/*
 * @lc app=leetcode.cn id=1542 lang=java
 *
 * [1542] 找出最长的超赞子字符串
 *
 * https://leetcode.cn/problems/find-longest-awesome-substring/description/
 *
 * algorithms
 * Hard (45.09%)
 * Likes:    110
 * Dislikes: 0
 * Total Accepted:    9.5K
 * Total Submissions: 18.7K
 * Testcase Example:  '"3242415"'
 *
 * 给你一个字符串 s 。请返回 s 中最长的 超赞子字符串 的长度。
 * 
 * 「超赞子字符串」需满足满足下述两个条件：
 * 
 * 
 * 该字符串是 s 的一个非空子字符串
 * 进行任意次数的字符交换后，该字符串可以变成一个回文字符串
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：s = "3242415"
 * 输出：5
 * 解释："24241" 是最长的超赞子字符串，交换其中的字符后，可以得到回文 "24142"
 * 
 * 
 * 示例 2：
 * 
 * 输入：s = "12345678"
 * 输出：1
 * 
 * 
 * 示例 3：
 * 
 * 输入：s = "213123"
 * 输出：6
 * 解释："213123" 是最长的超赞子字符串，交换其中的字符后，可以得到回文 "231132"
 * 
 * 
 * 示例 4：
 * 
 * 输入：s = "00"
 * 输出：2
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s.length <= 10^5
 * s 仅由数字组成
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int longestAwesome(String s) {
        int n = s.length(), result = 0;

        int[] indices = new int[1 << 10];
        Arrays.fill(indices, n);
        indices[0] = -1;

        int mask = 0;
        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';
            mask ^= 1 << digit;

            if (indices[mask] < n) {
                int currLength = i - indices[mask];
                result = Math.max(result, currLength);
            } else
                indices[mask] = i;

            for (int j = 0; j < 10; j++) {
                int prevMask = mask ^ (1 << j);
                if (indices[prevMask] < n) {
                    int currLength = i - indices[prevMask];
                    result = Math.max(result, currLength);
                }
            }
        }

        return result;
    }
}
// @lc code=end
