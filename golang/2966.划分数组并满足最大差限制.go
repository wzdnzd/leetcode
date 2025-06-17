/*
 * @lc app=leetcode.cn id=2966 lang=golang
 *
 * [2966] 划分数组并满足最大差限制
 *
 * https://leetcode.cn/problems/divide-array-into-arrays-with-max-difference/description/
 *
 * algorithms
 * Medium (67.91%)
 * Likes:    7
 * Dislikes: 0
 * Total Accepted:    7.3K
 * Total Submissions: 10.3K
 * Testcase Example:  '[1,3,4,8,7,9,3,5,1]\n2'
 *
 * 给你一个长度为 n 的整数数组 nums，以及一个正整数 k 。
 *
 * 将这个数组划分为 n / 3 个长度为 3 的子数组，并满足以下条件：
 *
 *
 * 子数组中 任意 两个元素的差必须 小于或等于 k 。
 *
 *
 * 返回一个 二维数组 ，包含所有的子数组。如果不可能满足条件，就返回一个空数组。如果有多个答案，返回 任意一个 即可。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [1,3,4,8,7,9,3,5,1], k = 2
 *
 * 输出：[[1,1,3],[3,4,5],[7,8,9]]
 *
 * 解释：
 *
 * 每个数组中任何两个元素之间的差小于或等于 2。
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [2,4,2,2,5,2], k = 2
 *
 * 输出：[]
 *
 * 解释：
 *
 * 将 nums 划分为 2 个长度为 3 的数组的不同方式有：
 *
 *
 * [[2,2,2],[2,4,5]] （及其排列）
 * [[2,2,4],[2,2,5]] （及其排列）
 *
 *
 * 因为有四个 2，所以无论我们如何划分，都会有一个包含元素 2 和 5 的数组。因为 5 - 2 = 3 > k，条件无法被满足，所以没有合法的划分。
 *
 *
 * 示例 3：
 *
 *
 * 输入：nums = [4,2,9,8,2,12,7,12,10,5,8,5,5,7,9,2,5,11], k = 14
 *
 * 输出：[[2,2,12],[4,8,5],[5,9,7],[7,8,5],[5,9,10],[11,12,2]]
 *
 * 解释：
 *
 * 每个数组中任何两个元素之间的差小于或等于 14。
 *
 *
 *
 *
 * 提示：
 *
 *
 * n == nums.length
 * 1 <= n <= 10^5
 * n 是 3 的倍数
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= 10^5
 *
 *
 */

// @lc code=start
package main

import "sort"

func divideArray(nums []int, k int) [][]int {
	sort.Ints(nums)

	n := len(nums)
	result := make([][]int, 0, n/3)

	for i := 0; i < n; i += 3 {
		if nums[i+2]-nums[i] > k {
			return [][]int{}
		}

		result = append(result, nums[i:i+3])
	}

	return result
}

// @lc code=end
