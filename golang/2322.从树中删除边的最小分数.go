/*
 * @lc app=leetcode.cn id=2322 lang=golang
 *
 * [2322] 从树中删除边的最小分数
 *
 * https://leetcode.cn/problems/minimum-score-after-removals-on-a-tree/description/
 *
 * algorithms
 * Hard (60.00%)
 * Likes:    58
 * Dislikes: 0
 * Total Accepted:    5.8K
 * Total Submissions: 9.3K
 * Testcase Example:  '[1,5,5,4,11]\n[[0,1],[1,2],[1,3],[3,4]]'
 *
 * 存在一棵无向连通树，树中有编号从 0 到 n - 1 的 n 个节点， 以及 n - 1 条边。
 *
 * 给你一个下标从 0 开始的整数数组 nums ，长度为 n ，其中 nums[i] 表示第 i 个节点的值。另给你一个二维整数数组 edges ，长度为
 * n - 1 ，其中 edges[i] = [ai, bi] 表示树中存在一条位于节点 ai 和 bi 之间的边。
 *
 * 删除树中两条 不同 的边以形成三个连通组件。对于一种删除边方案，定义如下步骤以计算其分数：
 *
 *
 * 分别获取三个组件 每个 组件中所有节点值的异或值。
 * 最大 异或值和 最小 异或值的 差值 就是这一种删除边方案的分数。
 *
 *
 *
 * 例如，三个组件的节点值分别是：[4,5,7]、[1,9] 和 [3,3,3] 。三个异或值分别是 4 ^ 5 ^ 7 = 6、1 ^ 9 = 8 和 3
 * ^ 3 ^ 3 = 3 。最大异或值是 8 ，最小异或值是 3 ，分数是 8 - 3 = 5 。
 *
 *
 * 返回在给定树上执行任意删除边方案可能的 最小 分数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,5,5,4,11], edges = [[0,1],[1,2],[1,3],[3,4]]
 * 输出：9
 * 解释：上图展示了一种删除边方案。
 * - 第 1 个组件的节点是 [1,3,4] ，值是 [5,4,11] 。异或值是 5 ^ 4 ^ 11 = 10 。
 * - 第 2 个组件的节点是 [0] ，值是 [1] 。异或值是 1 = 1 。
 * - 第 3 个组件的节点是 [2] ，值是 [5] 。异或值是 5 = 5 。
 * 分数是最大异或值和最小异或值的差值，10 - 1 = 9 。
 * 可以证明不存在分数比 9 小的删除边方案。
 *
 *
 * 示例 2：
 *
 * 输入：nums = [5,5,2,4,4,2], edges = [[0,1],[1,2],[5,2],[4,3],[1,3]]
 * 输出：0
 * 解释：上图展示了一种删除边方案。
 * - 第 1 个组件的节点是 [3,4] ，值是 [4,4] 。异或值是 4 ^ 4 = 0 。
 * - 第 2 个组件的节点是 [1,0] ，值是 [5,5] 。异或值是 5 ^ 5 = 0 。
 * - 第 3 个组件的节点是 [2,5] ，值是 [2,2] 。异或值是 2 ^ 2 = 0 。
 * 分数是最大异或值和最小异或值的差值，0 - 0 = 0 。
 * 无法获得比 0 更小的分数 0 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * n == nums.length
 * 3 <= n <= 1000
 * 1 <= nums[i] <= 10^8
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * edges 表示一棵有效的树
 *
 *
 */

// @lc code=start
package main

import "math"

func minimumScore(nums []int, edges [][]int) int {
	n := len(nums)
	e := make([][]int, n)
	for _, v := range edges {
		e[v[0]] = append(e[v[0]], v[1])
		e[v[1]] = append(e[v[1]], v[0])
	}

	sum := 0
	for _, x := range nums {
		sum ^= x
	}

	score := math.MaxInt32
	var dfsSecondCut func(int, int, int, int) int
	dfsSecondCut = func(x, f, oth, anc int) int {
		son := nums[x]
		for _, y := range e[x] {
			if y == f {
				continue
			}

			son ^= dfsSecondCut(y, x, oth, anc)
		}

		if f == anc {
			return son
		}

		score = min(score, calcScore(oth, son, sum^oth^son))
		return son
	}

	var dfsFirstCut func(int, int) int
	dfsFirstCut = func(x, f int) int {
		son := nums[x]
		for _, y := range e[x] {
			if y == f {
				continue
			}

			son ^= dfsFirstCut(y, x)
		}

		for _, y := range e[x] {
			if y == f {
				dfsSecondCut(y, x, son, x)
			}
		}

		return son
	}

	dfsFirstCut(0, -1)
	return score
}

func calcScore(part1, part2, part3 int) int {
	return max(part1, max(part2, part3)) - min(part1, min(part2, part3))
}

// @lc code=end
