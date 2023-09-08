/*
 * @lc app=leetcode.cn id=2099 lang=java
 *
 * [2099] 找到和最大的长度为 K 的子序列
 *
 * https://leetcode.cn/problems/find-subsequence-of-length-k-with-the-largest-sum/description/
 *
 * algorithms
 * Easy (48.32%)
 * Likes:    39
 * Dislikes: 0
 * Total Accepted:    9.9K
 * Total Submissions: 20.5K
 * Testcase Example:  '[2,1,3,3]\n2'
 *
 * 给你一个整数数组 nums 和一个整数 k 。你需要找到 nums 中长度为 k 的 子序列 ，且这个子序列的 和最大 。
 * 
 * 请你返回 任意 一个长度为 k 的整数子序列。
 * 
 * 子序列 定义为从一个数组里删除一些元素后，不改变剩下元素的顺序得到的数组。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums = [2,1,3,3], k = 2
 * 输出：[3,3]
 * 解释：
 * 子序列有最大和：3 + 3 = 6 。
 * 
 * 示例 2：
 * 
 * 输入：nums = [-1,-2,3,4], k = 3
 * 输出：[-1,3,4]
 * 解释：
 * 子序列有最大和：-1 + 3 + 4 = 6 。
 * 
 * 
 * 示例 3：
 * 
 * 输入：nums = [3,4,3,3], k = 2
 * 输出：[3,4]
 * 解释：
 * 子序列有最大和：3 + 4 = 7 。
 * 另一个可行的子序列为 [4, 3] 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 1000
 * -10^5 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        int[][] arrays = new int[n][2];
        for (int i = 0; i < n; i++) {
            arrays[i][0] = i;
            arrays[i][1] = nums[i];
        }

        Arrays.sort(arrays, (a, b) -> b[1] - a[1]);
        int[] indexes = new int[k];
        for (int i = 0; i < k; i++) {
            indexes[i] = arrays[i][0];
        }

        Arrays.sort(indexes);

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = nums[indexes[i]];
        }

        return ans;
    }
}
// @lc code=end
