/*
 * @lc app=leetcode.cn id=3573 lang=golang
 *
 * [3573] 买卖股票的最佳时机 V
 *
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-v/description/
 *
 * algorithms
 * Medium (50.30%)
 * Likes:    21
 * Dislikes: 0
 * Total Accepted:    5.2K
 * Total Submissions: 8.9K
 * Testcase Example:  '[1,7,9,8,2]\n2'
 *
 * 给你一个整数数组 prices，其中 prices[i] 是第 i 天股票的价格（美元），以及一个整数 k。
 *
 * 你最多可以进行 k 笔交易，每笔交易可以是以下任一类型：
 *
 *
 *
 * 普通交易：在第 i 天买入，然后在之后的第 j 天卖出，其中 i < j。你的利润是 prices[j] -
 * prices[i]。
 *
 *
 * 做空交易：在第 i 天卖出，然后在之后的第 j 天买回，其中 i < j。你的利润是 prices[i] - prices[j]。
 *
 *
 *
 * 注意：你必须在开始下一笔交易之前完成当前交易。此外，你不能在已经进行买入或卖出操作的同一天再次进行买入或卖出操作。
 *
 * 通过进行 最多 k 笔交易，返回你可以获得的最大总利润。
 *
 *
 *
 * 示例 1:
 *
 *
 * 输入: prices = [1,7,9,8,2], k = 2
 *
 * 输出: 14
 *
 * 解释:
 * 我们可以通过 2 笔交易获得 14 美元的利润：
 *
 *
 * 一笔普通交易：第 0 天以 1 美元买入，第 2 天以 9 美元卖出。
 * 一笔做空交易：第 3 天以 8 美元卖出，第 4 天以 2 美元买回。
 *
 *
 *
 * 示例 2:
 *
 *
 * 输入: prices = [12,16,19,19,8,1,19,13,9], k = 3
 *
 * 输出: 36
 *
 * 解释:
 * 我们可以通过 3 笔交易获得 36 美元的利润：
 *
 *
 * 一笔普通交易：第 0 天以 12 美元买入，第 2 天以 19 美元卖出。
 * 一笔做空交易：第 3 天以 19 美元卖出，第 4 天以 8 美元买回。
 * 一笔普通交易：第 5 天以 1 美元买入，第 6 天以 19 美元卖出。
 *
 *
 *
 *
 *
 * 提示:
 *
 *
 * 2 <= prices.length <= 10^3
 * 1 <= prices[i] <= 10^9
 * 1 <= k <= prices.length / 2
 *
 *
 */

// @lc code=start
package main

import "math"

func maximumProfit(prices []int, k int) int64 {
	dp := make([][3]int, k+2)
	for j := 1; j <= k+1; j++ {
		dp[j][1] = math.MinInt / 2
		dp[j][2] = math.MinInt / 2
	}

	dp[0][0] = math.MinInt / 2
	for _, p := range prices {
		for j := k + 1; j > 0; j-- {
			dp[j][0] = max(dp[j][0], max(dp[j][1]+p, dp[j][2]-p))
			dp[j][1] = max(dp[j][1], dp[j-1][0]-p)
			dp[j][2] = max(dp[j][2], dp[j-1][0]+p)
		}
	}

	return int64(dp[k+1][0])
}

func max(x, y int) int {
	if x < y {
		return y
	}

	return x
}

// @lc code=end
