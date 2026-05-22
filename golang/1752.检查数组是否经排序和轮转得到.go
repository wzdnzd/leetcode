/*
 * @lc app=leetcode.cn id=1752 lang=golang
 *
 * [1752] 检查数组是否经排序和轮转得到
 *
 * https://leetcode.cn/problems/check-if-array-is-sorted-and-rotated/description/
 *
 * algorithms
 * Easy (57.90%)
 * Likes:    109
 * Dislikes: 0
 * Total Accepted:    37.2K
 * Total Submissions: 63.8K
 * Testcase Example:  '[3,4,5,1,2]'
 *
 * 给你一个数组 nums 。nums 的源数组中，所有元素与 nums 相同，但按非递减顺序排列。
 *
 * 如果 nums 能够由源数组轮转若干位置（包括 0 个位置）得到，则返回 true ；否则，返回 false 。
 *
 * 源数组中可能存在 重复项 。
 *
 * 注意：数组 A 在轮转 x 个位置后得到长度相同的数组 B ，使得对于每一个有效的下标 i，满足 B[i] == A[(i+x) %
 * A.length]。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [3,4,5,1,2]
 * 输出：true
 * 解释：[1,2,3,4,5] 为有序的源数组。
 * 可以轮转 x = 2 个位置，使新数组从值为 3 的元素开始：[3,4,5,1,2] 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [2,1,3,4]
 * 输出：false
 * 解释：源数组无法经轮转得到 nums 。
 *
 *
 * 示例 3：
 *
 *
 * 输入：nums = [1,2,3]
 * 输出：true
 * 解释：[1,2,3] 为有序的源数组。
 * 可以轮转 x = 0 个位置（即不轮转）得到 nums 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 *
 *
 */

// @lc code=start
package main

func check(nums []int) bool {
	index, n := 0, len(nums)

	for i := 1; i < n; i++ {
		if nums[i] < nums[i-1] {
			index = i
			break
		}
	}

	if index == 0 {
		return true
	}

	for i := index + 1; i < n; i++ {
		if nums[i] < nums[i-1] {
			return false
		}
	}

	return nums[0] >= nums[n-1]
}

// @lc code=end
