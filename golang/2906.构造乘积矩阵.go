/*
 * @lc app=leetcode.cn id=2906 lang=golang
 *
 * [2906] 构造乘积矩阵
 *
 * https://leetcode.cn/problems/construct-product-matrix/description/
 *
 * algorithms
 * Medium (37.03%)
 * Likes:    36
 * Dislikes: 0
 * Total Accepted:    5.7K
 * Total Submissions: 14.3K
 * Testcase Example:  '[[1,2],[3,4]]'
 *
 * 给你一个下标从 0 开始、大小为 n * m 的二维整数矩阵 grid ，定义一个下标从 0 开始、大小为 n * m 的的二维矩阵
 * p。如果满足以下条件，则称 p 为 grid 的 乘积矩阵 ：
 *
 *
 * 对于每个元素 p[i][j] ，它的值等于除了 grid[i][j] 外所有元素的乘积。乘积对 12345 取余数。
 *
 *
 * 返回 grid 的乘积矩阵。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[1,2],[3,4]]
 * 输出：[[24,12],[8,6]]
 * 解释：p[0][0] = grid[0][1] * grid[1][0] * grid[1][1] = 2 * 3 * 4 = 24
 * p[0][1] = grid[0][0] * grid[1][0] * grid[1][1] = 1 * 3 * 4 = 12
 * p[1][0] = grid[0][0] * grid[0][1] * grid[1][1] = 1 * 2 * 4 = 8
 * p[1][1] = grid[0][0] * grid[0][1] * grid[1][0] = 1 * 2 * 3 = 6
 * 所以答案是 [[24,12],[8,6]] 。
 *
 * 示例 2：
 *
 *
 * 输入：grid = [[12345],[2],[1]]
 * 输出：[[2],[0],[0]]
 * 解释：p[0][0] = grid[0][1] * grid[0][2] = 2 * 1 = 2
 * p[0][1] = grid[0][0] * grid[0][2] = 12345 * 1 = 12345. 12345 % 12345 = 0 ，所以
 * p[0][1] = 0
 * p[0][2] = grid[0][0] * grid[0][1] = 12345 * 2 = 24690. 24690 % 12345 = 0 ，所以
 * p[0][2] = 0
 * 所以答案是 [[2],[0],[0]] 。
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= n == grid.length <= 10^5
 * 1 <= m == grid[i].length <= 10^5
 * 2 <= n * m <= 10^5
 * 1 <= grid[i][j] <= 10^9
 *
 *
 */

// @lc code=start
package main

func constructProductMatrix(grid [][]int) [][]int {
	const mod = 12345

	n, m := len(grid), len(grid[0])
	matrix := make([][]int, n)
	prefix, suffix := 1, 1

	for i := n - 1; i >= 0; i-- {
		matrix[i] = make([]int, m)
		for j := m - 1; j >= 0; j-- {
			matrix[i][j] = suffix
			suffix = suffix * grid[i][j] % mod
		}
	}

	for i, row := range grid {
		for j, x := range row {
			matrix[i][j] = matrix[i][j] * prefix % mod
			prefix = prefix * x % mod
		}
	}

	return matrix
}

// @lc code=end
