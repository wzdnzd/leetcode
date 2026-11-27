/*
 * @lc app=leetcode.cn id=3946 lang=golang
 *
 * [3946] 购买最多物品数目 I
 *
 * https://leetcode.cn/problems/maximum-number-of-items-from-sale-i/description/
 *
 * algorithms
 * Medium (41.75%)
 * Likes:    8
 * Dislikes: 0
 * Total Accepted:    2.8K
 * Total Submissions: 5.9K
 * Testcase Example:  '[[6,2],[2,6],[3,4]]\n9'
 *
 * 给你一个二维整数数组 items，其中 items[i] = [factori, pricei] 表示下标为 i 的物品。同时给你一个整数
 * budget。
 *
 * 每种物品都有无限个可供购买。你可以购买任意数量的任意物品，但购买物品的总花费最多为 budget。
 *
 * 购买物品后，你可以根据以下规则获得免费的物品：
 *
 *
 * 如果你购买了若干个物品 i，所有满足 j != i 且 factori 可以整除 factorj 的物品 j ，你都能 免费 获得一份。
 * 重复购买物品 i 不能 再获取额外的免费物品。
 * 如果免费物品 j 是通过购买不同种类的物品获得的，那么同一种物品 j 可以被免费获得多次。
 *
 *
 * 返回你在购买物品花费最多为 budget 的前提下，能够获得的 物品最大总数 ，包括购买的物品和免费的物品。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： items = [[6,2],[2,6],[3,4]], budget = 9
 *
 * 输出： 4
 *
 * 解释：
 *
 *
 * 你可以购买 2 个物品 0 和 1 个物品 2，总花费为 2 * 2 + 4 = 8，不超过 budget = 9。
 * 购买物品 2 可以免费获得 1 个物品 0，因为 factor2 = 3 可以整除 factor0 = 6。
 * 你最终拥有 3 个购买的物品和 1 个免费物品，总共 4 个物品。
 *
 *
 *
 * 示例 2：
 *
 *
 * 输入： items = [[2,4],[3,2],[4,1],[6,4],[12,4]], budget = 8
 *
 * 输出： 10
 *
 * 解释：
 *
 *
 * 你可以购买 1 个物品 0、1 个物品 1 以及 2 个物品 2，总花费为 4 + 2 + 2 * 1 = 8。
 * 购买物品 0 可以免费获得物品 2、3 和 4 各 1 个。
 * 购买物品 1 可以免费获得物品 3 和 4 各 1 个。
 * 购买物品 2 可以免费获得 1 个物品 4。
 * 因此，你获得了 6 个免费物品。你最终拥有 4 个购买的物品和 6 个免费物品，总共 10 个物品。
 *
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= items.length <= 1000
 * items[i] = [factori, pricei]
 * 1 <= factori, pricei <= 1500
 * 1 <= budget <= 1500
 *
 *
 */

// @lc code=start
package main

import "math"

func maximumSaleItems(items [][]int, budget int) int {
	dp := make([]int, budget+1)
	minPrice := math.MaxInt

	for _, item := range items {
		factor, price := item[0], item[1]
		minPrice = min(minPrice, price)

		count := 0
		for _, other := range items {
			if other[0]%factor == 0 {
				count++
			}
		}

		for j := budget; j >= price; j-- {
			dp[j] = max(dp[j], dp[j-price]+count)
		}
	}

	result := 0
	for i, num := range dp {
		result = max(result, num+(budget-i)/minPrice)
	}

	return result
}

// @lc code=end
