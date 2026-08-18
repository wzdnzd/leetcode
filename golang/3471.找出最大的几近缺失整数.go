/*
 * @lc app=leetcode.cn id=3471 lang=golang
 *
 * [3471] 找出最大的几近缺失整数
 *
 * https://leetcode.cn/problems/find-the-largest-almost-missing-integer/description/
 *
 * algorithms
 * Easy (31.88%)
 * Likes:    12
 * Dislikes: 0
 * Total Accepted:    8.4K
 * Total Submissions: 23.4K
 * Testcase Example:  '[3,9,2,1,7]\n3'
 *
 * 给你一个整数数组 nums 和一个整数 k 。
 *
 * 如果整数 x 恰好仅出现在 nums 中的一个大小为 k 的子数组中，则认为 x 是 nums 中的几近缺失（almost missing）整数。
 *
 * 返回 nums 中 最大的几近缺失 整数，如果不存在这样的整数，返回 -1 。
 * 子数组 是数组中的一个连续元素序列。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [3,9,2,1,7], k = 3
 *
 * 输出：7
 *
 * 解释：
 *
 *
 * 1 出现在两个大小为 3 的子数组中：[9, 2, 1]、[2, 1, 7]
 * 2 出现在三个大小为 3 的子数组中：[3, 9, 2]、[9, 2, 1]、[2, 1, 7]
 * 3 出现在一个大小为 3 的子数组中：[3, 9, 2]
 * 7 出现在一个大小为 3 的子数组中：[2, 1, 7]
 * 9 出现在两个大小为 3 的子数组中：[3, 9, 2]、[9, 2, 1]
 *
 *
 * 返回 7 ，因为它满足题意的所有整数中最大的那个。
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [3,9,7,2,1,7], k = 4
 *
 * 输出：3
 *
 * 解释：
 *
 *
 * 1 出现在两个大小为 4 的子数组中：[9, 7, 2, 1]、[7, 2, 1, 7]
 * 2 出现在三个大小为 4 的子数组中：[3, 9, 7, 2]、[9, 7, 2, 1]、[7, 2, 1, 7]
 * 3 出现在一个大小为 4 的子数组中：[3, 9, 7, 2]
 * 7 出现在三个大小为 4 的子数组中：[3, 9, 7, 2]、[9, 7, 2, 1]、[7, 2, 1, 7]
 * 9 出现在两个大小为 4 的子数组中：[3, 9, 7, 2]、[9, 7, 2, 1]
 *
 *
 * 返回 3 ，因为它满足题意的所有整数中最大的那个。
 *
 *
 * 示例 3：
 *
 *
 * 输入：nums = [0,0], k = 1
 *
 * 输出：-1
 *
 * 解释：
 *
 * 不存在满足题意的整数。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 50
 * 0 <= nums[i] <= 50
 * 1 <= k <= nums.length
 *
 *
 */

// @lc code=start
package main

import "slices"

func largestInteger(nums []int, k int) int {
	n := len(nums)
	if k == n {
		return slices.Max(nums)
	}

	if k == 1 {
		records := map[int]int{}
		for _, num := range nums {
			records[num]++
		}

		count := -1
		for num, x := range records {
			if x == 1 {
				count = max(count, num)
			}
		}

		return count
	}

	return max(check(nums[1:], nums[0]), check(nums[:n-1], nums[n-1]))
}

func check(nums []int, x int) int {
	if slices.Contains(nums, x) {
		return -1
	}

	return x
}

func max(x, y int) int {
	if x > y {
		return x
	}

	return y
}

// @lc code=end
