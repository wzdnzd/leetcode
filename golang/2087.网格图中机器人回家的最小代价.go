/*
 * @lc app=leetcode.cn id=2087 lang=golang
 *
 * [2087] 网格图中机器人回家的最小代价
 *
 * https://leetcode.cn/problems/minimum-cost-homecoming-of-a-robot-in-a-grid/description/
 *
 * algorithms
 * Medium (50.66%)
 * Likes:    23
 * Dislikes: 0
 * Total Accepted:    6.5K
 * Total Submissions: 12.2K
 * Testcase Example:  '[1,0]\n[2,3]\n[5,4,3]\n[8,2,6,7]'
 *
 * 给你一个 m x n 的网格图，其中 (0, 0) 是最左上角的格子，(m - 1, n - 1) 是最右下角的格子。给你一个整数数组 startPos
 * ，startPos = [startrow, startcol] 表示 初始 有一个 机器人 在格子 (startrow, startcol)
 * 处。同时给你一个整数数组 homePos ，homePos = [homerow, homecol] 表示机器人的 家 在格子 (homerow,
 * homecol) 处。
 *
 * 机器人需要回家。每一步它可以往四个方向移动：上，下，左，右，同时机器人不能移出边界。每一步移动都有一定代价。再给你两个下标从 0
 * 开始的额整数数组：长度为 m 的数组 rowCosts  和长度为 n 的数组 colCosts 。
 *
 *
 * 如果机器人往 上 或者往 下 移动到第 r 行 的格子，那么代价为 rowCosts[r] 。
 * 如果机器人往 左 或者往 右 移动到第 c 列 的格子，那么代价为 colCosts[c] 。
 *
 *
 * 请你返回机器人回家需要的 最小总代价 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：startPos = [1, 0], homePos = [2, 3], rowCosts = [5, 4, 3], colCosts = [8,
 * 2, 6, 7]
 * 输出：18
 * 解释：一个最优路径为：
 * 从 (1, 0) 开始
 * -> 往下走到 (2, 0) 。代价为 rowCosts[2] = 3 。
 * -> 往右走到 (2, 1) 。代价为 colCosts[1] = 2 。
 * -> 往右走到 (2, 2) 。代价为 colCosts[2] = 6 。
 * -> 往右走到 (2, 3) 。代价为 colCosts[3] = 7 。
 * 总代价为 3 + 2 + 6 + 7 = 18
 *
 * 示例 2：
 *
 * 输入：startPos = [0, 0], homePos = [0, 0], rowCosts = [5], colCosts = [26]
 * 输出：0
 * 解释：机器人已经在家了，所以不需要移动。总代价为 0 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * m == rowCosts.length
 * n == colCosts.length
 * 1 <= m, n <= 10^5
 * 0 <= rowCosts[r], colCosts[c] <= 10^4
 * startPos.length == 2
 * homePos.length == 2
 * 0 <= startrow, homerow < m
 * 0 <= startcol, homecol < n
 *
 *
 */

// @lc code=start
package main

func minCost(startPos, homePos, rowCosts, colCosts []int) int {
	x0, y0, x1, y1 := startPos[0], startPos[1], homePos[0], homePos[1]
	result := -rowCosts[x0] - colCosts[y0]

	for _, cost := range rowCosts[min(x0, x1) : max(x0, x1)+1] {
		result += cost
	}

	for _, cost := range colCosts[min(y0, y1) : max(y0, y1)+1] {
		result += cost
	}

	return result
}

func max(x, y int) int {
	if x < y {
		return y
	}

	return x
}

func min(x, y int) int {
	if x < y {
		return x
	}

	return y
}

// @lc code=end
