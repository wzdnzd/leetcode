/*
 * @lc app=leetcode.cn id=910 lang=golang
 *
 * [910] 最小差值 II
 *
 * https://leetcode.cn/problems/smallest-range-ii/description/
 *
 * algorithms
 * Medium (36.58%)
 * Likes:    204
 * Dislikes: 0
 * Total Accepted:    15.6K
 * Total Submissions: 40.9K
 * Testcase Example:  '[1]\n0'
 *
 * 给你一个整数数组 nums，和一个整数 k 。
 *
 * 对于每个下标 i（0 <= i < nums.length），将 nums[i] 变成  nums[i] + k 或 nums[i] - k 。
 *
 * nums 的 分数 是 nums 中最大元素和最小元素的差值。
 *
 * 在更改每个下标对应的值之后，返回 nums 的最小 分数 。
 *
 *
 *
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [1], k = 0
 * 输出：0
 * 解释：分数 = max(nums) - min(nums) = 1 - 1 = 0 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [0,10], k = 2
 * 输出：6
 * 解释：将数组变为 [2, 8] 。分数 = max(nums) - min(nums) = 8 - 2 = 6 。
 *
 *
 * 示例 3：
 *
 *
 * 输入：nums = [1,3,6], k = 3
 * 输出：3
 * 解释：将数组变为 [4, 6, 3] 。分数 = max(nums) - min(nums) = 6 - 3 = 3 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 10^4
 * 0 <= k <= 10^4
 *
 *
 */

// @lc code=start
package main

import "slices"

func smallestRangeII(nums []int, k int) int {
	n := len(nums)
	if n == 1 {
		return 0
	}

	slices.Sort(nums)
	result := nums[n-1] - nums[0]

	for i := 1; i < n; i++ {
		high := max(nums[n-1]-k, nums[i-1]+k)
		low := min(nums[0]+k, nums[i]-k)
		result = min(result, high-low)
	}

	return result
}

// @lc code=end
