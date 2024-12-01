/*
 * @lc app=leetcode.cn id=52 lang=golang
 *
 * [52] N 皇后 II
 *
 * https://leetcode.cn/problems/n-queens-ii/description/
 *
 * algorithms
 * Hard (82.42%)
 * Likes:    536
 * Dislikes: 0
 * Total Accepted:    167.5K
 * Total Submissions: 202.8K
 * Testcase Example:  '4'
 *
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 *
 *
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 4
 * 输出：2
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 *
 *
 * 示例 2：
 *
 *
 * 输入：n = 1
 * 输出：1
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= n <= 9
 *
 *
 *
 *
 */

// @lc code=start
package main

func totalNQueens(n int) int {
	columns := make([]bool, n)
	diag1 := make([]bool, n*2-1)
	diag2 := make([]bool, n*2-1)

	ans := 0

	var backtrace func(int)
	backtrace = func(r int) {
		if r == n {
			ans++
			return
		}

		for c, ok := range columns {
			k := r - c + n - 1
			if !ok && !diag1[r+c] && !diag2[k] {
				columns[c], diag1[r+c], diag2[k] = true, true, true
				backtrace(r + 1)
				columns[c], diag1[r+c], diag2[k] = false, false, false
			}
		}
	}

	backtrace(0)
	return ans
}

// @lc code=end
