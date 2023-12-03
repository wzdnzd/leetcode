/*
 * @lc app=leetcode.cn id=1979 lang=java
 *
 * [1979] 找出数组的最大公约数
 *
 * https://leetcode.cn/problems/find-greatest-common-divisor-of-array/description/
 *
 * algorithms
 * Easy (76.54%)
 * Likes:    45
 * Dislikes: 0
 * Total Accepted:    24.3K
 * Total Submissions: 31.7K
 * Testcase Example:  '[2,5,6,9,10]'
 *
 * 给你一个整数数组 nums ，返回数组中最大数和最小数的 最大公约数 。
 * 
 * 两个数的 最大公约数 是能够被两个数整除的最大正整数。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums = [2,5,6,9,10]
 * 输出：2
 * 解释：
 * nums 中最小的数是 2
 * nums 中最大的数是 10
 * 2 和 10 的最大公约数是 2
 * 
 * 
 * 示例 2：
 * 
 * 输入：nums = [7,5,6,8,3]
 * 输出：1
 * 解释：
 * nums 中最小的数是 3
 * nums 中最大的数是 8
 * 3 和 8 的最大公约数是 1
 * 
 * 
 * 示例 3：
 * 
 * 输入：nums = [3,3]
 * 输出：3
 * 解释：
 * nums 中最小的数是 3
 * nums 中最大的数是 3
 * 3 和 3 的最大公约数是 3
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 2 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findGCD(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        return gcd(min, max);
    }

    private int gcd(int x, int y) {
        if (y % x == 0)
            return x;

        return gcd(y % x, x);
    }
}
// @lc code=end
