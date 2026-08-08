/*
 * @lc app=leetcode.cn id=1140 lang=golang
 *
 * [1140] 石子游戏 II
 *
 * https://leetcode.cn/problems/stone-game-ii/description/
 *
 * algorithms
 * Medium (70.24%)
 * Likes:    329
 * Dislikes: 0
 * Total Accepted:    30.3K
 * Total Submissions: 43K
 * Testcase Example:  '[2,7,9,4,4]'
 *
 * Alice 和 Bob 继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。游戏以谁手中的石子最多来决出胜负。
 *
 * Alice 和 Bob 轮流进行，Alice 先开始。最初，M = 1。
 *
 * 在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。
 *
 * 游戏一直持续到所有石子都被拿走。
 *
 * 假设 Alice 和 Bob 都发挥出最佳水平，返回 Alice 可以得到的最大数量的石头。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：piles = [2,7,9,4,4]
 * 输出：10
 * 解释：如果一开始 Alice 取了一堆，Bob 取了两堆，然后 Alice 再取两堆。Alice 可以得到 2 + 4 + 4 = 10 堆。
 * 如果 Alice 一开始拿走了两堆，那么 Bob 可以拿走剩下的三堆。在这种情况下，Alice 得到 2 + 7 = 9 堆。返回
 * 10，因为它更大。
 *
 *
 * 示例 2:
 *
 *
 * 输入：piles = [1,2,3,4,5,100]
 * 输出：104
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= piles.length <= 100
 * 1 <= piles[i] <= 10^4
 *
 *
 */

// @lc code=start
package main

import "math"

func stoneGameII(piles []int) int {
	n := len(piles)
	dp := make([][]int, n)
	for row := range dp {
		dp[row] = make([]int, n+1)
	}

	for i, sum := n-1, 0; i >= 0; i-- {
		sum += piles[i]

		for m := 1; m <= i/2+1; m++ {
			if i+m*2 >= n {
				dp[i][m] = sum
			} else {
				maxV := math.MaxInt
				for x := 1; x <= m*2; x++ {
					maxV = min(maxV, dp[i+x][max(m, x)])
				}

				dp[i][m] = sum - maxV
			}
		}
	}

	return dp[0][1]
}

// @lc code=end
