/*
 * @lc app=leetcode.cn id=1673 lang=java
 *
 * [1673] 找出最具竞争力的子序列
 *
 * https://leetcode.cn/problems/find-the-most-competitive-subsequence/description/
 *
 * algorithms
 * Medium (40.57%)
 * Likes:    117
 * Dislikes: 0
 * Total Accepted:    13.9K
 * Total Submissions: 33.6K
 * Testcase Example:  '[3,5,2,6]\n2'
 *
 * 给你一个整数数组 nums 和一个正整数 k ，返回长度为 k 且最具 竞争力 的 nums 子序列。
 * 
 * 数组的子序列是从数组中删除一些元素（可能不删除元素）得到的序列。
 * 
 * 在子序列 a 和子序列 b 第一个不相同的位置上，如果 a 中的数字小于 b 中对应的数字，那么我们称子序列 a 比子序列 b（相同长度下）更具 竞争力
 * 。 例如，[1,3,4] 比 [1,3,5] 更具竞争力，在第一个不相同的位置，也就是最后一个位置上， 4 小于 5 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [3,5,2,6], k = 2
 * 输出：[2,6]
 * 解释：在所有可能的子序列集合 {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]} 中，[2,6] 最具竞争力。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [2,4,3,3,5,4,9,6], k = 4
 * 输出：[2,3,3,4]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 0 
 * 1 
 * 
 * 
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<Integer>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && n - i + stack.size() > k && stack.peek() > nums[i])
                stack.pop();

            stack.push(nums[i]);
        }

        int[] result = new int[k];
        while (stack.size() > k)
            stack.pop();

        for (int i = k - 1; i >= 0; i--)
            result[i] = stack.pop();

        return result;
    }
}
// @lc code=end
