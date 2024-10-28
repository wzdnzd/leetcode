/*
 * @lc app=leetcode.cn id=685 lang=golang
 *
 * [685] 冗余连接 II
 *
 * https://leetcode.cn/problems/redundant-connection-ii/description/
 *
 * algorithms
 * Hard (42.25%)
 * Likes:    423
 * Dislikes: 0
 * Total Accepted:    39.3K
 * Total Submissions: 92.3K
 * Testcase Example:  '[[1,2],[1,3],[2,3]]'
 *
 * 在本问题中，有根树指满足以下条件的 有向
 * 图。该树只有一个根节点，所有其他节点都是该根节点的后继。该树除了根节点之外的每一个节点都有且只有一个父节点，而根节点没有父节点。
 *
 * 输入一个有向图，该图由一个有着 n 个节点（节点值不重复，从 1 到 n）的树及一条附加的有向边构成。附加的边包含在 1 到 n
 * 中的两个不同顶点间，这条附加的边不属于树中已存在的边。
 *
 * 结果图是一个以边组成的二维数组 edges 。 每个元素是一对 [ui, vi]，用以表示 有向 图中连接顶点 ui 和顶点 vi 的边，其中 ui 是
 * vi 的一个父节点。
 *
 * 返回一条能删除的边，使得剩下的图是有 n 个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：edges = [[1,2],[1,3],[2,3]]
 * 输出：[2,3]
 *
 *
 * 示例 2：
 *
 *
 * 输入：edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
 * 输出：[4,1]
 *
 *
 *
 *
 * 提示：
 *
 *
 * n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ui, vi <= n
 *
 *
 */

// @lc code=start
package main

func findRedundantDirectedConnection(edges [][]int) []int {
	result := []int{}
	if len(edges) == 0 {
		return result
	}

	n, index, isCircle := len(edges), -1, false
	parents := make([]int, n+1)
	inDegree := map[int][]int{}

	for i, edge := range edges {
		x, y := edge[0], edge[1]

		if parents[x] == 0 {
			parents[x] = -1
		}

		if parents[y] == 0 {
			parents[y] = -1
		}

		inDegree[edge[1]] = append(inDegree[edge[1]], i)
		if len(inDegree[edge[1]]) == 2 {
			index = edge[1]
			continue
		}

		r1, r2 := find(parents, x), find(parents, y)
		if r1 != r2 {
			union(parents, r1, r2)
		} else {
			isCircle = true
			result = []int{x, y}
		}

	}

	if index >= 0 {
		if isCircle {
			return edges[inDegree[index][0]]
		}

		return edges[inDegree[index][1]]
	}

	return result

}

func find(parents []int, x int) int {
	for parents[x] > 0 {
		x = parents[x]
	}

	return x
}

func union(parents []int, x, y int) {
	parents[y] = x
}

// @lc code=end
