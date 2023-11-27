/*
 * @lc app=leetcode.cn id=907 lang=java
 *
 * [907] 子数组的最小值之和
 *
 * https://leetcode.cn/problems/sum-of-subarray-minimums/description/
 *
 * algorithms
 * Medium (38.48%)
 * Likes:    730
 * Dislikes: 0
 * Total Accepted:    55.5K
 * Total Submissions: 141.9K
 * Testcase Example:  '[3,1,2,4]'
 *
 * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 * 
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：arr = [3,1,2,4]
 * 输出：17
 * 解释：
 * 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。 
 * 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
 * 
 * 示例 2：
 * 
 * 
 * 输入：arr = [11,81,94,43,3]
 * 输出：444
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 1 
 * 
 * 
 * 
 * 
 */

// @lc code=start

import java.util.Deque;
import java.util.LinkedList;

class Solution {
    private static final int MOD = 1000000007;

    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] left = new int[n], right = new int[n];
        Deque<Integer> stack = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i])
                stack.pop();

            left[i] = i - (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }

        stack.clear();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i])
                stack.pop();

            right[i] = (stack.isEmpty() ? n : stack.peek()) - i;
            stack.push(i);
        }

        long ans = 0;
        for (int i = 0; i < n; i++)
            ans = (ans + (long) arr[i] * left[i] * right[i]) % MOD;

        return (int) ans;
    }
}
// @lc code=end
