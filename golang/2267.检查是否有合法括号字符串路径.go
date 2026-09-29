/*
 * @lc app=leetcode.cn id=2267 lang=golang
 *
 * [2267] 检查是否有合法括号字符串路径
 *
 * https://leetcode.cn/problems/check-if-there-is-a-valid-parentheses-string-path/description/
 *
 * algorithms
 * Hard (43.02%)
 * Likes:    68
 * Dislikes: 0
 * Total Accepted:    14.3K
 * Total Submissions: 32.4K
 * Testcase Example:  '[["(","(","("],[")","(",")"],["(","(",")"],["(","(",")"]]'
 *
 * 一个括号字符串是一个 非空 且只包含 '(' 和 ')' 的字符串。如果下面 任意 条件为 真 ，那么这个括号字符串就是 合法的 。
 *
 *
 * 字符串是 () 。
 * 字符串可以表示为 AB（A 连接 B），A 和 B 都是合法括号序列。
 * 字符串可以表示为 (A) ，其中 A 是合法括号序列。
 *
 *
 * 给你一个 m x n 的括号网格图矩阵 grid 。网格图中一个 合法括号路径 是满足以下所有条件的一条路径：
 *
 *
 * 路径开始于左上角格子 (0, 0) 。
 * 路径结束于右下角格子 (m - 1, n - 1) 。
 * 路径每次只会向 下 或者向 右 移动。
 * 路径经过的格子组成的括号字符串是 合法 的。
 *
 *
 * 如果网格图中存在一条 合法括号路径 ，请返回 true ，否则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 *
 * 输入：grid = [["(","(","("],[")","(",")"],["(","(",")"],["(","(",")"]]
 * 输出：true
 * 解释：上图展示了两条路径，它们都是合法括号字符串路径。
 * 第一条路径得到的合法字符串是 "()(())" 。
 * 第二条路径得到的合法字符串是 "((()))" 。
 * 注意可能有其他的合法括号字符串路径。
 *
 *
 * 示例 2：
 *
 *
 *
 *
 * 输入：grid = [[")",")"],["(","("]]
 * 输出：false
 * 解释：两条可行路径分别得到 "))(" 和 ")((" 。由于它们都不是合法括号字符串，我们返回 false 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * grid[i][j] 要么是 '(' ，要么是 ')' 。
 *
 *
 */

// @lc code=start
package main

func hasValidPath(grid [][]byte) bool {
	m, n := len(grid), len(grid[0])
	if (m+n)%2 == 0 || grid[0][0] == ')' || grid[m-1][n-1] == '(' {
		return false
	}

	visited := make([][][]bool, m)
	for i := range visited {
		visited[i] = make([][]bool, n)
		for j := range visited[i] {
			visited[i][j] = make([]bool, (m+n+1)/2)
		}
	}

	var dfs func(x, y, c int) bool
	dfs = func(x, y, c int) bool {
		if c > m-x+n-y-1 {
			return false
		}

		if x == m-1 && y == n-1 {
			return c == 1
		}

		if visited[x][y][c] {
			return false
		}
		visited[x][y][c] = true

		if grid[x][y] == '(' {
			c++
		} else if c--; c < 0 {
			return false
		}

		return x < m-1 && dfs(x+1, y, c) ||
			y < n-1 && dfs(x, y+1, c)
	}

	return dfs(0, 0, 0)
}

// @lc code=end
