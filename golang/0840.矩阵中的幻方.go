/*
 * @lc app=leetcode.cn id=840 lang=golang
 *
 * [840] 矩阵中的幻方
 *
 * https://leetcode.cn/problems/magic-squares-in-grid/description/
 *
 * algorithms
 * Medium (37.75%)
 * Likes:    77
 * Dislikes: 0
 * Total Accepted:    15K
 * Total Submissions: 38.8K
 * Testcase Example:  '[[4,3,8,4],[9,5,1,9],[2,7,6,2]]'
 *
 * 3 x 3 的幻方是一个填充有 从 1 到 9  的不同数字的 3 x 3 矩阵，其中每行，每列以及两条对角线上的各数之和都相等。
 *
 * 给定一个由整数组成的row x col 的 grid，其中有多少个 3 × 3 的 “幻方” 子矩阵？
 *
 * 注意：虽然幻方只能包含 1 到 9 的数字，但 grid 可以包含最多15的数字。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 *
 * 输入: grid = [[4,3,8,4],[9,5,1,9],[2,7,6,2]
 * 输出: 1
 * 解释:
 * 下面的子矩阵是一个 3 x 3 的幻方：
 *
 * 而这一个不是：
 *
 * 总的来说，在本示例所给定的矩阵中只有一个 3 x 3 的幻方子矩阵。
 *
 *
 * 示例 2:
 *
 *
 * 输入: grid = [[8]]
 * 输出: 0
 *
 *
 *
 *
 * 提示:
 *
 *
 * row == grid.length
 * col == grid[i].length
 * 1 <= row, col <= 10
 * 0 <= grid[i][j] <= 15
 *
 *
 */

// @lc code=start
package main

func numMagicSquaresInside(grid [][]int) int {
	m, n, count := len(grid), len(grid[0]), 0

	for i := range m - 2 {
		for j := range n - 2 {
			if grid[i+1][j+1] != 5 {
				continue
			}

			mask := 0
			var rowSum, colSum [3]int
			for r, row := range grid[i : i+3] {
				for c, col := range row[j : j+3] {
					mask |= 1 << col
					rowSum[r] += col
					colSum[c] += col
				}
			}

			if mask == 1<<10-2 &&
				rowSum[0] == 15 && rowSum[1] == 15 &&
				colSum[0] == 15 && colSum[1] == 15 {
				count++
			}
		}
	}

	return count
}

// @lc code=end
