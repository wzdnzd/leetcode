/*
 * @lc app=leetcode.cn id=421 lang=java
 *
 * [421] 数组中两个数的最大异或值
 *
 * https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array/description/
 *
 * algorithms
 * Medium (60.76%)
 * Likes:    609
 * Dislikes: 0
 * Total Accepted:    51.2K
 * Total Submissions: 84.8K
 * Testcase Example:  '[3,10,5,25,2,8]'
 *
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [3,10,5,25,2,8]
 * 输出：28
 * 解释：最大运算结果是 5 XOR 25 = 28.
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * 输出：127
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 2 * 10^5
 * 0 <= nums[i] <= 2^31 - 1
 * 
 * 
 * 
 * 
 */

// @lc code=start

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int findMaximumXOR(int[] nums) {
        int bits = 30, x = 0;

        for (int i = bits; i >= 0; i--) {
            Set<Integer> seen = new HashSet<>();
            for (int num : nums)
                seen.add(num >> i);

            x = x * 2 + 1;
            boolean found = false;

            for (int num : nums) {
                if (seen.contains(x ^ (num >> i))) {
                    found = true;
                    break;
                }
            }

            if (!found)
                x -= 1;
        }

        return x;
    }
}
// @lc code=end
