/*
 * @lc app=leetcode.cn id=3095 lang=golang
 *
 * [3095] 或值至少 K 的最短子数组 I
 *
 * https://leetcode.cn/problems/shortest-subarray-with-or-at-least-k-i/description/
 *
 * algorithms
 * Easy (46.44%)
 * Likes:    15
 * Dislikes: 0
 * Total Accepted:    6.9K
 * Total Submissions: 13.7K
 * Testcase Example:  '[1,2,3]\n2'
 *
 * 给你一个 非负 整数数组 nums 和一个整数 k 。
 *
 * 如果一个数组中所有元素的按位或运算 OR 的值 至少 为 k ，那么我们称这个数组是 特别的 。
 *
 * 请你返回 nums 中 最短特别非空 子数组的长度，如果特别子数组不存在，那么返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [1,2,3], k = 2
 *
 * 输出：1
 *
 * 解释：
 *
 * 子数组 [3] 的按位 OR 值为 3 ，所以我们返回 1 。
 *
 * 注意，[2] 也是一个特别子数组。
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [2,1,8], k = 10
 *
 * 输出：3
 *
 * 解释：
 *
 * 子数组 [2,1,8] 的按位 OR 值为 11 ，所以我们返回 3 。
 *
 *
 * 示例 3：
 *
 *
 * 输入：nums = [1,2], k = 0
 *
 * 输出：1
 *
 * 解释：
 *
 * 子数组 [1] 的按位 OR 值为 1 ，所以我们返回 1 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 50
 * 0 <= nums[i] <= 50
 * 0 <= k < 64
 *
 *
 */

// @lc code=start
package main

import "math"

func minimumSubarrayLength(nums []int, k int) int {
	n := len(nums)
	bits := make([]int, 30)
	count := math.MaxInt32

	for left, right := 0, 0; right < n; right++ {
		for i := 0; i < 30; i++ {
			bits[i] += (nums[right] >> i) & 1
		}

		for left <= right && calc(bits) >= k {
			count = min(count, right-left+1)
			for i := 0; i < 30; i++ {
				bits[i] -= (nums[left] >> i) & 1
			}

			left++
		}
	}

	if count == math.MaxInt32 {
		return -1
	}

	return count
}

func calc(bits []int) int {
	result := 0

	for i := 0; i < len(bits); i++ {
		if bits[i] > 0 {
			result |= 1 << i
		}
	}

	return result
}

// @lc code=end
