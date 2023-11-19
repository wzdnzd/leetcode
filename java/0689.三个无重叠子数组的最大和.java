/*
 * @lc app=leetcode.cn id=689 lang=java
 *
 * [689] 三个无重叠子数组的最大和
 *
 * https://leetcode.cn/problems/maximum-sum-of-3-non-overlapping-subarrays/description/
 *
 * algorithms
 * Hard (56.08%)
 * Likes:    348
 * Dislikes: 0
 * Total Accepted:    24K
 * Total Submissions: 42.4K
 * Testcase Example:  '[1,2,1,2,6,7,5,1]\n2'
 *
 * 给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且全部数字和（3 * k 项）最大的子数组，并返回这三个子数组。
 * 
 * 以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。如果有多个结果，返回字典序最小的一个。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,2,1,2,6,7,5,1], k = 2
 * 输出：[0,3,5]
 * 解释：子数组 [1, 2], [2, 6], [7, 5] 对应的起始下标为 [0, 3, 5]。
 * 也可以取 [2, 1], 但是结果 [1, 3, 5] 在字典序上更大。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1,2,1,2,1,2,1,2,1], k = 2
 * 输出：[0,2,4]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 2 * 10^4
 * 1 <= nums[i] < 2^16
 * 1 <= k <= floor(nums.length / 3)
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] indexes = new int[3], maxIndexes = new int[3];
        int[] sums = new int[3], maxSums = new int[3];

        for (int i = k * 2; i < nums.length; i++) {
            for (int j = 0; j < 3; j++)
                sums[j] += nums[i - k * (2 - j)];

            if (i >= 3 * k - 1) {
                if (sums[0] > maxSums[0]) {
                    maxSums[0] = sums[0];
                    maxIndexes[0] = i - 3 * k + 1;
                }

                if (maxSums[0] + sums[1] > maxSums[1]) {
                    maxSums[1] = maxSums[0] + sums[1];
                    maxIndexes[1] = maxIndexes[0];
                    maxIndexes[2] = i - k * 2 + 1;
                }

                if (maxSums[1] + sums[2] > maxSums[2]) {
                    maxSums[2] = maxSums[1] + sums[2];
                    indexes[0] = maxIndexes[1];
                    indexes[1] = maxIndexes[2];
                    indexes[2] = i - k + 1;
                }

                for (int j = 0; j < 3; j++)
                    sums[j] -= nums[i - (3 - j) * k + 1];
            }
        }

        return indexes;
    }
}
// @lc code=end
