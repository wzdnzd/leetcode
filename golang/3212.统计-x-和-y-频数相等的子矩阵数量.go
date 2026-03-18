/*
 * @lc app=leetcode.cn id=3212 lang=golang
 *
 * [3212] 统计 X 和 Y 频数相等的子矩阵数量
 *
 * https://leetcode.cn/problems/count-submatrices-with-equal-frequency-of-x-and-y/description/
 *
 * algorithms
 * Medium (52.77%)
 * Likes:    11
 * Dislikes: 0
 * Total Accepted:    8K
 * Total Submissions: 14.5K
 * Testcase Example:  '[["X","Y","."],["Y",".","."]]'
 *
 * 给你一个二维字符矩阵 grid，其中 grid[i][j] 可能是 'X'、'Y' 或 '.'，返回满足以下条件的子矩阵数量：
 *
 *
 * 包含 grid[0][0]
 * 'X' 和 'Y' 的频数相等。
 * 至少包含一个 'X'。
 *
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： grid = [["X","Y","."],["Y",".","."]]
 *
 * 输出： 3
 *
 * 解释：
 *
 *
 *
 *
 * 示例 2：
 *
 *
 * 输入： grid = [["X","X"],["X","Y"]]
 *
 * 输出： 0
 *
 * 解释：
 *
 * 不存在满足 'X' 和 'Y' 频数相等的子矩阵。
 *
 *
 * 示例 3：
 *
 *
 * 输入： grid = [[".","."],[".","."]]
 *
 * 输出： 0
 *
 * 解释：
 *
 * 不存在满足至少包含一个 'X' 的子矩阵。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= grid.length, grid[i].length <= 1000
 * grid[i][j] 可能是 'X'、'Y' 或 '.'.
 *
 *
 */

// @lc code=start
package main

func numberOfSubmatrices(grid [][]byte) int {
	m, n := len(grid), len(grid[0])
	records := make([][][2]int, m+1)
	for i := range records {
		records[i] = make([][2]int, n+1)
	}

	count := 0
	for i, row := range grid {
		for j, c := range row {
			records[i+1][j+1][0] = records[i+1][j][0] + records[i][j+1][0] - records[i][j][0]
			records[i+1][j+1][1] = records[i+1][j][1] + records[i][j+1][1] - records[i][j][1]

			if c != '.' {
				records[i+1][j+1][c&1]++
			}

			if records[i+1][j+1][0] > 0 && records[i+1][j+1][0] == records[i+1][j+1][1] {
				count++
			}
		}
	}

	return count
}

// @lc code=end
