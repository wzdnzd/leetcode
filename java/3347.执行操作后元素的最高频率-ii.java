/*
 * @lc app=leetcode.cn id=3347 lang=java
 *
 * [3347] 执行操作后元素的最高频率 II
 *
 * https://leetcode.cn/problems/maximum-frequency-of-an-element-after-performing-operations-ii/description/
 *
 * algorithms
 * Hard (41.28%)
 * Likes:    15
 * Dislikes: 0
 * Total Accepted:    5.8K
 * Total Submissions: 10.4K
 * Testcase Example:  '[1,4,5]\n1\n2'
 *
 * 给你一个整数数组 nums 和两个整数 k 和 numOperations 。
 * 
 * 你必须对 nums 执行 操作  numOperations 次。每次操作中，你可以：
 * 
 * 
 * 选择一个下标 i ，它在之前的操作中 没有 被选择过。
 * 将 nums[i] 增加范围 [-k, k] 中的一个整数。
 * 
 * 
 * 在执行完所有操作以后，请你返回 nums 中出现 频率最高 元素的出现次数。
 * 
 * 一个元素 x 的 频率 指的是它在数组中出现的次数。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,4,5], k = 1, numOperations = 2
 * 
 * 输出：2
 * 
 * 解释：
 * 
 * 通过以下操作得到最高频率 2 ：
 * 
 * 
 * 将 nums[1] 增加 0 ，nums 变为 [1, 4, 5] 。
 * 将 nums[2] 增加 -1 ，nums 变为 [1, 4, 4] 。
 * 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [5,11,20,20], k = 5, numOperations = 1
 * 
 * 输出：2
 * 
 * 解释：
 * 
 * 通过以下操作得到最高频率 2 ：
 * 
 * 
 * 将 nums[1] 增加 0 。
 * 
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= k <= 10^9
 * 0 <= numOperations <= nums.length
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Arrays.sort(nums);

        int maximumFrequencyInNums = maxFrequencyInNums(nums, k, numOperations);
        if (maximumFrequencyInNums >= numOperations)
            return maximumFrequencyInNums;

        int maximumFrequencyNotInNums = maxFrequencyNotInNums(nums, k, numOperations);
        return Math.max(maximumFrequencyInNums, maximumFrequencyNotInNums);
    }

    private int maxFrequencyInNums(int[] nums, int k, int numOperations) {
        int start = 0, end = 0;
        int n = nums.length, maximumFrequency = 0;
        int targetIndex = 0, targetFrequency = 0;

        while (targetIndex < n) {
            int target = nums[targetIndex];
            targetFrequency = 1;
            while (targetIndex + 1 < n && nums[targetIndex + 1] == target) {
                targetIndex++;
                targetFrequency++;
            }

            while (nums[start] < target - k)
                start++;

            while (end + 1 < n && nums[end + 1] <= target + k)
                end++;

            maximumFrequency = Math.max(maximumFrequency, Math.min(end - start + 1, targetFrequency + numOperations));
            targetIndex++;
        }

        return maximumFrequency;
    }

    private int maxFrequencyNotInNums(int[] nums, int k, int numOperations) {
        int start = 0, end = 0;
        int n = nums.length, maximumFrequency = 0;

        while (end < n) {
            while (nums[end] - nums[start] > 2 * k)
                start++;

            maximumFrequency = Math.max(maximumFrequency, Math.min(end - start + 1, numOperations));
            end++;
        }

        return maximumFrequency;
    }
}
// @lc code=end
