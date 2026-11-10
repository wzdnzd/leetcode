/*
 * @lc app=leetcode.cn id=3693 lang=golang
 *
 * [3693] 爬楼梯 II
 *
 * https://leetcode.cn/problems/climbing-stairs-ii/description/
 *
 * algorithms
 * Medium (64.08%)
 * Likes:    34
 * Dislikes: 0
 * Total Accepted:    16.8K
 * Total Submissions: 25.7K
 * Testcase Example:  '4\n[1,2,3,4]'
 *
 * 你正在爬一个有 n + 1 级台阶的楼梯，台阶编号从 0 到 n。
 *
 *
 * 你还得到了一个长度为 n 的 下标从 1 开始 的整数数组 costs，其中 costs[i] 是第 i 级台阶的成本。
 *
 * 从第 i 级台阶，你 只能 跳到第 i + 1、i + 2 或 i + 3 级台阶。从第 i 级台阶跳到第 j 级台阶的成本定义为： costs[j]
 * + (j - i)^2
 *
 * 你从第 0 级台阶开始，初始 cost = 0。
 *
 * 返回到达第 n 级台阶所需的 最小 总成本。
 *
 *
 *
 * 示例 1:
 *
 *
 * 输入：n = 4, costs = [1,2,3,4]
 *
 * 输出：13
 *
 * 解释：
 *
 * 一个最优路径是 0 → 1 → 2 → 4
 *
 *
 *
 *
 * 跳跃
 * 成本计算
 * 成本
 *
 *
 *
 *
 * 0 → 1
 * costs[1] + (1 - 0)^2 = 1 + 1
 * 2
 *
 *
 * 1 → 2
 * costs[2] + (2 - 1)^2 = 2 + 1
 * 3
 *
 *
 * 2 → 4
 * costs[4] + (4 - 2)^2 = 4 + 4
 * 8
 *
 *
 *
 *
 * 因此，最小总成本为 2 + 3 + 8 = 13
 *
 *
 * 示例 2:
 *
 *
 * 输入：n = 4, costs = [5,1,6,2]
 *
 * 输出：11
 *
 * 解释：
 *
 * 一个最优路径是 0 → 2 → 4
 *
 *
 *
 *
 * 跳跃
 * 成本计算
 * 成本
 *
 *
 *
 *
 * 0 → 2
 * costs[2] + (2 - 0)^2 = 1 + 4
 * 5
 *
 *
 * 2 → 4
 * costs[4] + (4 - 2)^2 = 2 + 4
 * 6
 *
 *
 *
 *
 * 因此，最小总成本为 5 + 6 = 11
 *
 *
 * 示例 3:
 *
 *
 * 输入：n = 3, costs = [9,8,3]
 *
 * 输出：12
 *
 * 解释：
 *
 * 最优路径是 0 → 3，总成本 = costs[3] + (3 - 0)^2 = 3 + 9 = 12
 *
 *
 *
 *
 * 提示:
 *
 *
 * 1 <= n == costs.length <= 10^5
 * 1 <= costs[i] <= 10^4
 *
 *
 */

// @lc code=start
package main

import "math"

func climbStairs(n int, costs []int) int {
	dp := make([]int, n+1)
	for i := 1; i <= n; i++ {
		result := math.MaxInt
		for j := max(i-3, 0); j < i; j++ {
			result = min(result, dp[j]+(i-j)*(i-j))
		}

		dp[i] = result + costs[i-1]
	}

	return dp[n]
}

func max(x, y int) int {
	if x >= y {
		return x
	}

	return y
}

func min(x, y int) int {
	if x <= y {
		return x
	}

	return y
}

// @lc code=end
