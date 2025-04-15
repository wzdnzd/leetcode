/*
 * @lc app=leetcode.cn id=2537 lang=golang
 *
 * [2537] 统计好子数组的数目
 *
 * https://leetcode.cn/problems/count-the-number-of-good-subarrays/description/
 *
 * algorithms
 * Medium (55.89%)
 * Likes:    75
 * Dislikes: 0
 * Total Accepted:    13.2K
 * Total Submissions: 22.7K
 * Testcase Example:  '[1,1,1,1,1]\n10'
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你返回 nums 中 好 子数组的数目。
 *
 * 一个子数组 arr 如果有 至少 k 对下标 (i, j) 满足 i < j 且 arr[i] == arr[j] ，那么称它是一个 好 子数组。
 *
 * 子数组 是原数组中一段连续 非空 的元素序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,1,1], k = 10
 * 输出：1
 * 解释：唯一的好子数组是这个数组本身。
 *
 *
 * 示例 2：
 *
 * 输入：nums = [3,1,4,3,2,2,4], k = 2
 * 输出：4
 * 解释：总共有 4 个不同的好子数组：
 * - [3,1,4,3,2,2] 有 2 对。
 * - [3,1,4,3,2,2,4] 有 3 对。
 * - [1,4,3,2,2,4] 有 2 对。
 * - [4,3,2,2,4] 有 2 对。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i], k <= 10^9
 *
 *
 */

// @lc code=start
package main

func countGood(nums []int, k int) int64 {
	records := map[int]int{}
	pairs, left := 0, 0
	count := int64(0)

	for _, x := range nums {
		pairs += records[x]
		records[x]++

		for pairs >= k {
			records[nums[left]]--
			pairs -= records[nums[left]]
			left++
		}

		count += int64(left)
	}

	return count
}

// @lc code=end
