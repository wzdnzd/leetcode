/*
 * @lc app=leetcode.cn id=301 lang=golang
 *
 * [301] 删除无效的括号
 *
 * https://leetcode.cn/problems/remove-invalid-parentheses/description/
 *
 * algorithms
 * Hard (56.30%)
 * Likes:    1037
 * Dislikes: 0
 * Total Accepted:    143.9K
 * Total Submissions: 253K
 * Testcase Example:  '"()())()"'
 *
 * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
 *
 * 返回所有可能的结果。答案可以按 任意顺序 返回。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "()())()"
 * 输出：["(())()","()()()"]
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "(a)())()"
 * 输出：["(a())()","(a)()()"]
 *
 *
 * 示例 3：
 *
 *
 * 输入：s = ")("
 * 输出：[""]
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1
 * s 由小写英文字母以及括号 '(' 和 ')' 组成
 * s 中至多含 20 个括号
 *
 *
 */

// @lc code=start
package main

func isValid(s string) bool {
	count := 0
	for _, c := range s {
		if c == '(' {
			count++
		} else if c == ')' {
			count--
			if count < 0 {
				return false
			}
		}
	}

	return count == 0
}

func helper(result *[]string, word string, start, left, right int) {
	if left == 0 && right == 0 {
		if isValid(word) {
			*result = append(*result, word)
		}

		return
	}

	for i := start; i < len(word); i++ {
		if i != start && word[i] == word[i-1] {
			continue
		}

		if left+right > len(word)-i {
			return
		}

		if left > 0 && word[i] == '(' {
			helper(result, word[:i]+word[i+1:], i, left-1, right)
		}

		if right > 0 && word[i] == ')' {
			helper(result, word[:i]+word[i+1:], i, left, right-1)
		}
	}
}

func removeInvalidParentheses(s string) []string {
	left, right := 0, 0
	result := make([]string, 0)

	for _, c := range s {
		if c == '(' {
			left++
		} else if c == ')' {
			if left == 0 {
				right++
			} else {
				left--
			}
		}
	}

	helper(&result, s, 0, left, right)
	return result
}

// @lc code=end
