/*
 * @lc app=leetcode.cn id=3411 lang=golang
 *
 * [3411] 最长乘积等价子数组
 *
 * https://leetcode.cn/problems/maximum-subarray-with-equal-products/description/
 *
 * algorithms
 * Easy (57.15%)
 * Likes:    17
 * Dislikes: 0
 * Total Accepted:    7K
 * Total Submissions: 11.6K
 * Testcase Example:  '[1,2,1,2,1,1,1]'
 *
 * 给你一个由 正整数 组成的数组 nums。
 *
 * 如果一个数组 arr 满足 prod(arr) == lcm(arr) * gcd(arr)，则称其为 乘积等价数组 ，其中：
 *
 *
 * prod(arr) 表示 arr 中所有元素的乘积。
 * gcd(arr) 表示 arr 中所有元素的最大公因数 (GCD)。
 * lcm(arr) 表示 arr 中所有元素的最小公倍数 (LCM)。
 *
 *
 * 返回数组 nums 的 最长 乘积等价 子数组 的长度。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： nums = [1,2,1,2,1,1,1]
 *
 * 输出： 5
 *
 * 解释：
 *
 * 最长的乘积等价子数组是 [1, 2, 1, 1, 1]，其中 prod([1, 2, 1, 1, 1]) = 2， gcd([1, 2, 1, 1,
 * 1]) = 1，以及 lcm([1, 2, 1, 1, 1]) = 2。
 *
 *
 * 示例 2：
 *
 *
 * 输入： nums = [2,3,4,5,6]
 *
 * 输出： 3
 *
 * 解释：
 *
 * 最长的乘积等价子数组是 [3, 4, 5]。
 *
 *
 * 示例 3：
 *
 *
 * 输入： nums = [1,2,3,1,4,5,1]
 *
 * 输出： 5
 *
 *
 *
 *
 * 提示：
 *
 *
 * 2 <= nums.length <= 100
 * 1 <= nums[i] <= 10
 *
 *
 */

// @lc code=start
package main

func maxLength(nums []int) int {
	result, mul, left := 2, 1, 0
	for right, num := range nums {
		for gcd(mul, num) > 1 {
			mul /= nums[left]
			left++
		}

		mul *= num
		result = max(result, right-left+1)
	}

	return result
}

func gcd(a, b int) int {
	for a != 0 {
		a, b = b%a, a
	}

	return b
}

// @lc code=end
