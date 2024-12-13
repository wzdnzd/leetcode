/*
 * @lc app=leetcode.cn id=3266 lang=golang
 *
 * [3266] K 次乘运算后的最终数组 II
 *
 * https://leetcode.cn/problems/final-array-state-after-k-multiplication-operations-ii/description/
 *
 * algorithms
 * Hard (22.79%)
 * Likes:    20
 * Dislikes: 0
 * Total Accepted:    3.8K
 * Total Submissions: 12.7K
 * Testcase Example:  '[2,1,3,5,6]\n5\n2'
 *
 * 给你一个整数数组 nums ，一个整数 k  和一个整数 multiplier 。
 *
 * 你需要对 nums 执行 k 次操作，每次操作中：
 *
 *
 * 找到 nums 中的 最小 值 x ，如果存在多个最小值，选择最 前面 的一个。
 * 将 x 替换为 x * multiplier 。
 *
 *
 * k 次操作以后，你需要将 nums 中每一个数值对 10^9 + 7 取余。
 *
 * 请你返回执行完 k 次乘运算以及取余运算之后，最终的 nums 数组。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [2,1,3,5,6], k = 5, multiplier = 2
 *
 * 输出：[8,4,6,5,6]
 *
 * 解释：
 *
 *
 *
 *
 * 操作
 * 结果
 *
 *
 * 1 次操作后
 * [2, 2, 3, 5, 6]
 *
 *
 * 2 次操作后
 * [4, 2, 3, 5, 6]
 *
 *
 * 3 次操作后
 * [4, 4, 3, 5, 6]
 *
 *
 * 4 次操作后
 * [4, 4, 6, 5, 6]
 *
 *
 * 5 次操作后
 * [8, 4, 6, 5, 6]
 *
 *
 * 取余操作后
 * [8, 4, 6, 5, 6]
 *
 *
 *
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [100000,2000], k = 2, multiplier = 1000000
 *
 * 输出：[999999307,999999993]
 *
 * 解释：
 *
 *
 *
 *
 * 操作
 * 结果
 *
 *
 * 1 次操作后
 * [100000, 2000000000]
 *
 *
 * 2 次操作后
 * [100000000000, 2000000000]
 *
 *
 * 取余操作后
 * [999999307, 999999993]
 *
 *
 *
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= 10^9
 * 1 <= multiplier <= 10^6
 *
 *
 */

// @lc code=start
package main

import (
	"container/heap"
	"sort"
)

func getFinalState(nums []int, k int, multiplier int) []int {
	if multiplier == 1 {
		return nums
	}

	n, m, maxVal := len(nums), int64(1e9+7), 0
	var hp minHeap
	for i, num := range nums {
		maxVal = max(maxVal, num)
		hp = append(hp, pair{int64(num), i})
	}

	heap.Init(&hp)
	for ; hp[0].left < int64(maxVal) && k > 0; k-- {
		x := heap.Pop(&hp).(pair)
		x.left *= int64(multiplier)
		heap.Push(&hp, x)
	}

	sort.Slice(hp, func(i, j int) bool {
		return hp[i].left < hp[j].left || hp[i].left == hp[j].left && hp[i].right < hp[j].right
	})

	for i := 0; i < n; i++ {
		t := k / n
		if i < k%n {
			t++
		}

		nums[hp[i].right] = int((hp[i].left % m) * quickMul(int64(multiplier), int64(t), m) % m)
	}

	return nums
}

func quickMul(x, y, m int64) int64 {
	result := int64(1)
	for y > 0 {
		if (y & 1) == 1 {
			result = (result * x) % m
		}

		y >>= 1
		x = (x * x) % m
	}

	return result
}

type pair struct {
	left  int64
	right int
}

type minHeap []pair

func (h minHeap) Len() int {
	return len(h)
}

func (h minHeap) Less(i, j int) bool {
	return h[i].left < h[j].left || h[i].left == h[j].left && h[i].right < h[j].right
}

func (h minHeap) Swap(i, j int) {
	h[i], h[j] = h[j], h[i]
}

func (h *minHeap) Push(x interface{}) {
	*h = append(*h, x.(pair))
}

func (h *minHeap) Pop() interface{} {
	n := len(*h)
	result := (*h)[n-1]
	*h = (*h)[0 : n-1]
	return result
}

// @lc code=end
