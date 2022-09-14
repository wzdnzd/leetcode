/*
 * @lc app=leetcode.cn id=327 lang=java
 *
 * [327] 区间和的个数
 *
 * https://leetcode.cn/problems/count-of-range-sum/description/
 *
 * algorithms
 * Hard (41.23%)
 * Likes:    481
 * Dislikes: 0
 * Total Accepted:    34.1K
 * Total Submissions: 82.8K
 * Testcase Example:  '[-2,5,-1]\n-2\n2'
 *
 * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和
 * upper）之内的 区间和的个数 。
 * 
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [-2,5,-1], lower = -2, upper = 2
 * 输出：3
 * 解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [0], lower = 0, upper = 0
 * 输出：1
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * -2^31 
 * -10^5 
 * 题目数据保证答案是一个 32 位 的整数
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0 || lower > upper)
            return 0;

        int length = nums.length;
        long[] prefixSums = new long[length + 1];
        for (int i = 0; i < length; i++) {
            prefixSums[i + 1] = prefixSums[i] + (long) nums[i];
        }

        return mergeSort(prefixSums, 0, length, lower, upper);
    }

    private int mergeSort(long[] nums, int start, int end, int lower, int upper) {
        if (start >= end)
            return 0;

        int mid = start + (end - start) / 2;
        int nl = mergeSort(nums, start, mid, lower, upper);
        int nr = mergeSort(nums, mid + 1, end, lower, upper);

        return nl + nr + mergeAndCount(nums, start, mid, end, lower, upper);
    }

    private int mergeAndCount(long[] nums, int start, int mid, int end, int lower, int upper) {
        int index = start, count = 0;
        int left = mid + 1, right = mid + 1;
        while (index <= mid) {
            while (left <= end && nums[left] - nums[index] < lower)
                left++;
            while (right <= end && nums[right] - nums[index] <= upper)
                right++;

            count += right - left;
            index++;
        }

        int i = start, j = mid + 1, k = 0;
        long[] array = new long[end - start + 1];
        while (i <= mid && j <= end) {
            if (nums[i] <= nums[j])
                array[k++] = nums[i++];
            else
                array[k++] = nums[j++];
        }

        while (i <= mid)
            array[k++] = nums[i++];
        while (j <= end)
            array[k++] = nums[j++];

        System.arraycopy(array, 0, nums, start, array.length);
        return count;
    }
}
// @lc code=end
