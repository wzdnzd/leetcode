/*
 * @lc app=leetcode.cn id=2815 lang=java
 *
 * [2815] 数组中的最大数对和
 *
 * https://leetcode.cn/problems/max-pair-sum-in-an-array/description/
 *
 * algorithms
 * Easy (68.07%)
 * Likes:    14
 * Dislikes: 0
 * Total Accepted:    7.9K
 * Total Submissions: 11.6K
 * Testcase Example:  '[51,71,17,24,42]'
 *
 * 给你一个下标从 0 开始的整数数组 nums 。请你从 nums 中找出和 最大 的一对数，且这两个数数位上最大的数字相等。
 * 
 * 返回最大和，如果不存在满足题意的数字对，返回 -1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums = [51,71,17,24,42]
 * 输出：88
 * 解释：
 * i = 1 和 j = 2 ，nums[i] 和 nums[j] 数位上最大的数字相等，且这一对的总和 71 + 17 = 88 。 
 * i = 3 和 j = 4 ，nums[i] 和 nums[j] 数位上最大的数字相等，且这一对的总和 24 + 42 = 66 。
 * 可以证明不存在其他数对满足数位上最大的数字相等，所以答案是 88 。
 * 
 * 示例 2：
 * 
 * 输入：nums = [1,2,3,4]
 * 输出：-1
 * 解释：不存在数对满足数位上最大的数字相等。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 2 <= nums.length <= 100
 * 1 <= nums[i] <= 10^4
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int maxSum(int[] nums) {
        int result = -1;
        int[] records = new int[10];
        Arrays.fill(records, Integer.MIN_VALUE);

        for (int num : nums) {
            int bit = 0;
            for (int x = num; x > 0; x /= 10)
                bit = Math.max(bit, x % 10);

            result = Math.max(result, num + records[bit]);
            records[bit] = Math.max(records[bit], num);
        }

        return result;
    }
}
// @lc code=end
