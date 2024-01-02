/*
 * @lc app=leetcode.cn id=2006 lang=java
 *
 * [2006] 差的绝对值为 K 的数对数目
 *
 * https://leetcode.cn/problems/count-number-of-pairs-with-absolute-difference-k/description/
 *
 * algorithms
 * Easy (83.05%)
 * Likes:    103
 * Dislikes: 0
 * Total Accepted:    57.7K
 * Total Submissions: 69.5K
 * Testcase Example:  '[1,2,2,1]\n1'
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你返回数对 (i, j) 的数目，满足 i < j 且 |nums[i] - nums[j]| == k
 * 。
 * 
 * |x| 的值定义为：
 * 
 * 
 * 如果 x >= 0 ，那么值为 x 。
 * 如果 x < 0 ，那么值为 -x 。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums = [1,2,2,1], k = 1
 * 输出：4
 * 解释：差的绝对值为 1 的数对为：
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 * 
 * 
 * 示例 2：
 * 
 * 输入：nums = [1,3], k = 3
 * 输出：0
 * 解释：没有任何数对差的绝对值为 3 。
 * 
 * 
 * 示例 3：
 * 
 * 输入：nums = [3,2,1,5,4], k = 2
 * 输出：3
 * 解释：差的绝对值为 2 的数对为：
 * - [3,2,1,5,4]
 * - [3,2,1,5,4]
 * - [3,2,1,5,4]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 * 1 <= k <= 99
 * 
 * 
 */

// @lc code=start

class Solution {
    public int countKDifference(int[] nums, int k) {
        int[] records = new int[101];
        int result = 0;

        for (int num : nums) {
            if (num - k >= 0)
                result += records[num - k];
            if (num + k < records.length)
                result += records[num + k];

            records[num]++;
        }

        return result;
    }
}
// @lc code=end
