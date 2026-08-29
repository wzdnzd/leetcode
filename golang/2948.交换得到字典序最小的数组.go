/*
 * @lc app=leetcode.cn id=2948 lang=golang
 *
 * [2948] 交换得到字典序最小的数组
 *
 * https://leetcode.cn/problems/make-lexicographically-smallest-array-by-swapping-elements/description/
 *
 * algorithms
 * Medium (48.99%)
 * Likes:    45
 * Dislikes: 0
 * Total Accepted:    6.1K
 * Total Submissions: 11.6K
 * Testcase Example:  '[1,5,3,9,8]\n2'
 *
 * 给你一个下标从 0 开始的 正整数 数组 nums 和一个 正整数 limit 。
 *
 * 在一次操作中，你可以选择任意两个下标 i 和 j，如果 满足 |nums[i] - nums[j]| <= limit ，则交换 nums[i] 和
 * nums[j] 。
 *
 * 返回执行任意次操作后能得到的 字典序最小的数组 。
 *
 * 如果在数组 a 和数组 b 第一个不同的位置上，数组 a 中的对应元素比数组 b 中的对应元素的字典序更小，则认为数组 a 就比数组 b
 * 字典序更小。例如，数组 [2,10,3] 比数组 [10,2,3] 字典序更小，下标 0 处是两个数组第一个不同的位置，且 2 < 10 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [1,5,3,9,8], limit = 2
 * 输出：[1,3,5,8,9]
 * 解释：执行 2 次操作：
 * - 交换 nums[1] 和 nums[2] 。数组变为 [1,3,5,9,8] 。
 * - 交换 nums[3] 和 nums[4] 。数组变为 [1,3,5,8,9] 。
 * 即便执行更多次操作，也无法得到字典序更小的数组。
 * 注意，执行不同的操作也可能会得到相同的结果。
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [1,7,6,18,2,1], limit = 3
 * 输出：[1,6,7,18,1,2]
 * 解释：执行 3 次操作：
 * - 交换 nums[1] 和 nums[2] 。数组变为 [1,6,7,18,2,1] 。
 * - 交换 nums[0] 和 nums[4] 。数组变为 [2,6,7,18,1,1] 。
 * - 交换 nums[0] 和 nums[5] 。数组变为 [1,6,7,18,1,2] 。
 * 即便执行更多次操作，也无法得到字典序更小的数组。
 *
 *
 * 示例 3：
 *
 *
 * 输入：nums = [1,7,28,19,10], limit = 3
 * 输出：[1,7,28,19,10]
 * 解释：[1,7,28,19,10] 是字典序最小的数组，因为不管怎么选择下标都无法执行操作。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= limit <= 10^9
 *
 *
 */

// @lc code=start
package main

import "slices"

func lexicographicallySmallestArray(nums []int, limit int) []int {
	n := len(nums)
	positions := make([]int, n)
	for position := range positions {
		positions[position] = position
	}
	slices.SortFunc(positions, func(i, j int) int { return nums[i] - nums[j] })

	groups := []int{}
	records := make([]int, n)

	for i, p := range positions {
		if i == 0 || nums[p]-nums[positions[i-1]] > limit {
			groups = append(groups, i)
		}

		records[p] = len(groups) - 1
	}

	result := make([]int, n)
	for i, gid := range records {
		result[i] = nums[positions[groups[gid]]]
		groups[gid]++
	}

	return result
}

// @lc code=end
