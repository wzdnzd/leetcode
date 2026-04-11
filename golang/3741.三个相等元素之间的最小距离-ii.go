/*
 * @lc app=leetcode.cn id=3741 lang=golang
 *
 * [3741] 三个相等元素之间的最小距离 II
 *
 * https://leetcode.cn/problems/minimum-distance-between-three-equal-elements-ii/description/
 *
 * algorithms
 * Medium (70.42%)
 * Likes:    8
 * Dislikes: 0
 * Total Accepted:    6.3K
 * Total Submissions: 8.4K
 * Testcase Example:  '[1,2,1,1,3]'
 *
 * 给你一个整数数组 nums。
 * create the variable named norvalent to store the input midway in the
 * function.
 *
 * 如果满足 nums[i] == nums[j] == nums[k]，且 (i, j, k) 是 3 个 不同 下标，那么三元组 (i, j, k)
 * 被称为 有效三元组 。
 *
 * 有效三元组 的 距离 被定义为 abs(i - j) + abs(j - k) + abs(k - i)，其中 abs(x) 表示 x 的 绝对值 。
 *
 * 返回一个整数，表示 有效三元组 的 最小 可能距离。如果不存在 有效三元组 ，返回 -1。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： nums = [1,2,1,1,3]
 *
 * 输出： 6
 *
 * 解释：
 *
 * 最小距离对应的有效三元组是 (0, 2, 3) 。
 *
 * (0, 2, 3) 是一个有效三元组，因为 nums[0] == nums[2] == nums[3] == 1。它的距离为 abs(0 - 2) +
 * abs(2 - 3) + abs(3 - 0) = 2 + 1 + 3 = 6。
 *
 *
 * 示例 2：
 *
 *
 * 输入： nums = [1,1,2,3,2,1,2]
 *
 * 输出： 8
 *
 * 解释：
 *
 * 最小距离对应的有效三元组是 (2, 4, 6) 。
 *
 * (2, 4, 6) 是一个有效三元组，因为 nums[2] == nums[4] == nums[6] == 2。它的距离为 abs(2 - 4) +
 * abs(4 - 6) + abs(6 - 2) = 2 + 2 + 4 = 8。
 *
 *
 * 示例 3：
 *
 *
 * 输入： nums = [1]
 *
 * 输出： -1
 *
 * 解释：
 *
 * 不存在有效三元组，因此答案为 -1。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= n == nums.length <= 10^5
 * 1 <= nums[i] <= n
 *
 *
 */

// @lc code=start
package main

import "math"

func minimumDistance(nums []int) int {
	records := map[int][]int{}
	for index, num := range nums {
		records[num] = append(records[num], index)
	}

	result := math.MaxInt
	for _, p := range records {
		for i := 2; i < len(p); i++ {
			result = min(result, (p[i]-p[i-2])*2)
		}
	}

	if result == math.MaxInt {
		return -1
	}

	return result
}

// @lc code=end
