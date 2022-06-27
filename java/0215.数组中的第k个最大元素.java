/*
 * @lc app=leetcode.cn id=215 lang=java
 *
 * [215] 数组中的第K个最大元素
 *
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/description/
 *
 * algorithms
 * Medium (64.75%)
 * Likes:    1709
 * Dislikes: 0
 * Total Accepted:    656.2K
 * Total Submissions: 1M
 * Testcase Example:  '[3,2,1,5,6,4]\n2'
 *
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 
 * 
 * 
 * 提示： 
 * 
 * 
 * 1 
 * -10^4 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findKthLargest(int[] nums, int k) {
        heapSort(nums);
        return nums[k - 1];
    }

    private void swap(int[] nums, int i, int j) {
        int v = nums[i];
        nums[i] = nums[j];
        nums[j] = v;
    }

    private void heapify(int[] nums, int start, int end) {
        int pivot = nums[start];
        int index = 2 * start + 1;

        while (index < end) {
            if ((index + 1) < end && nums[index] > nums[index + 1]) {
                index++;
            }

            if (pivot < nums[index])
                break;

            nums[start] = nums[index];
            start = index;
            index = 2 * start + 1;

        }

        nums[start] = pivot;
    }

    /**
     * 详见：https://mp.weixin.qq.com/s/b6bikBaQDsgC_S_wO8CVwA?
     * 
     * @param nums
     */
    private void heapSort(int[] nums) {
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            heapify(nums, i, nums.length);
        }

        for (int i = nums.length - 1; i > 0; i--) {
            swap(nums, 0, i);
            heapify(nums, 0, i);
        }
    }
}
// @lc code=end
