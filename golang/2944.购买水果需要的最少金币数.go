/*
 * @lc app=leetcode.cn id=2944 lang=golang
 *
 * [2944] 购买水果需要的最少金币数
 *
 * https://leetcode.cn/problems/minimum-number-of-coins-for-fruits/description/
 *
 * algorithms
 * Medium (63.18%)
 * Likes:    31
 * Dislikes: 0
 * Total Accepted:    8.2K
 * Total Submissions: 12.4K
 * Testcase Example:  '[3,1,2]'
 *
 * 给你一个 下标从 1 开始的 整数数组 prices ，其中 prices[i] 表示你购买第 i 个水果需要花费的金币数目。
 *
 * 水果超市有如下促销活动：
 *
 *
 * 如果你花费 prices[i] 购买了下标为 i 的水果，那么你可以免费获得下标范围在 [i + 1, i + i] 的水果。
 *
 *
 * 注意 ，即使你 可以 免费获得水果 j ，你仍然可以花费 prices[j] 个金币去购买它以获得它的奖励。
 *
 * 请你返回获得所有水果所需要的 最少 金币数。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：prices = [3,1,2]
 *
 * 输出：4
 *
 * 解释：
 *
 *
 * 用 prices[0] = 3 个金币购买第 1 个水果，你可以免费获得第 2 个水果。
 * 用 prices[1] = 1 个金币购买第 2 个水果，你可以免费获得第 3 个水果。
 * 免费获得第 3 个水果。
 *
 *
 * 请注意，即使您可以免费获得第 2 个水果作为购买第 1 个水果的奖励，但您购买它是为了获得其奖励，这是更优化的。
 *
 *
 * 示例 2：
 *
 *
 * 输入：prices = [1,10,1,1]
 *
 * 输出：2
 *
 * 解释：
 *
 *
 * 用 prices[0] = 1 个金币购买第 1 个水果，你可以免费获得第 2 个水果。
 * 免费获得第 2 个水果。
 * 用 prices[2] = 1 个金币购买第 3 个水果，你可以免费获得第 4 个水果。
 * 免费获得第 4 个水果。
 *
 *
 *
 * 示例 3：
 *
 *
 * 输入：prices = [26,18,6,12,49,7,45,45]
 *
 * 输出：39
 *
 * 解释：
 *
 *
 * 用 prices[0] = 26 个金币购买第 1 个水果，你可以免费获得第 2 个水果。
 * 免费获得第 2 个水果。
 * 用 prices[2] = 6 个金币购买第 3 个水果，你可以免费获得第 4，5，6（接下来的三个）水果。
 * 免费获得第 4 个水果。
 * 免费获得第 5 个水果。
 * 用 prices[5] = 7 个金币购买第 6 个水果，你可以免费获得第 7 和 第 8 个水果。
 * 免费获得第 7 个水果。
 * 免费获得第 8 个水果。
 *
 *
 * 请注意，即使您可以免费获得第 6 个水果作为购买第 3 个水果的奖励，但您购买它是为了获得其奖励，这是更优化的。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= prices.length <= 1000
 * 1 <= prices[i] <= 10^5
 *
 *
 */

// @lc code=start
package main

func minimumCoins(prices []int) int {
	n := len(prices)
	queue := [][2]int{{n, 0}}

	for i := n - 1; i >= 0; i-- {
		for len(queue) > 0 && queue[len(queue)-1][0] >= 2*i+3 {
			queue = queue[:len(queue)-1]
		}

		current := queue[len(queue)-1][1] + prices[i]
		for len(queue) > 0 && queue[0][1] >= current {
			queue = queue[1:]
		}

		queue = append([][2]int{{i, current}}, queue...)
	}

	return queue[0][1]
}

// @lc code=end
