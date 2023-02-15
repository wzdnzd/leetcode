/*
 * @lc app=leetcode.cn id=912 lang=golang
 *
 * [912] 排序数组
 *
 * https://leetcode.cn/problems/sort-an-array/description/
 *
 * algorithms
 * Medium (55.60%)
 * Likes:    678
 * Dislikes: 0
 * Total Accepted:    448.4K
 * Total Submissions: 807.6K
 * Testcase Example:  '[5,2,3,1]'
 *
 * 给你一个整数数组 nums，请你将该数组升序排列。
 *
 *
 *
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 5 * 10^4
 * -5 * 10^4 <= nums[i] <= 5 * 10^4
 *
 *
 */
package main

// @lc code=start
func sortArray(nums []int) []int {
	quickSort(nums, 0, len(nums)-1)
	return nums
}

func quickSort(nums []int, start, end int) {
	if start >= end {
		return
	}

	mid := partition(nums, start, end)
	quickSort(nums, start, mid-1)
	quickSort(nums, mid+1, end)
}

func partition(nums []int, start, end int) int {
	mid := (start + end) / 2
	pivot, index := nums[mid], start
	swap(nums, mid, end)
	for cur := start; cur < end; cur++ {
		if nums[cur] < pivot {
			swap(nums, cur, index)
			index++
		}
	}

	swap(nums, index, end)
	return index
}

func swap(nums []int, i, j int) {
	nums[i], nums[j] = nums[j], nums[i]
}

// @lc code=end
