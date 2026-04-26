/*
 * @lc app=leetcode.cn id=1391 lang=golang
 *
 * [1391] 检查网格中是否存在有效路径
 *
 * https://leetcode.cn/problems/check-if-there-is-a-valid-path-in-a-grid/description/
 *
 * algorithms
 * Medium (46.13%)
 * Likes:    111
 * Dislikes: 0
 * Total Accepted:    14.7K
 * Total Submissions: 31.1K
 * Testcase Example:  '[[2,4,3],[6,5,2]]'
 *
 * 给你一个 m x n 的网格 grid。网格里的每个单元都代表一条街道。grid[i][j] 的街道可以是：
 *
 *
 * 1 表示连接左单元格和右单元格的街道。
 * 2 表示连接上单元格和下单元格的街道。
 * 3 表示连接左单元格和下单元格的街道。
 * 4 表示连接右单元格和下单元格的街道。
 * 5 表示连接左单元格和上单元格的街道。
 * 6 表示连接右单元格和上单元格的街道。
 *
 *
 *
 *
 * 你最开始从左上角的单元格 (0,0) 开始出发，网格中的「有效路径」是指从左上方的单元格 (0,0) 开始、一直到右下方的 (m-1,n-1)
 * 结束的路径。该路径必须只沿着街道走。
 *
 * 注意：你 不能 变更街道。
 *
 * 如果网格中存在有效的路径，则返回 true，否则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[2,4,3],[6,5,2]]
 * 输出：true
 * 解释：如图所示，你可以从 (0, 0) 开始，访问网格中的所有单元格并到达 (m - 1, n - 1) 。
 *
 *
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[1,2,1],[1,2,1]]
 * 输出：false
 * 解释：如图所示，单元格 (0, 0) 上的街道没有与任何其他单元格上的街道相连，你只会停在 (0, 0) 处。
 *
 *
 * 示例 3：
 *
 * 输入：grid = [[1,1,2]]
 * 输出：false
 * 解释：你会停在 (0, 1)，而且无法到达 (0, 2) 。
 *
 *
 * 示例 4：
 *
 * 输入：grid = [[1,1,1,1,1,1,3]]
 * 输出：true
 *
 *
 * 示例 5：
 *
 * 输入：grid = [[2],[2],[2],[2],[2],[2],[6]]
 * 输出：true
 *
 *
 *
 *
 * 提示：
 *
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * 1 <= grid[i][j] <= 6
 *
 *
 */

// @lc code=start
package main

var directions = [7][2][2]int{
	{},
	{{0, -1}, {0, 1}},
	{{-1, 0}, {1, 0}},
	{{0, -1}, {1, 0}},
	{{0, 1}, {1, 0}},
	{{0, -1}, {-1, 0}},
	{{0, 1}, {-1, 0}},
}

func hasValidPath(grid [][]int) bool {
	m, n := len(grid), len(grid[0])
	visited := make([][]bool, m)
	for i := range visited {
		visited[i] = make([]bool, n)
	}

	var dfs func(int, int) bool
	dfs = func(x, y int) bool {
		if x == m-1 && y == n-1 {
			return true
		}

		visited[x][y] = true
		for _, direction := range directions[grid[x][y]] {
			i, j := x+direction[0], y+direction[1]
			if 0 <= i && i < m && 0 <= j && j < n && !visited[i][j] &&
				contains(grid[i][j], [2]int{-direction[0], -direction[1]}) && dfs(i, j) {
				return true
			}
		}

		return false
	}

	return dfs(0, 0)
}

func contains(street int, direction [2]int) bool {
	return directions[street][0] == direction || directions[street][1] == direction
}

// @lc code=end
