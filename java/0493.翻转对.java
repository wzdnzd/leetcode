/*
 * @lc app=leetcode.cn id=493 lang=java
 *
 * [493] 翻转对
 *
 * https://leetcode.cn/problems/reverse-pairs/description/
 *
 * algorithms
 * Hard (35.78%)
 * Likes:    372
 * Dislikes: 0
 * Total Accepted:    34.6K
 * Total Submissions: 96.7K
 * Testcase Example:  '[1,3,2,3,1]'
 *
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * 
 * 你需要返回给定数组中的重要翻转对的数量。
 * 
 * 示例 1:
 * 
 * 
 * 输入: [1,3,2,3,1]
 * 输出: 2
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: [2,4,3,5,1]
 * 输出: 3
 * 
 * 
 * 注意:
 * 
 * 
 * 给定数组的长度不会超过50000。
 * 输入数组中的所有数字都在32位整数的表示范围内。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        return reversePairsRecursive(nums, 0, nums.length - 1);
    }

    private int reversePairsRecursive(int[] nums, int start, int end) {
        if (start >= end)
            return 0;

        int mid = start + (end - start) / 2;
        int nl = reversePairsRecursive(nums, start, mid);
        int nr = reversePairsRecursive(nums, mid + 1, end);

        return nl + nr + mergeAndCount(nums, start, mid, end);
    }

    private int mergeAndCount(int[] nums, int start, int mid, int end) {
        int i = start, j = mid + 1, count = 0;
        while (i <= mid) {
            while (j <= end && (long) nums[i] > 2 * (long) nums[j])
                j++;

            count += j - mid - 1;
            i++;
        }

        int p1 = start, p2 = mid + 1, index = 0;
        int[] array = new int[end - start + 1];
        while (p1 <= mid && p2 <= end) {
            if (nums[p1] <= nums[p2])
                array[index++] = nums[p1++];
            else
                array[index++] = nums[p2++];
        }

        while (p1 <= mid)
            array[index++] = nums[p1++];
        while (p2 <= end)
            array[index++] = nums[p2++];

        System.arraycopy(array, 0, nums, start, array.length);

        return count;
    }
}
// @lc code=end
