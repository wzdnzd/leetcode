/*
 * @lc app=leetcode.cn id=961 lang=golang
 *
 * [961] 在长度 2N 的数组中找出重复 N 次的元素
 *
 * https://leetcode.cn/problems/n-repeated-element-in-size-2n-array/description/
 *
 * algorithms
 * Easy (70.70%)
 * Likes:    208
 * Dislikes: 0
 * Total Accepted:    94.1K
 * Total Submissions: 133.1K
 * Testcase Example:  '[1,2,3,3]'
 *
 * 给你一个整数数组 nums ，该数组具有以下属性：
 *
 *
 *
 *
 * nums.length == 2 * n.
 * nums 包含 n + 1 个 不同的 元素
 * nums 中恰有一个元素重复 n 次
 *
 *
 * 找出并返回重复了 n 次的那个元素。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [1,2,3,3]
 * 输出：3
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [2,1,2,5,3,2]
 * 输出：2
 *
 *
 * 示例 3：
 *
 *
 * 输入：nums = [5,1,5,2,5,3,5,4]
 * 输出：5
 *
 *
 *
 *
 *
 *
 * 提示：
 *
 *
 * 2 <= n <= 5000
 * nums.length == 2 * n
 * 0 <= nums[i] <= 10^4
 * nums 由 n + 1 个 不同的 元素组成，且其中一个元素恰好重复 n 次
 *
 *
 */

// @lc code=start
package main

func repeatedNTimes(nums []int) int {
	records := map[int]bool{}
	for _, num := range nums {
		if records[num] {
			return num
		}

		records[num] = true
	}

	return -1
}

// @lc code=end
