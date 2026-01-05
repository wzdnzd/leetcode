/*
 * @lc app=leetcode.cn id=1975 lang=golang
 *
 * [1975] 最大方阵和
 *
 * https://leetcode.cn/problems/maximum-matrix-sum/description/
 *
 * algorithms
 * Medium (43.79%)
 * Likes:    32
 * Dislikes: 0
 * Total Accepted:    8.1K
 * Total Submissions: 17.6K
 * Testcase Example:  '[[1,-1],[-1,1]]'
 *
 * 给你一个 n x n 的整数方阵 matrix 。你可以执行以下操作 任意次 ：
 *
 *
 * 选择 matrix 中 相邻 两个元素，并将它们都 乘以 -1 。
 *
 *
 * 如果两个元素有 公共边 ，那么它们就是 相邻 的。
 *
 * 你的目的是 最大化 方阵元素的和。请你在执行以上操作之后，返回方阵的 最大 和。
 *
 *
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,-1],[-1,1]]
 * 输出：4
 * 解释：我们可以执行以下操作使和等于 4 ：
 * - 将第一行的 2 个元素乘以 -1 。
 * - 将第一列的 2 个元素乘以 -1 。
 *
 *
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
 * 输出：16
 * 解释：我们可以执行以下操作使和等于 16 ：
 * - 将第二行的最后 2 个元素乘以 -1 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * n == matrix.length == matrix[i].length
 * 2 <= n <= 250
 * -10^5 <= matrix[i][j] <= 10^5
 *
 *
 */

// @lc code=start
package main

import "math"

func maxMatrixSum(matrix [][]int) int64 {
	count, total, minVal := 0, 0, math.MaxInt
	for _, row := range matrix {
		for _, num := range row {
			if num < 0 {
				count++
				num = -num
			}

			total += num
			if num < minVal {
				minVal = num
			}
		}
	}

	if count%2 == 1 {
		total -= 2 * minVal
	}

	return int64(total)
}

// @lc code=end
