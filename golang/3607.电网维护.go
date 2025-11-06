/*
 * @lc app=leetcode.cn id=3607 lang=golang
 *
 * [3607] 电网维护
 *
 * https://leetcode.cn/problems/power-grid-maintenance/description/
 *
 * algorithms
 * Medium (47.99%)
 * Likes:    13
 * Dislikes: 0
 * Total Accepted:    3.1K
 * Total Submissions: 6.5K
 * Testcase Example:  '5\n[[1,2],[2,3],[3,4],[4,5]]\n[[1,3],[2,1],[1,1],[2,2],[1,2]]'
 *
 * 给你一个整数 c，表示 c 个电站，每个电站有一个唯一标识符 id，从 1 到 c 编号。
 *
 * 这些电站通过 n 条 双向 电缆互相连接，表示为一个二维数组 connections，其中每个元素 connections[i] = [ui, vi]
 * 表示电站 ui 和电站 vi 之间的连接。直接或间接连接的电站组成了一个 电网 。
 *
 * 最初，所有 电站均处于在线（正常运行）状态。
 *
 * 另给你一个二维数组 queries，其中每个查询属于以下 两种类型之一 ：
 *
 *
 *
 * [1, x]：请求对电站 x 进行维护检查。如果电站 x 在线，则它自行解决检查。如果电站 x 已离线，则检查由与 x 同一 电网 中 编号最小
 * 的在线电站解决。如果该电网中 不存在 任何 在线 电站，则返回 -1。
 *
 *
 * [2, x]：电站 x 离线（即变为非运行状态）。
 *
 *
 *
 * 返回一个整数数组，表示按照查询中出现的顺序，所有类型为 [1, x] 的查询结果。
 *
 * 注意：电网的结构是固定的；离线（非运行）的节点仍然属于其所在的电网，且离线操作不会改变电网的连接性。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： c = 5, connections = [[1,2],[2,3],[3,4],[4,5]], queries =
 * [[1,3],[2,1],[1,1],[2,2],[1,2]]
 *
 * 输出： [3,2,3]
 *
 * 解释：
 *
 *
 *
 *
 * 最初，所有电站 {1, 2, 3, 4, 5} 都在线，并组成一个电网。
 * 查询 [1,3]：电站 3 在线，因此维护检查由电站 3 自行解决。
 * 查询 [2,1]：电站 1 离线。剩余在线电站为 {2, 3, 4, 5}。
 * 查询 [1,1]：电站 1 离线，因此检查由电网中编号最小的在线电站解决，即电站 2。
 * 查询 [2,2]：电站 2 离线。剩余在线电站为 {3, 4, 5}。
 * 查询 [1,2]：电站 2 离线，因此检查由电网中编号最小的在线电站解决，即电站 3。
 *
 *
 *
 * 示例 2：
 *
 *
 * 输入： c = 3, connections = [], queries = [[1,1],[2,1],[1,1]]
 *
 * 输出： [1,-1]
 *
 * 解释：
 *
 *
 * 没有连接，因此每个电站是一个独立的电网。
 * 查询 [1,1]：电站 1 在线，且属于其独立电网，因此维护检查由电站 1 自行解决。
 * 查询 [2,1]：电站 1 离线。
 * 查询 [1,1]：电站 1 离线，且其电网中没有其他电站，因此结果为 -1。
 *
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= c <= 10^5
 * 0 <= n == connections.length <= min(10^5, c * (c - 1) / 2)
 * connections[i].length == 2
 * 1 <= ui, vi <= c
 * ui != vi
 * 1 <= queries.length <= 2 * 10^5
 * queries[i].length == 2
 * queries[i][0] 为 1 或 2。
 * 1 <= queries[i][1] <= c
 *
 *
 */

// @lc code=start
package main

import "container/heap"

func processQueries(c int, connections [][]int, queries [][]int) []int {
	graph := make([][]int, c+1)
	vertices := make([]Vertex, c+1)

	for i := 0; i <= c; i++ {
		graph[i] = make([]int, 0)
		vertices[i] = Vertex{vertexId: i, powerGridId: -1}
	}

	for _, conn := range connections {
		u, v := conn[0], conn[1]
		graph[u] = append(graph[u], v)
		graph[v] = append(graph[v], u)
	}

	powerGrids := make([]*IntHeap, 0)
	for i, powerGridId := 1, 0; i <= c; i++ {
		v := &vertices[i]

		if v.powerGridId == -1 {
			powerGrid := &IntHeap{}
			heap.Init(powerGrid)
			traverse(v, powerGridId, powerGrid, graph, vertices)
			powerGrids = append(powerGrids, powerGrid)
			powerGridId++
		}
	}

	ans := make([]int, 0)
	for _, q := range queries {
		op, x := q[0], q[1]
		if op == 1 {
			if !vertices[x].offline {
				ans = append(ans, x)
			} else {
				powerGrid := powerGrids[vertices[x].powerGridId]
				for powerGrid.Len() > 0 && vertices[(*powerGrid)[0]].offline {
					heap.Pop(powerGrid)
				}

				if powerGrid.Len() > 0 {
					ans = append(ans, (*powerGrid)[0])
				} else {
					ans = append(ans, -1)
				}
			}
		} else if op == 2 {
			vertices[x].offline = true
		}
	}

	return ans
}

func traverse(u *Vertex, powerGridId int, powerGrid *IntHeap, graph [][]int, vertices []Vertex) {
	u.powerGridId = powerGridId
	heap.Push(powerGrid, u.vertexId)

	for _, vid := range graph[u.vertexId] {
		v := &vertices[vid]
		if v.powerGridId == -1 {
			traverse(v, powerGridId, powerGrid, graph, vertices)
		}
	}
}

type Vertex struct {
	vertexId    int
	offline     bool
	powerGridId int
}

type IntHeap []int

func (h IntHeap) Len() int           { return len(h) }
func (h IntHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h IntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *IntHeap) Push(x interface{}) {
	*h = append(*h, x.(int))
}

func (h *IntHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

// @lc code=end
