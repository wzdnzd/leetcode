/*
 * @lc app=leetcode.cn id=3254 lang=golang
 *
 * [3254] 长度为 K 的子数组的能量值 I
 *
 * https://leetcode.cn/problems/find-the-power-of-k-size-subarrays-i/description/
 *
 * algorithms
 * Medium (51.15%)
 * Likes:    8
 * Dislikes: 0
 * Total Accepted:    4.7K
 * Total Submissions: 8.6K
 * Testcase Example:  '[1,2,3,4,3,2,5]\n3'
 *
 * 给你一个长度为 n 的整数数组 nums 和一个正整数 k 。
 *
 * 一个数组的 能量值 定义为：
 *
 *
 * 如果 所有 元素都是依次 连续 且 上升 的，那么能量值为 最大 的元素。
 * 否则为 -1 。
 *
 *
 * 你需要求出 nums 中所有长度为 k 的 子数组 的能量值。
 *
 * 请你返回一个长度为 n - k + 1 的整数数组 results ，其中 results[i] 是子数组 nums[i..(i + k - 1)]
 * 的能量值。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [1,2,3,4,3,2,5], k = 3
 *
 * 输出：[3,4,-1,-1,-1]
 *
 * 解释：
 *
 * nums 中总共有 5 个长度为 3 的子数组：
 *
 *
 * [1, 2, 3] 中最大元素为 3 。
 * [2, 3, 4] 中最大元素为 4 。
 * [3, 4, 3] 中元素 不是 连续的。
 * [4, 3, 2] 中元素 不是 上升的。
 * [3, 2, 5] 中元素 不是 连续的。
 *
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [2,2,2,2,2], k = 4
 *
 * 输出：[-1,-1]
 *
 *
 * 示例 3：
 *
 *
 * 输入：nums = [3,2,3,2,3,2], k = 2
 *
 * 输出：[-1,3,-1,3,-1]
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= n == nums.length <= 500
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= n
 *
 *
 */

// @lc code=start
package main

func resultsArray(nums []int, k int) []int {
	n := len(nums)
	count := 0
	results := make([]int, n-k+1)

	for i := 0; i < n; i++ {
		if i == 0 || nums[i]-nums[i-1] != 1 {
			count = 1
		} else {
			count++
		}

		if count >= k {
			results[i-k+1] = nums[i]
		} else if i-k+1 >= 0 {
			results[i-k+1] = -1
		}
	}

	return results
}

// @lc code=end
