/*
 * @lc app=leetcode.cn id=632 lang=golang
 *
 * [632] 最小区间
 *
 * https://leetcode.cn/problems/smallest-range-covering-elements-from-k-lists/description/
 *
 * algorithms
 * Hard (61.30%)
 * Likes:    461
 * Dislikes: 0
 * Total Accepted:    31.9K
 * Total Submissions: 51.5K
 * Testcase Example:  '[[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]'
 *
 * 你有 k 个 非递减排列 的整数列表。找到一个 最小 区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
 *
 * 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * 输出：[20,24]
 * 解释：
 * 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
 * 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
 * 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [[1,2,3],[1,2,3],[1,2,3]]
 * 输出：[1,1]
 *
 *
 *
 *
 * 提示：
 *
 *
 * nums.length == k
 * 1 <= k <= 3500
 * 1 <= nums[i].length <= 50
 * -10^5 <= nums[i][j] <= 10^5
 * nums[i] 按非递减顺序排列
 *
 *
 *
 *
 */

// @lc code=start
package main

import "container/heap"

type Item struct {
	Val int
	Id  int
}

type IHeap []Item

func (h IHeap) Len() int {
	return len(h)
}

func (h IHeap) Less(i, j int) bool {
	return h[i].Val < h[j].Val
}

func (h IHeap) Swap(i, j int) {
	h[i], h[j] = h[j], h[i]
}

func (h *IHeap) Push(v interface{}) {
	*h = append(*h, v.(Item))
}

func (h *IHeap) Pop() interface{} {
	v := (*h)[len(*h)-1]
	*h = (*h)[:len(*h)-1]
	return v
}

func smallestRange(nums [][]int) []int {
	result := []int{-100001, 100001}
	n := len(nums)

	if n == 0 {
		return result
	}

	for i := 0; i < n; i++ {
		if len(nums[i]) == 0 {
			return result
		}
	}

	curMax := nums[0][0]
	h := IHeap(make([]Item, 0))
	for i := 0; i < n; i++ {
		heap.Push(&h, Item{Val: nums[i][0], Id: i})
		if nums[i][0] > curMax {
			curMax = nums[i][0]
		}

		nums[i] = nums[i][1:]
	}

	heap.Init(&h)

	for true {
		item := heap.Pop(&h).(Item)
		if curMax-item.Val < result[1]-result[0] {
			result = []int{item.Val, curMax}
		}

		id := item.Id
		if len(nums[id]) == 0 {
			break
		}

		heap.Push(&h, Item{Val: nums[id][0], Id: id})
		if nums[id][0] > curMax {
			curMax = nums[id][0]
		}

		nums[id] = nums[id][1:]
	}

	return result
}

// @lc code=end
