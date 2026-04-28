/*
 * @lc app=leetcode.cn id=3225 lang=golang
 *
 * [3225] 网格图操作后的最大分数
 *
 * https://leetcode.cn/problems/maximum-score-from-grid-operations/description/
 *
 * algorithms
 * Hard (18.55%)
 * Likes:    14
 * Dislikes: 0
 * Total Accepted:    1.9K
 * Total Submissions: 7.7K
 * Testcase Example:  '[[0,0,0,0,0],[0,0,3,0,0],[0,1,0,0,0],[5,0,0,3,0],[0,0,0,0,2]]'
 *
 * 给你一个大小为 n x n 的二维矩阵 grid ，一开始所有格子都是白色的。一次操作中，你可以选择任意下标为 (i, j) 的格子，并将第 j
 * 列中从最上面到第 i 行所有格子改成黑色。
 *
 * 如果格子 (i, j) 为白色，且左边或者右边的格子至少一个格子为黑色，那么我们将 grid[i][j] 加到最后网格图的总分中去。
 *
 * 请你返回执行任意次操作以后，最终网格图的 最大 总分数。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[0,0,0,0,0],[0,0,3,0,0],[0,1,0,0,0],[5,0,0,3,0],[0,0,0,0,2]]
 *
 * 输出：11
 *
 * 解释：
 *
 * 第一次操作中，我们将第 1 列中，最上面的格子到第 3 行的格子染成黑色。第二次操作中，我们将第 4
 * 列中，最上面的格子到最后一行的格子染成黑色。最后网格图总分为 grid[3][0] + grid[1][2] + grid[3][3] 等于 11
 * 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：grid =
 * [[10,9,0,0,15],[7,1,0,8,0],[5,20,0,11,0],[0,0,0,1,2],[8,12,1,10,3]]
 *
 * 输出：94
 *
 * 解释：
 *
 * 我们对第 1 ，2 ，3 列分别从上往下染黑色到第 1 ，4， 0 行。最后网格图总分为 grid[0][0] + grid[1][0] +
 * grid[2][1] + grid[4][1] + grid[1][3] + grid[2][3] + grid[3][3] + grid[4][3]
 * + grid[0][4] 等于 94 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= n == grid.length <= 100
 * n == grid[i].length
 * 0 <= grid[i][j] <= 10^9
 *
 *
 */

// @lc code=start
package main

func maximumScore(grid [][]int) int64 {
	n := len(grid)

	colSums := make([][]int64, n)
	for j := range colSums {
		colSums[j] = make([]int64, n+1)
		for i, row := range grid {
			colSums[j][i+1] = colSums[j][i] + int64(row[j])
		}
	}

	dp := make([][][2]int64, n)
	for j := range dp {
		dp[j] = make([][2]int64, n+1)
	}

	for j := 0; j < n-1; j++ {
		preMax := dp[j][0][1] - colSums[j][0]

		for i := 1; i <= n; i++ {
			dp[j+1][i][0] = max(dp[j][i][0], preMax+colSums[j][i])
			dp[j+1][i][1] = dp[j+1][i][0]
			preMax = max(preMax, dp[j][i][1]-colSums[j][i])
		}

		sufMax := dp[j][n][0] + colSums[j+1][n]

		for i := n - 1; i > 0; i-- {
			dp[j+1][i][0] = max(dp[j+1][i][0], sufMax-colSums[j+1][i])
			sufMax = max(sufMax, dp[j][i][0]+colSums[j+1][i])
		}

		dp[j+1][0][0] = sufMax
		dp[j+1][0][1] = max(dp[j][0][0], dp[j][n][0])
	}

	score := int64(0)
	for _, row := range dp[n-1] {
		score = max(score, row[0])
	}

	return score
}

func max(x, y int64) int64 {
	if x < y {
		return y
	}

	return x
}

// @lc code=end
