/*
 * @lc app=leetcode.cn id=3235 lang=golang
 *
 * [3235] 判断矩形的两个角落是否可达
 *
 * https://leetcode.cn/problems/check-if-the-rectangle-corner-is-reachable/description/
 *
 * algorithms
 * Hard (30.50%)
 * Likes:    11
 * Dislikes: 0
 * Total Accepted:    2.5K
 * Total Submissions: 6.4K
 * Testcase Example:  '3\n4\n[[2,1,1]]'
 *
 * 给你两个正整数 xCorner 和 yCorner 和一个二维整数数组 circles ，其中 circles[i] = [xi, yi, ri]
 * 表示一个圆心在 (xi, yi) 半径为 ri 的圆。
 *
 * 坐标平面内有一个左下角在原点，右上角在 (xCorner, yCorner) 的矩形。你需要判断是否存在一条从左下角到右上角的路径满足：路径 完全
 * 在矩形内部，不会 触碰或者经过 任何 圆的内部和边界，同时 只 在起点和终点接触到矩形。
 *
 * 如果存在这样的路径，请你返回 true ，否则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：X = 3, Y = 4, circles = [[2,1,1]]
 *
 * 输出：true
 *
 * 解释：
 *
 *
 *
 * 黑色曲线表示一条从 (0, 0) 到 (3, 4) 的路径。
 *
 *
 * 示例 2：
 *
 *
 * 输入：X = 3, Y = 3, circles = [[1,1,2]]
 *
 * 输出：false
 *
 * 解释：
 *
 *
 *
 * 不存在从 (0, 0) 到 (3, 3) 的路径。
 *
 *
 * 示例 3：
 *
 *
 * 输入：X = 3, Y = 3, circles = [[2,1,1],[1,2,1]]
 *
 * 输出：false
 *
 * 解释：
 *
 *
 *
 * 不存在从 (0, 0) 到 (3, 3) 的路径。
 *
 *
 * 示例 4：
 *
 *
 * 输入：X = 4, Y = 4, circles = [[5,5,1]]
 *
 * 输出：true
 *
 * 解释：
 *
 *
 *
 *
 *
 *
 * 提示：
 *
 *
 * 3 <= xCorner, yCorner <= 10^9
 * 1 <= circles.length <= 1000
 * circles[i].length == 3
 * 1 <= xi, yi, ri <= 10^9
 *
 *
 */

// @lc code=start
package main

func canReachCorner(xCorner int, yCorner int, circles [][]int) bool {
	visited := make([]bool, len(circles))

	var dfs func(int) bool
	dfs = func(i int) bool {
		x1, y1, r1 := circles[i][0], circles[i][1], circles[i][2]

		if y1 <= yCorner && abs(x1-xCorner) <= r1 || x1 <= xCorner && y1 <= r1 || x1 > xCorner && isInCircle(x1, y1, r1, xCorner, 0) {
			return true
		}

		visited[i] = true
		for j, c := range circles {
			x2, y2, r2 := c[0], c[1], c[2]

			if !visited[j] && (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2) <= (r1+r2)*(r1+r2) &&
				x1*r2+x2*r1 < (r1+r2)*xCorner &&
				y1*r2+y2*r1 < (r1+r2)*yCorner &&
				dfs(j) {
				return true
			}
		}

		return false
	}

	for i, c := range circles {
		x, y, r := c[0], c[1], c[2]

		if isInCircle(x, y, r, 0, 0) ||
			isInCircle(x, y, r, xCorner, yCorner) ||
			!visited[i] && (x <= xCorner && abs(y-yCorner) <= r || y <= yCorner && x <= r || y > yCorner && isInCircle(x, y, r, 0, yCorner)) && dfs(i) {
			return false
		}
	}

	return true
}

func abs(x int) int {
	if x < 0 {
		return -x
	}

	return x
}

func isInCircle(x, y, r, xi, yi int) bool {
	return (x-xi)*(x-xi)+(y-yi)*(y-yi) <= r*r
}

// @lc code=end
