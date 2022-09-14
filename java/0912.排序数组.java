import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=912 lang=java
 *
 * [912] 排序数组
 *
 * https://leetcode.cn/problems/sort-an-array/description/
 *
 * algorithms
 * Medium (55.60%)
 * Likes:    678
 * Dislikes: 0
 * Total Accepted:    448.4K
 * Total Submissions: 807.6K
 * Testcase Example:  '[5,2,3,1]'
 *
 * 给你一个整数数组 nums，请你将该数组升序排列。
 * 
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 5 * 10^4
 * -5 * 10^4 <= nums[i] <= 5 * 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] sortArray(int[] nums) {
        return mergeSort(nums);
    }

    private int[] mergeSort(int[] nums) {
        if (nums == null || nums.length == 0)
            return nums;

        return sort(nums, 0, nums.length - 1);
    }

    private int[] sort(int[] nums, int start, int end) {
        if (start >= end)
            return nums;

        int mid = start + (end - start) / 2;
        sort(nums, start, mid);
        sort(nums, mid + 1, end);

        return merge(nums, start, mid, end);
    }

    private int[] merge(int[] nums, int start, int mid, int end) {
        if (nums == null || nums.length == 0)
            return nums;

        int i = start, j = mid + 1, index = 0;
        int[] array = new int[end - start + 1];
        while (i <= mid && j <= end) {
            if (nums[i] <= nums[j])
                array[index++] = nums[i++];
            else
                array[index++] = nums[j++];
        }

        while (i <= mid)
            array[index++] = nums[i++];
        while (j <= end)
            array[index++] = nums[j++];

        System.arraycopy(array, 0, nums, start, array.length);
        return nums;
    }
}
// @lc code=end
