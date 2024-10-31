/*
 * @lc app=leetcode.cn id=3165 lang=golang
 *
 * [3165] 不包含相邻元素的子序列的最大和
 *
 * https://leetcode.cn/problems/maximum-sum-of-subsequence-with-non-adjacent-elements/description/
 *
 * algorithms
 * Hard (27.21%)
 * Likes:    15
 * Dislikes: 0
 * Total Accepted:    2.7K
 * Total Submissions: 8.5K
 * Testcase Example:  '[3,5,9]\n[[1,-2],[0,-3]]'
 *
 * 给你一个整数数组 nums 和一个二维数组 queries，其中 queries[i] = [posi, xi]。
 *
 * 对于每个查询 i，首先将 nums[posi] 设置为 xi，然后计算查询 i 的答案，该答案为 nums 中 不包含相邻元素 的 子序列 的 最大
 * 和。
 *
 * 返回所有查询的答案之和。
 *
 * 由于最终答案可能非常大，返回其对 10^9 + 7 取余 的结果。
 *
 * 子序列 是指从另一个数组中删除一些或不删除元素而不改变剩余元素顺序得到的数组。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [3,5,9], queries = [[1,-2],[0,-3]]
 *
 * 输出：21
 *
 * 解释：
 * 执行第 1 个查询后，nums = [3,-2,9]，不包含相邻元素的子序列的最大和为 3 + 9 = 12。
 * 执行第 2 个查询后，nums = [-3,-2,9]，不包含相邻元素的子序列的最大和为 9 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [0,-1], queries = [[0,-5]]
 *
 * 输出：0
 *
 * 解释：
 * 执行第 1 个查询后，nums = [-5,-1]，不包含相邻元素的子序列的最大和为 0（选择空子序列）。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 5 * 10^4
 * -10^5 <= nums[i] <= 10^5
 * 1 <= queries.length <= 5 * 10^4
 * queries[i] == [posi, xi]
 * 0 <= posi <= nums.length - 1
 * -10^5 <= xi <= 10^5
 *
 *
 */

// @lc code=start
package main

type Node struct {
	l, r               int
	s00, s01, s10, s11 int
}

func NewNode(l, r int) *Node {
	return &Node{l: l, r: r, s00: 0, s01: 0, s10: 0, s11: 0}
}

type SegmentTree struct {
	nodes []*Node
}

func NewSegmentTree(n int) *SegmentTree {
	tree := make([]*Node, n*4)
	st := &SegmentTree{nodes: tree}
	st.build(1, 1, n)

	return st
}

func (st *SegmentTree) build(u, l, r int) {
	st.nodes[u] = NewNode(l, r)
	if l == r {
		return
	}

	mid := (l + r) >> 1
	st.build(u<<1, l, mid)
	st.build(u<<1|1, mid+1, r)
}

func (st *SegmentTree) query(u, l, r int) int {
	if st.nodes[u].l >= l && st.nodes[u].r <= r {
		return st.nodes[u].s11
	}

	mid := (st.nodes[u].l + st.nodes[u].r) >> 1
	result := 0

	if r <= mid {
		result = st.query(u<<1, l, r)
	}

	if l > mid {
		result = max(result, st.query(u<<1|1, l, r))
	}

	return result
}

func (st *SegmentTree) pushup(u int) {
	left := st.nodes[u<<1]
	right := st.nodes[u<<1|1]

	st.nodes[u].s00 = max(left.s00+right.s10, left.s01+right.s00)
	st.nodes[u].s01 = max(left.s00+right.s11, left.s01+right.s01)
	st.nodes[u].s10 = max(left.s10+right.s10, left.s11+right.s00)
	st.nodes[u].s11 = max(left.s10+right.s11, left.s11+right.s01)
}

func (st *SegmentTree) modify(u, x, v int) {
	if st.nodes[u].l == st.nodes[u].r {
		st.nodes[u].s11 = max(0, v)
		return
	}

	mid := (st.nodes[u].l + st.nodes[u].r) >> 1
	if x <= mid {
		st.modify(u<<1, x, v)
	} else {
		st.modify(u<<1|1, x, v)
	}

	st.pushup(u)
}

func maximumSumSubsequence(nums []int, queries [][]int) int {
	n := len(nums)
	tree := NewSegmentTree(n)
	for i, x := range nums {
		tree.modify(1, i+1, x)
	}

	const mod int = 1e9 + 7
	result := 0

	for _, q := range queries {
		tree.modify(1, q[0]+1, q[1])
		result = (result + tree.query(1, 1, n)) % mod
	}

	return result
}

// @lc code=end
