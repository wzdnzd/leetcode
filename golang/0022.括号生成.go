/*
 * @lc app=leetcode.cn id=22 lang=golang
 *
 * [22] 括号生成
 *
 * https://leetcode.cn/problems/generate-parentheses/description/
 *
 * algorithms
 * Medium (79.03%)
 * Likes:    4150
 * Dislikes: 0
 * Total Accepted:    1.4M
 * Total Submissions: 1.7M
 * Testcase Example:  '3'
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 *
 * 示例 2：
 *
 *
 * 输入：n = 1
 * 输出：["()"]
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= n <= 8
 *
 *
 */

// @lc code=start
package main

func generateParenthesis(n int) []string {
	path := make([]byte, n*2)
	result := make([]string, 0)

	var dfs func(int, int)
	dfs = func(left, right int) {
		if right == n {
			result = append(result, string(path))
			return
		}

		if left < n {
			path[left+right] = '('
			dfs(left+1, right)
		}

		if right < left {
			path[left+right] = ')'
			dfs(left, right+1)
		}
	}

	dfs(0, 0)
	return result
}

// @lc code=end
