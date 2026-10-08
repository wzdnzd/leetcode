/*
 * @lc app=leetcode.cn id=678 lang=golang
 *
 * [678] 有效的括号字符串
 *
 * https://leetcode.cn/problems/valid-parenthesis-string/description/
 *
 * algorithms
 * Medium (40.57%)
 * Likes:    715
 * Dislikes: 0
 * Total Accepted:    97.8K
 * Total Submissions: 236.7K
 * Testcase Example:  '"()"'
 *
 * 给你一个只包含三种字符的字符串，支持的字符类型分别是 '('、')' 和 '*'。请你检验这个字符串是否为有效字符串，如果是 有效 字符串返回 true
 * 。
 *
 * 有效 字符串符合如下规则：
 *
 *
 * 任何左括号 '(' 必须有相应的右括号 ')'。
 * 任何右括号 ')' 必须有相应的左括号 '(' 。
 * 左括号 '(' 必须在对应的右括号之前 ')'。
 * '*' 可以被视为单个右括号 ')' ，或单个左括号 '(' ，或一个空字符串 ""。
 *
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "()"
 * 输出：true
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "(*)"
 * 输出：true
 *
 *
 * 示例 3：
 *
 *
 * 输入：s = "(*))"
 * 输出：true
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= s.length <= 100
 * s[i] 为 '('、')' 或 '*'
 *
 *
 */

// @lc code=start
package main

func checkValidString(s string) bool {
	minCount, maxCount := 0, 0
	for _, c := range s {
		if c == '(' {
			minCount++
			maxCount++
		} else if c == ')' {
			minCount = max(minCount-1, 0)
			maxCount--
			if maxCount < 0 {
				return false
			}
		} else {
			minCount = max(minCount-1, 0)
			maxCount++
		}
	}
	return minCount == 0
}

func max(x, y int) int {
	if x > y {
		return x
	}

	return y
}

// @lc code=end
