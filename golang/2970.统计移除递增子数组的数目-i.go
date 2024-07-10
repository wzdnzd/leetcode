/*
 * @lc app=leetcode.cn id=2970 lang=golang
 *
 * [2970] 统计移除递增子数组的数目 I
 *
 * https://leetcode.cn/problems/count-the-number-of-incremovable-subarrays-i/description/
 *
 * algorithms
 * Easy (61.51%)
 * Likes:    8
 * Dislikes: 0
 * Total Accepted:    4.3K
 * Total Submissions: 6.5K
 * Testcase Example:  '[1,2,3,4]'
 *
 * 给你一个下标从 0 开始的 正 整数数组 nums 。
 *
 * 如果 nums 的一个子数组满足：移除这个子数组后剩余元素 严格递增 ，那么我们称这个子数组为 移除递增 子数组。比方说，[5, 3, 4, 6, 7]
 * 中的 [3, 4] 是一个移除递增子数组，因为移除该子数组后，[5, 3, 4, 6, 7] 变为 [5, 6, 7] ，是严格递增的。
 *
 * 请你返回 nums 中 移除递增 子数组的总数目。
 *
 * 注意 ，剩余元素为空的数组也视为是递增的。
 *
 * 子数组 指的是一个数组中一段连续的元素序列。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [1,2,3,4]
 * 输出：10
 * 解释：10 个移除递增子数组分别为：[1], [2], [3], [4], [1,2], [2,3], [3,4], [1,2,3], [2,3,4]
 * 和 [1,2,3,4]。移除任意一个子数组后，剩余元素都是递增的。注意，空数组不是移除递增子数组。
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [6,5,7,8]
 * 输出：7
 * 解释：7 个移除递增子数组分别为：[5], [6], [5,7], [6,5], [5,7,8], [6,5,7] 和 [6,5,7,8] 。
 * nums 中只有这 7 个移除递增子数组。
 *
 *
 * 示例 3：
 *
 *
 * 输入：nums = [8,7,6,6]
 * 输出：3
 * 解释：3 个移除递增子数组分别为：[8,7,6], [7,6,6] 和 [8,7,6,6] 。注意 [8,7] 不是移除递增子数组因为移除 [8,7]
 * 后 nums 变为 [6,6] ，它不是严格递增的。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 50
 * 1 <= nums[i] <= 50
 *
 *
 */

// @lc code=start
package main

func incremovableSubarrayCount(nums []int) int {
	i := 0
	n := len(nums)

	for i < n-1 && nums[i] < nums[i+1] {
		i++
	}

	if i == n-1 {
		return n * (n + 1) / 2
	}

	count := i + 2
	for j := n - 1; j == n-1 || nums[j] < nums[j+1]; j-- {
		for i >= 0 && nums[i] >= nums[j] {
			i--
		}

		count += i + 2
	}

	return count
}

// @lc code=end
