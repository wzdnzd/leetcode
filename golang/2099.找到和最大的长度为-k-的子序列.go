/*
 * @lc app=leetcode.cn id=2099 lang=golang
 *
 * [2099] 找到和最大的长度为 K 的子序列
 *
 * https://leetcode.cn/problems/find-subsequence-of-length-k-with-the-largest-sum/description/
 *
 * algorithms
 * Easy (50.01%)
 * Likes:    56
 * Dislikes: 0
 * Total Accepted:    15.1K
 * Total Submissions: 29.1K
 * Testcase Example:  '[2,1,3,3]\n2'
 *
 * 给你一个整数数组 nums 和一个整数 k 。你需要找到 nums 中长度为 k 的 子序列 ，且这个子序列的 和最大 。
 *
 * 请你返回 任意 一个长度为 k 的整数子序列。
 *
 * 子序列 定义为从一个数组里删除一些元素后，不改变剩下元素的顺序得到的数组。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,1,3,3], k = 2
 * 输出：[3,3]
 * 解释：
 * 子序列有最大和：3 + 3 = 6 。
 *
 * 示例 2：
 *
 * 输入：nums = [-1,-2,3,4], k = 3
 * 输出：[-1,3,4]
 * 解释：
 * 子序列有最大和：-1 + 3 + 4 = 6 。
 *
 *
 * 示例 3：
 *
 * 输入：nums = [3,4,3,3], k = 2
 * 输出：[3,4]
 * 解释：
 * 子序列有最大和：3 + 4 = 7 。
 * 另一个可行的子序列为 [4, 3] 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 1000
 * -10^5 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 *
 *
 */

// @lc code=start
package main

import "sort"

func maxSubsequence(nums []int, k int) []int {
	n := len(nums)
	arrays := make([][2]int, n)
	for i := 0; i < n; i++ {
		arrays[i] = [2]int{i, nums[i]}
	}

	sort.Slice(arrays, func(i, j int) bool {
		return arrays[i][1] > arrays[j][1]
	})

	indexes := make([]int, k)
	for i := 0; i < k; i++ {
		indexes[i] = arrays[i][0]
	}

	sort.Ints(indexes)

	result := make([]int, k)
	for i := 0; i < k; i++ {
		result[i] = nums[indexes[i]]
	}

	return result
}

// @lc code=end
