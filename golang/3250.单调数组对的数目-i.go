/*
 * @lc app=leetcode.cn id=3250 lang=golang
 *
 * [3250] 单调数组对的数目 I
 *
 * https://leetcode.cn/problems/find-the-count-of-monotonic-pairs-i/description/
 *
 * algorithms
 * Hard (57.53%)
 * Likes:    9
 * Dislikes: 0
 * Total Accepted:    4.1K
 * Total Submissions: 6.7K
 * Testcase Example:  '[2,3,2]'
 *
 * 给你一个长度为 n 的 正 整数数组 nums 。
 *
 * 如果两个 非负 整数数组 (arr1, arr2) 满足以下条件，我们称它们是 单调 数组对：
 *
 *
 * 两个数组的长度都是 n 。
 * arr1 是单调 非递减 的，换句话说 arr1[0] <= arr1[1] <= ... <= arr1[n - 1] 。
 * arr2 是单调 非递增 的，换句话说 arr2[0] >= arr2[1] >= ... >= arr2[n - 1] 。
 * 对于所有的 0 <= i <= n - 1 都有 arr1[i] + arr2[i] == nums[i] 。
 *
 *
 * 请你返回所有 单调 数组对的数目。
 *
 * 由于答案可能很大，请你将它对 10^9 + 7 取余 后返回。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [2,3,2]
 *
 * 输出：4
 *
 * 解释：
 *
 * 单调数组对包括：
 *
 *
 * ([0, 1, 1], [2, 2, 1])
 * ([0, 1, 2], [2, 2, 0])
 * ([0, 2, 2], [2, 1, 0])
 * ([1, 2, 2], [1, 1, 0])
 *
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [5,5,5,5]
 *
 * 输出：126
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= n == nums.length <= 2000
 * 1 <= nums[i] <= 50
 *
 *
 */

// @lc code=start
package main

func countOfPairs(nums []int) int {
	const mod = 1e9 + 7

	n := len(nums)
	m := nums[n-1]
	dp := make([]int, m+1)

	for i := range dp[:min(nums[0], m)+1] {
		dp[i] = 1
	}

	for i := 1; i < n; i++ {
		for j := 1; j <= m; j++ {
			dp[j] += dp[j-1]
		}

		k := max(nums[i]-nums[i-1], 0)
		for j := min(nums[i], m); j >= k; j-- {
			dp[j] = dp[j-k] % mod
		}

		for i := 0; i < min(k, m+1); i++ {
			dp[i] = 0
		}
	}

	result := 0
	for _, v := range dp {
		result += v
	}

	return result % mod
}

// @lc code=end
