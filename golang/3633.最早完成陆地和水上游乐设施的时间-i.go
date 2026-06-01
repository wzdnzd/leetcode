/*
 * @lc app=leetcode.cn id=3633 lang=golang
 *
 * [3633] 最早完成陆地和水上游乐设施的时间 I
 *
 * https://leetcode.cn/problems/earliest-finish-time-for-land-and-water-rides-i/description/
 *
 * algorithms
 * Easy (68.55%)
 * Likes:    6
 * Dislikes: 0
 * Total Accepted:    3.3K
 * Total Submissions: 4.5K
 * Testcase Example:  '[2,8]\n[4,1]\n[6]\n[3]'
 *
 * 给你两种类别的游乐园项目：陆地游乐设施 和 水上游乐设施。
 *
 *
 * 陆地游乐设施
 *
 *
 * landStartTime[i] – 第 i 个陆地游乐设施最早可以开始的时间。
 * landDuration[i] – 第 i 个陆地游乐设施持续的时间。
 *
 *
 * 水上游乐设施
 *
 * waterStartTime[j] – 第 j 个水上游乐设施最早可以开始的时间。
 * waterDuration[j] – 第 j 个水上游乐设施持续的时间。
 *
 *
 *
 *
 * 一位游客必须从 每个 类别中体验 恰好一个 游乐设施，顺序 不限 。
 *
 *
 * 游乐设施可以在其开放时间开始，或 之后任意时间 开始。
 * 如果一个游乐设施在时间 t 开始，它将在时间 t + duration 结束。
 * 完成一个游乐设施后，游客可以立即乘坐另一个（如果它已经开放），或者等待它开放。
 *
 *
 * 返回游客完成这两个游乐设施的 最早可能时间 。
 *
 *
 *
 * 示例 1:
 *
 *
 * 输入：landStartTime = [2,8], landDuration = [4,1], waterStartTime = [6],
 * waterDuration = [3]
 *
 * 输出：9
 *
 * 解释：
 *
 *
 * 方案 A（陆地游乐设施 0 → 水上游乐设施 0）：
 *
 * 在时间 landStartTime[0] = 2 开始陆地游乐设施 0。在 2 + landDuration[0] = 6 结束。
 * 水上游乐设施 0 在时间 waterStartTime[0] = 6 开放。立即在时间 6 开始，在 6 + waterDuration[0] = 9
 * 结束。
 *
 *
 * 方案 B（水上游乐设施 0 → 陆地游乐设施 1）：
 *
 * 在时间 waterStartTime[0] = 6 开始水上游乐设施 0。在 6 + waterDuration[0] = 9 结束。
 * 陆地游乐设施 1 在 landStartTime[1] = 8 开放。在时间 9 开始，在 9 + landDuration[1] = 10
 * 结束。
 *
 *
 * 方案 C（陆地游乐设施 1 → 水上游乐设施 0）：
 *
 * 在时间 landStartTime[1] = 8 开始陆地游乐设施 1。在 8 + landDuration[1] = 9 结束。
 * 水上游乐设施 0 在 waterStartTime[0] = 6 开放。在时间 9 开始，在 9 + waterDuration[0] = 12
 * 结束。
 *
 *
 * 方案 D（水上游乐设施 0 → 陆地游乐设施 0）：
 *
 * 在时间 waterStartTime[0] = 6 开始水上游乐设施 0。在 6 + waterDuration[0] = 9 结束。
 * 陆地游乐设施 0 在 landStartTime[0] = 2 开放。在时间 9 开始，在 9 + landDuration[0] = 13
 * 结束。
 *
 *
 *
 *
 * 方案 A 提供了最早的结束时间 9。
 *
 *
 * 示例 2:
 *
 *
 * 输入：landStartTime = [5], landDuration = [3], waterStartTime = [1],
 * waterDuration = [10]
 *
 * 输出：14
 *
 * 解释：
 *
 *
 * 方案 A（水上游乐设施 0 → 陆地游乐设施 0）：
 *
 * 在时间 waterStartTime[0] = 1 开始水上游乐设施 0。在 1 + waterDuration[0] = 11 结束。
 * 陆地游乐设施 0 在 landStartTime[0] = 5 开放。立即在时间 11 开始，在 11 + landDuration[0] = 14
 * 结束。
 *
 *
 * 方案 B（陆地游乐设施 0 → 水上游乐设施 0）：
 *
 * 在时间 landStartTime[0] = 5 开始陆地游乐设施 0。在 5 + landDuration[0] = 8 结束。
 * 水上游乐设施 0 在 waterStartTime[0] = 1 开放。立即在时间 8 开始，在 8 + waterDuration[0] = 18
 * 结束。
 *
 *
 *
 *
 * 方案 A 提供了最早的结束时间 14。​​​​​​​
 *
 *
 *
 *
 * 提示:
 *
 *
 * 1 <= n, m <= 100
 * landStartTime.length == landDuration.length == n
 * waterStartTime.length == waterDuration.length == m
 * 1 <= landStartTime[i], landDuration[i], waterStartTime[j], waterDuration[j]
 * <= 1000
 *
 *
 */

// @lc code=start
package main

import "math"

func earliestFinishTime(landStartTime []int, landDuration []int, waterStartTime []int, waterDuration []int) int {
	landWater := calcEarliestFinishTime(landStartTime, landDuration, waterStartTime, waterDuration)
	waterLand := calcEarliestFinishTime(waterStartTime, waterDuration, landStartTime, landDuration)
	return min(landWater, waterLand)
}

func calcEarliestFinishTime(landStartTime []int, landDuration []int, waterStartTime []int, waterDuration []int) int {
	finish := math.MaxInt
	for i, start := range landStartTime {
		finish = min(finish, start+landDuration[i])
	}

	result := math.MaxInt
	for i, start := range waterStartTime {
		result = min(result, max(start, finish)+waterDuration[i])
	}

	return result
}

func min(x, y int) int {
	if x > y {
		return y
	}

	return x
}

func max(x, y int) int {
	if x < y {
		return y
	}

	return x
}

// @lc code=end
