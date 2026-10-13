/*
 * @lc app=leetcode.cn id=3727 lang=golang
 *
 * [3727] 最大交替平方和
 *
 * https://leetcode.cn/problems/maximum-alternating-sum-of-squares/description/
 *
 * algorithms
 * Medium (73.28%)
 * Likes:    5
 * Dislikes: 0
 * Total Accepted:    5.5K
 * Total Submissions: 7.2K
 * Testcase Example:  '[1,2,3]'
 *
 * 给你一个整数数组 nums。你可以以任意顺序 重新排列元素 。
 *
 * 数组 arr 的 交替得分 定义为：
 *
 *
 * score = arr[0]^2 - arr[1]^2 + arr[2]^2 - arr[3]^2 + ...
 *
 *
 * 在对 nums 重新排列后，返回其 最大可能的交替得分。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： nums = [1,2,3]
 *
 * 输出： 12
 *
 * 解释：
 *
 * nums 的一种可行重排为 [2,1,3]，该排列在所有可能重排中给出了最大交替得分。
 *
 * 交替得分计算如下：
 *
 * score = 2^2 - 1^2 + 3^2 = 4 - 1 + 9 = 12
 *
 *
 * 示例 2：
 *
 *
 * 输入： nums = [1,-1,2,-2,3,-3]
 *
 * 输出： 16
 *
 * 解释：
 *
 * nums 的一种可行重排为 [-3,-1,-2,1,3,2]，该排列在所有可能重排中给出了最大交替得分。
 *
 * 交替得分计算如下：
 *
 * score = (-3)^2 - (-1)^2 + (-2)^2 - (1)^2 + (3)^2 - (2)^2 = 9 - 1 + 4 - 1 + 9
 * - 4 = 16
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 10^5
 * -4 * 10^4 <= nums[i] <= 4 * 10^4
 *
 *
 */

// @lc code=start
package main

import "slices"

func maxAlternatingSum(nums []int) int64 {
	slices.SortFunc(nums, func(a, b int) int { return abs(b) - abs(a) })

	size := (len(nums) - 1) / 2
	sum := int64(0)

	for i, num := range nums {
		squared := int64(num) * int64(num)
		if i > size {
			squared *= -1
		}

		sum += squared
	}

	return sum
}

func abs(x int) int {
	if x < 0 {
		return -x
	}

	return x
}

// @lc code=end
