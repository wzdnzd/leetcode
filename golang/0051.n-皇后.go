/*
 * @lc app=leetcode.cn id=51 lang=golang
 *
 * [51] N 皇后
 *
 * https://leetcode.cn/problems/n-queens/description/
 *
 * algorithms
 * Hard (74.33%)
 * Likes:    2176
 * Dislikes: 0
 * Total Accepted:    461.1K
 * Total Submissions: 618.3K
 * Testcase Example:  '4'
 *
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 *
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 *
 *
 *
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 *
 *
 * 示例 2：
 *
 *
 * 输入：n = 1
 * 输出：[["Q"]]
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= n <= 9
 *
 *
 *
 *
 */

// @lc code=start
package main

import "strings"

func solveNQueens(n int) [][]string {
	board := make([][]string, n)

	for i := range board {
		board[i] = make([]string, n)
		for j := range board[i] {
			board[i][j] = "."
		}
	}

	// 记录每一行是否存在皇后
	columns := map[int]bool{}

	// 记录左上到右下的对角线是否存在皇后，该方向上行坐标与列坐标的差值相等
	diag1 := map[int]bool{}

	// 记录右上到左下的对角线是否存在皇后，该方向上行坐标与列坐标的和相等
	diag2 := map[int]bool{}

	result := [][]string{}
	backtrace(0, board, &result, n, columns, diag1, diag2)

	return result
}

func backtrace(r int, board [][]string, result *[][]string, n int, columns, diag1, diag2 map[int]bool) {
	if r == n {
		solution := make([]string, len(board))
		for i := 0; i < n; i++ {
			solution[i] = strings.Join(board[i], "")
		}

		*result = append(*result, solution)
		return
	}

	for c := 0; c < n; c++ {
		if !columns[c] && !diag1[r+c] && !diag2[r-c] {
			board[r][c] = "Q"
			columns[c] = true
			diag1[r+c] = true
			diag2[r-c] = true

			backtrace(r+1, board, result, n, columns, diag1, diag2)

			board[r][c] = "."
			columns[c] = false
			diag1[r+c] = false
			diag2[r-c] = false
		}
	}
}

// @lc code=end
