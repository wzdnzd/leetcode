/*
 * @lc app=leetcode.cn id=688 lang=golang
 *
 * [688] 骑士在棋盘上的概率
 *
 * https://leetcode.cn/problems/knight-probability-in-chessboard/description/
 *
 * algorithms
 * Medium (58.02%)
 * Likes:    357
 * Dislikes: 0
 * Total Accepted:    40.7K
 * Total Submissions: 69.8K
 * Testcase Example:  '3\n2\n0\n0'
 *
 * 在一个 n x n 的国际象棋棋盘上，一个骑士从单元格 (row, column) 开始，并尝试进行 k 次移动。行和列是 从 0 开始
 * 的，所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
 *
 * 象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
 *
 *
 *
 * 每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。
 *
 * 骑士继续移动，直到它走了 k 步或离开了棋盘。
 *
 * 返回 骑士在棋盘停止移动后仍留在棋盘上的概率 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入: n = 3, k = 2, row = 0, column = 0
 * 输出: 0.0625
 * 解释: 有两步(到(1,2)，(2,1))可以让骑士留在棋盘上。
 * 在每一个位置上，也有两种移动可以让骑士留在棋盘上。
 * 骑士留在棋盘上的总概率是0.0625。
 *
 *
 * 示例 2：
 *
 *
 * 输入: n = 1, k = 0, row = 0, column = 0
 * 输出: 1.00000
 *
 *
 *
 *
 * 提示:
 *
 *
 * 1 <= n <= 25
 * 0 <= k <= 100
 * 0 <= row, column <= n - 1
 *
 *
 */

// @lc code=start
package main

var directions = []struct{ x, y int }{{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}}

func knightProbability(n, k, row, column int) float64 {
	dp := make([][][]float64, k+1)

	for i := range dp {
		dp[i] = make([][]float64, n+4)
		for j := range dp[i] {
			dp[i][j] = make([]float64, n+4)
		}
	}

	for i := 2; i < n+2; i++ {
		for j := 2; j < n+2; j++ {
			dp[0][i][j] = 1
		}
	}

	for steps := 1; steps <= k; steps++ {
		for i := 2; i < n+2; i++ {
			for j := 2; j < n+2; j++ {
				for _, d := range directions {
					dp[steps][i][j] += dp[steps-1][i+d.x][j+d.y]
				}

				dp[steps][i][j] /= 8
			}
		}
	}

	return dp[k][row+2][column+2]
}

// @lc code=end
