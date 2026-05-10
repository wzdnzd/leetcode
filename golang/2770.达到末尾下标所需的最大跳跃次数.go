/*
 * @lc app=leetcode.cn id=2770 lang=golang
 *
 * [2770] 达到末尾下标所需的最大跳跃次数
 *
 * https://leetcode.cn/problems/maximum-number-of-jumps-to-reach-the-last-index/description/
 *
 * algorithms
 * Medium (38.70%)
 * Likes:    24
 * Dislikes: 0
 * Total Accepted:    10K
 * Total Submissions: 24.5K
 * Testcase Example:  '[1,3,6,4,1,2]\n2'
 *
 * 给你一个下标从 0 开始、由 n 个整数组成的数组 nums 和一个整数 target 。
 *
 * 你的初始位置在下标 0 。在一步操作中，你可以从下标 i 跳跃到任意满足下述条件的下标 j ：
 *
 *
 * 0 <= i < j < n
 * -target <= nums[j] - nums[i] <= target
 *
 *
 * 返回到达下标 n - 1 处所需的 最大跳跃次数 。
 *
 * 如果无法到达下标 n - 1 ，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,6,4,1,2], target = 2
 * 输出：3
 * 解释：要想以最大跳跃次数从下标 0 到下标 n - 1 ，可以按下述跳跃序列执行操作：
 * - 从下标 0 跳跃到下标 1 。
 * - 从下标 1 跳跃到下标 3 。
 * - 从下标 3 跳跃到下标 5 。
 * 可以证明，从 0 到 n - 1 的所有方案中，不存在比 3 步更长的跳跃序列。因此，答案是 3 。
 *
 * 示例 2：
 *
 * 输入：nums = [1,3,6,4,1,2], target = 3
 * 输出：5
 * 解释：要想以最大跳跃次数从下标 0 到下标 n - 1 ，可以按下述跳跃序列执行操作：
 * - 从下标 0 跳跃到下标 1 。
 * - 从下标 1 跳跃到下标 2 。
 * - 从下标 2 跳跃到下标 3 。
 * - 从下标 3 跳跃到下标 4 。
 * - 从下标 4 跳跃到下标 5 。
 * 可以证明，从 0 到 n - 1 的所有方案中，不存在比 5 步更长的跳跃序列。因此，答案是 5 。
 *
 * 示例 3：
 *
 * 输入：nums = [1,3,6,4,1,2], target = 0
 * 输出：-1
 * 解释：可以证明不存在从 0 到 n - 1 的跳跃序列。因此，答案是 -1 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 2 <= nums.length == n <= 1000
 * -10^9 <= nums[i] <= 10^9
 * 0 <= target <= 2 * 10^9
 *
 *
 */

// @lc code=start
package main

import (
	"math"
	"math/bits"
	"slices"
	"sort"
)

type segmentTree []int

func (tree segmentTree) update(node, left, right, index, val int) {
	if left == right {
		tree[node] = val
		return
	}

	m := (left + right) / 2
	if index <= m {
		tree.update(node*2, left, m, index, val)
	} else {
		tree.update(node*2+1, m+1, right, index, val)
	}

	tree[node] = max(tree[node*2], tree[node*2+1])
}

func (tree segmentTree) query(node, left, right, low, high int) int {
	if low <= left && right <= high {
		return tree[node]
	}

	m := (left + right) / 2
	if high <= m {
		return tree.query(node*2, left, m, low, high)
	}

	if low > m {
		return tree.query(node*2+1, m+1, right, low, high)
	}

	return max(tree.query(node*2, left, m, low, high), tree.query(node*2+1, m+1, right, low, high))
}

func maximumJumps(nums []int, target int) int {
	sorted := slices.Clone(nums)
	slices.Sort(sorted)
	sorted = slices.Compact(sorted)

	m, n := len(sorted), len(nums)
	tree := make(segmentTree, 2<<bits.Len(uint(m-1)))
	for i := range tree {
		tree[i] = math.MinInt
	}

	tree.update(1, 0, m-1, sort.SearchInts(sorted, nums[0]), 0)

	for j := 1; ; j++ {
		left := sort.SearchInts(sorted, nums[j]-target)
		right := sort.SearchInts(sorted, nums[j]+target+1) - 1

		val := tree.query(1, 0, m-1, left, right) + 1
		if j == n-1 {
			if val < 0 {
				return -1
			}

			return val
		}

		tree.update(1, 0, m-1, sort.SearchInts(sorted, nums[j]), val)
	}
}

// @lc code=end
