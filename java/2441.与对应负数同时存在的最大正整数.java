/*
 * @lc app=leetcode.cn id=2441 lang=java
 *
 * [2441] 与对应负数同时存在的最大正整数
 *
 * https://leetcode.cn/problems/largest-positive-integer-that-exists-with-its-negative/description/
 *
 * algorithms
 * Easy (72.49%)
 * Likes:    45
 * Dislikes: 0
 * Total Accepted:    31.8K
 * Total Submissions: 43.9K
 * Testcase Example:  '[-1,2,-3,3]'
 *
 * 给你一个 不包含 任何零的整数数组 nums ，找出自身与对应的负数都在数组中存在的最大正整数 k 。
 * 
 * 返回正整数 k ，如果不存在这样的整数，返回 -1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [-1,2,-3,3]
 * 输出：3
 * 解释：3 是数组中唯一一个满足题目要求的 k 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [-1,10,6,7,-7,1]
 * 输出：7
 * 解释：数组中存在 1 和 7 对应的负数，7 的值更大。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [-10,8,6,7,-2,-3]
 * 输出：-1
 * 解释：不存在满足题目要求的 k ，返回 -1 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 1000
 * -1000 <= nums[i] <= 1000
 * nums[i] != 0
 * 
 * 
 */

// @lc code=start

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int findMaxK(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num > 0)
                set.add(num);
        }

        int result = -1;
        for (int num : nums) {
            if (num < 0 && set.contains(-num))
                result = Math.max(result, -num);
        }

        return result;
    }
}
// @lc code=end
