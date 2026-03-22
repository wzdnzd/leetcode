/*
 * @lc app=leetcode.cn id=1594 lang=golang
 *
 * [1594] 矩阵的最大非负积
 *
 * https://leetcode.cn/problems/maximum-non-negative-product-in-a-matrix/description/
 *
 * algorithms
 * Medium (40.06%)
 * Likes:    87
 * Dislikes: 0
 * Total Accepted:    16K
 * Total Submissions: 39.2K
 * Testcase Example:  '[[-1,-2,-3],[-2,-3,-3],[-3,-3,-2]]'
 *
 * 给你一个大小为 m x n 的矩阵 grid 。最初，你位于左上角 (0, 0) ，每一步，你可以在矩阵中 向右 或 向下 移动。
 *
 * 在从左上角 (0, 0) 开始到右下角 (m - 1, n - 1) 结束的所有路径中，找出具有 最大非负积
 * 的路径。路径的积是沿路径访问的单元格中所有整数的乘积。
 *
 * 返回 最大非负积 对 10^9 + 7 取余 的结果。如果最大积为 负数 ，则返回 -1 。
 *
 * 注意，取余是在得到最大积之后执行的。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[-1,-2,-3],[-2,-3,-3],[-3,-3,-2]]
 * 输出：-1
 * 解释：从 (0, 0) 到 (2, 2) 的路径中无法得到非负积，所以返回 -1 。
 *
 * 示例 2：
 *
 *
 * 输入：grid = [[1,-2,1],[1,-2,1],[3,-4,1]]
 * 输出：8
 * 解释：最大非负积对应的路径如图所示 (1 * 1 * -2 * -4 * 1 = 8)
 *
 *
 * 示例 3：
 *
 *
 * 输入：grid = [[1,3],[0,-4]]
 * 输出：0
 * 解释：最大非负积对应的路径如图所示 (1 * 0 * -4 = 0)
 *
 *
 *
 *
 * 提示：
 *
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 15
 * -4 <= grid[i][j] <= 4
 *
 *
 */

// @lc code=start
package main

import "math"

func maxProductPath(grid [][]int) int {
	const mod = 1000000007

	n := len(grid[0])
	dpMin := make([]int, n)
	dpMax := make([]int, n)

	for i, row := range grid {
		for j, x := range row {
			if i == 0 && j == 0 {
				dpMin[0], dpMax[0] = x, x
				continue
			}

			resultMin := math.MaxInt
			resultMax := math.MinInt

			if i > 0 {
				minV, maxV := dpMin[j], dpMax[j]
				resultMin = min(minV*x, maxV*x)
				resultMax = max(minV*x, maxV*x)
			}

			if j > 0 {
				minV, maxV := dpMin[j-1], dpMax[j-1]
				resultMin = min(resultMin, min(minV*x, maxV*x))
				resultMax = max(resultMax, max(minV*x, maxV*x))
			}

			dpMin[j] = resultMin
			dpMax[j] = resultMax
		}
	}

	result := dpMax[n-1]
	if result < 0 {
		return -1
	}

	return result % mod
}

func min(x, y int) int {
	if x > y {
		return y
	}

	return x
}

func max(x, y int) int {
	if x < y {
		return y
	}

	return x
}

// @lc code=end
