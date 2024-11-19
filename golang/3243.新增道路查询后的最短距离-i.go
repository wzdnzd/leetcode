/*
 * @lc app=leetcode.cn id=3243 lang=golang
 *
 * [3243] 新增道路查询后的最短距离 I
 *
 * https://leetcode.cn/problems/shortest-distance-after-road-addition-queries-i/description/
 *
 * algorithms
 * Medium (39.46%)
 * Likes:    15
 * Dislikes: 0
 * Total Accepted:    5.8K
 * Total Submissions: 12.8K
 * Testcase Example:  '5\n[[2,4],[0,2],[0,4]]'
 *
 * 给你一个整数 n 和一个二维整数数组 queries。
 *
 * 有 n 个城市，编号从 0 到 n - 1。初始时，每个城市 i 都有一条单向道路通往城市 i + 1（ 0 <= i < n - 1）。
 *
 * queries[i] = [ui, vi] 表示新建一条从城市 ui 到城市 vi 的单向道路。每次查询后，你需要找到从城市 0 到城市 n - 1
 * 的最短路径的长度。
 *
 * 返回一个数组 answer，对于范围 [0, queries.length - 1] 中的每个 i，answer[i] 是处理完前 i + 1
 * 个查询后，从城市 0 到城市 n - 1 的最短路径的长度。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： n = 5, queries = [[2, 4], [0, 2], [0, 4]]
 *
 * 输出： [3, 2, 1]
 *
 * 解释：
 *
 *
 *
 * 新增一条从 2 到 4 的道路后，从 0 到 4 的最短路径长度为 3。
 *
 *
 *
 * 新增一条从 0 到 2 的道路后，从 0 到 4 的最短路径长度为 2。
 *
 *
 *
 * 新增一条从 0 到 4 的道路后，从 0 到 4 的最短路径长度为 1。
 *
 *
 * 示例 2：
 *
 *
 * 输入： n = 4, queries = [[0, 3], [0, 2]]
 *
 * 输出： [1, 1]
 *
 * 解释：
 *
 *
 *
 * 新增一条从 0 到 3 的道路后，从 0 到 3 的最短路径长度为 1。
 *
 *
 *
 * 新增一条从 0 到 2 的道路后，从 0 到 3 的最短路径长度仍为 1。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 3 <= n <= 500
 * 1 <= queries.length <= 500
 * queries[i].length == 2
 * 0 <= queries[i][0] < queries[i][1] < n
 * 1 < queries[i][1] - queries[i][0]
 * 查询中没有重复的道路。
 *
 *
 */

// @lc code=start
package main

func shortestDistanceAfterQueries(n int, queries [][]int) []int {
	neighbors := make([][]int, n)
	for i := 0; i < n-1; i++ {
		neighbors[i] = append(neighbors[i], i+1)
	}

	var result []int
	for _, query := range queries {
		neighbors[query[0]] = append(neighbors[query[0]], query[1])
		result = append(result, bfs(n, neighbors))
	}

	return result
}

func bfs(n int, neighbors [][]int) int {
	distances := make([]int, n)
	for i := 1; i < n; i++ {
		distances[i] = -1
	}

	queue := []int{0}
	for len(queue) > 0 {
		x := queue[0]
		queue = queue[1:]

		for _, y := range neighbors[x] {
			if distances[y] >= 0 {
				continue
			}

			queue = append(queue, y)
			distances[y] = distances[x] + 1
		}
	}

	return distances[n-1]
}

// @lc code=end
