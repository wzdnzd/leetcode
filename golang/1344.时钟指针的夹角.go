/*
 * @lc app=leetcode.cn id=1344 lang=golang
 *
 * [1344] 时钟指针的夹角
 *
 * https://leetcode.cn/problems/angle-between-hands-of-a-clock/description/
 *
 * algorithms
 * Medium (61.33%)
 * Likes:    63
 * Dislikes: 0
 * Total Accepted:    16K
 * Total Submissions: 25.7K
 * Testcase Example:  '12\n30'
 *
 * 给你两个数 hour 和 minutes 。请你返回在时钟上，由给定时间的时针和分针组成的较小角的角度（60 单位制）。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：hour = 12, minutes = 30
 * 输出：165
 *
 *
 * 示例 2：
 *
 *
 *
 * 输入：hour = 3, minutes = 30
 * 输出；75
 *
 *
 * 示例 3：
 *
 *
 *
 * 输入：hour = 3, minutes = 15
 * 输出：7.5
 *
 *
 * 示例 4：
 *
 * 输入：hour = 4, minutes = 50
 * 输出：155
 *
 *
 * 示例 5：
 *
 * 输入：hour = 12, minutes = 0
 * 输出：0
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= hour <= 12
 * 0 <= minutes <= 59
 * 与标准答案误差在 10^-5 以内的结果都被视为正确结果。
 *
 *
 */

// @lc code=start
package main

func angleClock(hour, minutes int) float64 {
	degree := abs(hour*60 - minutes*11)
	return float64(min(degree, 720-degree)) / 2
}

func abs(x int) int {
	if x < 0 {
		return -x
	}

	return x
}

func min(x, y int) int {
	if x < y {
		return x
	}

	return y
}

// @lc code=end
