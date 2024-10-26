/*
 * @lc app=leetcode.cn id=684 lang=golang
 *
 * [684] 冗余连接
 *
 * https://leetcode.cn/problems/redundant-connection/description/
 *
 * algorithms
 * Medium (68.06%)
 * Likes:    647
 * Dislikes: 0
 * Total Accepted:    123.2K
 * Total Submissions: 180.7K
 * Testcase Example:  '[[1,2],[1,3],[2,3]]'
 *
 * 树可以看成是一个连通且 无环 的 无向 图。
 *
 * 给定往一棵 n 个节点 (节点值 1～n) 的树中添加一条边后的图。添加的边的两个顶点包含在 1 到 n
 * 中间，且这条附加的边不属于树中已存在的边。图的信息记录于长度为 n 的二维数组 edges ，edges[i] = [ai, bi] 表示图中在 ai
 * 和 bi 之间存在一条边。
 *
 * 请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组 edges 中最后出现的那个。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 *
 * 输入: edges = [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 *
 *
 * 示例 2：
 *
 *
 *
 *
 * 输入: edges = [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * 输出: [1,4]
 *
 *
 *
 *
 * 提示:
 *
 *
 * n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ai < bi <= edges.length
 * ai != bi
 * edges 中无重复元素
 * 给定的图是连通的
 *
 *
 */

// @lc code=start
package main

func findRedundantConnection(edges [][]int) []int {
	parents := make([]int, len(edges)+1)
	for i := range parents {
		parents[i] = i
	}

	var find func(int) int
	find = func(x int) int {
		if parents[x] != x {
			parents[x] = find(parents[x])
		}

		return parents[x]
	}

	union := func(from, to int) bool {
		x, y := find(from), find(to)
		if x == y {
			return false
		}

		parents[x] = y
		return true
	}

	for _, edge := range edges {
		if !union(edge[0], edge[1]) {
			return edge
		}
	}

	return nil
}

// @lc code=end
