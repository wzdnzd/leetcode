/*
 * @lc app=leetcode.cn id=1614 lang=golang
 *
 * [1614] 括号的最大嵌套深度
 *
 * https://leetcode.cn/problems/maximum-nesting-depth-of-the-parentheses/description/
 *
 * algorithms
 * Easy (81.40%)
 * Likes:    163
 * Dislikes: 0
 * Total Accepted:    91.4K
 * Total Submissions: 112.1K
 * Testcase Example:  '"(1+(2*3)+((8)/4))+1"'
 *
 * 给定 有效括号字符串 s，返回 s 的 嵌套深度。嵌套深度是嵌套括号的 最大 数量。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "(1+(2*3)+((8)/4))+1"
 *
 * 输出：3
 *
 * 解释：数字 8 在嵌套的 3 层括号中。
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "(1)+((2))+(((3)))"
 *
 * 输出：3
 *
 * 解释：数字 3 在嵌套的 3 层括号中。
 *
 *
 * 示例 3：
 *
 *
 * 输入：s = "()(())((()()))"
 *
 * 输出：3
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= s.length <= 100
 * s 由数字 0-9 和字符 '+'、'-'、'*'、'/'、'('、')' 组成
 * 题目数据保证括号字符串 s 是 有效的括号字符串
 *
 *
 */

// @lc code=start
package main

func maxDepth(s string) int {
	count, maxDepth := 0, 0
	for _, c := range s {
		if c == '(' {
			count++
			maxDepth = max(maxDepth, count)
		} else if c == ')' {
			count--
		}
	}

	return maxDepth
}

func max(x, y int) int {
	if x >= y {
		return x
	}

	return y
}

// @lc code=end
