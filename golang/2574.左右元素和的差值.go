/*
 * @lc app=leetcode.cn id=2574 lang=golang
 *
 * [2574] 左右元素和的差值
 *
 * https://leetcode.cn/problems/left-and-right-sum-differences/description/
 *
 * algorithms
 * Easy (83.61%)
 * Likes:    29
 * Dislikes: 0
 * Total Accepted:    21.9K
 * Total Submissions: 26.1K
 * Testcase Example:  '[10,4,8,3]'
 *
 * 给你一个下标从 0 开始的长度为 n 的整数数组 nums。
 *
 * 定义两个数组 leftSum 和 rightSum，其中：
 *
 *
 * leftSum[i] 是数组 nums 中下标 i 左侧元素之和。如果不存在对应的元素，leftSum[i] = 0 。
 * rightSum[i] 是数组 nums 中下标 i 右侧元素之和。如果不存在对应的元素，rightSum[i] = 0 。
 *
 *
 * 返回长度为 n 数组 answer，其中 answer[i] = |leftSum[i] - rightSum[i]|。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [10,4,8,3]
 * 输出：[15,1,11,22]
 * 解释：数组 leftSum 为 [0,10,14,22] 且数组 rightSum 为 [15,11,3,0] 。
 * 数组 answer 为 [|0 - 15|,|10 - 11|,|14 - 3|,|22 - 0|] = [15,1,11,22] 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [1]
 * 输出：[0]
 * 解释：数组 leftSum 为 [0] 且数组 rightSum 为 [0] 。
 * 数组 answer 为 [|0 - 0|] = [0] 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 10^5
 *
 *
 */

// @lc code=start
package main

func leftRightDifference(nums []int) []int {
	n, total := len(nums), 0
	for _, num := range nums {
		total += num
	}

	answer := make([]int, n)
	leftSum := 0
	for i, num := range nums {
		answer[i] = abs(total - 2*leftSum - num)
		leftSum += num
	}

	return answer
}

func abs(x int) int {
	if x < 0 {
		return -x
	}

	return x
}

// @lc code=end
