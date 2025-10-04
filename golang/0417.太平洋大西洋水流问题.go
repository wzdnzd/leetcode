/*
 * @lc app=leetcode.cn id=417 lang=golang
 *
 * [417] 太平洋大西洋水流问题
 *
 * https://leetcode.cn/problems/pacific-atlantic-water-flow/description/
 *
 * algorithms
 * Medium (56.70%)
 * Likes:    768
 * Dislikes: 0
 * Total Accepted:    116.5K
 * Total Submissions: 204.7K
 * Testcase Example:  '[[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]'
 *
 * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 *
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c)
 * 上单元格 高于海平面的高度 。
 *
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
 *
 * 返回网格坐标 result 的 2D 列表 ，其中 result[i] = [ri, ci] 表示雨水从单元格 (ri, ci) 流动
 * 既可流向太平洋也可流向大西洋 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 *
 * 输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * 输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 *
 *
 * 示例 2：
 *
 *
 * 输入: heights = [[2,1],[1,2]]
 * 输出: [[0,0],[0,1],[1,0],[1,1]]
 *
 *
 *
 *
 * 提示：
 *
 *
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 10^5
 *
 *
 */

// @lc code=start
package main

var directions = []struct{ x, y int }{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

func pacificAtlantic(heights [][]int) [][]int {
	m, n := len(heights), len(heights[0])

	pacifics := make([][]bool, m)
	atlantics := make([][]bool, m)
	result := make([][]int, 0)

	for i := range pacifics {
		pacifics[i] = make([]bool, n)
		atlantics[i] = make([]bool, n)
	}

	var dfs func(int, int, [][]bool)
	dfs = func(x, y int, oceans [][]bool) {
		if oceans[x][y] {
			return
		}

		oceans[x][y] = true
		for _, d := range directions {
			if a, b := x+d.x, y+d.y; 0 <= a && a < m && 0 <= b && b < n && heights[a][b] >= heights[x][y] {
				dfs(a, b, oceans)
			}
		}
	}

	for i := 0; i < m; i++ {
		dfs(i, 0, pacifics)
	}

	for j := 1; j < n; j++ {
		dfs(0, j, pacifics)
	}

	for i := 0; i < m; i++ {
		dfs(i, n-1, atlantics)
	}

	for j := 0; j < n-1; j++ {
		dfs(m-1, j, atlantics)
	}

	for i, row := range pacifics {
		for j, ok := range row {
			if ok && atlantics[i][j] {
				result = append(result, []int{i, j})
			}
		}
	}

	return result
}

// @lc code=end
