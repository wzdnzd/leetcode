/*
 * @lc app=leetcode.cn id=3531 lang=golang
 *
 * [3531] 统计被覆盖的建筑
 *
 * https://leetcode.cn/problems/count-covered-buildings/description/
 *
 * algorithms
 * Medium (47.06%)
 * Likes:    15
 * Dislikes: 0
 * Total Accepted:    4.4K
 * Total Submissions: 8.2K
 * Testcase Example:  '3\n[[1,2],[2,2],[3,2],[2,1],[2,3]]'
 *
 * 给你一个正整数 n，表示一个 n x n 的城市，同时给定一个二维数组 buildings，其中 buildings[i] = [x, y]
 * 表示位于坐标 [x, y] 的一个 唯一 建筑。
 *
 * 如果一个建筑在四个方向（左、右、上、下）中每个方向上都至少存在一个建筑，则称该建筑 被覆盖 。
 *
 * 返回 被覆盖 的建筑数量。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 *
 * 输入: n = 3, buildings = [[1,2],[2,2],[3,2],[2,1],[2,3]]
 *
 * 输出: 1
 *
 * 解释:
 *
 *
 * 只有建筑 [2,2] 被覆盖，因为它在每个方向上都至少存在一个建筑：
 *
 *
 * 上方 ([1,2])
 * 下方 ([3,2])
 * 左方 ([2,1])
 * 右方 ([2,3])
 *
 *
 * 因此，被覆盖的建筑数量是 1。
 *
 *
 *
 * 示例 2：
 *
 *
 *
 *
 * 输入: n = 3, buildings = [[1,1],[1,2],[2,1],[2,2]]
 *
 * 输出: 0
 *
 * 解释:
 *
 *
 * 没有任何一个建筑在每个方向上都有至少一个建筑。
 *
 *
 *
 * 示例 3：
 *
 *
 *
 *
 * 输入: n = 5, buildings = [[1,3],[3,2],[3,3],[3,5],[5,3]]
 *
 * 输出: 1
 *
 * 解释:
 *
 *
 * 只有建筑 [3,3] 被覆盖，因为它在每个方向上至少存在一个建筑：
 *
 *
 * 上方 ([1,3])
 * 下方 ([5,3])
 * 左方 ([3,2])
 * 右方 ([3,5])
 *
 *
 * 因此，被覆盖的建筑数量是 1。
 *
 *
 *
 *
 *
 * 提示：
 *
 *
 * 2 <= n <= 10^5
 * 1 <= buildings.length <= 10^5
 * buildings[i] = [x, y]
 * 1 <= x, y <= n
 * buildings 中所有坐标均 唯一 。
 *
 *
 */

// @lc code=start
package main

func countCoveredBuildings(n int, buildings [][]int) int {
	minRows, maxRows := make([]int, n+1), make([]int, n+1)
	minCols, maxCols := make([]int, n+1), make([]int, n+1)

	for i := range minRows {
		minRows[i] = n + 1
		minCols[i] = n + 1
	}

	for _, building := range buildings {
		x, y := building[0], building[1]

		maxRows[y] = max(maxRows[y], x)
		minRows[y] = min(minRows[y], x)
		maxCols[x] = max(maxCols[x], y)
		minCols[x] = min(minCols[x], y)
	}

	count := 0
	for _, building := range buildings {
		x, y := building[0], building[1]
		if x > minRows[y] && x < maxRows[y] && y > minCols[x] && y < maxCols[x] {
			count++
		}
	}

	return count
}

// @lc code=end
