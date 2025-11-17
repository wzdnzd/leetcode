/*
 * @lc app=leetcode.cn id=1004 lang=golang
 *
 * [1004] 最大连续1的个数 III
 *
 * https://leetcode.cn/problems/max-consecutive-ones-iii/description/
 *
 * algorithms
 * Medium (61.38%)
 * Likes:    811
 * Dislikes: 0
 * Total Accepted:    221.8K
 * Total Submissions: 361.2K
 * Testcase Example:  '[1,1,1,0,0,0,1,1,1,1,0]\n2'
 *
 * 给定一个二进制数组 nums 和一个整数 k，假设最多可以翻转 k 个 0 ，则返回执行操作后 数组中连续 1 的最大个数 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：[1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 *
 * 示例 2：
 *
 *
 * 输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 10^5
 * nums[i] 不是 0 就是 1
 * 0 <= k <= nums.length
 *
 *
 */

// @lc code=start
package main

func longestOnes(nums []int, k int) int {
	left, zeros, count := 0, 0, 0
	for right, num := range nums {
		if num == 0 {
			zeros++
		}

		for zeros > k {
			if nums[left] == 0 {
				zeros--
			}

			left++
		}

		count = max(count, right-left+1)
	}

	return count
}

// @lc code=end
