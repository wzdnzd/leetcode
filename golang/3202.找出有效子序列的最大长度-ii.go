/*
 * @lc app=leetcode.cn id=3202 lang=golang
 *
 * [3202] 找出有效子序列的最大长度 II
 *
 * https://leetcode.cn/problems/find-the-maximum-length-of-valid-subsequence-ii/description/
 *
 * algorithms
 * Medium (46.11%)
 * Likes:    22
 * Dislikes: 0
 * Total Accepted:    7.2K
 * Total Submissions: 14.1K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * 给你一个整数数组 nums 和一个 正 整数 k 。
 * nums 的一个 子序列 sub 的长度为 x ，如果其满足以下条件，则称其为 有效子序列 ：
 *
 *
 * (sub[0] + sub[1]) % k == (sub[1] + sub[2]) % k == ... == (sub[x - 2] + sub[x
 * - 1]) % k
 *
 * 返回 nums 的 最长有效子序列 的长度。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [1,2,3,4,5], k = 2
 *
 * 输出：5
 *
 * 解释：
 *
 * 最长有效子序列是 [1, 2, 3, 4, 5] 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [1,4,2,3,1,4], k = 3
 *
 * 输出：4
 *
 * 解释：
 *
 * 最长有效子序列是 [1, 4, 1, 4] 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 2 <= nums.length <= 10^3
 * 1 <= nums[i] <= 10^7
 * 1 <= k <= 10^3
 *
 *
 */

// @lc code=start
package main

func maximumLength(nums []int, k int) int {
	dp := make([][]int, k)
	for i := range dp {
		dp[i] = make([]int, k)
	}

	count := 0
	for _, num := range nums {
		num %= k
		for prev := 0; prev < k; prev++ {
			dp[prev][num] = dp[num][prev] + 1
			count = max(count, dp[prev][num])
		}
	}

	return count
}

// @lc code=end
