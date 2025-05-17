/*
 * @lc app=leetcode.cn id=1931 lang=golang
 *
 * [1931] 用三种不同颜色为网格涂色
 *
 * https://leetcode.cn/problems/painting-a-grid-with-three-different-colors/description/
 *
 * algorithms
 * Hard (61.32%)
 * Likes:    54
 * Dislikes: 0
 * Total Accepted:    4.4K
 * Total Submissions: 7.1K
 * Testcase Example:  '1\n1'
 *
 * 给你两个整数 m 和 n 。构造一个 m x n 的网格，其中每个单元格最开始是白色。请你用 红、绿、蓝
 * 三种颜色为每个单元格涂色。所有单元格都需要被涂色。
 *
 * 涂色方案需要满足：不存在相邻两个单元格颜色相同的情况 。返回网格涂色的方法数。因为答案可能非常大， 返回 对 10^9 + 7 取余 的结果。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：m = 1, n = 1
 * 输出：3
 * 解释：如上图所示，存在三种可能的涂色方案。
 *
 *
 * 示例 2：
 *
 *
 * 输入：m = 1, n = 2
 * 输出：6
 * 解释：如上图所示，存在六种可能的涂色方案。
 *
 *
 * 示例 3：
 *
 *
 * 输入：m = 5, n = 5
 * 输出：580986
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1
 * 1
 *
 *
 */

// @lc code=start
package main

import "math"

const mod = 1000000007

func colorTheGrid(m int, n int) int {
	valid := make(map[int][]int)

	maskEnd := int(math.Pow(3, float64(m)))
	for mask := 0; mask < maskEnd; mask++ {
		color := make([]int, m)
		tempMask := mask
		for i := 0; i < m; i++ {
			color[i] = tempMask % 3
			tempMask /= 3
		}

		check := true
		for i := 0; i < m-1; i++ {
			if color[i] == color[i+1] {
				check = false
				break
			}
		}

		if check {
			valid[mask] = color
		}
	}

	adjacent := make(map[int][]int)
	for prevConfig := range valid {
		for currConfig := range valid {
			check := true
			for i := 0; i < m; i++ {
				if valid[prevConfig][i] == valid[currConfig][i] {
					check = false
					break
				}
			}

			if check {
				adjacent[prevConfig] = append(adjacent[prevConfig], currConfig)
			}
		}
	}

	dp := make(map[int]int)
	for mask := range valid {
		dp[mask] = 1
	}

	for i := 1; i < n; i++ {
		nextDpState := make(map[int]int)
		for currConfig := range valid {
			for _, prevConfig := range adjacent[currConfig] {
				nextDpState[currConfig] = (nextDpState[currConfig] + dp[prevConfig]) % mod
			}
		}

		dp = nextDpState
	}

	count := 0
	for _, num := range dp {
		count = (count + num) % mod
	}

	return count
}

// @lc code=end
