/*
 * @lc app=leetcode.cn id=3619 lang=golang
 *
 * [3619] 总价值可以被 K 整除的岛屿数目
 *
 * https://leetcode.cn/problems/count-islands-with-total-value-divisible-by-k/description/
 *
 * algorithms
 * Medium (57.25%)
 * Likes:    13
 * Dislikes: 0
 * Total Accepted:    8.6K
 * Total Submissions: 14.8K
 * Testcase Example:  '[[0,2,1,0,0],[0,5,0,0,5],[0,0,1,0,0],[0,1,4,7,0],[0,2,0,0,8]]\n5'
 *
 * 给你一个 m x n 的矩阵 grid 和一个正整数 k。一个 岛屿 是由 正 整数（表示陆地）组成的，并且陆地间 四周 连通（水平或垂直）。
 *
 * 一个岛屿的总价值是该岛屿中所有单元格的值之和。
 *
 * 返回总价值可以被 k 整除 的岛屿数量。
 *
 *
 *
 * 示例 1:
 *
 *
 * 输入: grid = [[0,2,1,0,0],[0,5,0,0,5],[0,0,1,0,0],[0,1,4,7,0],[0,2,0,0,8]], k
 * = 5
 *
 * 输出: 2
 *
 * 解释:
 *
 * 网格中包含四个岛屿。蓝色高亮显示的岛屿的总价值可以被 5 整除，而红色高亮显示的岛屿则不能。
 *
 *
 * 示例 2:
 *
 *
 * 输入: grid = [[3,0,3,0], [0,3,0,3], [3,0,3,0]], k = 3
 *
 * 输出: 6
 *
 * 解释:
 *
 * 网格中包含六个岛屿，每个岛屿的总价值都可以被 3 整除。
 *
 *
 *
 *
 * 提示:
 *
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 1000
 * 1 <= m * n <= 10^5
 * 0 <= grid[i][j] <= 10^6
 * 1 <= k < = 10^6
 *
 *
 */

// @lc code=start
package main

func countIslands(grid [][]int, k int) int {
	directions := []struct{ x, y int }{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}
	m, n := len(grid), len(grid[0])

	var dfs func(int, int) int
	dfs = func(i, j int) int {
		result := grid[i][j]
		grid[i][j] = 0

		for _, d := range directions {
			x, y := i+d.x, j+d.y
			if 0 <= x && x < m && 0 <= y && y < n && grid[x][y] > 0 {
				result += dfs(x, y)
			}
		}

		return result
	}

	count := 0
	for i, row := range grid {
		for j, x := range row {
			if x > 0 && dfs(i, j)%k == 0 {
				count++
			}
		}
	}

	return count
}

// @lc code=end
