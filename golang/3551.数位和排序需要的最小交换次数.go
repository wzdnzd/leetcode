/*
 * @lc app=leetcode.cn id=3551 lang=golang
 *
 * [3551] 数位和排序需要的最小交换次数
 *
 * https://leetcode.cn/problems/minimum-swaps-to-sort-by-digit-sum/description/
 *
 * algorithms
 * Medium (54.15%)
 * Likes:    37
 * Dislikes: 0
 * Total Accepted:    6.9K
 * Total Submissions: 11.6K
 * Testcase Example:  '[37,100]'
 *
 * 给你一个由 互不相同 的正整数组成的数组 nums，需要根据每个数字的数位和（即每一位数字相加求和）按 升序
 * 对数组进行排序。如果两个数字的数位和相等，则较小的数字排在前面。
 *
 * 返回将 nums 排列为上述排序顺序所需的 最小 交换次数。
 *
 * 一次 交换 定义为交换数组中两个不同位置的值。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入: nums = [37,100]
 *
 * 输出: 1
 *
 * 解释:
 *
 *
 * 计算每个整数的数位和：[3 + 7 = 10, 1 + 0 + 0 = 1] → [10, 1]
 * 根据数位和排序：[100, 37]。将 37 与 100 交换，得到排序后的数组。
 * 因此，将 nums 排列为排序顺序所需的最小交换次数为 1。
 *
 *
 *
 * 示例 2：
 *
 *
 * 输入: nums = [22,14,33,7]
 *
 * 输出: 0
 *
 * 解释:
 *
 *
 * 计算每个整数的数位和：[2 + 2 = 4, 1 + 4 = 5, 3 + 3 = 6, 7 = 7] → [4, 5, 6, 7]
 * 根据数位和排序：[22, 14, 33, 7]。数组已经是排序好的。
 * 因此，将 nums 排列为排序顺序所需的最小交换次数为 0。
 *
 *
 *
 * 示例 3：
 *
 *
 * 输入: nums = [18,43,34,16]
 *
 * 输出: 2
 *
 * 解释:
 *
 *
 * 计算每个整数的数位和：[1 + 8 = 9, 4 + 3 = 7, 3 + 4 = 7, 1 + 6 = 7] → [9, 7, 7, 7]
 * 根据数位和排序：[16, 34, 43, 18]。将 18 与 16 交换，再将 43 与 34 交换，得到排序后的数组。
 * 因此，将 nums 排列为排序顺序所需的最小交换次数为 2。
 *
 *
 *
 *
 *
 * 提示:
 *
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * nums 由 互不相同 的正整数组成。
 *
 *
 */

// @lc code=start
package main

import (
	"cmp"
	"slices"
)

func minSwaps(nums []int) int {
	n := len(nums)
	type tuple struct{ s, x, i int }
	records := make([]tuple, n)

	for i, num := range nums {
		sum := 0
		for x := num; x > 0; x /= 10 {
			sum += x % 10
		}

		records[i] = tuple{sum, num, i}
	}

	slices.SortFunc(records, func(a, b tuple) int { return cmp.Or(a.s-b.s, a.x-b.x) })

	count := 0
	for _, p := range records {
		if p.i < 0 {
			continue
		}

		count++
		for i := p.i; i >= 0; {
			i, records[i].i = records[i].i, -1
		}
	}

	return n - count
}

// @lc code=end
