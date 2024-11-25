/*
 * @lc app=leetcode.cn id=743 lang=golang
 *
 * [743] 网络延迟时间
 *
 * https://leetcode.cn/problems/network-delay-time/description/
 *
 * algorithms
 * Medium (56.79%)
 * Likes:    776
 * Dislikes: 0
 * Total Accepted:    142.2K
 * Total Submissions: 248.4K
 * Testcase Example:  '[[2,1,1],[2,3,1],[3,4,1]]\n4\n2'
 *
 * 有 n 个网络节点，标记为 1 到 n。
 *
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点，
 * wi 是一个信号从源节点传递到目标节点的时间。
 *
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 *
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * 输出：2
 *
 *
 * 示例 2：
 *
 *
 * 输入：times = [[1,2,1]], n = 2, k = 1
 * 输出：1
 *
 *
 * 示例 3：
 *
 *
 * 输入：times = [[1,2,1]], n = 2, k = 2
 * 输出：-1
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= k <= n <= 100
 * 1 <= times.length <= 6000
 * times[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 0 <= wi <= 100
 * 所有 (ui, vi) 对都 互不相同（即，不含重复边）
 *
 *
 */

// @lc code=start
package main

import "math"

func networkDelayTime(times [][]int, n int, k int) int {
	const infinite = math.MaxInt / 2

	graph := make([][]int, n)
	for i := 0; i < n; i++ {
		graph[i] = make([]int, n)
		for j := 0; j < n; j++ {
			graph[i][j] = infinite
		}
	}

	for _, time := range times {
		u, v, w := time[0]-1, time[1]-1, time[2]
		graph[u][v] = w
	}

	weights := dijkstra(graph, k)
	maxDelay := math.MinInt
	for _, w := range weights {
		if w == infinite {
			return -1
		}

		maxDelay = max(maxDelay, w)
	}

	return maxDelay
}

func dijkstra(graph [][]int, k int) []int {
	if graph == nil || len(graph) == 0 || k < 1 || k > len(graph) {
		panic("invalid arguments")
	}

	n := len(graph)
	distances := make([]int, n)
	for i := 0; i < n; i++ {
		distances[i] = math.MaxInt / 2
	}
	distances[k-1] = 0

	visited := make([]bool, n)
	for i := 0; i < n; i++ {
		current := -1
		for j := 0; j < n; j++ {
			if !visited[j] && (current == -1 || distances[j] < distances[current]) {
				current = j
			}
		}

		visited[current] = true
		for j := 0; j < n; j++ {
			distances[j] = min(distances[j], distances[current]+graph[current][j])
		}
	}

	return distances
}

// @lc code=end
