/*
 * @lc app=leetcode.cn id=1477 lang=golang
 *
 * [1477] 找两个和为目标值且不重叠的子数组
 *
 * https://leetcode.cn/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/description/
 *
 * algorithms
 * Medium (36.21%)
 * Likes:    190
 * Dislikes: 0
 * Total Accepted:    16.9K
 * Total Submissions: 44.6K
 * Testcase Example:  '[3,2,2,4,3]\n3'
 *
 * 给你一个整数数组 arr 和一个整数值 target 。
 *
 * 请你在 arr 中找 两个互不重叠的子数组 且它们的和都等于 target 。可能会有多种方案，请你返回满足要求的两个子数组长度和的 最小值 。
 *
 * 请返回满足要求的最小长度和，如果无法找到这样的两个子数组，请返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,2,4,3], target = 3
 * 输出：2
 * 解释：只有两个子数组和为 3 （[3] 和 [3]）。它们的长度和为 2 。
 *
 *
 * 示例 2：
 *
 * 输入：arr = [7,3,4,7], target = 7
 * 输出：2
 * 解释：尽管我们有 3 个互不重叠的子数组和为 7 （[7], [3,4] 和 [7]），但我们会选择第一个和第三个子数组，因为它们的长度和 2
 * 是最小值。
 *
 *
 * 示例 3：
 *
 * 输入：arr = [4,3,2,6,2,3,4], target = 6
 * 输出：-1
 * 解释：我们只有一个和为 6 的子数组。
 *
 *
 * 示例 4：
 *
 * 输入：arr = [5,5,4,4,5], target = 3
 * 输出：-1
 * 解释：我们无法找到和为 3 的子数组。
 *
 *
 * 示例 5：
 *
 * 输入：arr = [3,1,1,1,5,1,2,1], target = 3
 * 输出：3
 * 解释：注意子数组 [1,2] 和 [2,1] 不能成为一个方案因为它们重叠了。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 1000
 * 1 <= target <= 10^8
 *
 *
 */

// @lc code=start
package main

import "math"

func minSumOfLengths(arr []int, target int) int {
	const INFINITY = math.MaxInt / 2

	n, count := len(arr), INFINITY
	records := make([]int, n)
	for i := range records {
		records[i] = INFINITY
	}

	start, end, sum := 0, 0, 0
	for end < n {
		sum += arr[end]
		for sum > target {
			sum -= arr[start]
			start++
		}

		current := INFINITY
		if sum == target {
			current = end - start + 1
		}

		if start > 0 {
			count = min(count, records[start-1]+current)
		}

		if end > 0 {
			records[end] = min(records[end-1], current)
		} else {
			records[end] = current
		}

		end++
	}

	if count != INFINITY {
		return count
	}

	return -1
}

func min(x, y int) int {
	if x < y {
		return x
	}

	return y
}

// @lc code=end
