/*
 * @lc app=leetcode.cn id=3360 lang=golang
 *
 * [3360] 移除石头游戏
 *
 * https://leetcode.cn/problems/stone-removal-game/description/
 *
 * algorithms
 * Easy (57.07%)
 * Likes:    4
 * Dislikes: 0
 * Total Accepted:    5.5K
 * Total Submissions: 9.1K
 * Testcase Example:  '12'
 *
 * Alice 和 Bob 在玩一个游戏，他们俩轮流从一堆石头中移除石头，Alice 先进行操作。
 *
 *
 * Alice 在第一次操作中移除 恰好 10 个石头。
 * 接下来的每次操作中，每位玩家移除的石头数 恰好 为另一位玩家上一次操作的石头数减 1 。
 *
 *
 * 第一位没法进行操作的玩家输掉这个游戏。
 *
 * 给你一个正整数 n 表示一开始石头的数目，如果 Alice 赢下这个游戏，请你返回 true ，否则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 12
 *
 * 输出：true
 *
 * 解释：
 *
 *
 * Alice 第一次操作中移除 10 个石头，剩下 2 个石头给 Bob 。
 * Bob 无法移除 9 个石头，所以 Alice 赢下游戏。
 *
 *
 *
 * 示例 2：
 *
 *
 * 输入：n = 1
 *
 * 输出：false
 *
 * 解释：
 *
 *
 * Alice 无法移除 10 个石头，所以 Alice 输掉游戏。
 *
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= n <= 50
 *
 *
 */

// @lc code=start
package main

func canAliceWin(n int) bool {
	count := 10

	for n >= count {
		n -= count
		count--
	}

	return (10-count)%2 > 0
}

// @lc code=end
