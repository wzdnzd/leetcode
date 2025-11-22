/*
 * @lc app=leetcode.cn id=1262 lang=golang
 *
 * [1262] 可被三整除的最大和
 *
 * https://leetcode.cn/problems/greatest-sum-divisible-by-three/description/
 *
 * algorithms
 * Medium (57.36%)
 * Likes:    384
 * Dislikes: 0
 * Total Accepted:    53.5K
 * Total Submissions: 92.8K
 * Testcase Example:  '[3,6,5,1,8]'
 *
 * 给你一个整数数组 nums，请你找出并返回能被三整除的元素 最大和。
 *
 *
 *
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [3,6,5,1,8]
 * 输出：18
 * 解释：选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）。
 *
 * 示例 2：
 *
 *
 * 输入：nums = [4]
 * 输出：0
 * 解释：4 不能被 3 整除，所以无法选出数字，返回 0。
 *
 *
 * 示例 3：
 *
 *
 * 输入：nums = [1,2,3,4,4]
 * 输出：12
 * 解释：选出数字 1, 3, 4 以及 4，它们的和是 12（可被 3 整除的最大和）。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 4 * 10^4
 * 1 <= nums[i] <= 10^4
 *
 *
 */

// @lc code=start
package main

import "math"

func maxSumDivThree(nums []int) int {
	dp := [2][3]int{{0, math.MinInt, math.MinInt}}

	for i, x := range nums {
		for j := 0; j < 3; j++ {
			dp[(i+1)%2][j] = max(dp[i%2][j], dp[i%2][(j+x)%3]+x)
		}
	}

	return dp[len(nums)%2][0]
}

func max(x, y int) int {
	if x < y {
		return y
	}

	return x
}

// @lc code=end
