/*
 * @lc app=leetcode.cn id=3185 lang=golang
 *
 * [3185] 构成整天的下标对数目 II
 *
 * https://leetcode.cn/problems/count-pairs-that-form-a-complete-day-ii/description/
 *
 * algorithms
 * Medium (49.49%)
 * Likes:    8
 * Dislikes: 0
 * Total Accepted:    7.4K
 * Total Submissions: 14.4K
 * Testcase Example:  '[12,12,30,24,24]'
 *
 * 给你一个整数数组 hours，表示以 小时 为单位的时间，返回一个整数，表示满足 i < j 且 hours[i] + hours[j] 构成 整天
 * 的下标对 i, j 的数目。
 *
 * 整天 定义为时间持续时间是 24 小时的 整数倍 。
 *
 * 例如，1 天是 24 小时，2 天是 48 小时，3 天是 72 小时，以此类推。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： hours = [12,12,30,24,24]
 *
 * 输出： 2
 *
 * 解释：
 *
 * 构成整天的下标对分别是 (0, 1) 和 (3, 4)。
 *
 *
 * 示例 2：
 *
 *
 * 输入： hours = [72,48,24,3]
 *
 * 输出： 3
 *
 * 解释：
 *
 * 构成整天的下标对分别是 (0, 1)、(0, 2) 和 (1, 2)。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= hours.length <= 5 * 10^5
 * 1 <= hours[i] <= 10^9
 *
 *
 */

// @lc code=start
package main

func countCompleteDayPairs(hours []int) int64 {
	const HOURS_OF_DAY = 24
	arrays := [HOURS_OF_DAY]int{}
	var result int64

	for _, hour := range hours {
		index := hour % HOURS_OF_DAY
		result += int64(arrays[(HOURS_OF_DAY-index)%HOURS_OF_DAY])
		arrays[index]++
	}

	return result
}

// @lc code=end
