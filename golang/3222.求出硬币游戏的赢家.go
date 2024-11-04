/*
 * @lc app=leetcode.cn id=3222 lang=golang
 *
 * [3222] 求出硬币游戏的赢家
 *
 * https://leetcode.cn/problems/find-the-winning-player-in-coin-game/description/
 *
 * algorithms
 * Easy (63.13%)
 * Likes:    4
 * Dislikes: 0
 * Total Accepted:    6.4K
 * Total Submissions: 9.6K
 * Testcase Example:  '2\n7'
 *
 * 给你两个 正 整数 x 和 y ，分别表示价值为 75 和 10 的硬币的数目。
 *
 * Alice 和 Bob 正在玩一个游戏。每一轮中，Alice 先进行操作，Bob 后操作。每次操作中，玩家需要拿出价值 总和 为 115
 * 的硬币。如果一名玩家无法执行此操作，那么这名玩家 输掉 游戏。
 *
 * 两名玩家都采取 最优 策略，请你返回游戏的赢家。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：x = 2, y = 7
 *
 * 输出："Alice"
 *
 * 解释：
 *
 * 游戏一次操作后结束：
 *
 *
 * Alice 拿走 1 枚价值为 75 的硬币和 4 枚价值为 10 的硬币。
 *
 *
 *
 * 示例 2：
 *
 *
 * 输入：x = 4, y = 11
 *
 * 输出："Bob"
 *
 * 解释：
 *
 * 游戏 2 次操作后结束：
 *
 *
 * Alice 拿走 1 枚价值为 75 的硬币和 4 枚价值为 10 的硬币。
 * Bob 拿走 1 枚价值为 75 的硬币和 4 枚价值为 10 的硬币。
 *
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= x, y <= 100
 *
 *
 */

// @lc code=start
package main

func losingPlayer(x int, y int) string {
	if min(x, y/4)%2 != 0 {
		return "Alice"
	}

	return "Bob"
}

// @lc code=end
