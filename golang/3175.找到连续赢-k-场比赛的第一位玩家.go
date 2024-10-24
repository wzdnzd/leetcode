/*
 * @lc app=leetcode.cn id=3175 lang=golang
 *
 * [3175] 找到连续赢 K 场比赛的第一位玩家
 *
 * https://leetcode.cn/problems/find-the-first-player-to-win-k-games-in-a-row/description/
 *
 * algorithms
 * Medium (46.15%)
 * Likes:    9
 * Dislikes: 0
 * Total Accepted:    5.6K
 * Total Submissions: 11.3K
 * Testcase Example:  '[4,2,6,3,9]\n2'
 *
 * 有 n 位玩家在进行比赛，玩家编号依次为 0 到 n - 1 。
 *
 * 给你一个长度为 n 的整数数组 skills 和一个 正 整数 k ，其中 skills[i] 是第 i 位玩家的技能等级。skills 中所有整数
 * 互不相同 。
 *
 * 所有玩家从编号 0 到 n - 1 排成一列。
 *
 * 比赛进行方式如下：
 *
 *
 * 队列中最前面两名玩家进行一场比赛，技能等级 更高 的玩家胜出。
 * 比赛后，获胜者保持在队列的开头，而失败者排到队列的末尾。
 *
 *
 * 这个比赛的赢家是 第一位连续 赢下 k 场比赛的玩家。
 *
 * 请你返回这个比赛的赢家编号。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：skills = [4,2,6,3,9], k = 2
 *
 * 输出：2
 *
 * 解释：
 *
 * 一开始，队列里的玩家为 [0,1,2,3,4] 。比赛过程如下：
 *
 *
 * 玩家 0 和 1 进行一场比赛，玩家 0 的技能等级高于玩家 1 ，玩家 0 胜出，队列变为 [0,2,3,4,1] 。
 * 玩家 0 和 2 进行一场比赛，玩家 2 的技能等级高于玩家 0 ，玩家 2 胜出，队列变为 [2,3,4,1,0] 。
 * 玩家 2 和 3 进行一场比赛，玩家 2 的技能等级高于玩家 3 ，玩家 2 胜出，队列变为 [2,4,1,0,3] 。
 *
 *
 * 玩家 2 连续赢了 k = 2 场比赛，所以赢家是玩家 2 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：skills = [2,5,4], k = 3
 *
 * 输出：1
 *
 * 解释：
 *
 * 一开始，队列里的玩家为 [0,1,2] 。比赛过程如下：
 *
 *
 * 玩家 0 和 1 进行一场比赛，玩家 1 的技能等级高于玩家 0 ，玩家 1 胜出，队列变为 [1,2,0] 。
 * 玩家 1 和 2 进行一场比赛，玩家 1 的技能等级高于玩家 2 ，玩家 1 胜出，队列变为 [1,0,2] 。
 * 玩家 1 和 0 进行一场比赛，玩家 1 的技能等级高于玩家 0 ，玩家 1 胜出，队列变为 [1,2,0] 。
 *
 *
 * 玩家 1 连续赢了 k = 3 场比赛，所以赢家是玩家 1 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * n == skills.length
 * 2 <= n <= 10^5
 * 1 <= k <= 10^9
 * 1 <= skills[i] <= 10^6
 * skills 中的整数互不相同。
 *
 *
 */

// @lc code=start
package main

func findWinningPlayer(skills []int, k int) int {
	var index, count int

	for i := 1; i < len(skills); i++ {
		if skills[index] > skills[i] {
			count++
		} else {
			index = i
			count = 1
		}

		if count == k {
			return index
		}
	}

	return index
}

// @lc code=end
