/*
 * @lc app=leetcode.cn id=215 lang=golang
 *
 * [215] 数组中的第K个最大元素
 *
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/description/
 *
 * algorithms
 * Medium (64.20%)
 * Likes:    2050
 * Dislikes: 0
 * Total Accepted:    802.4K
 * Total Submissions: 1.3M
 * Testcase Example:  '[3,2,1,5,6,4]\n2'
 *
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 *
 *
 * 示例 1:
 *
 *
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 *
 *
 * 示例 2:
 *
 *
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 *
 *
 */
package main

import (
	"math/rand"
	"time"
)

// @lc code=start
func findKthLargest(nums []int, k int) int {
	rand.Seed(time.Now().Unix())
	return quickSelect(nums, 0, len(nums)-1, len(nums)-k)
}

func quickSelect(nums []int, left, right, k int) int {
	mid := partition(nums, left, right)
	if mid == k {
		return nums[mid]
	} else if mid < k {
		return quickSelect(nums, mid+1, right, k)
	} else {
		return quickSelect(nums, left, mid-1, k)
	}
}

func partition(nums []int, left, right int) int {
	cur := rand.Intn(right-left+1) + left
	nums[left], nums[cur] = nums[cur], nums[left]

	index, pivot := left, nums[left]
	for i := left; i <= right; i++ {
		if nums[i] < pivot {
			index++
			nums[i], nums[index] = nums[index], nums[i]
		}
	}

	nums[left], nums[index] = nums[index], nums[left]
	return index
}

// @lc code=end
