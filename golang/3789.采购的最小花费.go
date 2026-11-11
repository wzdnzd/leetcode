/*
 * @lc app=leetcode.cn id=3789 lang=golang
 *
 * [3789] 采购的最小花费
 *
 * https://leetcode.cn/problems/minimum-cost-to-acquire-required-items/description/
 *
 * algorithms
 * Medium (46.31%)
 * Likes:    6
 * Dislikes: 0
 * Total Accepted:    2.9K
 * Total Submissions: 6.2K
 * Testcase Example:  '3\n2\n1\n3\n2'
 *
 * 给你五个整数 cost1, cost2, costBoth, need1 和 need2。
 *
 *
 * 有三种类型的物品可以购买：
 *
 *
 * 类型 1 的物品花费 cost1，并仅满足类型 1 的需求 1 个单位。
 * 类型 2 的物品花费 cost2，并仅满足类型 2 的需求 1 个单位。
 * 类型 3 的物品花费 costBoth，同时满足类型 1 和类型 2 的需求各 1 个单位。
 *
 *
 * 你需要购买足够的物品，使得：
 *
 *
 * 满足类型 1 的总需求 至少 为 need1。
 * 满足类型 2 的总需求 至少 为 need2。
 *
 *
 * 返回满足这些需求的 最小 可能总花费。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： cost1 = 3, cost2 = 2, costBoth = 1, need1 = 3, need2 = 2
 *
 * 输出： 3
 *
 * 解释：
 *
 * 购买 3 个类型 3 的物品，总花费为 3 * 1 = 3，此时类型 1 的总需求满足 3（>= need1 = 3），类型 2 的总需求满足 3（>=
 * need2 = 2）。
 * 任何其他有效的购买方案都会花费更多，因此最小总花费为 3。
 *
 *
 * 示例 2：
 *
 *
 * 输入： cost1 = 5, cost2 = 4, costBoth = 15, need1 = 2, need2 = 3
 *
 * 输出： 22
 *
 * 解释：
 *
 * 购买 need1 = 2 个类型 1 的物品和 need2 = 3 个类型 2 的物品，总花费为 2 * 5 + 3 * 4 = 10 + 12 =
 * 22。
 * 任何其他有效的购买方案都会花费更多，因此最小总花费为 22。
 *
 *
 * 示例 3：
 *
 *
 * 输入： cost1 = 5, cost2 = 4, costBoth = 15, need1 = 0, need2 = 0
 *
 * 输出： 0
 *
 * 解释：
 *
 * 由于不需要任何物品（need1 = need2 = 0），因此无需购买，总花费为 0。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= cost1, cost2, costBoth <= 10^6
 * 0 <= need1, need2 <= 10^9
 *
 *
 */

// @lc code=start
package main

func minimumCost(cost1, cost2, costBoth, need1, need2 int) int64 {
	// 只买两种单品，不买组合包
	costSeparateOnly := cost1*need1 + cost2*need2
	if need1 > need2 {
		need1, need2 = need2, need1
		cost2 = cost1
	}

	// 全部用组合包覆盖
	costBothOnly := costBoth * need2
	// 组合包覆盖较小需求，差额用对应单品补齐
	costBothAndRemainder := costBoth*need1 + cost2*(need2-need1)
	return int64(min(costSeparateOnly, min(costBothOnly, costBothAndRemainder)))
}

func min(x, y int) int {
	if x <= y {
		return x
	}

	return y
}

// @lc code=end
