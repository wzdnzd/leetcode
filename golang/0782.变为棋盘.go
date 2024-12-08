/*
 * @lc app=leetcode.cn id=782 lang=golang
 *
 * [782] 变为棋盘
 *
 * https://leetcode.cn/problems/transform-to-chessboard/description/
 *
 * algorithms
 * Hard (59.33%)
 * Likes:    187
 * Dislikes: 0
 * Total Accepted:    15.5K
 * Total Submissions: 25.8K
 * Testcase Example:  '[[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]'
 *
 * 一个 n x n 的二维网络 board 仅由 0 和 1 组成 。每次移动，你能交换任意两列或是两行的位置。
 *
 * 返回 将这个矩阵变为  “棋盘”  所需的最小移动次数 。如果不存在可行的变换，输出 -1。
 *
 * “棋盘” 是指任意一格的上下左右四个方向的值均与本身不同的矩阵。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 *
 * 输入: board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]
 * 输出: 2
 * 解释:一种可行的变换方式如下，从左到右：
 * 第一次移动交换了第一列和第二列。
 * 第二次移动交换了第二行和第三行。
 *
 *
 * 示例 2:
 *
 *
 *
 *
 * 输入: board = [[0, 1], [1, 0]]
 * 输出: 0
 * 解释: 注意左上角的格值为0时也是合法的棋盘，也是合法的棋盘.
 *
 *
 * 示例 3:
 *
 *
 *
 *
 * 输入: board = [[1, 0], [1, 0]]
 * 输出: -1
 * 解释: 任意的变换都不能使这个输入变为合法的棋盘。
 *
 *
 *
 *
 * 提示：
 *
 *
 * n == board.length
 * n == board[i].length
 * 2 <= n <= 30
 * board[i][j] 将只包含 0或 1
 *
 *
 */

// @lc code=start
package main

func movesToChessboard(board [][]int) int {
	n := len(board)
	firstRow := board[0]
	firstCol := make([]int, n)
	var rowCnt, colCnt [2]int

	for i, row := range board {
		rowCnt[firstRow[i]]++
		firstCol[i] = row[0]
		colCnt[firstCol[i]]++
	}

	if abs(rowCnt[0]-rowCnt[1]) > 1 || abs(colCnt[0]-colCnt[1]) > 1 {
		return -1
	}

	for _, row := range board {
		same := row[0] == firstRow[0]
		for i, x := range row {
			if (x == firstRow[i]) != same {
				return -1
			}
		}
	}

	return minSwap(firstRow, rowCnt) + minSwap(firstCol, colCnt)
}

func minSwap(nums []int, record [2]int) int {
	num := 0
	if record[1] > record[0] {
		num = 1
	}

	diff := 0
	for i, x := range nums {
		diff += i%2 ^ x ^ num
	}

	n := len(nums)
	if n%2 > 0 {
		return diff / 2
	}

	return min(diff, n-diff) / 2
}

func abs(x int) int {
	if x < 0 {
		return -x
	}

	return x
}

// @lc code=end
