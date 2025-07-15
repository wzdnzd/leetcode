/*
 * @lc app=leetcode.cn id=3201 lang=golang
 *
 * [3201] 找出有效子序列的最大长度 I
 *
 * https://leetcode.cn/problems/find-the-maximum-length-of-valid-subsequence-i/description/
 *
 * algorithms
 * Medium (38.79%)
 * Likes:    11
 * Dislikes: 0
 * Total Accepted:    6.1K
 * Total Submissions: 14.6K
 * Testcase Example:  '[1,2,3,4]'
 *
 * 给你一个整数数组 nums。
 *
 * nums 的子序列 sub 的长度为 x ，如果其满足以下条件，则称其为 有效子序列：
 *
 *
 * (sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x
 * - 1]) % 2
 *
 *
 * 返回 nums 的 最长的有效子序列 的长度。
 *
 * 一个 子序列 指的是从原数组中删除一些元素（也可以不删除任何元素），剩余元素保持原来顺序组成的新数组。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： nums = [1,2,3,4]
 *
 * 输出： 4
 *
 * 解释：
 *
 * 最长的有效子序列是 [1, 2, 3, 4]。
 *
 *
 * 示例 2：
 *
 *
 * 输入： nums = [1,2,1,1,2,1,2]
 *
 * 输出： 6
 *
 * 解释：
 *
 * 最长的有效子序列是 [1, 2, 1, 2, 1, 2]。
 *
 *
 * 示例 3：
 *
 *
 * 输入： nums = [1,3]
 *
 * 输出： 2
 *
 * 解释：
 *
 * 最长的有效子序列是 [1, 3]。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 2 <= nums.length <= 2 * 10^5
 * 1 <= nums[i] <= 10^7
 *
 *
 */

// @lc code=start
package main

func maximumLength(nums []int) int {
	parrerns := [][]int{{0, 0}, {0, 1}, {1, 0}, {1, 1}}

	count := 0
	for _, parrern := range parrerns {
		k := 0
		for _, num := range nums {
			if num%2 == parrern[k%2] {
				k++
			}
		}

		count = max(count, k)
	}

	return count
}

// @lc code=end
