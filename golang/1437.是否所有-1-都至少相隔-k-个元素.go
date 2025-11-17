/*
 * @lc app=leetcode.cn id=1437 lang=golang
 *
 * [1437] 是否所有 1 都至少相隔 k 个元素
 *
 * https://leetcode.cn/problems/check-if-all-1s-are-at-least-length-k-places-away/description/
 *
 * algorithms
 * Easy (56.28%)
 * Likes:    40
 * Dislikes: 0
 * Total Accepted:    23.7K
 * Total Submissions: 41.4K
 * Testcase Example:  '[1,0,0,0,1,0,0,1]\n2'
 *
 * 给你一个由若干 0 和 1 组成的数组 nums 以及整数 k。如果所有 1 都至少相隔 k 个元素，则返回 true ；否则，返回 false
 * 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 *
 * 输入：nums = [1,0,0,0,1,0,0,1], k = 2
 * 输出：true
 * 解释：每个 1 都至少相隔 2 个元素。
 *
 * 示例 2：
 *
 *
 *
 *
 * 输入：nums = [1,0,0,1,0,1], k = 2
 * 输出：false
 * 解释：第二个 1 和第三个 1 之间只隔了 1 个元素。
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 10^5
 * 0 <= k <= nums.length
 * nums[i] 的值为 0 或 1
 *
 *
 */

// @lc code=start
package main

func kLengthApart(nums []int, k int) bool {
	left, n := 0, len(nums)
	for left < n && nums[left] != 1 {
		left++
	}

	for right := left + 1; right < n; right++ {
		if nums[right] == 1 {
			if right-left <= k {
				return false
			}

			left = right
		}
	}

	return true
}

// @lc code=end
