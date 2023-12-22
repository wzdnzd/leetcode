/*
 * @lc app=leetcode.cn id=2932 lang=java
 *
 * [2932] 找出强数对的最大异或值 I
 *
 * https://leetcode.cn/problems/maximum-strong-pair-xor-i/description/
 *
 * algorithms
 * Easy (74.14%)
 * Likes:    3
 * Dislikes: 0
 * Total Accepted:    5.2K
 * Total Submissions: 6.9K
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * 给你一个下标从 0 开始的整数数组 nums 。如果一对整数 x 和 y 满足以下条件，则称其为 强数对 ：
 * 
 * 
 * |x - y| <= min(x, y)
 * 
 * 
 * 你需要从 nums 中选出两个整数，且满足：这两个整数可以形成一个强数对，并且它们的按位异或（XOR）值是在该数组所有强数对中的 最大值 。
 * 
 * 返回数组 nums 所有可能的强数对中的 最大 异或值。
 * 
 * 注意，你可以选择同一个整数两次来形成一个强数对。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,2,3,4,5]
 * 输出：7
 * 解释：数组 nums 中有 11 个强数对：(1, 1), (1, 2), (2, 2), (2, 3), (2, 4), (3, 3), (3,
 * 4), (3, 5), (4, 4), (4, 5) 和 (5, 5) 。
 * 这些强数对中的最大异或值是 3 XOR 4 = 7 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [10,100]
 * 输出：0
 * 解释：数组 nums 中有 2 个强数对：(10, 10) 和 (100, 100) 。
 * 这些强数对中的最大异或值是 10 XOR 10 = 0 ，数对 (100, 100) 的异或值也是 100 XOR 100 = 0 。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [5,6,25,30]
 * 输出：7
 * 解释：数组 nums 中有 6 个强数对：(5, 5), (5, 6), (6, 6), (25, 25), (25, 30) 和 (30, 30) 。
 * 这些强数对中的最大异或值是 25 XOR 30 = 7 ；另一个异或值非零的数对是 (5, 6) ，其异或值是 5 XOR 6 = 3 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 50
 * 1 <= nums[i] <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maximumStrongPairXor(int[] nums) {
        int result = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) <= Math.min(nums[i], nums[j]))
                    result = Math.max(result, nums[i] ^ nums[j]);
            }
        }

        return result;
    }
}
// @lc code=end
