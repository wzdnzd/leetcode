/*
 * @lc app=leetcode.cn id=905 lang=java
 *
 * [905] 按奇偶排序数组
 *
 * https://leetcode.cn/problems/sort-array-by-parity/description/
 *
 * algorithms
 * Easy (71.01%)
 * Likes:    367
 * Dislikes: 0
 * Total Accepted:    123.7K
 * Total Submissions: 174.2K
 * Testcase Example:  '[3,1,2,4]'
 *
 * 给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。
 * 
 * 返回满足此条件的 任一数组 作为答案。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [3,1,2,4]
 * 输出：[2,4,3,1]
 * 解释：[4,2,3,1]、[2,4,1,3] 和 [4,2,1,3] 也会被视作正确答案。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [0]
 * 输出：[0]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 5000
 * 0 <= nums[i] <= 5000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int n = nums.length;
        int[] arrays = new int[n];
        int left = 0, right = n - 1;

        for (int num : nums) {
            if (num % 2 == 0)
                arrays[left++] = num;
            else
                arrays[right--] = num;
        }

        return arrays;
    }
}

// @lc code=end
