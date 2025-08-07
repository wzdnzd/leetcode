/*
 * @lc app=leetcode.cn id=808 lang=golang
 *
 * [808] 分汤
 *
 * https://leetcode.cn/problems/soup-servings/description/
 *
 * algorithms
 * Medium (58.16%)
 * Likes:    254
 * Dislikes: 0
 * Total Accepted:    25.9K
 * Total Submissions: 44.3K
 * Testcase Example:  '50'
 *
 * 你有两种汤，A 和 B，每种初始为 n 毫升。在每一轮中，会随机选择以下四种服务操作中的一种，每种操作的概率为 0.25，且与之前的所有轮次
 * 无关：
 *
 *
 * 从汤 A 取 100 毫升，从汤 B 取 0 毫升
 * 从汤 A 取 75 毫升，从汤 B 取 25 毫升
 * 从汤 A 取 50 毫升，从汤 B 取 50 毫升
 * 从汤 A 取 25 毫升，从汤 B 取 75 毫升
 *
 *
 * 注意：
 *
 *
 * 不存在先分配 100 ml 汤B 的操作。
 * 汤 A 和 B 在每次操作中同时被倒入。
 * 如果一次操作要求你倒出比剩余的汤更多的量，请倒出该汤剩余的所有部分。
 *
 *
 * 操作过程在任何回合中任一汤被用完后立即停止。
 *
 * 返回汤 A 在 B 前耗尽的概率，加上两种汤在 同一回合 耗尽概率的一半。返回值在正确答案 10^-5 的范围内将被认为是正确的。
 *
 *
 *
 * 示例 1:
 *
 *
 * 输入：n = 50
 * 输出：0.62500
 * 解释：
 * 如果我们选择前两个操作，A 首先将变为空。
 * 对于第三个操作，A 和 B 会同时变为空。
 * 对于第四个操作，B 首先将变为空。
 * 所以 A 变为空的总概率加上 A 和 B 同时变为空的概率的一半是 0.25 *(1 + 1 + 0.5 + 0)= 0.625。
 *
 *
 * 示例 2:
 *
 *
 * 输入：n = 100
 * 输出：0.71875
 * 解释：
 * 如果我们选择第一个操作，A 首先将变为空。
 * 如果我们选择第二个操作，A 将在执行操作 [1, 2, 3] 时变为空，然后 A 和 B 在执行操作 4 时同时变空。
 * 如果我们选择第三个操作，A 将在执行操作 [1, 2] 时变为空，然后 A 和 B 在执行操作 3 时同时变空。
 * 如果我们选择第四个操作，A 将在执行操作 1 时变为空，然后 A 和 B 在执行操作 2 时同时变空。
 * 所以 A 变为空的总概率加上 A 和 B 同时变为空的概率的一半是 0.71875。
 *
 *
 *
 *
 * 提示:
 *
 *
 * 0 <= n <= 10^9
 *
 *
 */

// @lc code=start
package main

func soupServings(n int) float64 {
	n = (n + 24) / 25
	if n >= 179 {
		return 1
	}

	dp := make([][]float64, n+1)
	for i := range dp {
		dp[i] = make([]float64, n+1)
	}

	dp[0][0] = 0.5
	for i := 1; i <= n; i++ {
		dp[0][i] = 1
	}

	for i := 1; i <= n; i++ {
		for j := 1; j <= n; j++ {
			dp[i][j] = (dp[max(0, i-4)][j] + dp[max(0, i-3)][max(0, j-1)] +
				dp[max(0, i-2)][max(0, j-2)] + dp[max(0, i-1)][max(0, j-3)]) / 4
		}
	}

	return dp[n][n]
}

func max(x, y int) int {
	if y > x {
		return y
	}

	return x
}

// @lc code=end
