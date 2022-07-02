/*
 * @lc app=leetcode.cn id=324 lang=java
 *
 * [324] 摆动排序 II
 *
 * https://leetcode.cn/problems/wiggle-sort-ii/description/
 *
 * algorithms
 * Medium (38.65%)
 * Likes:    478
 * Dislikes: 0
 * Total Accepted:    53.3K
 * Total Submissions: 131K
 * Testcase Example:  '[1,5,1,1,6,4]'
 *
 * 给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 * 
 * 你可以假设所有输入数组都可以得到满足题目要求的结果。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,5,1,1,6,4]
 * 输出：[1,6,1,5,1,4]
 * 解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1,3,2,2,3,1]
 * 输出：[2,3,1,3,1,2]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 0 
 * 题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果
 * 
 * 
 * 
 * 
 * 进阶：你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 * 
 */

// @lc code=start
class Solution {
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int x = quickSelect(nums, 0, n - 1, (n + 1) >> 1);
        int l = 0, r = n - 1, loc = 0;
        while (loc <= r) {
            if (nums[map(loc, n)] > x)
                swap(nums, map(loc++, n), map(l++, n));
            else if (nums[map(loc, n)] < x)
                swap(nums, map(loc, n), map(r--, n));
            else
                loc++;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int value = nums[i];
        nums[i] = nums[j];
        nums[j] = value;
    }

    private int map(int index, int length) {
        return (2 * index + 1) % (length | 1);
    }

    private int quickSelect(int[] nums, int l, int r, int k) {
        if (l == r)
            return nums[l];

        int x = nums[(l + r) >> 1], i = l - 1, j = r + 1;
        while (i < j) {
            do
                i++;
            while (nums[i] < x);
            do
                j--;
            while (nums[j] > x);
            if (i < j)
                swap(nums, i, j);
        }

        int cnt = j - l + 1;
        if (k <= cnt)
            return quickSelect(nums, l, j, k);
        else
            return quickSelect(nums, j + 1, r, k - cnt);
    }
}
// @lc code=end
