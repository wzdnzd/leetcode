/*
 * @lc app=leetcode.cn id=3300 lang=golang
 *
 * [3300] 替换为数位和以后的最小元素
 *
 * https://leetcode.cn/problems/minimum-element-after-replacement-with-digit-sum/description/
 *
 * algorithms
 * Easy (84.81%)
 * Likes:    4
 * Dislikes: 0
 * Total Accepted:    6.7K
 * Total Submissions: 7.8K
 * Testcase Example:  '[10,12,13,14]'
 *
 * 给你一个整数数组 nums 。
 *
 * 请你将 nums 中每一个元素都替换为它的各个数位之 和 。
 *
 * 请你返回替换所有元素以后 nums 中的 最小 元素。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [10,12,13,14]
 *
 * 输出：1
 *
 * 解释：
 *
 * nums 替换后变为 [1, 3, 4, 5] ，最小元素为 1 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [1,2,3,4]
 *
 * 输出：1
 *
 * 解释：
 *
 * nums 替换后变为 [1, 2, 3, 4] ，最小元素为 1 。
 *
 *
 * 示例 3：
 *
 *
 * 输入：nums = [999,19,199]
 *
 * 输出：10
 *
 * 解释：
 *
 * nums 替换后变为 [27, 10, 19] ，最小元素为 10 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 10^4
 *
 *
 */

// @lc code=start
package main

import "math"

func minElement(nums []int) int {
	result := math.MaxInt

	for _, num := range nums {
		sum := 0
		for num != 0 {
			sum += num % 10
			num /= 10
		}

		result = min(result, sum)
	}

	return result
}

func min(x, y int) int {
	if x > y {
		return y
	}

	return x
}

// @lc code=end
